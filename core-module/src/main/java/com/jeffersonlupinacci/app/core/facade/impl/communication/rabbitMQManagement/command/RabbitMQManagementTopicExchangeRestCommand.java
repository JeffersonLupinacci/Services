package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQManagement.RabbitMQRestTemplateFactory;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.TopicExchange;
import java.util.Objects;

/**
 * The Rabbit Topic Exchange Rest Client Command
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQManagementTopicExchangeRestCommand extends BaseCommand<TopicExchange[]> {

  /**
   * Execute the Command
   *
   * @return the TopicExchange[] Array
   */
  @Override
  public TopicExchange[] process() {

    String resourceUrl = SpringContext.getEnvironmentProperty("rabbitmq.exchangeService");
    return Objects.requireNonNull(SpringContext
        .getBean(RabbitMQRestTemplateFactory.class)
        .getObject())
        .getForObject(resourceUrl, TopicExchange[].class);
  }

}
