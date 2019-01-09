package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement.RabbitMQRestTemplateFactory;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.Queue;
import java.util.Objects;

/**
 * The Rabbit Queue Rest Client Command
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQManagementQueueRestCommand extends BaseCommand<Queue[]> {

  /**
   * Execute the Command
   *
   * @return the Queue[] Array
   */
  public Queue[] process() {
    String resourceUrl = SpringContext.getEnvironmentProperty("rabbitmq.queueService");
    return Objects.requireNonNull(SpringContext
        .getBean(RabbitMQRestTemplateFactory.class)
        .getObject())
        .getForObject(resourceUrl, Queue[].class);
  }

}
