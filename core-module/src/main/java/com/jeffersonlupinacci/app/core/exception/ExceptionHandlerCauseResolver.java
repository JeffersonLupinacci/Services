package com.jeffersonlupinacci.app.core.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandlerCauseResolver {

  private List<ExceptionHandlerData> causes = new ArrayList<>();

  @Autowired
  MessageSource messageSource;

  public void generate(String message, Throwable throwable) {
    causes.clear();
    add(messageSource.getMessage(message, null, LocaleContextHolder.getLocale()), throwable.toString());
  }

  public void generate(CommandExecuteException ex) {
    causes.clear();
    add(ex.getCause().getMessage(), "Execution Exception");
  }


  private void add(String message, String cause) {
    causes.add(new ExceptionHandlerData(message, cause));
  }

  public List<ExceptionHandlerData> getCauses() {
    return Collections.unmodifiableList(causes);
  }

}
