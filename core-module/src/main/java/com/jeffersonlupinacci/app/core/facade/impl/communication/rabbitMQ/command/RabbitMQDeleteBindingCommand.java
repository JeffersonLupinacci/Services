package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

/**
 * The Delete Binding Command
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQDeleteBindingCommand extends BaseCommand<Boolean> {

  private final String queueName;
  private final String topicExchangeName;
  private final String routingKey;

  /**
   * Default Constructor
   *
   * @param queueName the Queue Name
   * @param topicExchangeName the Topic Exchange Name
   * @param routingKey the Routing Key
   */
  public RabbitMQDeleteBindingCommand(String queueName, String topicExchangeName, String routingKey) {
    super();
    this.queueName = queueName;
    this.topicExchangeName = topicExchangeName;
    this.routingKey = routingKey;
  }

  /**
   * Execute the Command
   *
   * @return the Boolean Check
   */
  @Override
  protected Boolean process() throws CommandExecuteException {

    Validate.isTrue(StringUtils.isNotEmpty(this.queueName), "Invalid Queue Name");
    Validate.isTrue(StringUtils.isNotEmpty(this.topicExchangeName), "Invalid Topic Exchange Name");
    Validate.isTrue(StringUtils.isNotEmpty(this.routingKey), "Invalid Routing Key");

    AmqpAdmin admin = SpringContext.getBean(AmqpAdmin.class);

    // Check Queue
    Properties queueProperties = admin.getQueueProperties(this.queueName);
    if (null == queueProperties) {
      throw new CommandExecuteException("Queue Don't Exists");
    }

    // Remove the Binding
    admin.removeBinding(BindingBuilder
        .bind(new Queue(this.queueName))
        .to(new TopicExchange(this.topicExchangeName))
        .with(this.routingKey));

    return true;
  }

}
