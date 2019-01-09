package com.jeffersonlupinacci.app.core.annotation;


import com.jeffersonlupinacci.app.core.SpringContext;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.Jackson2XmlMessageConverter;

/**
 * The Message Format Type
 *
 * @author jeffersonlupinacci
 */
public enum MessageFormatType {

  JSON("application/json") {

    @Override
    public AbstractMessageConverter getConverter() {
      return SpringContext.getBean(Jackson2JsonMessageConverter.class, "rabbitMQ_JsonConverter");
    }

  },

  XML("application/xml") {

    @Override
    public AbstractMessageConverter getConverter() {
      return SpringContext.getBean(Jackson2XmlMessageConverter.class, "rabbitMQ_XmlConverter");
    }


  };

  private final String contentType;

  /**
   * @param contentType the Content Type
   */
  MessageFormatType(String contentType) {
    this.contentType = contentType;
  }

  /**
   * Get the Enum Based in one Content Type
   *
   * @param contentType the Content Type
   * @return the Enum
   */
  public static MessageFormatType valueOfContentType(String contentType) {
    for (MessageFormatType value : MessageFormatType.values()) {
      if (value.contentType.equals(contentType)) {
        return value;
      }
    }
    return null;
  }

  /**
   * @return the Content Type
   */
  public String getContentType() {
    return contentType;
  }

  /**
   * @return the RabbitMQ Converter
   */
  public abstract AbstractMessageConverter getConverter();


}
