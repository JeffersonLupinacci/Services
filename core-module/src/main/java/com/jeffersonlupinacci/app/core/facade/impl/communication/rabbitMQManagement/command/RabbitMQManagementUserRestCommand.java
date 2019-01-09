package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement.RabbitMQRestTemplateFactory;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.User;
import java.util.Objects;

/**
 * The Rabbit User Rest Client Command
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQManagementUserRestCommand extends BaseCommand<User[]> {

  /**
   * Execute the Command
   *
   * @return The User[] Array
   */
  @Override
  public User[] process() {
    String resourceUrl = SpringContext.getEnvironmentProperty("rabbitmq.userService");
    return Objects.requireNonNull(SpringContext
        .getBean(RabbitMQRestTemplateFactory.class)
        .getObject())
        .getForObject(resourceUrl, User[].class);
  }

}
