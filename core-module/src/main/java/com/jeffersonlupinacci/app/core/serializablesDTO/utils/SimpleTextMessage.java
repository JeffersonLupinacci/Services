package com.jeffersonlupinacci.app.core.serializablesDTO.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeffersonlupinacci.app.core.annotation.MessageFormat;
import com.jeffersonlupinacci.app.core.annotation.MessageFormatType;
import com.jeffersonlupinacci.app.core.serializablesDTO.BaseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@MessageFormat(type = MessageFormatType.JSON)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleTextMessage extends BaseMessage {

  @JsonProperty("message")
  @Getter
  @Setter
  String message;

}
