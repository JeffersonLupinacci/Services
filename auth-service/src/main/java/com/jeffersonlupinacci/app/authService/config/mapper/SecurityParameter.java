package com.jeffersonlupinacci.app.authService.config.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The Security Parameter
 *
 * @author jeffersonlupinacci
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityParameter {

  String keyStore;
  String trustStore;
  String keyStorePassword;
  String trustStorePassword;

  @JsonProperty(value = "server")
  private void server(JsonNode server) {
    JsonNode ssl = server.get("ssl");
    this.keyStore = ssl.get("key-store").asText();
    this.trustStore = ssl.get("trust-store").asText();
    this.keyStorePassword = ssl.get("key-store-password").asText();
    this.trustStorePassword = ssl.get("trust-store-password").asText();

    if (keyStore.contains("classpath:")) {
      this.keyStore = this.getClass().getClassLoader()
          .getResource(keyStore.replace("classpath:", "")).getPath();
    }

    if (trustStore.contains("classpath:")) {
      this.trustStore = this.getClass().getClassLoader()
          .getResource(trustStore.replace("classpath:", "")).getPath();
    }

  }

}
