package com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.rabbitMQ.RabbitMQListenerContainerGroup;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

/**
 * The Delete Binding Command
 *
 * @author jeffersonlupinacci
 */
public class RabbitMQDeleteListenerCommand extends BaseCommand<Boolean> {

  private final String listenerName;

  /**
   * Default Constructor
   *
   * @param listenerName the Listener Name
   */
  public RabbitMQDeleteListenerCommand(String listenerName) {
    super();
    this.listenerName = listenerName;
  }

  /**
   * Execute the Command
   *
   * @return the Boolean Check
   */
  @Override
  protected Boolean process() throws CommandExecuteException {

    Validate.isTrue(StringUtils.isNotEmpty(this.listenerName), "Invalid Listener Name");

    // All registered listeners
    RabbitMQListenerContainerGroup listenerGroup = SpringContext.getBean(RabbitMQListenerContainerGroup.class);

    // Already exists?
    if (null == listenerGroup.getListener(this.listenerName)) {
      throw new CommandExecuteException("Listener doesn't exist");
    } else {
      listenerGroup.removeListener(this.listenerName);
    }

    return true;
  }

}
