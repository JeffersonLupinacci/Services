package com.jeffersonlupinacci.app.schedulerService.config.quarts;

import com.jeffersonlupinacci.app.schedulerService.service.JobListenerService;
import com.jeffersonlupinacci.app.schedulerService.service.TriggerListenerService;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * The Quartz Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(2)
@Configuration
@EnableAsync
@EnableScheduling
@Import({JobListenerService.class, TriggerListenerService.class})
public class QuartzConfig {

  /**
   * The Job Factory Bean
   *
   * @param ctx the Application Context
   * @return the Job Factory
   */
  @Bean
  public JobFactory jobFactory(ApplicationContext ctx) {
    return new QuartzJobFactory(ctx.getAutowireCapableBeanFactory());
  }

  /**
   * The Scheduler Factory Bean
   *
   * @param jobFactory the Job Factory
   * @param jobListener the Job Listener
   * @return the Scheduler Factory Bean
   * @throws IOException the IO Exception
   */
  @Bean
  public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("quartzDataSource") DataSource dataSource,
      JobFactory jobFactory, JobListenerService jobListener,
      TriggerListenerService triggerListener) throws IOException {
    SchedulerFactoryBean factory = new SchedulerFactoryBean();
    factory.setDataSource(dataSource);
    factory.setJobFactory(jobFactory);
    factory.setQuartzProperties(quartzProperties());
    factory.setGlobalJobListeners(jobListener);
    factory.setGlobalTriggerListeners(triggerListener);
    factory.setWaitForJobsToCompleteOnShutdown(true);
    factory.setApplicationContextSchedulerContextKey("applicationContext");
    return factory;
  }

  /**
   * @return Quartz Properties
   * @throws IOException the IO Exception
   */
  @Bean
  public Properties quartzProperties() throws IOException {
    PropertiesFactoryBean props = new PropertiesFactoryBean();
    props.setLocation(new ClassPathResource("/quartz.properties"));
    props.afterPropertiesSet();
    return props.getObject();
  }
}


