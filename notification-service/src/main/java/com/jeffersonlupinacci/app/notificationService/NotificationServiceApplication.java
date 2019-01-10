package com.jeffersonlupinacci.app.notificationService;

import com.jeffersonlupinacci.app.core.config.AppConfiguration;
import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.notificationService.notification.NotificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Notification Service
 *
 * Swagger Client - Jwt Client Spring - Security Client
 *
 * @author jeffersonlupinacci
 */
@SpringBootApplication
public class NotificationServiceApplication {

  public static void main(String[] args) throws CommandExecuteException {

    try {
      AppConfiguration.define(args);
    } catch (Exception e) {
      e.printStackTrace();
    }

    SpringApplication.run(NotificationServiceApplication.class, args);

    NotificationService.start();

  }

}

