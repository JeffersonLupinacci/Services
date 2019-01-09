package com.jeffersonlupinacci.app.schedulerService.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author jeffersonlupinacci
 */
@SuppressWarnings("unused")
public class ParallelJob implements Job {

  private final Log logger = LogFactory.getLog(getClass());

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    try {
      String id = jobExecutionContext.getJobDetail().getKey().getName();
    } catch (Exception e) {
      throw new JobExecutionException(e);
    }
  }
}
