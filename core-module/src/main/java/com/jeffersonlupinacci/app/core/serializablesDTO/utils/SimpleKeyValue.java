package com.jeffersonlupinacci.app.core.serializablesDTO.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import lombok.Getter;

/**
 * The Simple Key Value Json Mapper Param
 * @author jeffersonlupinacci
 */
@Getter
public class SimpleKeyValue {

  @JsonProperty("content")
  Map<String, String> content;

  public SimpleKeyValue(String jsonString) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    this.content = mapper.readValue(jsonString, new TypeReference<Map<String, String>>() {
    });
  }

}
