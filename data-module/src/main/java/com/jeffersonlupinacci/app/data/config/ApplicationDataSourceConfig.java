package com.jeffersonlupinacci.app.data.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The Application Data Source Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(1)
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.jeffersonlupinacci.app.data.repository",
    entityManagerFactoryRef = "appEntityManagerFactory",
    transactionManagerRef = "appTransactionManager")
@ComponentScan(basePackages = {"com.jeffersonlupinacci.app.data.*"})
@EntityScan("com.jeffersonlupinacci.app.data.domain.*")
public class ApplicationDataSourceConfig {

  @Primary
  @Bean(name = "appDataSource")
  @ConfigurationProperties(prefix = "app.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Primary
  @Bean(name = "appEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("appDataSource") DataSource dataSource) {

    LocalContainerEntityManagerFactoryBean localContainer = new LocalContainerEntityManagerFactoryBean();
    localContainer.setPersistenceProvider(new HibernatePersistenceProvider());
    localContainer.setPersistenceUnitName("appPersistence");
    localContainer.setPackagesToScan("com.jeffersonlupinacci.app.data");
    localContainer.setDataSource(dataSource());
    localContainer.setJpaDialect(new HibernateJpaDialect());
    localContainer.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);

    return localContainer;
  }

  @Primary
  @Bean(name = "appTransactionManager")
  public PlatformTransactionManager transactionManager(@Qualifier("appEntityManagerFactory")
      EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

}
