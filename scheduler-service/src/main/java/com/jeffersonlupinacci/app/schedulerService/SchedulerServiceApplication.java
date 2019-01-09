package com.jeffersonlupinacci.app.schedulerService;

import com.jeffersonlupinacci.app.schedulerService.config.security.SSLStoreConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Scheduler Service
 * Swagger Client - Jwt Client Spring - Security Client - Quartz
 * @author jeffersonlupinacci
 */
@SpringBootApplication
public class SchedulerServiceApplication {

  public static void main(String[] args) {

    try {
      SSLStoreConfig.configure(args);
    } catch (Exception e) {
      e.printStackTrace();
    }

    SpringApplication.run(SchedulerServiceApplication.class, args);

  }

}

