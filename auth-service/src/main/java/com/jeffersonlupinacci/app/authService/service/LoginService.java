package com.jeffersonlupinacci.app.authService.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The Attempt Logins Service
 *
 * @author jeffersonlupinacci
 */
@Service
public class LoginService {

  @Value("${app.security.max-login-attempts:10}")
  private Integer maxloginAttempts = 10;

  @Value("${app.security.login-attempts-reset-in-hours:1}")
  private Integer loginAttemptReset = 1;

  private LoadingCache<String, Integer> failureCache;

  public LoginService() {
    super();
    failureCache = CacheBuilder.newBuilder().expireAfterWrite(loginAttemptReset, TimeUnit.HOURS)
        .build(new CacheLoader<String, Integer>() {
          public Integer load(String key) {
            return 0;
          }
        });
  }

  /**
   * Invalidate a Failed Attempt
   *
   * @param user the user
   */
  public void loginSucceeded(String user) {
    failureCache.invalidate(user);
  }

  /**
   * Log a Failed Attempt
   *
   * @param user the user
   */
  public void loginFailed(String user) {
    int attempts;
    try {
      attempts = failureCache.get(user);
    } catch (ExecutionException e) {
      attempts = 0;
    }
    attempts++;
    failureCache.put(user, attempts);
  }

  /**
   * Check if this attempt stay blocked
   *
   * @param user the user
   * @return the boolean check
   */
  public boolean isUserBlocked(String user) {
    try {
      return failureCache.get(user) >= maxloginAttempts;
    } catch (ExecutionException e) {
      return false;
    }
  }

}
