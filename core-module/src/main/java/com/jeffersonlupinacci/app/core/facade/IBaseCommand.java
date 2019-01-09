package com.jeffersonlupinacci.app.core.facade;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;

/**
 * The Base Command Interface
 *
 * @author jeffersonlupinacci
 */
interface IBaseCommand<T> {

  @SuppressWarnings("unused")
  T execute() throws CommandExecuteException;

}
