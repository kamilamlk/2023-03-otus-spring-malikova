package ru.otus.csv.mapper;

import ru.otus.csv.model.Question;
import java.util.Arrays;
import java.util.List;

/**
 * Maps string object to question object.
 */
public class StringToQuestionMapper implements QuestionMapper<String> {
  private static final int QUESTION_INDEX = 0;
  private static final int FIRST_ANSWER_INDEX = 1;
  private static final String QUESTION_SEPARATER = ", ";

  @Override
  public Question map(String questionLine) {
    List<String> questionArray = Arrays.asList(questionLine.split(QUESTION_SEPARATER));
    String question = questionArray.get(QUESTION_INDEX);
    List<String> options = questionArray.subList(FIRST_ANSWER_INDEX, questionArray.size());
    return new Question(question, options);
  }
}
