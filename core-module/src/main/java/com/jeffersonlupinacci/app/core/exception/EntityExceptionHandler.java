package com.jeffersonlupinacci.app.core.exception;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The Error Handler Resolver Advice
 *
 * @author jeffersonlupinacci
 */
@ControllerAdvice
@SuppressWarnings("unused")
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  ExceptionHandlerCauseResolver exceptionResolver;

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    exceptionResolver.generate("message.notReadable", ex);
    return handleExceptionInternal(ex, exceptionResolver.getCauses(), headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    exceptionResolver.generate("message.argumentNotValid", ex);
    return handleExceptionInternal(ex, exceptionResolver.getCauses(), headers, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({CommandExecuteException.class})
  @SuppressWarnings("unused")
  protected ResponseEntity<Object> handleCommandExecuteException(CommandExecuteException ex, WebRequest request) {
    exceptionResolver.generate(ex);
    return handleExceptionInternal(ex, exceptionResolver.getCauses(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
        request);
  }

  @ExceptionHandler({AmqpException.class})
  @SuppressWarnings("unused")
  protected ResponseEntity<Object> handleAmqpException(AmqpException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    exceptionResolver.generate("message.amqp", ex);
    return handleExceptionInternal(ex, exceptionResolver.getCauses(), headers, HttpStatus.BAD_REQUEST, request);
  }

}
