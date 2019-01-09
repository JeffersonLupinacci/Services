package com.jeffersonlupinacci.app.core.facade;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import java.util.concurrent.CompletableFuture;

/**
 * The Base Async Command
 *
 * @author jeffersonlupinacci
 */
public abstract class BaseAsyncCommand<T> extends BaseCommand<CompletableFuture<T>> {

  /**
   * Execute the command
   *
   * @return the Return of Execution
   * @throws CommandExecuteException the Command Execute Exception
   */
  @Override
  public CompletableFuture<T> execute() throws CommandExecuteException {

    String name = Thread.currentThread().getName();
    Thread.currentThread().setName(name + getClass().getSimpleName());

    return super.execute();

  }

  /**
   * Process the command
   *
   * @return the Return of the command
   */
  protected abstract CompletableFuture<T> process() throws CommandExecuteException;

}
