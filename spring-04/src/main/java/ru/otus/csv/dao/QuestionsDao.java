package ru.otus.csv.dao;

import java.util.List;
import java.util.Locale;
import ru.otus.csv.model.Question;

/**
 * Reads questions from a datasource.
 */
public interface QuestionsDao {
  List<Question> findQuestions(String code, Locale locale);

  String getMessage(String code, Locale locale);

  String getMessageFromTemplate(String code, String[] params, Locale locale);
}
