package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.amqp.core.AmqpAdmin;

/**
 * The Delete Topic Exchange Command
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQDeleteTopicExchangeCommand extends BaseCommand<Boolean> {

  private final String name;

  /**
   * Default Constructor
   *
   * @param name the Queue Name
   */
  public RabbitMQDeleteTopicExchangeCommand(String name) {
    super();
    this.name = name;
  }

  /**
   * Execute the Command
   *
   * @return the Boolean Check Return
   */
  @Override
  protected Boolean process() {

    Validate.isTrue(StringUtils.isNotEmpty(this.name), "Invalid Topic Exchange Name");

    SpringContext
        .getBean(AmqpAdmin.class)
        .deleteExchange(this.name);
    return true;
  }
}
