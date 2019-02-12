package com.jeffersonlupinacci.app.core.config;

import static com.jeffersonlupinacci.app.core.config.AppConfiguration.MAIL;

import java.io.IOException;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

/**
 * The Spring Mail Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(1)
@Configuration
public class SpringMailConfig {

  public static final String ENCODING = "UTF-8";


  /**
   * @return The Default Java Mail Sender
   * @throws IOException the IO Exception
   */
  @Bean
  public JavaMailSender defaultSender() throws IOException {

    final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    mailSender.setHost(MAIL.getHost());
    mailSender.setPort(MAIL.getPort());
    mailSender.setProtocol(MAIL.getProtocol());
    mailSender.setUsername(MAIL.getUsername());
    mailSender.setPassword(MAIL.getPassword());

    mailSender.setJavaMailProperties(MAIL.getProperties());

    return mailSender;
  }

  /**
   * @return The Email Message Source - Resources
   */
  @Bean
  public ResourceBundleMessageSource emailMessageSource() {
    final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("i18n/messages");
    return messageSource;
  }

  /**
   * @return The Email Template Engine
   */
  @Bean
  @Qualifier("mailTemplate")
  public TemplateEngine emailTemplateEngine() {
    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.addTemplateResolver(textTemplateResolver());
    templateEngine.addTemplateResolver(htmlTemplateResolver());
    templateEngine.addTemplateResolver(stringTemplateResolver());
    templateEngine.setTemplateEngineMessageSource(emailMessageSource());
    return templateEngine;
  }

  /**
   * @return The Text Template Resolver
   */
  private ITemplateResolver textTemplateResolver() {
    final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setOrder(Integer.valueOf(1));
    templateResolver.setResolvablePatterns(Collections.singleton("text/*"));
    templateResolver.setPrefix("/mail/");
    templateResolver.setSuffix(".txt");
    templateResolver.setTemplateMode(TemplateMode.TEXT);
    templateResolver.setCharacterEncoding(ENCODING);
    templateResolver.setCacheable(false);
    return templateResolver;
  }

  /**
   * @return The Html Template Resolver
   */
  private ITemplateResolver htmlTemplateResolver() {
    final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setOrder(Integer.valueOf(2));
    templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
    templateResolver.setPrefix("/mail/");
    templateResolver.setSuffix(".mail.html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    templateResolver.setCharacterEncoding(ENCODING);
    templateResolver.setCacheable(false);
    return templateResolver;
  }

  /**
   * @return The String Template Resolver
   */
  private ITemplateResolver stringTemplateResolver() {
    final StringTemplateResolver templateResolver = new StringTemplateResolver();
    templateResolver.setOrder(Integer.valueOf(3));
    templateResolver.setTemplateMode("HTML");
    templateResolver.setTemplateMode("TEXT");
    templateResolver.setCacheable(false);
    return templateResolver;
  }

}
