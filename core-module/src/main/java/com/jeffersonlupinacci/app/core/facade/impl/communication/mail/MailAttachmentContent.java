package com.jeffersonlupinacci.app.core.facade.impl.communication.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.InputStreamSource;

/**
 * The Mail Attachment Content
 *
 * @author jeffersonlupinacci
 */
@Getter
@Setter
public class MailAttachmentContent {

  private final InputStreamSource inputStreamSource;
  private final String contentType;
  private final String attachmentName;

  /**
   * Default Constructor
   *
   * @param attachmentName the Attachment Name
   * @param inputStreamSource the Input Stream Source
   * @param contentType the Content Type
   */
  public MailAttachmentContent(String attachmentName, InputStreamSource inputStreamSource, String contentType) {
    this.attachmentName = attachmentName;
    this.inputStreamSource = inputStreamSource;
    this.contentType = contentType;
  }

}
