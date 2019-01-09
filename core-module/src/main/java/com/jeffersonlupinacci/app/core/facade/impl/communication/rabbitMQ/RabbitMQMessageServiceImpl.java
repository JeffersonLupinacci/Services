package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command.RabbitMQCreateBindingCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command.RabbitMQCreateListenerCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command.RabbitMQCreateQueueCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command.RabbitMQCreateTopicExchangeCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command.RabbitMQDeleteBindingCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command.RabbitMQDeleteListenerCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command.RabbitMQDeleteQueueCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command.RabbitMQDeleteTopicExchangeCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command.RabbitMQRetrieveListenersCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command.RabbitMQSendMessageCommand;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.RabbitMQMessageService;
import com.jeffersonlupinacci.app.core.serializablesDTO.BaseMessage;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.Listener;
import java.util.Collection;
import java.util.Map;

/**
 * The Rabbit MQ Message Service Implementation
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQMessageServiceImpl implements RabbitMQMessageService {

  /**
   * Send a Message
   *
   * @param message the Message to Send
   * @param topicExchange the Topic Exchange
   * @param routingKey the Routing Key
   * @return the Boolean Check
   * @throws CommandExecuteException the Command Execution Exception
   */
  @Override
  public Boolean sendMessage(BaseMessage message, String topicExchange, String routingKey) throws CommandExecuteException {
    return new RabbitMQSendMessageCommand(message, topicExchange, routingKey).execute();
  }

  /**
   * Create a Queue
   *
   * @param name the Queue Name
   * @param durable the Durable Check
   * @param exclusive the Exclusive Check
   * @param autoDelete the Auto Delete Check
   * @param arguments the Arguments
   * @return the Boolean Check
   * @throws CommandExecuteException The Command Execution Exception
   */
  @Override
  public Boolean createQueue(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, String> arguments) throws CommandExecuteException {
    return new RabbitMQCreateQueueCommand(name, durable, exclusive, autoDelete, arguments).execute();
  }

  /**
   * Delete a Queue
   *
   * @param name the Queue Name
   * @return the Boolean Check
   * @throws CommandExecuteException the Command Execution Exception
   */
  @Override
  public Boolean deleteQueue(String name) throws CommandExecuteException {
    return new RabbitMQDeleteQueueCommand(name).execute();
  }

  /**
   * Create a Topic Exchange
   *
   * @param name the Queue Name
   * @param durable the Durable Check
   * @param autoDelete the Auto Delete Check
   * @return the Boolean Check
   * @throws CommandExecuteException The Command Execution Exception
   */
  @Override
  public Boolean createTopicExchange(String name, boolean durable, boolean autoDelete) throws CommandExecuteException {
    return new RabbitMQCreateTopicExchangeCommand(name, durable, autoDelete).execute();
  }

  /**
   * Delete a Topic Exchange
   *
   * @param name the Topic Name
   * @return the Boolean Check
   * @throws CommandExecuteException the Command Execution Exception
   */
  @Override
  public Boolean deleteTopicExchange(String name) throws CommandExecuteException {
    return new RabbitMQDeleteTopicExchangeCommand(name).execute();
  }

  /**
   * Create a Binding
   *
   * @param queueName the Queue Name
   * @param topicExchangeName the Topic Exchange Name
   * @param routingKey the Routing Key
   * @return the Boolean Check Return
   * @throws CommandExecuteException the Command Execute Exception
   */
  @Override
  public Boolean createBinding(String queueName, String topicExchangeName, String routingKey) throws CommandExecuteException {
    return new RabbitMQCreateBindingCommand(queueName, topicExchangeName, routingKey).execute();
  }

  /**
   * Delete a Binding
   *
   * @param queueName the Queue Name
   * @param topicExchangeName the Topic Exchange Name
   * @param routingKey the Routing Key
   * @return the Boolean Check Return
   * @throws CommandExecuteException the Command Execute Exception
   */
  @Override
  public Boolean deleteBinding(String queueName, String topicExchangeName, String routingKey) throws CommandExecuteException {
    return new RabbitMQDeleteBindingCommand(queueName, topicExchangeName, routingKey).execute();
  }

  /**
   * Create the Listener
   *
   * @param config the RabbitMQ Listener Configuration
   * @return the Boolean Check Return
   * @throws CommandExecuteException the Command Execute Exception
   */
  @Override
  public Boolean createListener(RabbitMQListenerConfiguration config) throws CommandExecuteException {
    return new RabbitMQCreateListenerCommand(config).execute();
  }

  /**
   * Get the Listeners
   *
   * @return the Listeners
   */
  @Override
  public Collection<Listener> getListeners() throws CommandExecuteException {
    return new RabbitMQRetrieveListenersCommand().execute();
  }

  /**
   * Delete a Listener
   *
   * @param listenerName the Listener Name
   * @return the Boolean Check Return
   * @throws CommandExecuteException the Command Execute Exception
   */
  @Override
  public Boolean deleteListener(String listenerName) throws CommandExecuteException {
    return new RabbitMQDeleteListenerCommand(listenerName).execute();
  }


}
