package ru.otus.csv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import ru.otus.csv.mapper.QuestionMapper;
import ru.otus.csv.model.Question;

/**
 * Reads questions from csv file.
 */
@Repository
public class PropertiesQuestionsDao implements QuestionsDao {
  private static final String SEPARATOR = ";";

  private final QuestionMapper<String> mapper;
  private final MessageSource messageSource;

  public PropertiesQuestionsDao(
          QuestionMapper<String> mapper,
          MessageSource messageSource) {
    this.mapper = mapper;
    this.messageSource = messageSource;
  }

  @Override
  public List<Question> findQuestions(String code, Locale locale) {
    String message = messageSource.getMessage(code, null, locale);
    List<String> rawQuestions = List.of(message.split(SEPARATOR));

    List<Question> questions = new ArrayList<>();
    for (String rawQuestion : rawQuestions) {
      questions.add(mapper.map(rawQuestion));
    }
    return questions;
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
