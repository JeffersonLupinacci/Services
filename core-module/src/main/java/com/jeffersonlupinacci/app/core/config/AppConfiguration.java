package com.jeffersonlupinacci.app.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.jeffersonlupinacci.app.core.config.mapper.MailParameter;
import com.jeffersonlupinacci.app.core.config.mapper.SecurityParameter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.commons.lang.ArrayUtils;

/**
 * The Core App Configuration
 *
 * @author jeffersonlupinacci
 */
public class AppConfiguration {

  public static SecurityParameter SSL_SECURITY;
  public static MailParameter MAIL;

  public static void define(String[] args) throws IOException {

    File configFile = null;
    if (!ArrayUtils.isEmpty(args)) {
      for (String arg : args) {
        if (arg.startsWith("-Dspring.config.location=") || arg.startsWith("--spring.config.location=")) {
          configFile = new File(arg.substring(arg.lastIndexOf("=") + 1));
          break;
        }
      }
    }

    if (null == configFile) {
      try {
        configFile = new File(AppConfiguration.class.getClassLoader().getResource("application.yml").toURI());
      } catch (URISyntaxException e) {
      }
    }

    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    SSL_SECURITY = mapper.readValue(configFile, SecurityParameter.class);
    MAIL = mapper.readValue(configFile, MailParameter.class);

    System.setProperty("javax.net.ssl.keyStore", AppConfiguration.SSL_SECURITY.getKeyStore());
    System.setProperty("javax.net.ssl.keyStorePassword", AppConfiguration.SSL_SECURITY.getKeyStorePassword());
    System.setProperty("javax.net.ssl.trustStore", AppConfiguration.SSL_SECURITY.getTrustStore());
    System.setProperty("javax.net.ssl.trustStorePassword", AppConfiguration.SSL_SECURITY.getTrustStorePassword());

  }

}
