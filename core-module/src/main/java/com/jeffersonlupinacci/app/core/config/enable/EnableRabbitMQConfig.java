package com.jeffersonlupinacci.app.core.config.enable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.Jackson2XmlMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

/**
 * The RabbitMQ Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(1)
@Profile(value = "rabbitMQ")
@EnableRabbit
@Configuration
public class EnableRabbitMQConfig {

  private static final Logger logger = LoggerFactory.getLogger(EnableRabbitMQConfig.class);

  @Value("${rabbitmq.host}")
  private String host;

  @Value("${rabbitmq.port:5672}")
  private int port;

  @Value("${rabbitmq.username}")
  private String username;

  @Value("${rabbitmq.password}")
  private String password;

  @Value("${rabbitmq.publisherConfirms:false}")
  private boolean publisherConfirms;

  @Value("${rabbitmq.publisherReturns:false}")
  private boolean publisherReturns;

  /**
   * RabbitMQ Connection Factory
   *
   * @return the Connection Factory
   */
  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.host, this.port);
    connectionFactory.setUsername(this.username);
    connectionFactory.setPassword(this.password);
    connectionFactory.setPublisherConfirms(this.publisherConfirms);
    connectionFactory.setPublisherReturns(this.publisherReturns);
    logger.info(String.format("Creating CachingConnectionFactory with: %s@%s:%d", this.username, this.host, this.port));
    return connectionFactory;
  }

  /**
   * Required for executing administration functions against an AMQP Broker
   */
  @Bean
  public AmqpAdmin amqpAdmin() {
    return new RabbitAdmin(connectionFactory());
  }

  @Bean(name = "rabbitMQ_JsonConverter")
  public Jackson2JsonMessageConverter jsonConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean("rabbitMQ_XMLConverter")
  public Jackson2XmlMessageConverter xmlConverter() {
    return new Jackson2XmlMessageConverter();
  }


}
