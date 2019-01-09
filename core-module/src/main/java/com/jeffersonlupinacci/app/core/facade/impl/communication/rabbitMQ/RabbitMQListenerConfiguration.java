package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ;

import org.springframework.amqp.core.AcknowledgeMode;

/**
 * The RabbitMQ Listener Configuration/Builder
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQListenerConfiguration {

  private final String queueName;
  private final Boolean exposeListenerChannel;
  private final Integer maxConcurrentConsumers;
  private final Integer concurrentConsumers;
  private final AcknowledgeMode acknowledgeMode;
  private final Boolean channelTransacted;

  private RabbitMQListenerConfiguration(RabbitMQListenerConfigurationBuilder builder) {
    this.queueName = builder.queueName;
    this.exposeListenerChannel = builder.exposeListenerChannel;
    this.maxConcurrentConsumers = builder.maxConcurrentConsumers;
    this.concurrentConsumers = builder.concurrentConsumers;
    this.acknowledgeMode = builder.acknowledgeMode;
    this.channelTransacted = builder.channelTransacted;
  }

  @SuppressWarnings("unused")
  public static RabbitMQListenerConfigurationBuilder builder() {
    return new RabbitMQListenerConfigurationBuilder();
  }

  public String getQueueName() {
    return queueName;
  }

  public Boolean getExposeListenerChannel() {
    return exposeListenerChannel;
  }

  public Integer getMaxConcurrentConsumers() {
    return maxConcurrentConsumers;
  }

  public Integer getConcurrentConsumers() {
    return concurrentConsumers;
  }

  public AcknowledgeMode getAcknowledgeMode() {
    return acknowledgeMode;
  }

  public Boolean getChannelTransacted() {
    return channelTransacted;
  }

  public static final class RabbitMQListenerConfigurationBuilder {

    private String queueName;
    private Boolean exposeListenerChannel;
    private Integer maxConcurrentConsumers;
    private Integer concurrentConsumers;
    private AcknowledgeMode acknowledgeMode;
    private Boolean channelTransacted;

    private RabbitMQListenerConfigurationBuilder() {
    }

    @SuppressWarnings("unused")
    public RabbitMQListenerConfiguration build() {
      return new RabbitMQListenerConfiguration(this);
    }

    @SuppressWarnings("unused")
    public RabbitMQListenerConfigurationBuilder queueName(String queueName) {
      this.queueName = queueName;
      return this;
    }

    @SuppressWarnings("unused")
    public RabbitMQListenerConfigurationBuilder exposeListenerChannel(Boolean exposeListenerChannel) {
      this.exposeListenerChannel = exposeListenerChannel;
      return this;
    }

    @SuppressWarnings("unused")
    public RabbitMQListenerConfigurationBuilder maxConcurrentConsumers(Integer maxConcurrentConsumers) {
      this.maxConcurrentConsumers = maxConcurrentConsumers;
      return this;
    }

    @SuppressWarnings("unused")
    public RabbitMQListenerConfigurationBuilder concurrentConsumers(Integer concurrentConsumers) {
      this.concurrentConsumers = concurrentConsumers;
      return this;
    }

    @SuppressWarnings("unused")
    public RabbitMQListenerConfigurationBuilder acknowledgeMode(AcknowledgeMode acknowledgeMode) {
      this.acknowledgeMode = acknowledgeMode;
      return this;
    }

    @SuppressWarnings("unused")
    public RabbitMQListenerConfigurationBuilder channelTransacted(Boolean channelTransacted) {
      this.channelTransacted = channelTransacted;
      return this;
    }
  }
}
