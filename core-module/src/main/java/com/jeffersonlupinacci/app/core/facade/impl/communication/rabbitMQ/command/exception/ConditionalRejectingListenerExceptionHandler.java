package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.messaging.converter.MessageConversionException;

/**
 * The Conditional Rejecting Listener Exception Handler
 *
 * @author jeffersonlupinacci
 */
public class ConditionalRejectingListenerExceptionHandler implements FatalExceptionStrategy {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public boolean isFatal(Throwable t) {
    if (t instanceof ListenerExecutionFailedException) {
      if (t.getCause() instanceof MessageConversionException) {
        log.error("Message Listener Error: ", t);
      } else if (t.getCause() instanceof NullPointerException) {
        log.error("Message Listener Error: NullPointerException", t);
      }
      return true;
    }
    return false;
  }
}

