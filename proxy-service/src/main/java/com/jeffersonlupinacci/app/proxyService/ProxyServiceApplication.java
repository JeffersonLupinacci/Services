package com.jeffersonlupinacci.app.proxyService;

import com.jeffersonlupinacci.app.proxyService.config.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Proxy Service
 *
 * Zuul Proxy - Swagger Server - Spring Cloud Config - Client Spring Eureka - Client Swagger Server
 *
 * @author jeffersonlupinacci
 */
@SpringBootApplication
public class ProxyServiceApplication {

  public static void main(String[] args) {

    try {
      AppConfiguration.define(args);
    } catch (Exception e) {
      e.printStackTrace();
    }

    SpringApplication.run(ProxyServiceApplication.class, args);
  }

}

