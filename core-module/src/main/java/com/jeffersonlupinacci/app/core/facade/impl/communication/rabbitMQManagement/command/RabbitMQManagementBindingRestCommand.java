package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement.RabbitMQRestTemplateFactory;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.Binding;
import java.util.Objects;

/**
 * The Rabbit Binding Rest Client Command
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQManagementBindingRestCommand extends BaseCommand<Binding[]> {

  /**
   * Execute the Command
   *
   * @return the Binding[] Array
   */
  @Override
  public Binding[] process() {

    String resourceUrl = SpringContext.getEnvironmentProperty("rabbitmq.bindingService");
    return Objects.requireNonNull(SpringContext
        .getBean(RabbitMQRestTemplateFactory.class)
        .getObject())
        .getForObject(resourceUrl, Binding[].class);
  }

}
