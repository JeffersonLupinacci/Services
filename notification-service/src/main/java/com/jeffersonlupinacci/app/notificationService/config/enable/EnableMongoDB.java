package com.jeffersonlupinacci.app.notificationService.config.enable;

// import com.mongodb.reactivestreams.client.MongoClient;
// import com.mongodb.reactivestreams.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
// import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

/**
 * The MongoDB Configuration
 *
 * @author jeffersonlupinacci
 */
// @Configuration
public class EnableMongoDB {

  @Value("${app.mongoDB.database-name}")
  private String databaseName;

  /**
   * The MongoDB Client
   * @return
   */
//  @Bean
//  public MongoClient mongoClient() {
//    return MongoClients.create();
//  }

  /**
   * The Reactive Mongodb Template
   * @return
   */
//  @Bean
//  public ReactiveMongoTemplate reactiveMongoTemplate() {
//    return new ReactiveMongoTemplate(mongoClient(), databaseName);
//  }

}
