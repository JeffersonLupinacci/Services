package com.jeffersonlupinacci.app.applicationService.api.communication;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.RabbitMQManagementService;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.RabbitMQMessageService;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.Queue;
import com.jeffersonlupinacci.app.core.serializablesDTO.utils.SimpleKeyValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Communication Rest Controller API
 *
 * @author jeffersonlupinacci
 */
@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("/communication")
@Api(value = "Queue Operations", description = "Queue Operations", tags = {"Queue"})
@SuppressWarnings("unused")
public class QueueOperationRestController {

  @Autowired
  RabbitMQMessageService messageService;

  @Autowired
  RabbitMQManagementService managementService;

  @ApiOperation(value = "Create Queue", response = Boolean.class,
      produces = "application/json", notes = "Create a new Queue", nickname = "createQueue")
  @PostMapping(value = "/queues", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createQueue(String name, boolean durable, boolean exclusive,
      boolean autoDelete, @RequestParam(required = false) SimpleKeyValue arguments) throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(messageService
        .createQueue(name, durable, exclusive, autoDelete, (null != arguments ? arguments.getContent() : null)));
  }

  @ApiOperation(value = "Delete Queue", response = Boolean.class,
      produces = "application/json", notes = "Delete a Queue", nickname = "deleteQueue")
  @DeleteMapping(value = "/queues", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> deleteQueue(String name) throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(messageService.deleteQueue(name));
  }

  @ApiOperation(value = "Get All Queues", response = Queue[].class,
      produces = "application/json", notes = "Get All Queues", nickname = "getQueues")
  @GetMapping(value = "/queues", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getQueues() throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(managementService.getQueues());
  }

}
