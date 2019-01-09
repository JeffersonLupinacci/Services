package com.jeffersonlupinacci.app.schedulerService.config.quarts;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The Quartz Data Source Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(2)
@Configuration
@EnableTransactionManagement
public class QuartzDataSourceConfig {

  @Bean(name = "quartzDataSource")
  @ConfigurationProperties(prefix = "scheduler.quartz.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "quaEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean
  entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("quartzDataSource") DataSource dataSource) {
    return builder
        .dataSource(dataSource)
        .packages("com.jeffersonlupinacci.app.schedulerService")
        .persistenceUnit("quartzPersistence")
        .build();
  }

  @Primary
  @Bean(name = "quartzTransactionManager")
  public PlatformTransactionManager transactionManager(@Qualifier("quartzEntityManagerFactory")
      EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

}
