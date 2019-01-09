package com.jeffersonlupinacci.app.schedulerService.config;

import com.jeffersonlupinacci.app.core.config.WebConfig;
import com.jeffersonlupinacci.app.core.config.disable.DisableEurekaConfig;
import com.jeffersonlupinacci.app.core.config.enable.EnableEurekaConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

@Order(0)
@Configuration()
@Import({WebConfig.class,
    EnableEurekaConfig.class,
    DisableEurekaConfig.class})

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class})

public class LoadConfiguration {

}

