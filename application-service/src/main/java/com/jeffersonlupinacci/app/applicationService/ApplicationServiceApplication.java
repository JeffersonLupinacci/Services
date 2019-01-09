package com.jeffersonlupinacci.app.applicationService;

import com.jeffersonlupinacci.app.core.config.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application Service
 *
 * Swagger Client - Jwt Client Spring - Security Client
 *
 * @author jeffersonlupinacci
 */
@SpringBootApplication
public class ApplicationServiceApplication {

  public static void main(String[] args) {

    try {
      AppConfiguration.define(args);
    } catch (Exception e) {
      e.printStackTrace();
    }

    SpringApplication.run(ApplicationServiceApplication.class, args);

    /*try {

      SpringContext.getBean(MailService.class)
          .sendMessage(MailComposition.builder()
          .withSubject("aaaaa")
              .withFrom("jeffersonlupinacci@gmail.com")
          .withTo("jeffersonlupinacci@gmail.com")
              .withHtmlTemplate("asdfasdfadsfadsf")
          .build());
    } catch (CommandExecuteException e) {
      e.printStackTrace();
    }*/

  }

}

