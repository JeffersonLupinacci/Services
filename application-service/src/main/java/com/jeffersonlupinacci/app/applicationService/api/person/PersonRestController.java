package com.jeffersonlupinacci.app.applicationService.api.person;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.interfaces.person.PersonService;
import com.jeffersonlupinacci.app.data.domain.person.Person;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/person")
@Secured("ROLE_USER")
@RestController
public class PersonRestController {

  @Autowired
  PersonService personService;

  @ApiOperation(value = "Create Person", response = Person.class,
      produces = "application/json", notes = "Create a new Person", nickname = "create")
  @PostMapping()
  public ResponseEntity<Person> createPerson(@RequestBody @Valid Person person) {
    try {
      Person personCreated = this.personService.createPerson(person);
      return ResponseEntity.status(HttpStatus.CREATED).body(personCreated);
    } catch (CommandExecuteException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

  }

  @ApiOperation(value = "Retrieve a Person", response = Person.class,
      produces = "application/json", notes = "Retrieve a Person in JSON Format", nickname = "getPerson")
  @GetMapping()
  public ResponseEntity<Person> getPerson() {
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

}
