package com.jeffersonlupinacci.app.core.facade.impl.communication.mail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.springframework.core.io.InputStreamSource;

/**
 * @author jeffersonlu
 */
public class MailComposition {

  private String from;
  private String to;
  private String bcc;
  private String cc;

  private String subject;

  private String htmlTemplate;
  private Locale locale;

  private Map<String, Object> parameters;
  private Collection<MailAttachmentContent> attachments;
  private Collection<MailAttachmentContent> inlineAttachments;

  private MailComposition(Builder builder) {
    from = builder.from;
    to = builder.to;
    bcc = builder.bcc;
    cc = builder.cc;
    subject = builder.subject;
    htmlTemplate = builder.htmlTemplate;
    locale = builder.locale;
    parameters = builder.parameters;
    attachments = builder.attachments;
    inlineAttachments = builder.inlineAttachments;
  }

  public static Builder builder() {
    return new Builder();
  }

  /**
   * @return the Locale
   */
  public Locale getLocale() {
    return locale;
  }

  /**
   * @return the Map of Parameters
   */
  public Map<String, Object> getParameters() {
    return Collections.unmodifiableMap(parameters);
  }

  /**
   * @return the Attachments
   */
  public Collection<MailAttachmentContent> getAttachments() {
    return Collections.unmodifiableCollection(attachments);
  }

  /**
   * @return the Inline Attachments
   */
  public Collection<MailAttachmentContent> getInlineAttachments() {
    return Collections.unmodifiableCollection(inlineAttachments);
  }

  /**
   * @return the HTML template
   */
  public String getHtmlTemplate() {
    return htmlTemplate;
  }

  /**
   * @return the Subject
   */
  public String getSubject() {
    return subject;
  }

  /**
   * @return the From
   */
  public String getFrom() {
    return from;
  }

  /**
   * @return the To
   */
  public String getTo() {
    return to;
  }

  /**
   * @return the Bcc
   */
  public String getBcc() {
    return bcc;
  }

  /**
   * @return the CC
   */
  public String getCc() {
    return cc;
  }

  /**
   * A Mail Composition Builder
   */
  public static final class Builder {

    private String from;
    private String to;
    private String bcc;
    private String cc;
    private String subject;
    private String htmlTemplate;
    private Locale locale;
    private Map<String, Object> parameters;
    private Collection<MailAttachmentContent> attachments;
    private Collection<MailAttachmentContent> inlineAttachments;

    private Builder() {
      this.parameters = new HashMap<>();
      this.attachments = new ArrayList<>();
      this.inlineAttachments = new ArrayList<>();
      this.locale = Locale.US;
      this.htmlTemplate = "";
    }

    /**
     * @param from the From
     * @return this
     */
    public Builder withFrom(String from) {
      this.from = from;
      return this;
    }

    /**
     * @param to the To
     * @return this
     */
    public Builder withTo(String to) {
      this.to = to;
      return this;
    }

    /**
     * @param bcc the Bcc
     * @return this
     */
    public Builder withBcc(String bcc) {
      this.bcc = bcc;
      return this;
    }

    /**
     * @param cc the Cc
     * @return this
     */
    public Builder withCc(String cc) {
      this.cc = cc;
      return this;
    }

    /**
     * @param subject the Subject
     * @return this
     */
    public Builder withSubject(String subject) {
      this.subject = subject;
      return this;
    }

    /**
     * @param htmlTemplate the Html Template
     * @return this
     */
    public Builder withHtmlTemplate(String htmlTemplate) {
      this.htmlTemplate = htmlTemplate;
      return this;
    }

    /**
     * @param locale the Locale
     * @return this
     */
    public Builder withLocale(Locale locale) {
      this.locale = locale;
      return this;
    }

    /**
     * Add the parameter
     *
     * @param key the Parameter Key
     * @param value the Parameter Value
     */
    public Builder addParameters(String key, Object value) {
      this.parameters.put(key, value);
      return this;
    }

    /**
     * Add the Attachment
     *
     * @param filename the Filename
     * @param inputStreamSource the Binary Input Stream Source
     * @param contentType the Content Type
     */
    public void addAttachment(String filename, InputStreamSource inputStreamSource, String contentType) {
      this.attachments.add(new MailAttachmentContent(filename, inputStreamSource, contentType));
    }

    /**
     * Add the Inline Attachment
     *
     * @param filename the Filename
     * @param inputStreamSource the Binary Input Stream Source
     * @param contentType the Content Type
     */
    public void addInlineAttachment(String filename, InputStreamSource inputStreamSource, String contentType) {
      this.inlineAttachments.add(new MailAttachmentContent(filename, inputStreamSource, contentType));
    }

    /**
     * Create a new Instance of Mail Composition
     *
     * @return the Instance of Mail Composition
     */
    public MailComposition build() {
      return new MailComposition(this);
    }

  }
}


