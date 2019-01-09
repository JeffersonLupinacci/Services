package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.thymeleaf.util.MapUtils;

/**
 * The Create Queue Command
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQCreateQueueCommand extends BaseCommand<Boolean> {

  private final String name;
  private final boolean durable;
  private final boolean exclusive;
  private final boolean autoDelete;
  private final Map<String,String> arguments;
  /**
   * Default Constructor
   *
   * @param name the Name
   * @param durable the Durable check
   * @param exclusive the Exclusive check
   * @param autoDelete the Auto Delete check
   * @param arguments the Arguments
   */
  public RabbitMQCreateQueueCommand(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, String> arguments) {
    super();
    this.name = name;
    this.durable = durable;
    this.exclusive = exclusive;
    this.autoDelete = autoDelete;
    this.arguments = arguments;
  }

  /**
   * Execute the Command
   *
   * @return the Boolean Check
   */
  @Override
  protected Boolean process() {

    Validate.isTrue(StringUtils.isNotEmpty(this.name), "Invalid Queue Name");
    Validate.isTrue(!(this.exclusive && this.durable), "Invalid Combination Exclusive/Durable");

    Queue queue = new Queue(this.name, this.durable, this.exclusive, this.autoDelete);

    if (!MapUtils.isEmpty(arguments)){
      this.arguments.forEach((key, value) -> queue.getArguments().put(key, value));
    }

    SpringContext
        .getBean(AmqpAdmin.class)
        .declareQueue(queue);
    return true;
  }

}
