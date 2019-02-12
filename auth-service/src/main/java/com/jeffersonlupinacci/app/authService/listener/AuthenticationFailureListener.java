package com.jeffersonlupinacci.app.authService.listener;

import com.jeffersonlupinacci.app.authService.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * The listener of failed authentication attempts
 *
 * @author jeffersonlupinacci
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

  @Autowired
  private LoginService loginService;

  public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {

    loginService.loginFailed(e.getAuthentication().getName());

  }
}
