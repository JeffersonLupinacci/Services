package com.jeffersonlupinacci.app.discoveryService;

import com.jeffersonlupinacci.app.discoveryService.config.security.SSLStoreConfig;
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
      SSLStoreConfig.configure(args);
    } catch (Exception e) {
      e.printStackTrace();
    }

    SpringApplication.run(DiscoveryServiceApplication.class, args);

  }

}

