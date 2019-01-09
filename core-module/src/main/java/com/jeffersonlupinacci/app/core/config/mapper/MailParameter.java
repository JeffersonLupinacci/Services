package com.jeffersonlupinacci.app.core.config.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MailParameter {

  private String host;

  private Integer port;

  private String protocol;

  private String username;

  private String password;

  private Properties properties;

  @JsonProperty(value = "mail")
  private void mail(JsonNode mail) {

    properties = new Properties();
    JsonNode server = mail.get("server");
    JsonNode smtp = mail.get("smtp");

    this.host = smtp.get("host").textValue();
    this.port = smtp.get("port").intValue();
    this.protocol = server.get("protocol").textValue();
    this.username = server.get("username").textValue();
    this.password = server.get("password").textValue();

    for (Iterator<Entry<String, JsonNode>> mailItems = mail.fields(); mailItems.hasNext(); ) {
      Entry<String, JsonNode> group = mailItems.next();
      if (!group.getKey().equals("smtp")) {
        continue;
      }
      Iterator<Entry<String, JsonNode>> groupItems = group.getValue().fields();
      while (groupItems.hasNext()) {
        Entry<String, JsonNode> item = groupItems.next();
        properties.put("mail." + group.getKey() + "." + item.getKey(), item.getValue());
      }
    }

  }

}
