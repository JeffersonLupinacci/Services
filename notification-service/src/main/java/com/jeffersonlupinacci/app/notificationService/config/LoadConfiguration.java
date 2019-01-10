package com.jeffersonlupinacci.app.notificationService.config;

import com.jeffersonlupinacci.app.core.config.ContextConfig;
import com.jeffersonlupinacci.app.core.config.WebConfig;
import com.jeffersonlupinacci.app.core.config.disable.DisableEurekaConfig;
import com.jeffersonlupinacci.app.core.config.disable.DisableRabbitMQConfig;
import com.jeffersonlupinacci.app.core.config.enable.EnableEurekaConfig;
import com.jeffersonlupinacci.app.core.config.enable.EnableRabbitMQConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
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

    // Rabbit MQ
    EnableRabbitMQConfig.class,
    DisableRabbitMQConfig.class,

    // Eureka
    EnableEurekaConfig.class,
    DisableEurekaConfig.class,

})

@EnableAutoConfiguration(exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class,
    UserDetailsServiceAutoConfiguration.class})
public class LoadConfiguration {

}

