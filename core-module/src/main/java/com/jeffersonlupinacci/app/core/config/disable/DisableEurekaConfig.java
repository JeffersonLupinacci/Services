package com.jeffersonlupinacci.app.core.config.disable;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

/**
 * Disable Eureka Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(3)
@Profile("!eureka")
@Configuration
@EnableAutoConfiguration(exclude = {EurekaClientAutoConfiguration.class})
public class DisableEurekaConfig {

}
