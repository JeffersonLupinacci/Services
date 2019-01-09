package com.jeffersonlupinacci.app.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Exception Handler Data
 * @author jeffersonlupinacci
 */
@Getter
@AllArgsConstructor
public class ExceptionHandlerData {

  private String userMessage;
  private String cause;

}
