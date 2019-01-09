package com.jeffersonlupinacci.app.schedulerService.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Service;

/**
 * @author jeffersonlupinacci
 */
@Service
public class JobListenerService implements JobListener {

  private final Log logger = LogFactory.getLog(getClass());

  /**
   * @return The Listener Name
   */
  @Override
  public String getName() {
    return getClass().getSimpleName();
  }

  /**
   * The Job to be Executed
   *
   * @param ctx the Job Execution Context
   */
  @Override
  public void jobToBeExecuted(JobExecutionContext ctx) {
    logger.info(String.format("Job to be executed: %s", ctx.getJobDetail().getKey().getName()));
  }

  /**
   * Job Denied
   *
   * @param ctx the Job Execution Context
   */
  @Override
  public void jobExecutionVetoed(JobExecutionContext ctx) {
    logger.info(String.format("Job denied: %s", ctx.getJobDetail().getKey().getName()));
  }

  /**
   * The Executed Job
   *
   * @param ctx the Job Execution Context
   * @param jobEx the Job Execution Exception
   */
  @Override
  public void jobWasExecuted(JobExecutionContext ctx, JobExecutionException jobEx) {
    logger.info(
        String.format("Job was executed: %s%s", ctx.getJobDetail().getKey().getName(), jobEx != null ? ", with error" : "")
    );
  }
}
