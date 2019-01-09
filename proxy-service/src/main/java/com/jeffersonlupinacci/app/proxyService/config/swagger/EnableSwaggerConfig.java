package com.jeffersonlupinacci.app.proxyService.config.swagger;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Swagger Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(2)
@Primary
@EnableSwagger2
@Configuration
public class EnableSwaggerConfig implements SwaggerResourcesProvider {

  @Autowired
  SwaggerResourceConfig swaggerResourceConfig;

  @Override
  public List<SwaggerResource> get() {

    List<SwaggerResource> items = new ArrayList<>();

    // Filter Swagger over Authenticate User
    final UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    if ((null != user) && (!CollectionUtils.isEmpty(user.getAuthorities()))) {
      swaggerResourceConfig.getItems().forEach(res -> user.getAuthorities().stream()
          .filter(uAuth -> res.getAuthorities().contains(uAuth.getAuthority())).forEach(city -> items.add(res)));
    }

    return items;
  }

}
