package com.jeffersonlupinacci.app.proxyService.config.swagger;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Swagger Documentation API
 *
 * @author jeffersonlupinacci
 */
@Order(1)
@ConfigurationProperties(prefix = "api")
@Configuration
@Getter
@Setter
public class SwaggerResourceConfig {

  /**
   * End Points
   */
  private List<CustomSwaggerResource> items;

  public SwaggerResourceConfig(List<CustomSwaggerResource> items) {
    this.items = items;
  }

  /**
   * Default Constructor
   */
  public SwaggerResourceConfig() {
  }

}
