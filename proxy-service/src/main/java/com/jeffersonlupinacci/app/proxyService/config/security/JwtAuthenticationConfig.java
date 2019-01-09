package com.jeffersonlupinacci.app.proxyService.config.security;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The Jwt Authentication Config
 *
 * @author jeffersonlupinacci
 */
@Component
@Getter
public class JwtAuthenticationConfig {

  @Value("${app.security.jwt.url:/login}")
  private String url;

  @Value("${app.security.jwt.header:Authorization}")
  private String header;

  @Value("${app.security.jwt.prefix:Bearer}")
  private String prefix;

  @Value("${app.security.jwt.expiration:#{24*60*60}}")
  private int expiration; // default 24 hours

  @Value("${app.security.jwt.secret}")
  private String secret;

}
