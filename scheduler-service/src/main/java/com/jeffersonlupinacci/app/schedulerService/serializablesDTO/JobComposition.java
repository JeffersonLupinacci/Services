package com.jeffersonlupinacci.app.schedulerService.serializablesDTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeffersonlupinacci.app.schedulerService.enumeration.JobType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The Job Composition
 * @author jeffersonlupinacci
 */
@Getter
@Setter
@Builder(toBuilder = true)
@ApiModel(value = "Job Composition", description = "The Job Composition Model")
public class JobComposition implements Serializable {

  @ApiModelProperty(value = "The Job Identifier")
  @JsonProperty(required = true)
  final private String identifier;

  @ApiModelProperty(value = "The Job Type")
  @JsonProperty(required = true)
  final private JobType jobType;

  @ApiModelProperty(value = "The Job Parameters List")
  @JsonProperty()
  final private Map<String, String> parameters;

  /**
   * Swagger Constructor
   *
   * @param identifier the Job Identifier
   * @param parameters the Parameter List
   */
  @JsonCreator
  public JobComposition(@JsonProperty("jobIdentifier") String identifier,
      @JsonProperty("jobType") JobType jobType,
      @JsonProperty("parameters") Map<String, String> parameters) {
    this.identifier = identifier;
    this.jobType = jobType;
    this.parameters = parameters;
  }

}
