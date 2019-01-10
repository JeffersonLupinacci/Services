package com.jeffersonlupinacci.app.authService.listener;

import com.jeffersonlupinacci.app.authService.service.LoginService;
import javax.servlet.http.HttpServletRequest;
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
  private HttpServletRequest request;

  @Autowired
  private LoginService loginService;

  public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {

    final String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      loginService.loginFailed(request.getRemoteAddr());
    } else {
      loginService.loginFailed(xfHeader.split(",")[0]);
    }

  }
}
