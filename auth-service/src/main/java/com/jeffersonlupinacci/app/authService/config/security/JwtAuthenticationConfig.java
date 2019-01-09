package com.jeffersonlupinacci.app.authService.config.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The Jwt Authentication Configuration
 *
 * @author jeffersonlupinacci
 */
@Getter
@Component
public class JwtAuthenticationConfig {

  @Value("${app.security.jwt.url}")
  private String url;

  @Value("${app.security.jwt.header}")
  private String header;

  @Value("${app.security.jwt.prefix}")
  private String prefix;

  @Value("${app.security.jwt.expiration}")
  private int expiration; // default 24 hours

  @Value("${app.security.jwt.secret}")
  private String secret;

}
