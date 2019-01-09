package com.jeffersonlupinacci.app.core.facade.interfaces.comunication;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.RabbitMQListenerConfiguration;
import com.jeffersonlupinacci.app.core.serializablesDTO.BaseMessage;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.Listener;
import java.util.Collection;
import java.util.Map;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * The RabbitMQ Message Service
 *
 * @author jeffersonlupinacci
 */
@Profile(value = "rabbitMQ")
@Service
public interface RabbitMQMessageService {

  /**
   * Send a Message
   *
   * @param messageObject the Message Object
   * @param topicExchange the Topic Exchange
   * @param routingKey the Routing Key
   * @return the Boolean Check Return
   * @throws CommandExecuteException the Command Execute Exception
   */
  @SuppressWarnings("unused")
  Boolean sendMessage(BaseMessage messageObject, String topicExchange, String routingKey) throws CommandExecuteException;

  /**
   * Create a Queue
   *
   * @param name the Queue Name
   * @param durable the Durable Check
   * @param exclusive the Exclusive Check
   * @param autoDelete the Auto Delete Check
   * @param arguments the Arguments
   * @return The Boolean Check Return
   * @throws CommandExecuteException the Command Execute Exception
   */
  @SuppressWarnings("unused")
  Boolean createQueue(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, String> arguments) throws CommandExecuteException;

  /**
   * Delete a Queue
   *
   * @param name the Queue Name
   * @return The Boolean Check Return
   * @throws CommandExecuteException the Command Execute Exception
   */
  @SuppressWarnings("unused")
  Boolean deleteQueue(String name) throws CommandExecuteException;

  /**
   * Create a Topic Exchange
   *
   * @param name the Queue Name
   * @param durable the Durable Check
   * @param autoDelete the Auto Delete Check
   * @return the Boolean Check
   * @throws CommandExecuteException The Command Execution Exception
   */
  @SuppressWarnings("unused")
  Boolean createTopicExchange(String name, boolean durable, boolean autoDelete) throws CommandExecuteException;

  /**
   * Delete a Topic Exchange
   *
   * @param name the Queue Name
   * @return The Boolean Check Return
   * @throws CommandExecuteException the Command Execute Exception
   */
  @SuppressWarnings("unused")
  Boolean deleteTopicExchange(String name) throws CommandExecuteException;

  /**
   * Create a Binding
   *
   * @param queueName the Queue Name
   * @param topicExchangeName the Topic Exchange Name
   * @param routingKey the Routing Key
   * @return the Boolean Check Return
   * @throws CommandExecuteException the Command Execute Exception
   */
  @SuppressWarnings("unused")
  Boolean createBinding(String queueName, String topicExchangeName, String routingKey) throws CommandExecuteException;

  /**
   * Delete a Binding
   *
   * @param queueName the Queue Name
   * @param topicExchangeName the Topic Exchange Name
   * @param routingKey the Routing Key
   * @return the Boolean Check Return
   * @throws CommandExecuteException the Command Execute Exception
   */
  @SuppressWarnings("unused")
  Boolean deleteBinding(String queueName, String topicExchangeName, String routingKey) throws CommandExecuteException;

  /**
   * Create the Listener
   *
   * @param config the RabbitMQ Listener Configuration
   * @return the Boolean Check Return
   * @throws CommandExecuteException the Command Execution Exception
   */
  @SuppressWarnings("unused")
  Boolean createListener(RabbitMQListenerConfiguration config) throws CommandExecuteException;

  /**
   * Get the Listeners
   *
   * @return the Listeners
   */
  @SuppressWarnings("unused")
  Collection<Listener> getListeners() throws CommandExecuteException;


  /**
   * Delete a Listener
   *
   * @param listenerName the Listener Name
   * @return the Boolean Check Return
   * @throws CommandExecuteException the Command Execute Exception
   */
  @SuppressWarnings("unused")
  Boolean deleteListener(String listenerName) throws CommandExecuteException;


}
