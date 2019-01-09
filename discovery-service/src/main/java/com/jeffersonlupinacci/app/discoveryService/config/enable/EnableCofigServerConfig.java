package com.jeffersonlupinacci.app.discoveryService.config.enable;

import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * The Spring Cloud Config Server Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(2)
@EnableConfigServer
@Configuration
public class EnableCofigServerConfig {

}
