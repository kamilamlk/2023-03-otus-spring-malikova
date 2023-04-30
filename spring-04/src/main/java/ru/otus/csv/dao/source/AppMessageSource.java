package ru.otus.csv.dao.source;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * Gets data from message source.
 */
@Component
public class AppMessageSource implements Source {
  private final MessageSource messageSource;

  public AppMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  public String getMessage(String code, Locale locale) {
    return messageSource.getMessage(code, null, locale);
  }

  @Override
  public String getMessageFromTemplate(String code, String[] params, Locale locale) {
    return messageSource.getMessage(code, params, locale);
  }
}
