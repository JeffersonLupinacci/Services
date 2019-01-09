package com.jeffersonlupinacci.app.authService.config.enable;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * The Enable Discovery Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(0)
@EnableDiscoveryClient
@Configuration
public class EnableDiscoveryConfig {

}
