package com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ;

import lombok.Getter;
import org.springframework.amqp.core.AcknowledgeMode;

/**
 * @author jeffersonlupinacci
 */
@Getter
public class Listener {

  private String key;
  private String[] queueNames;
  private String listenerId;
  private int activeConsumerCount;
  private AcknowledgeMode acknowledgeMode;

  public Listener(String key, String[] queueNames, String listenerId, int activeConsumerCount, AcknowledgeMode acknowledgeMode) {
    this.key = key;
    this.queueNames = queueNames;
    this.listenerId = listenerId;
    this.activeConsumerCount = activeConsumerCount;
    this.acknowledgeMode = acknowledgeMode;
  }

}
