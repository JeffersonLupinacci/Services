package com.jeffersonlupinacci.app.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface MessageFormat {

  /**
   * Returns an array of the kinds of elements an annotation type can be applied to.
   *
   * @return an array of the kinds of elements an annotation type can be applied to
   */
  MessageFormatType type();
}
