package com.jeffersonlupinacci.app.applicationService.api.communication;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.RabbitMQManagementService;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.RabbitMQMessageService;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.Binding;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * The Communication Rest Controller API
 *
 * @author jeffersonlupinacci
 */
@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("/communication")
@Api(value = "Binding Operations", description = "Binding Operations", tags = {"Binding"})
@SuppressWarnings("unused")
public class BindingOperationRestController {

  @Autowired
  RabbitMQMessageService messageService;

  @Autowired
  RabbitMQManagementService managementService;

  @ApiOperation(value = "Create Binding", response = Boolean.class,
      produces = "application/json", notes = "Create a Binding", nickname = "createBinding")
  @PostMapping(value = "/bindings", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createBinding(String queueName, String topicExchangeName, String routingKey) throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(messageService.createBinding(queueName, topicExchangeName, routingKey));
  }

  @ApiOperation(value = "Delete Binding", response = Boolean.class,
      produces = "application/json", notes = "Delete a Binding", nickname = "deleteBinding")
  @DeleteMapping(value = "/bindings", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> deleteBinding(String queueName, String topicExchangeName, String routingKey) throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(messageService.deleteBinding(queueName, topicExchangeName, routingKey));
  }

  @ApiOperation(value = "Get Bindings", response = Binding[].class,
      produces = "application/json", notes = "Get All Bindings", nickname = "getBindings")
  @GetMapping(value = "/bindings", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getBindings() throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(managementService.getBindings());
  }

}
