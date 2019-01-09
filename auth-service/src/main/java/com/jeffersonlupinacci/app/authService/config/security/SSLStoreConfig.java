package com.jeffersonlupinacci.app.authService.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.commons.lang.ArrayUtils;

/**
 * The SSL KeyStore Configuration
 *
 * @author jeffersonlupinacci
 */
public class SSLStoreConfig {

  public static void configure(String... args) throws IOException {

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
        configFile = new File(SSLStoreConfig.class.getClassLoader().getResource("application.yml").toURI());
      } catch (URISyntaxException e) {
      }
    }

    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    SecurityParameter securityParameter = mapper.readValue(configFile, SecurityParameter.class);

    System.setProperty("javax.net.ssl.keyStore", securityParameter.keyStore);
    System.setProperty("javax.net.ssl.keyStorePassword", securityParameter.keyStorePassword);
    System.setProperty("javax.net.ssl.trustStore", securityParameter.trustStore);
    System.setProperty("javax.net.ssl.trustStorePassword", securityParameter.trustStorePassword);

  }

}
