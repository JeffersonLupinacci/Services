package com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Rabbit MQ Queue DTO
 *
 * @author jeffersonlupinacci
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Queue {

  @JsonProperty(value = "name")
  @Resource
  private String name;

  @JsonProperty(value = "auto_delete")
  @Resource
  private Boolean autoDelete;

  @JsonProperty(value = "durable")
  @Resource
  private Boolean durable;

  @JsonProperty(value = "exclusive")
  @Resource
  private Boolean exclusive;

  @JsonProperty(value = "messages")
  @Resource
  private Long messages;

  @JsonProperty(value = "node")
  @Resource
  private String node;

  @JsonProperty(value = "state")
  @Resource
  private String state;

}
