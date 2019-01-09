package com.jeffersonlupinacci.app.core.facade.impl.person;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.impl.person.command.CreatePersonCommand;
import com.jeffersonlupinacci.app.core.facade.impl.person.command.GetAllPersonsCommand;
import com.jeffersonlupinacci.app.core.facade.impl.person.command.GetAsyncPersonCommand;
import com.jeffersonlupinacci.app.core.facade.interfaces.person.PersonService;
import com.jeffersonlupinacci.app.data.domain.person.Person;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

/**
 * The Person Service Implementation
 *
 * @author jeffersonlupinacci
 */
public class PersonServiceImpl implements PersonService {

  @Override
  public Person createPerson(Person person) throws CommandExecuteException {
    return new CreatePersonCommand(person).execute();
  }

  @Override
  public Collection<Person> getAllPersons() throws CommandExecuteException {
    return new GetAllPersonsCommand().execute();
  }

  @Async
  @Override
  public CompletableFuture<Person> getAsyncPersons(Integer delay) throws CommandExecuteException {
    return new GetAsyncPersonCommand(delay).execute();
  }
}
