package com.jeffersonlupinacci.app.core.config.enable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Iterator;
import java.util.Properties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The KeyStore Security Parameter
 *
 * @author jeffersonlupinacci
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailParameter {

  Properties properties;

  @JsonProperty(value = "email")
  private void email(JsonNode email) {
    JsonNode smtp = email.get("smtp");
    this.properties = new Properties();

    Iterator<JsonNode> it = smtp.iterator();
    while (it.hasNext()){
      JsonNode item = it.next();
      this.properties.put(item.asText(), item.textValue());
    }



  }

}
