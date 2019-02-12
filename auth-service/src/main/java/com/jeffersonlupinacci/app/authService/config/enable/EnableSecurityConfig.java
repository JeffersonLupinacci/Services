package com.jeffersonlupinacci.app.authService.config.enable;

import com.jeffersonlupinacci.app.authService.config.security.JwtAuthenticationConfig;
import com.jeffersonlupinacci.app.authService.config.security.JwtUsernamePasswordAuthenticationFilter;
import com.jeffersonlupinacci.app.authService.service.AppUserDetailsService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The Enable Security Configuration
 *
 * @author jeffersonlupinacci
 */
@EnableAutoConfiguration()
@EnableWebSecurity
public class EnableSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  JwtAuthenticationConfig config;

  @Autowired
  private AppUserDetailsService appUserDetailsService;

  /**
   * The Jwt Config Bean
   *
   * @return The Jwt Config
   */
  @Bean
  public JwtAuthenticationConfig jwtConfig() {
    return new JwtAuthenticationConfig();
  }

  /**
   * The Authentcation Manager Bean
   *
   * @return the Authentcation Manager
   * @throws Exception the Exception
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * Configure The Authentication Manager Builder
   *
   * @param auth the Authentication Manager Builder
   * @throws Exception the Exception
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder());
  }

  /**
   * Configure the Http Secure Params
   *
   * @param http the Http Security
   * @throws Exception the Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .logout().disable()
        .formLogin().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .anonymous()
        .and()
        .exceptionHandling().authenticationEntryPoint(
        (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()
        .addFilterAfter(new JwtUsernamePasswordAuthenticationFilter(config, authenticationManager()),
            UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()

        .antMatchers("/login").permitAll()
        .antMatchers("/check").permitAll()
        .antMatchers("/logout").permitAll()
        .antMatchers("/actuator/**").permitAll()

        .anyRequest().authenticated();
  }

  /**
   * @return The Password Encoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
