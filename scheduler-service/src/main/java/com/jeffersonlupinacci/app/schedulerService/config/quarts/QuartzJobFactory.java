package com.jeffersonlupinacci.app.schedulerService.config.quarts;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * The Quartz Job Factory
 *
 * @author jeffersonlupinacci
 */
public class QuartzJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

  private final transient AutowireCapableBeanFactory factory;

  /**
   * @param factory the Quartz Bean Factory
   */
  public QuartzJobFactory(AutowireCapableBeanFactory factory) {
    this.factory = factory;
  }

  /**
   * Create a Job Instance
   *
   * @param bundle the Trigger Fired Bundle
   * @return the Generated Object
   * @throws Exception the Exception
   */
  @Override
  protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
    Object job = super.createJobInstance(bundle);
    factory.autowireBean(job);
    return job;
  }

}
