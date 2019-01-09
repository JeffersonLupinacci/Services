package com.jeffersonlupinacci.app.core.facade.impl.person.command;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.BaseAsyncCommand;
import com.jeffersonlupinacci.app.data.domain.person.Person;
import java.util.concurrent.CompletableFuture;

/**
 * Create Person Command
 *
 * @author jeffersonlupinacci
 */
public class GetAsyncPersonCommand extends BaseAsyncCommand<Person> {

  private final Integer delay;

  public GetAsyncPersonCommand(Integer delay) {
    super();
    this.delay = delay;
  }

  /**
   * Process the command
   *
   * @return the Return of the command
   */
  @Override
  protected CompletableFuture<Person> process() throws CommandExecuteException {

    try {
      log.info("Starting Async: " + delay);

      Thread.sleep(delay);

      log.info("End Async");

    } catch (InterruptedException e) {
      throw new CommandExecuteException(e);
    }

    return null;
  }
}
