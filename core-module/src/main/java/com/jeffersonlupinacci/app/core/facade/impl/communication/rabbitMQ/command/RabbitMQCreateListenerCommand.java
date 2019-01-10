package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.annotation.MessageFormatType;
import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.RabbitMQConsumer;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.RabbitMQListenerConfiguration;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.RabbitMQListenerContainerGroup;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command.exception.ConditionalRejectingListenerExceptionHandler;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * The Create Queue Command
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQCreateListenerCommand extends BaseCommand<Boolean> {

  private final RabbitMQListenerConfiguration config;
  private RabbitMQConsumer consumer;

  /**
   * Default Constructor
   *
   * @param config the RabbitMQ Listener configuration
   */
  public RabbitMQCreateListenerCommand(RabbitMQListenerConfiguration config) {
    super();
    this.config = config;
  }

  /**
   * Execute the Command
   *
   * @return the Boolean Check
   */
  @Override
  protected Boolean process() throws CommandExecuteException {

    // All registered listeners
    RabbitMQListenerContainerGroup listenerGroup = SpringContext.getBean(RabbitMQListenerContainerGroup.class);

    // Already exists?
    if (null != listenerGroup.getListener(this.config.getQueueName())) {
      if (listenerGroup.getListener(this.config.getQueueName()).isActive()) {
        throw new CommandExecuteException("Listener Already exist");
      } else {
        listenerGroup.removeListener(this.config.getQueueName());
      }
    }

    ConnectionFactory connectionFactory = SpringContext.getBean(ConnectionFactory.class);
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);

    /* Configuration */
    container.setExposeListenerChannel(this.config.getExposeListenerChannel());
    container.setMaxConcurrentConsumers(this.config.getMaxConcurrentConsumers());
    container.setConcurrentConsumers(this.config.getConcurrentConsumers());
    container.setAcknowledgeMode(this.config.getAcknowledgeMode());
    container.addQueues(new Queue(this.config.getQueueName()));
    container.setChannelTransacted(this.config.getChannelTransacted());
    container.setErrorHandler(new ConditionalRejectingErrorHandler(new ConditionalRejectingListenerExceptionHandler()));

    try {
      consumer = this.config.getConsummer().newInstance();
    } catch (InstantiationException e) {
      throw new CommandExecuteException(e);
    } catch (IllegalAccessException e) {
      throw new CommandExecuteException(e);
    }

    /* The Message Listener Entry Point */
    container.setMessageListener((Message message) -> {

      MessageFormatType formatType = MessageFormatType.valueOfContentType(message.getMessageProperties().getContentType());

      if (null != formatType) {
        @SuppressWarnings("unchecked")
        MessageConverter converter = formatType.getConverter();
        try {
          this.consumer.setMessage(converter.fromMessage(message)).execute();
        } catch (CommandExecuteException e) {
          log.warn("Consumer Error", e);
        }
      }

    });

    listenerGroup.addListener(container);

    /* Start Thread */
    container.start();

    return true;
  }

}


