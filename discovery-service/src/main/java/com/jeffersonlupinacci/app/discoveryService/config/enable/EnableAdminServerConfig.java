package com.jeffersonlupinacci.app.discoveryService.config.enable;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * The Spring Boot Admin Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(2)
@EnableAdminServer
@Configuration
public class EnableAdminServerConfig {

}
