package com.jeffersonlupinacci.app.schedulerService.config.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * The Jwt Token Authentication Filter
 *
 * @author jeffersonlupinacci
 */
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

  private final JwtAuthenticationConfig config;

  /**
   * Default Constructor
   *
   * @param config tje Jwt Authentication Config
   */
  public JwtTokenAuthenticationFilter(JwtAuthenticationConfig config) {
    this.config = config;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain)
      throws ServletException, IOException {

    /*
      Bearer Redirected from Zuul

      User Browser(Post Man) ->
        Send Bearer ->
          Zuul Proxy ->
            Zuul Valid Endpoint /api/app/** ->
              Send Bearer to Application
    */

    String token = req.getHeader(config.getHeader());

    // Bearer by User LoginForm
    if (null == token) {
      Cookie[] cookies = req.getCookies();
      if (null != cookies) {
        for (Cookie c : cookies) {
          if (c.getName().equals(config.getPrefix())) {
            token = config.getPrefix() + " " + c.getValue();
            break;
          }
        }
      }
    }

    if (token != null && token.startsWith(config.getPrefix() + " ")) {
      token = token.replace(config.getPrefix() + " ", "");
      try {
        Claims claims = Jwts.parser()
            .setSigningKey(config.getSecret().getBytes())
            .parseClaimsJws(token)
            .getBody();
        String username = claims.getSubject();
        @SuppressWarnings("unchecked")
        List<String> authorities = claims.get("authorities", List.class);
        if (username != null) {
          UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
              authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
          SecurityContextHolder.getContext().setAuthentication(auth);
        }
      } catch (Exception ignore) {
        SecurityContextHolder.clearContext();
      }
    }
    filterChain.doFilter(req, rsp);
  }
}
