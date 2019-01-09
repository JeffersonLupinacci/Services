package com.jeffersonlupinacci.app.applicationService.config;

import com.jeffersonlupinacci.app.core.config.ContextConfig;
import com.jeffersonlupinacci.app.core.config.SpringMailConfig;
import com.jeffersonlupinacci.app.core.config.WebConfig;
import com.jeffersonlupinacci.app.core.config.disable.DisableEurekaConfig;
import com.jeffersonlupinacci.app.core.config.disable.DisableRabbitMQConfig;
import com.jeffersonlupinacci.app.core.config.enable.EnableAsyncConfig;
import com.jeffersonlupinacci.app.core.config.enable.EnableEurekaConfig;
import com.jeffersonlupinacci.app.core.config.enable.EnableRabbitMQConfig;
import com.jeffersonlupinacci.app.data.config.ApplicationDataSourceConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

/**
 * Configuration Loader
 *
 * @author jeffersonlupinacci
 */
@Order(0)
@Configuration()
@Import({

    // Web Configuration
    WebConfig.class,

    // Beans Configuration
    ContextConfig.class,

    // Mail Sender Configuration
    SpringMailConfig.class,

    // Async Tasks
    EnableAsyncConfig.class,

    // Rabbit MQ
    EnableRabbitMQConfig.class,
    DisableRabbitMQConfig.class,

    // Eureka
    EnableEurekaConfig.class,
    DisableEurekaConfig.class,

    // Application Data Source
    ApplicationDataSourceConfig.class

})

@EnableAutoConfiguration(exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class,
    UserDetailsServiceAutoConfiguration.class})
public class LoadConfiguration {

}

