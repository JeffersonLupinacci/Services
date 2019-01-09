package com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Rabbit MQ Topic Exchange DTO
 *
 * @author jeffersonlupinacci
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicExchange {

  @JsonProperty(value = "name")
  @Resource
  private String name;

  @JsonProperty(value = "vhost")
  @Resource
  private String vhost;

  @JsonProperty(value = "type")
  @Resource
  private String type;

  @JsonProperty(value = "internal")
  @Resource
  private Boolean internal;

  @JsonProperty(value = "durable")
  @Resource
  private Boolean durable;

  @JsonProperty(value = "auto_delete")
  @Resource
  private Boolean autoDelete;

}
