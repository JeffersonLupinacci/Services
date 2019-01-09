package com.jeffersonlupinacci.app.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * The Spring Context
 *
 * @author jeffersonlupinacci
 */
@Component
public class SpringContext implements ApplicationContextAware {

  private static ApplicationContext context;

  /**
   * Get a Bean from Spring Application Context
   *
   * @param beanClass the Bean Class
   * @param <T> the Class Type Required
   * @return the The Required Class
   */
  public static <T> T getBean(Class<T> beanClass) {
    return context.getBean(beanClass);
  }

  /**
   * Get a Bean from Spring Application Context
   *
   * @param beanClass the Bean Class
   * @param <T> the Class Type Required
   * @param qualifier the Qualifier
   * @return the The Required Class
   */
  public static <T> T getBean(Class<T> beanClass, String qualifier) {
    return BeanFactoryAnnotationUtils.qualifiedBeanOfType(context.getAutowireCapableBeanFactory(), beanClass, qualifier);
  }

  /**
   * Get a String from Environment Spring
   *
   * @param resourceName the Resource Name
   * @return the Resource Text
   */
  public static String getEnvironmentProperty(String resourceName) {
    return context.getEnvironment().getProperty(resourceName);
  }

  /**
   * Get a Resource from Spring Context
   *
   * @param resourceName the Resource Name
   * @return the Resource
   */
  public static Resource getResource(String resourceName) {
    return context.getResource(resourceName);
  }

  /**
   * Set the Application Context
   *
   * @param applicationContext the Application Context
   * @throws BeansException the Beans Exception
   */
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
  }

}
