package com.jeffersonlupinacci.app.core.exception;

/**
 * The Command Execution Exception
 *
 * @author jeffersonlupinacci
 */
public class CommandExecuteException extends Exception {

  /**
   * @param throwable the the Exception
   */
  public CommandExecuteException(Throwable throwable) {
    super(throwable);
  }

  /**
   * @param message the Message
   */
  public CommandExecuteException(String message) {
    super(message);
  }

}
