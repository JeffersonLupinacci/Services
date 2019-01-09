package com.jeffersonlupinacci.app.core.config.enable;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * The Asynchronous Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(0)
@Configuration
@EnableAsync
public class EnableAsyncConfig {

  /**
   * @return the Async Thread Pool Task Executor
   */
  @Bean
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(2);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix("APP_ASYNC-");
    executor.initialize();
    return executor;
  }

}
