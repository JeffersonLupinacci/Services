package com.jeffersonlupinacci.app.core.facade;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Base Command
 *
 * @author jeffersonlupinacci
 */
public abstract class BaseCommand<T> implements IBaseCommand<T> {

  protected Logger log = LoggerFactory.getLogger(this.getClass());

  /**
   * Process the command
   *
   * @return the Return of the command
   */
  protected abstract T process() throws CommandExecuteException;

  /**
   * Execute the command
   *
   * @return the Return of Execution
   * @throws CommandExecuteException the Command Execute Exception
   */
  public T execute() throws CommandExecuteException {

    long start = System.currentTimeMillis();

    try {
      log.info("Starting {}", this.getClass().getSimpleName());

      return this.process();

    } catch (Throwable e) {
      Throwable cause = ExceptionUtils.getRootCause(e);
      cause = null == cause ? e.getCause() : cause;

      String message = null;
      if (null != cause) {
        message = (null == cause.getLocalizedMessage()) ? cause.getMessage() : cause.getLocalizedMessage();
      }

      log.warn("Error caused by: {}", message);
      throw new CommandExecuteException(e);
    } finally {
      log.info("Elapsed time: {}", (System.currentTimeMillis() - start));
    }
  }

}
