package ru.otus.csv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Repository;
import ru.otus.csv.dao.source.Source;
import ru.otus.csv.mapper.QuestionMapper;
import ru.otus.csv.model.Question;

/**
 * Reads questions from csv file.
 */
@Repository
public class ResourceQuestionsDao implements QuestionsDao {
  private static final String SEPARATOR = ";";

  private final QuestionMapper<String> mapper;
  private final Source source;

  public ResourceQuestionsDao(
          QuestionMapper<String> mapper,
          Source source) {
    this.mapper = mapper;
    this.source = source;
  }

  @Override
  public List<Question> findQuestions(String code, Locale locale) {
    List<String> rawQuestions = getRawQuestions(code, locale);

    List<Question> questions = new ArrayList<>();
    for (String rawQuestion : rawQuestions) {
      questions.add(mapper.map(rawQuestion));
    }
    return questions;
  }

  @Override
  public String getMessage(String code, Locale locale) {
    return source.getMessage(code, locale);
  }

  @Override
  public String getMessageFromTemplate(String code, String[] params, Locale locale) {
    return source.getMessageFromTemplate(code, params, locale);
  }

  private List<String> getRawQuestions(String code, Locale locale) {
    String message = source.getMessage(code, locale);
    return List.of(message.split(SEPARATOR));
  }
}
