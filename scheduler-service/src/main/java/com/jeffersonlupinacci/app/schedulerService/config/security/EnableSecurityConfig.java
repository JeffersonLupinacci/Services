package com.jeffersonlupinacci.app.schedulerService.config.security;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(securedEnabled = true)
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

        // Default Exception Handler
        .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()

        // JWT Filter
        .addFilterAfter(new JwtTokenAuthenticationFilter(config), UsernamePasswordAuthenticationFilter.class)

        .authorizeRequests()
        .antMatchers("/actuator/**").permitAll()

        // Swagger Documentation
        .antMatchers("/v2/api-docs/").hasRole("USER")

        .anyRequest().authenticated();
  }
}
