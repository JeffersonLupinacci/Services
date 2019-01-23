package com.jeffersonlupinacci.app.notificationService;


import static java.lang.Thread.sleep;

import com.jeffersonlupinacci.app.core.SpringContext;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebSocketClientTest extends BaseTestCase {

  private static Logger log = LoggerFactory.getLogger(WebSocketClientTest.class);

  @Test
  public void testWebSocket() {

    try {

      WebSocketContainer container = ContainerProvider.getWebSocketContainer();
      String uri = "ws://localhost:9201/notification";

      log.info("Connecting to ", uri);
      container.connectToServer(TestClientEndpoint.class, URI.create(uri));

      TestClientEndpoint.messageLatch.await(100, TimeUnit.SECONDS);

      while (true) {
        sleep(1);
      }

    } catch (DeploymentException | InterruptedException | IOException ex) {
      log.warn("Exception ->", ex);
    }


  }

}
