package com.jeffersonlupinacci.app.core.api.communication;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.RabbitMQManagementService;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.RabbitMQMessageService;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.User;
import com.jeffersonlupinacci.app.core.serializablesDTO.utils.SimpleTextMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@Api(value = "Common Operations", description = "Common Operations", tags = {"Common"})
@SuppressWarnings("unused")
public class CommonOperationRestController {

  @Autowired
  RabbitMQMessageService messageService;

  @Autowired
  RabbitMQManagementService managementService;

  @ApiOperation(value = "Simple Text Message", response = Boolean.class,
      produces = "application/json", notes = "Send a Simple Text Message", nickname = "sendTextMessage")
  @PostMapping(value = "/sendmessage", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> sendMessage(String message, String destination, String topicExchange, String routingKey) throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(messageService.sendMessage(new SimpleTextMessage(message, destination), topicExchange, routingKey));
  }

  @ApiOperation(value = "Get Users", response = User[].class,
      produces = "application/json", notes = "Get All Users", nickname = "getUsers")
  @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getUsers() throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(managementService.getUsers());
  }

}
