package com.jeffersonlupinacci.app.schedulerService.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.springframework.stereotype.Service;

/**
 * @author jeffersonlupinacci
 */
@Service
public class TriggerListenerService implements TriggerListener {

  private final Log logger = LogFactory.getLog(getClass());

  @Override
  public String getName() {
    return getClass().getSimpleName();
  }

  public void triggerComplete(Trigger trigger, JobExecutionContext context,
      Trigger.CompletedExecutionInstruction triggerInstructionCode) {
    logger.info(String.format("Trigger completed: %s", trigger.getKey()));
  }

  public void triggerFired(Trigger trigger, JobExecutionContext context) {
    logger.info(String.format("Trigger fired: %s", trigger.getKey()));
  }

  public void triggerMisfired(Trigger trigger) {
    logger.info(String.format("Trigger misfired: %s", trigger.getKey()));
  }

  public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
    logger.info(String.format("Trigger veto job execution: %s", trigger.getKey()));
    return true;
  }

}
