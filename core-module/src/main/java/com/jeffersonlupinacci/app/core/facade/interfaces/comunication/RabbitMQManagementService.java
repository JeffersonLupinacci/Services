package com.jeffersonlupinacci.app.core.facade.interfaces.comunication;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.Binding;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.Queue;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.TopicExchange;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * The Rabbit MQ Management Service
 *
 * @author jeffersonlupinacci
 */
@Profile(value = "rabbitMQ")
@Service
public interface RabbitMQManagementService {

  /**
   * Get the Queues
   *
   * @return the Queues
   */
  @SuppressWarnings("unused")
  Queue[] getQueues() throws CommandExecuteException;

  /**
   * GEt the Users
   *
   * @return the Users
   */
  @SuppressWarnings("unused")
  User[] getUsers() throws CommandExecuteException;

  /**
   * Get the Bindings
   *
   * @return the Bindings
   */
  @SuppressWarnings("unused")
  Binding[] getBindings() throws CommandExecuteException;

  /**
   * Get the Topic Exchange
   *
   * @return the Topic Exchange
   */
  @SuppressWarnings("unused")
  TopicExchange[] getTopicExchanges() throws CommandExecuteException;

}
