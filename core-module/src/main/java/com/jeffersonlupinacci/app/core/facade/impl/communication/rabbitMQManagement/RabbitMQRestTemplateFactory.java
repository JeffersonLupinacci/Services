package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author jeffersonlupinacci Rabbit MQ Rest Template Factory
 */
@Profile(value = "rabbitMQ")
@Component
public class RabbitMQRestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {

  @Value("${rabbitmq.username}")
  private String username;

  @Value("${rabbitmq.password}")
  private String password;

  private RestTemplate restTemplate;

  /**
   * @return Object Instance
   */
  public RestTemplate getObject() {
    return this.restTemplate;
  }

  /**
   * @return Object Type
   */
  public Class<RestTemplate> getObjectType() {
    return RestTemplate.class;
  }

  /**
   * @return Singleton
   */
  public boolean isSingleton() {
    return true;
  }

  /**
   * Set the Parameters
   */
  public void afterPropertiesSet() {
    this.restTemplate = new RestTemplate();
    this.restTemplate.getInterceptors()
        .add(new BasicAuthenticationInterceptor(this.username, this.password));
  }
}
