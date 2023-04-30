package ru.otus.csv.dao.source;

import java.util.Locale;

/**
 * Source to get data from.
 */
public interface Source {
  String getMessage(String code, Locale locale);

  String getMessageFromTemplate(String code, String[] params, Locale locale);
}
