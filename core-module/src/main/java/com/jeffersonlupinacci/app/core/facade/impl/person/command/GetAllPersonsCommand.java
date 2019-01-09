package com.jeffersonlupinacci.app.core.facade.impl.person.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import com.jeffersonlupinacci.app.data.domain.person.Person;
import com.jeffersonlupinacci.app.data.repository.PersonRepository;
import java.util.Collection;

/**
 * The Command
 */
public class GetAllPersonsCommand extends BaseCommand<Collection<Person>> {

  /**
   * Process the command
   *
   * @return the Return of the command
   */
  @Override
  protected Collection<Person> process() {
    return SpringContext
        .getBean(PersonRepository.class)
        .findAll();
  }

}
