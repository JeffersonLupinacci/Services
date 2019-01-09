package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.RabbitMQListenerContainerGroup;
import com.jeffersonlupinacci.app.core.serializablesDTO.rabbitMQ.Listener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.thymeleaf.util.MapUtils;

/**
 * Get the List of Active Listeners
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQRetrieveListenersCommand extends BaseCommand<Collection<Listener>> {

  /**
   * Execute the Command
   *
   * @return the List of Active Listeners
   */
  @Override
  protected Collection<Listener> process() throws CommandExecuteException {

    RabbitMQListenerContainerGroup listenerGroup = SpringContext.getBean(RabbitMQListenerContainerGroup.class);
    List<Listener> toReturn = new ArrayList<>();

    if (!MapUtils.isEmpty(listenerGroup.getActiveListeners())) {
      listenerGroup.getActiveListeners().forEach((key, value) -> toReturn.add(
          new Listener(
              key,
              value.getQueueNames(),
              value.getListenerId(),
              value.getActiveConsumerCount(),
              value.getAcknowledgeMode())));
    }

    return toReturn;

  }

}
