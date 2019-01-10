package com.jeffersonlupinacci.app.notificationService.notification;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.RabbitMQListenerConfiguration;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.RabbitMQMessageService;
import org.springframework.amqp.core.AcknowledgeMode;

/**
 * The Notification Service Start
 *
 * @author jeffersonlupinacci
 */
public class NotificationService {

  private static final String QUEUE_NAME = "notificationQueue";
  private static final String TOPIC_NAME = "notificationTopic";
  private static final String ROUTING_KEY = "*";

  public static void start() throws CommandExecuteException {

    RabbitMQMessageService messageService = SpringContext.getBean(RabbitMQMessageService.class);

    // Create the Queue
    messageService.createQueue(QUEUE_NAME, true, false, false, null);

    // Create the Topic Exchange
    messageService.createTopicExchange(TOPIC_NAME, true, false);

    // Create the Binding
    messageService.createBinding(QUEUE_NAME, TOPIC_NAME, ROUTING_KEY);

    // Start The Listener
    messageService
        .createListener(RabbitMQListenerConfiguration
            .builder()
            .concurrentConsumers(1)
            .maxConcurrentConsumers(1)
            .exposeListenerChannel(true)
            .acknowledgeMode(AcknowledgeMode.AUTO)
            .queueName(QUEUE_NAME)
            .channelTransacted(false)
            .consummer(NotificationConsumer.class)
            .build());

  }

}
