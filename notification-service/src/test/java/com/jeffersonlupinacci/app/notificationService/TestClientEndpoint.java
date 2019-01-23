package com.jeffersonlupinacci.app.notificationService;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientEndpoint(configurator = TestClientEndpointConfigurator.class)
public class TestClientEndpoint {

  Logger log = LoggerFactory.getLogger(this.getClass());

  final static CountDownLatch messageLatch = new CountDownLatch(1);

  @OnOpen
  public void onOpen(Session session) {

    System.out.println("Connected to endpoint: " + session.getBasicRemote());
    try {
      String message = "Hi";
      log.info("Sending message to endpoint -> ", message);
      session.getBasicRemote().sendText(message);
    } catch (IOException ex) {
      log.warn("IOException ->", ex);
    }
  }

  @OnMessage
  public void processMessage(String message) {
    log.info("Received message in client ->", message);
    TestClientEndpoint.messageLatch.countDown();
  }

  @OnError
  public void processError(Throwable t) {
    log.warn("Throwable ->", t);
  }
}
