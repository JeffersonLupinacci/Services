package com.jeffersonlupinacci.app.authService.listener;

import com.jeffersonlupinacci.app.authService.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * The listener of successful authentication attempts
 *
 * @author jeffersonlupinacci
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

  @Autowired
  private LoginService loginService;

  public void onApplicationEvent(AuthenticationSuccessEvent e) {

    loginService.loginSucceeded(e.getAuthentication().getName());

  }
}
