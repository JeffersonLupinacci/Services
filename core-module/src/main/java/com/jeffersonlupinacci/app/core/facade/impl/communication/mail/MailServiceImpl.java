package com.jeffersonlupinacci.app.core.facade.impl.communication.mail;

import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.impl.communication.mail.command.SendMailCommand;
import com.jeffersonlupinacci.app.core.facade.interfaces.comunication.MailService;

/**
 * The Mail Service Implementation
 *
 * @author jeffersonlupinacci
 */
public class MailServiceImpl implements MailService {

  @Override
  public Boolean sendMessage(MailComposition mailComposition) throws CommandExecuteException {
    return new SendMailCommand(mailComposition).execute();
  }
}


