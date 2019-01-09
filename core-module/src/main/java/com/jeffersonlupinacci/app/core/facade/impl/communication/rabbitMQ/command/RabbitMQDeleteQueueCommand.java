package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.amqp.core.AmqpAdmin;

/**
 * The Delete Queue Command
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQDeleteQueueCommand extends BaseCommand<Boolean> {

  private final String name;

  /**
   * Default Constructor
   *
   * @param name the Queue Name
   */
  public RabbitMQDeleteQueueCommand(String name) {
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

    Validate.isTrue(StringUtils.isNotEmpty(this.name), "Invalid Queue Name");

    SpringContext
        .getBean(AmqpAdmin.class)
        .deleteQueue(this.name);
    return true;
  }
}
