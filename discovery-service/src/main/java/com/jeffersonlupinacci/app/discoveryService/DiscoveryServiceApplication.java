package com.jeffersonlupinacci.app.discoveryService;

import com.jeffersonlupinacci.app.discoveryService.config.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Discovery Service
 *
 * Eureka Server - Spring Boot Admin Server - Spring Cloud Config Server
 *
 * @author jeffersonlupinacci
 */

@SpringBootApplication
@EnableAutoConfiguration
public class DiscoveryServiceApplication {

  public static void main(String[] args) {

    try {
      AppConfiguration.define(args);
    } catch (Exception e) {
      e.printStackTrace();
    }

    SpringApplication.run(DiscoveryServiceApplication.class, args);

  }

}

