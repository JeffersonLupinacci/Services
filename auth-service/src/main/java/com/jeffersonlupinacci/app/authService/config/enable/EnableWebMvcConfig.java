package com.jeffersonlupinacci.app.authService.config.enable;

import com.jeffersonlupinacci.app.authService.controller.LoginController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Enable Web Mvc Configuration
 *
 * @author jeffersonlupinacci
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {LoginController.class})
public class EnableWebMvcConfig implements WebMvcConfigurer {

}
