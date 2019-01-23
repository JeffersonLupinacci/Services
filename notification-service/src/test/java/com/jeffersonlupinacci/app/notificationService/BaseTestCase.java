package com.jeffersonlupinacci.app.notificationService;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.config.AppConfiguration;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest()
@ContextConfiguration(
    classes = {SpringContext.class})
public class BaseTestCase {

  @Before()
  public void configure() {
    try {
      AppConfiguration.define(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
