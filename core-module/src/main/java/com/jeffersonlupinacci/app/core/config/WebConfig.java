package com.jeffersonlupinacci.app.core.config;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import java.util.Collections;
import org.joda.time.DateTime;
import org.springframework.boot.actuate.endpoint.http.ActuatorMediaType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.datetime.joda.DateTimeFormatterFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * The Web Config
 *
 * @author jeffersonlupinacci
 */
@Configuration
public class WebConfig {

  /**
   * @return Actuator Converter
   */
  @Bean
  public MappingJackson2HttpMessageConverter actuatorHttpMessageConverter() {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(Jackson2ObjectMapperBuilder.json().featuresToDisable(WRITE_DATES_AS_TIMESTAMPS).build());
    converter.setSupportedMediaTypes(Collections.singletonList(MediaType.parseMediaType(ActuatorMediaType.V2_JSON)));
    return converter;
  }

  /**
   * @return the Jackson Joda Time Module
   */
  @Bean
  public Module jacksonJodaModule() {
    JodaModule module = new JodaModule();
    DateTimeFormatterFactory formatterFactory = new DateTimeFormatterFactory();
    formatterFactory.setIso(ISO.DATE);
    module.addSerializer(DateTime.class, new DateTimeSerializer(
        new JacksonJodaDateFormat(formatterFactory.createDateTimeFormatter()
            .withZoneUTC())));
    return module;
  }


}
