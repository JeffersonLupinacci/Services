package com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Rabbit MQ Binding DTO
 *
 * @author jeffersonlupinacci
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Binding {

  @JsonProperty(value = "source")
  @Resource
  private String source;

  @JsonProperty(value = "vhost")
  @Resource
  private String vhost;

  @JsonProperty(value = "destination")
  @Resource
  private String destination;

  @JsonProperty(value = "destination_type")
  @Resource
  private String destinationType;

  @JsonProperty(value = "routing_key")
  @Resource
  private String routingKey;

  @JsonProperty(value = "properties_key")
  @Resource
  private String propertiesKey;

}
