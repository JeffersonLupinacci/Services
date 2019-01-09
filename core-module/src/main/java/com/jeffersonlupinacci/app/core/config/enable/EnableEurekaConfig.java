package com.jeffersonlupinacci.app.core.config.enable;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

/**
 * The Eureka Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(2)
@Profile("eureka")
@EnableDiscoveryClient
@Configuration
public class EnableEurekaConfig {

}
