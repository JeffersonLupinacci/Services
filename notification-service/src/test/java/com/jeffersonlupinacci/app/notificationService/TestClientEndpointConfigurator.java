package com.jeffersonlupinacci.app.notificationService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.HandshakeResponse;

public class TestClientEndpointConfigurator extends ClientEndpointConfig.Configurator {

  @Override
  public void beforeRequest(Map<String, List<String>> headers) {
    headers.put("WWW-Authenticate", Arrays.asList("Basic"));
  }

  @Override
  public void afterResponse(HandshakeResponse handshakeResponse) {
    //
  }
}
