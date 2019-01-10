package com.jeffersonlupinacci.app.applicationService.config.enable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Swagger Configuration
 *
 * @author jeffersonlupinacci
 */
@Profile(value = "swagger")
@Order(1)
@EnableSwagger2
@Configuration
public class EnableSwaggerConfig {

  @Value("${info.app.name}")
  private String name;

  @Value("${info.app.version}")
  private String version;

  @Value("${info.app.description}")
  private String description;

  /**
   * The Person API
   */
  @Bean
  public Docket personDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("person")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.jeffersonlupinacci.app.applicationService.api.person"))
        .build()
        .apiInfo(new ApiInfoBuilder()
            .title(name)
            .description(description)
            .version(version)
            .build());

  }

  /**
   * The Communication API
   */
  @Bean
  public Docket comunicationDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("communication")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.jeffersonlupinacci.app.core.api.communication"))
        .build()
        .apiInfo(new ApiInfoBuilder()
            .title(name)
            .description(description)
            .version(version)
            .build());

  }

}
