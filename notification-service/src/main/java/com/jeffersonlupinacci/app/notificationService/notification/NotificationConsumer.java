package com.jeffersonlupinacci.app.notificationService.notification;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.RabbitMQConsumer;
import com.jeffersonlupinacci.app.core.serializablesDTO.utils.SimpleTextMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * The RabbitMQ Notification Consumer
 *
 * @author jeffersonlupinacci
 */
public class NotificationConsumer extends RabbitMQConsumer<Boolean> {

  /**
   * Process the command
   *
   * @return the Return of the command
   */
  @Override
  protected Boolean process() throws CommandExecuteException {

    if (this.getMessage() instanceof SimpleTextMessage) {

      SimpleTextMessage msg = (SimpleTextMessage) this.getMessage();

      SpringContext
          .getBean(SimpMessagingTemplate.class)
          .convertAndSendToUser(msg.getDestination(), "/queue/notification", msg.getMessage());

      return true;
    } else {
      return false;
    }

  }

}
