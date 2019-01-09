package com.jeffersonlupinacci.app.applicationService.api.communication;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.RabbitMQManagementService;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.RabbitMQMessageService;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.TopicExchange;
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
@Api(value = "Topic Operations", description = "Topic Exchange Operations", tags = {"Topic"})
@SuppressWarnings("unused")
public class TopicOperationRestController {

  @Autowired
  RabbitMQMessageService messageService;

  @Autowired
  RabbitMQManagementService managementService;

  @ApiOperation(value = "Create Topic Exchange", response = Boolean.class,
      produces = "application/json", notes = "Create a Topic Exchange", nickname = "createTopicExchange")
  @PostMapping(value = "/topics", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createTopicExchange(String name, boolean durable, boolean autoDelete) throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(messageService.createTopicExchange(name, durable, autoDelete));
  }

  @ApiOperation(value = "Delete Topic Exchange", response = Boolean.class,
      produces = "application/json", notes = "Delete a Topic Exchange", nickname = "deleteTopicExchange")
  @DeleteMapping(value = "/topics", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> deleteTopicExchange(String name) throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(messageService.deleteTopicExchange(name));
  }

  @ApiOperation(value = "Get All Topic Exchanges", response = TopicExchange[].class,
      produces = "application/json", notes = "Get All Topic Exchanges", nickname = "getTopicExchanges")
  @GetMapping(value = "/topics", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getTopicExchanges() throws CommandExecuteException {
    return ResponseEntity.status(HttpStatus.OK).body(managementService.getTopicExchanges());
  }

}
