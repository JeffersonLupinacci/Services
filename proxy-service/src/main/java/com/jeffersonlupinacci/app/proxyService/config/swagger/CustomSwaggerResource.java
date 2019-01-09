package com.jeffersonlupinacci.app.proxyService.config.swagger;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.swagger.web.SwaggerResource;

/**
 * The Custom Swagger Resource With Authorization
 *
 * @author jeffersonlupinacci
 */
@Getter
@Setter
public class CustomSwaggerResource extends SwaggerResource {

  List<String> authorities;

}
