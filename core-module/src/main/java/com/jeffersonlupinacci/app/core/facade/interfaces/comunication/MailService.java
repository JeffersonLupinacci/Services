package com.jeffersonlupinacci.app.core.facade.interfaces.comunication;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.impl.communication.mail.MailComposition;
import org.springframework.stereotype.Service;

/**
 * The Mail Service
 *
 * @author jeffersonlupinacci
 */
@Service
public interface MailService {

  /**
   * Send the Mail Message
   *
   * @param mailComposition the Mail Composition
   * @return the Boolean Check
   * @throws CommandExecuteException the Command Execution Exception
   */
  Boolean sendMessage(MailComposition mailComposition) throws CommandExecuteException;

}
