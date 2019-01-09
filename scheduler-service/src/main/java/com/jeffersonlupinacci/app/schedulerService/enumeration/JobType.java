package com.jeffersonlupinacci.app.schedulerService.enumeration;

import com.jeffersonlupinacci.app.schedulerService.job.ParallelJob;
import com.jeffersonlupinacci.app.schedulerService.job.SerialJob;

/**
 * @author jeffersonlupinacci The Job Type
 */
public enum JobType {

  @SuppressWarnings("unused")
  SERIAL(SerialJob.class, "parallel-job-group"),

  @SuppressWarnings("unused")
  PARALLEL(ParallelJob.class, "serial-job-group");

  private final Class implementation;
  private final String group;

  /**
   * The Job Type
   *
   * @param implementation the Implementation
   * @param group the Group
   */
  JobType(Class implementation, String group) {
    this.implementation = implementation;
    this.group = group;
  }

  /**
   * @return the Implementation
   */
  public Class getImplementation() {
    return this.implementation;
  }

  /**
   * @return the Group
   */
  public String getGroup() {
    return this.group;
  }
}


