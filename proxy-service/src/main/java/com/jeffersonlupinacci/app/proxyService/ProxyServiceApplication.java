package com.jeffersonlupinacci.app.proxyService;

import com.jeffersonlupinacci.app.proxyService.config.security.SSLStoreConfig;
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
      SSLStoreConfig.configure(args);
    } catch (Exception e) {
      e.printStackTrace();
    }

    SpringApplication.run(ProxyServiceApplication.class, args);
  }

}

