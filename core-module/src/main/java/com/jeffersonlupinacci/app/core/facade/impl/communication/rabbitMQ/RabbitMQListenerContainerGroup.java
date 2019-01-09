package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author jeffersonlupinacci Rabbit MQ Listener Container Group
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RabbitMQListenerContainerGroup implements InitializingBean {

  private Map<String, SimpleMessageListenerContainer> activeListeners;

  /**
   * @return Get the Active Listeners
   */
  @SuppressWarnings("unused")
  public Map<String, SimpleMessageListenerContainer> getActiveListeners() {
    return Collections.unmodifiableMap(activeListeners);
  }

  public SimpleMessageListenerContainer getListener(String queueName) {
    return activeListeners.get(queueName);
  }

  /**
   * Set the Parameters
   */
  public void afterPropertiesSet() {
    this.activeListeners = new HashMap<>();
  }

  /**
   * Add a New Listener Container
   *
   * @param listenerContainer the Listener Container
   */
  public void addListener(SimpleMessageListenerContainer listenerContainer) {
    this.activeListeners.put(String.join(",", listenerContainer.getQueueNames()), listenerContainer);
  }

  /**
   * remove an existing listener
   *
   * @param queueName the Queue Name
   */
  public void removeListener(String queueName) {
    SimpleMessageListenerContainer listener = this.activeListeners.get(queueName);
    if (null != listener) {
      if (listener.isActive()) {
        listener.shutdown();
      }
    }
    this.activeListeners.remove(queueName, listener);
  }
}
