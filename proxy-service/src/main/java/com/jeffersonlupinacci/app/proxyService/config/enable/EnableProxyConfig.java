package com.jeffersonlupinacci.app.proxyService.config.enable;

import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * The Zuul Proxy Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(1)
@EnableZuulProxy
@Configuration
public class EnableProxyConfig {

}
