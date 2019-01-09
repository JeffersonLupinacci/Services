package com.jeffersonlupinacci.app.discoveryService.config.enable;

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * The Eureka Server Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(0)
@EnableEurekaServer
@Configuration
public class EnableEurekaServerConfig {


}
