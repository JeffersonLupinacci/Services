package com.jeffersonlupinacci.app.core.facade.impl.person.command;

import static org.springframework.util.CollectionUtils.isEmpty;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import com.jeffersonlupinacci.app.data.domain.person.Person;
import com.jeffersonlupinacci.app.data.repository.PersonRepository;

/**
 * Create Person Command
 *
 * @author jeffersonlupinacci
 */
public class CreatePersonCommand extends BaseCommand<Person> {

  private final Person person;

  /**
   * Default Constructor
   *
   * @param person the Person
   */
  public CreatePersonCommand(Person person) {
    super();
    this.person = person;
  }

  /**
   * Process the command
   *
   * @return the Return of the command
   */
  @Override
  protected Person process() throws CommandExecuteException {

    this.person.setId(null);

    if (!isEmpty(this.person.getAddresses())) {
      this.person.getAddresses().forEach(address -> address.setPerson(this.person));
    }

    if (!isEmpty(this.person.getPhones())) {
      this.person.getPhones().forEach(phone -> phone.setPerson(this.person));
    }

    if (!isEmpty(this.person.getEmails())) {
      this.person.getEmails().forEach(email -> email.setPerson(this.person));
    }

    return SpringContext
        .getBean(PersonRepository.class)
        .save(this.person);
  }
}
