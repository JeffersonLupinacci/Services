package com.jeffersonlupinacci.app.applicationService.api.communication;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.RabbitMQListenerConfiguration;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.RabbitMQManagementService;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.RabbitMQMessageService;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.Listener;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Communication Rest Controller API
 *
 * @author jeffersonlupinacci
 */
@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("/communication")
@Api(value = "Listener Operations", description = "Listener Operations", tags = {"Listener"})
@SuppressWarnings("unused")
public class ListenerOperationRestController {

  @Autowired
  RabbitMQMessageService messageService;

  @Autowired
  RabbitMQManagementService managementService;

  @ApiOperation(value = "Create Listener", response = Boolean.class,
      produces = "application/json", notes = "Create a Listener", nickname = "createListener")
  @PostMapping(value = "/listeners", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createListener(String queueName, Boolean exposeListenerChannel,
      Integer maxConcurrentConsumers, Integer concurrentConsumers, AcknowledgeMode acknowledgeMode,
      Boolean channelTransacted) throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(messageService
        .createListener(RabbitMQListenerConfiguration.builder()
            .queueName(queueName)
            .exposeListenerChannel(exposeListenerChannel)
            .maxConcurrentConsumers(maxConcurrentConsumers)
            .concurrentConsumers(concurrentConsumers)
            .acknowledgeMode(acknowledgeMode)
            .channelTransacted(channelTransacted).build()));
  }

  @ApiOperation(value = "Delete Listener", response = Boolean.class,
      produces = "application/json", notes = "Delete a Queue", nickname = "deleteListener")
  @DeleteMapping(value = "/listeners", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> deleteListener(String name) throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(messageService.deleteListener(name));
  }

  @ApiOperation(value = "Get Listeners", response = Listener[].class,
      produces = "application/json", notes = "Get All Bindings", nickname = "getListeners")
  @GetMapping(value = "/listeners", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getListeners() throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(messageService.getListeners());
  }

}
