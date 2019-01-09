package com.jeffersonlupinacci.app.schedulerService.api;

import com.jeffersonlupinacci.app.schedulerService.serializablesDTO.JobComposition;
import com.jeffersonlupinacci.app.schedulerService.serializablesDTO.OperationSummary;
import com.jeffersonlupinacci.app.schedulerService.service.SchedulerOperationService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Scheduler Operation Controller
 *
 * @author jeffersonlupinacci
 */
@RestController
@RequestMapping("scheduler")
public class SchedulerOperationController {

  @Autowired
  SchedulerOperationService schedulerOperationService;

  /**
   * Create a new Job Operation into the Cron
   *
   * @param jobComposition the Job Composition
   * @return the Return
   */
  @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<OperationSummary> create(@RequestBody JobComposition jobComposition) throws SchedulerException {
    OperationSummary operationSummary = schedulerOperationService.createJob(jobComposition);
    return ResponseEntity.status(HttpStatus.OK).body(operationSummary);
  }


}
