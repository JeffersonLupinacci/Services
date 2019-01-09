package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.annotation.MessageFormat;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import com.jeffersonlupinacci.app.core.serializablesDTO.BaseMessage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * Send a Message
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQSendMessageCommand extends BaseCommand<Boolean> {

  private final Object message;

  private final String topicExchange;

  private final String routingKey;

  /**
   * Default Constructor
   *
   * @param message the Message
   * @param topicExchange the Topic Exchange
   * @param routingKey the Routing Key
   */
  public RabbitMQSendMessageCommand(BaseMessage message, String topicExchange, String routingKey) {
    super();
    this.message = message;
    this.topicExchange = topicExchange;
    this.routingKey = routingKey;
  }

  /**
   * Execute The Command
   *
   * @return the Boolean Check
   */
  @Override
  public Boolean process() {

    Validate.notNull(this.message, "Invalid Message");
    Validate.isTrue(StringUtils.isNotEmpty(this.topicExchange), "Invalid Topic Exchange Name");
    Validate.isTrue(StringUtils.isNotEmpty(this.routingKey), "Invalid routing Key");

    if (this.message.getClass().isAnnotationPresent(MessageFormat.class)) {

      // Get the Converter
      Object converterImplementation = this.message.getClass().getAnnotation(MessageFormat.class).type().getConverter();

      // Get the Rabbit Template
      RabbitTemplate rabbitTemplate = SpringContext.getBean(RabbitTemplate.class);

      // Set the Converter
      rabbitTemplate.setMessageConverter((MessageConverter) converterImplementation);

      // Send Message
      rabbitTemplate.convertAndSend(this.topicExchange, this.routingKey, this.message);

      return true;
    }

    return false;
  }


}
