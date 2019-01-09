package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.TopicExchange;

/**
 * The Create Topic Exchange Command
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQCreateTopicExchangeCommand extends BaseCommand<Boolean> {

  private final String name;
  private final boolean durable;
  private final boolean autoDelete;

  /**
   * Default Constructor
   *
   * @param name the Name
   * @param durable the Durable check
   * @param autoDelete the Auto Delete check
   */
  public RabbitMQCreateTopicExchangeCommand(String name, boolean durable, boolean autoDelete) {
    super();
    this.name = name;
    this.durable = durable;
    this.autoDelete = autoDelete;
  }

  /**
   * Execute the Command
   *
   * @return the Boolean Check
   */
  @Override
  protected Boolean process() {

    Validate.isTrue(StringUtils.isNotEmpty(this.name), "Invalid Topic Exchange Name");

    SpringContext
        .getBean(AmqpAdmin.class)
        .declareExchange(new TopicExchange(this.name, this.durable, this.autoDelete));
    return true;
  }

}
