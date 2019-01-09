package com.jeffersonlupinacci.app.proxyService.config.enable;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Enable Web Mvc Configuration
 *
 * @author jeffersonlupinacci
 */
@Configuration
@EnableWebMvc
public class EnableWebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {

    /** Swagger */
    registry.addRedirectViewController("/documentation/api/scheduler/v2/api-docs", "/api/scheduler/v2/api-docs").setKeepQueryParams(true);
    registry.addRedirectViewController("/documentation/api/app/v2/api-docs", "/api/app/v2/api-docs").setKeepQueryParams(true);
    registry.addRedirectViewController("/documentation", "/documentation/swagger-ui.html").setKeepQueryParams(true);
    registry.addRedirectViewController("/documentation/", "/documentation/swagger-ui.html").setKeepQueryParams(true);
    registry.addRedirectViewController("/documentation/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
    registry.addRedirectViewController("/documentation/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
    registry.addRedirectViewController("/documentation/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
    registry.addRedirectViewController("/documentation/swagger-resources", "/swagger-resources");

    /* Eureka UI */
    registry.addRedirectViewController("/eureka-ui", "/mgmt/eureka-ui/");
    registry.addRedirectViewController("/eureka-ui/lastn", "/mgmt/eureka-ui/lastn");
    registry.addRedirectViewController("/eureka/css/wro.css", "/mgmt/eureka/css/wro.css");
    registry.addRedirectViewController("/eureka/js/wro.js", "/mgmt/eureka/js/wro.js");

  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/documentation/**")
        .addResourceLocations("classpath:/META-INF/resources/");
  }


}
