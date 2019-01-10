package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ;

/**
 * The RabbitMQ Message Consumer Contract
 *
 * @author jeffersonlupinacci
 */
public interface IRabbitMQConsumer {

  IRabbitMQConsumer setMessage(Object message);

  Object getMessage();

}
