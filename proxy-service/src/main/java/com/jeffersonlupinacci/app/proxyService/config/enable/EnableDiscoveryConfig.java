package com.jeffersonlupinacci.app.proxyService.config.enable;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * The Discovery Cliente Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(0)
@EnableDiscoveryClient
@Configuration
public class EnableDiscoveryConfig {

}
