package com.jeffersonlupinacci.app.authService.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * The Jwt Username Password Authentication Filter
 *
 * @author jeffersonlupinacci
 */
public class JwtUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private final JwtAuthenticationConfig config;

  public JwtUsernamePasswordAuthenticationFilter(JwtAuthenticationConfig config, AuthenticationManager authManager) {
    super(new AntPathRequestMatcher("/auth/login", "POST"));
    setAuthenticationManager(authManager);
    this.config = config;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse rsp)
      throws AuthenticationException {
    TokenUser u = this.translateUser(req);
    Authentication auth = getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
        u.getUsername(), u.getPassword(), Collections.emptyList()
    ));
    return auth;

  }

  /**
   * Basic Auth Interceptor
   *
   * @param request the Http Servlet Request
   * @return the Return
   */
  private TokenUser translateUser(HttpServletRequest request) {
    Enumeration<String> authHeader = request.getHeaders("Authorization");
    while (authHeader.hasMoreElements()) {
      String test = authHeader.nextElement();
      if (StringUtils.isNotEmpty(test)) {
        if (test.startsWith("Basic")) {
          byte[] byteArray = Base64.decodeBase64(test.replace("Basic", "").trim());
          String[] values = new String(byteArray).split(":");
          return new TokenUser(values[0], values[1]);
        }
      }
    }
    return null;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse rsp, FilterChain chain,
      Authentication auth) {
    Instant now = Instant.now();
    String token = Jwts.builder()
        .setSubject(auth.getName())
        .claim("authorities", auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plusSeconds(config.getExpiration())))
        .signWith(SignatureAlgorithm.HS256, config.getSecret().getBytes())
        .compact();
    rsp.addHeader(config.getHeader(), config.getPrefix() + " " + token);
  }

  @Getter
  @Setter
  private static class TokenUser {

    private String username, password;

    public TokenUser(String username, String password) {
      this.username = username;
      this.password = password;
    }

    public TokenUser() {
    }

  }

}
