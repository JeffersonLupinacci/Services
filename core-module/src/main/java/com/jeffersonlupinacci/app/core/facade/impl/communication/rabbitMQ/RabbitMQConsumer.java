package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ;

import com.jeffersonlupinacci.app.core.facade.BaseCommand;

/**
 * The RabbitMQ Consumer Command
 * @param <T>
 */
public abstract class RabbitMQConsumer<T> extends BaseCommand<T> implements IRabbitMQConsumer {

  private Object message;

  @Override
  public RabbitMQConsumer setMessage(Object message) {
    this.message = message;
    return this;
  }

  @Override
  public Object getMessage() {
    return this.message;
  }

}
