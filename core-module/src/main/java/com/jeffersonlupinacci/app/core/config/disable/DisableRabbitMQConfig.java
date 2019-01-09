package com.jeffersonlupinacci.app.core.config.disable;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

/**
 * Disable RabbitMQ Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(3)
@Profile("!rabbitMQ")
@Configuration
@EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
public class DisableRabbitMQConfig {

}
