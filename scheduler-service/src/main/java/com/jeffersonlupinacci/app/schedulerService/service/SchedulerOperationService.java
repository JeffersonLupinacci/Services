package com.jeffersonlupinacci.app.schedulerService.service;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import com.jeffersonlupinacci.app.schedulerService.serializablesDTO.JobComposition;
import com.jeffersonlupinacci.app.schedulerService.serializablesDTO.OperationSummary;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

/**
 * The Scheduler Operation Service
 *
 * @author jeffersonlupinacci
 */
@Service
public class SchedulerOperationService {

  private final Scheduler scheduler;

  @Autowired
  public SchedulerOperationService(SchedulerFactoryBean schedulerFactory) {
    this.scheduler = schedulerFactory.getScheduler();
  }

  /**
   * Create a New Job
   *
   * @param jobComposition the JobComposition
   * @return the Job Operation Summary
   */
  public OperationSummary createJob(JobComposition jobComposition) throws SchedulerException {

    StringBuilder params = new StringBuilder();
    JobDataMap jobDataMap = new JobDataMap();

    jobComposition.getParameters().forEach((key, value) ->
        {
          jobDataMap.put(key, value);
          params.append(value);
        }
    );

    String id = String.format("%s_%s", jobComposition.getIdentifier(), params.toString());

    JobDetail job =
        newJob(jobComposition.getJobType().getImplementation())
            .withIdentity(id, jobComposition.getJobType().getGroup())
            .setJobData(jobDataMap)
            .requestRecovery(true)
            .storeDurably()
            .build();

    SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
        .simpleSchedule()
        .withIntervalInSeconds(10)
        .repeatForever();

    Trigger trigger =
        newTrigger()
            .withIdentity(id + "-trigger", jobComposition.getJobType().getGroup())
            .startNow()
            .withSchedule(scheduleBuilder)
            .build();

    scheduler.scheduleJob(job, trigger);

    return new OperationSummary().setResult(true);
  }

}
