package com.jeffersonlupinacci.app.authService.controller;

import com.jeffersonlupinacci.app.authService.config.security.JwtAuthenticationConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User LoginForm Controller
 *
 * @author jeffersonlupinacci
 */

@Controller
public class LoginController {

  @Autowired
  @Qualifier("jwtAuthenticationConfig")
  JwtAuthenticationConfig config;
  @Autowired
  private AuthenticationManager authenticationManager;

  /**
   * Controller URL
   */
  @RequestMapping("/auth/login")
  @GetMapping
  public String login() {
    return "login";
  }

  /**
   * Check the Login
   *
   * @param response the Http Servlet Response
   * @param username the Username
   * @param password the Password
   * @return the Authorization
   */
  @PostMapping(name = "/auth/check")
  public ResponseEntity<String> check(HttpServletResponse response, String username, String password) {

    Authentication auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        username, password, Collections.emptyList()));

    if (auth.isAuthenticated()) {

      Instant now = Instant.now();
      String bearer = Jwts.builder()
          .setSubject(auth.getName())
          .claim("authorities", auth.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
          .setIssuedAt(Date.from(now))
          .setExpiration(Date.from(now.plusSeconds(config.getExpiration())))
          .signWith(SignatureAlgorithm.HS256, config.getSecret().getBytes())
          .compact();

      Cookie cookie = new Cookie("Bearer", bearer);
      cookie.setPath("/");
      cookie.setMaxAge(60 * 60 * 24);
      response.addCookie(cookie);

      return ResponseEntity.status(HttpStatus.OK).body("200");
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("401");

  }

  /**
   * Controller URL
   */
  @RequestMapping("/auth/logout")
  @GetMapping
  public String logout(HttpServletResponse response) {
    response.addCookie(new Cookie("Bearer", null));
    return "login";
  }

}
