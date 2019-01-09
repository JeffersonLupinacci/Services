package com.jeffersonlupinacci.app.core.facade.impl.communication.mail.command;

import com.jeffersonlupinacci.app.core.SpringContext;
import com.jeffersonlupinacci.app.core.config.SpringMailConfig;
import com.jeffersonlupinacci.app.core.exception.CommandExecuteException;
import com.jeffersonlupinacci.app.core.facade.BaseCommand;
import com.jeffersonlupinacci.app.core.facade.impl.communication.mail.MailAttachmentContent;
import com.jeffersonlupinacci.app.core.facade.impl.communication.mail.MailComposition;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * The Send Mail Command
 *
 * @author jeffersonlupinacci
 */
public class SendMailCommand extends BaseCommand<Boolean> {

  private final MailComposition mailComposition;

  /**
   * @param mailComposition the Mail Composition
   */
  public SendMailCommand(MailComposition mailComposition) {
    super();
    this.mailComposition = mailComposition;
  }

  /**
   * @return the Return of the command
   */
  @Override
  protected Boolean process() throws CommandExecuteException {

    try {

      final JavaMailSender mailSender = SpringContext.getBean(JavaMailSender.class);
      final TemplateEngine templateEngine = SpringContext.getBean(TemplateEngine.class, "emailTemplateEngine");
      final Context ctx = new Context(mailComposition.getLocale());

      // Passing the parameters
      mailComposition.getParameters().forEach(ctx::setVariable);

      final String htmlContent = templateEngine.process(mailComposition.getHtmlTemplate(), ctx);
      final MimeMessage mimeMessage = mailSender.createMimeMessage();
      final MimeMessageHelper message;

      // Message Compose
      message = new MimeMessageHelper(mimeMessage,
          mailComposition.getAttachments().size() > 0, SpringMailConfig.ENCODING);

      message.setSubject(mailComposition.getSubject());
      message.setFrom(mailComposition.getFrom());
      message.setTo(mailComposition.getTo());
      message.setText(htmlContent, true);

      if (null != mailComposition.getCc()) {
        message.addCc(mailComposition.getCc());
      }

      if (null != mailComposition.getBcc()) {
        message.addBcc(mailComposition.getBcc());
      }

      // Attachment
      for (MailAttachmentContent x : mailComposition.getAttachments()) {
        message.addAttachment(x.getAttachmentName(), x.getInputStreamSource(), x.getContentType());
      }

      // Inline Attachment
      for (MailAttachmentContent x : mailComposition.getInlineAttachments()) {
        message.addInline(x.getAttachmentName(), x.getInputStreamSource(), x.getContentType());
      }

      mailSender.send(mimeMessage);

      return true;

    } catch (MessagingException e) {
      throw new CommandExecuteException(e);
    }

  }

}
