package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement.command.RabbitMQManagementBindingRestCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement.command.RabbitMQManagementQueueRestCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement.command.RabbitMQManagementTopicExchangeRestCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement.command.RabbitMQManagementUserRestCommand;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.RabbitMQManagementService;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.Binding;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.Queue;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.TopicExchange;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.User;

/**
 * The Rabbit MQ Management Service Implementation
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQManagementServiceImpl implements RabbitMQManagementService {

  /**
   * Get the Queues
   *
   * @return the Queues
   */
  @Override
  public Queue[] getQueues() throws CommandExecuteException {
    return new RabbitMQManagementQueueRestCommand().execute();
  }

  /**
   * Get the Users
   *
   * @return the Users
   */
  public User[] getUsers() throws CommandExecuteException {
    return new RabbitMQManagementUserRestCommand().execute();
  }

  /**
   * Get the Users
   *
   * @return the Users
   */
  public Binding[] getBindings() throws CommandExecuteException {
    return new RabbitMQManagementBindingRestCommand().execute();
  }

  /**
   * Get the Topic Exchange
   *
   * @return the Topic Exchange
   */
  public TopicExchange[] getTopicExchanges() throws CommandExecuteException {
    return new RabbitMQManagementTopicExchangeRestCommand().execute();
  }

}
