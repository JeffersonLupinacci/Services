package com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Rabbit MQ User DTO
 *
 * @author jeffersonlupinacci
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

  @JsonProperty(value = "name")
  @Resource
  private String name;

  // @JsonProperty(value = "password_hash")
  // @Resource
  // private String passwordHash;

  @JsonProperty(value = "tags")
  @Resource
  private String tags;

}
