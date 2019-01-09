package com.jeffersonlupinacci.app.authService;

import com.jeffersonlupinacci.app.authService.config.security.SSLStoreConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Auth Service
 *
 * Spring Cloud Security Server - Spring Cloud Conf Client - Spring Eureka Client
 *
 * @author jeffersonlupinacci
 */
@SpringBootApplication
public class AuthServiceApplication {

  public static void main(String[] args) {

    try {
      SSLStoreConfig.configure(args);
    } catch (Exception e) {
      e.printStackTrace();
    }

    SpringApplication.run(AuthServiceApplication.class, args);
  }

}

