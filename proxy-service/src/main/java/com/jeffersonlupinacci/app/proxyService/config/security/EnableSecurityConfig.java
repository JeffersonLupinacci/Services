package com.jeffersonlupinacci.app.proxyService.config.security;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The Web Security Configuration
 *
 * @author jeffersonlupinacci
 */
@EnableWebSecurity
@EnableAutoConfiguration(exclude = UserDetailsServiceAutoConfiguration.class)
public class EnableSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtAuthenticationConfig config;

  @Bean
  public JwtAuthenticationConfig jwtConfig() {
    return new JwtAuthenticationConfig();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf().disable()

        // Login Form
        .formLogin().disable()

        // Session Policy
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()

        // Anonymous
        .anonymous()
        .and()

        // Default Exception Handler
        .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()

        // JWT Filter
        .addFilterAfter(new JwtTokenAuthenticationFilter(config), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()

        // Resource
        .antMatchers("/resources/**").permitAll()

        // Auth Service
        .antMatchers("/auth/login**").permitAll()

        // Auth Service Actuator - Only Admin
        .antMatchers("/auth/actuator**").hasRole("ADMIN")

        // Application Service
        .antMatchers("/api/app/").hasRole("USER")

        // Application Service
        .antMatchers("/api/scheduler/").hasRole("USER")

        // Eureka-GUI and Spring Boot Admin - Only Admin
        .antMatchers("/mgmt/**").hasRole("ADMIN")

        .antMatchers("/actuator/health**").permitAll()

        // Swagger Documentation
        .antMatchers("/documentation/**").hasRole("USER");
  }
}
