package com.jeffersonlupinacci.app.core.facade.interfaces.person;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.data.domain.person.Person;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;

/**
 * The Person Service
 *
 * @author jeffersonlupinacci
 */
@Service
public interface PersonService {

  Person createPerson(Person person) throws CommandExecuteException;

  Collection<Person> getAllPersons() throws CommandExecuteException;

  CompletableFuture<Person> getAsyncPersons(Integer delay) throws CommandExecuteException;

}
