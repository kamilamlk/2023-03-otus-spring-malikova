package ru.otus.csv.mapper;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.otus.csv.model.Question;

/**
 * Maps string object to question object.
 */
@Component
public class StringToQuestionMapper implements QuestionMapper<String> {
  private static final int QUESTION_INDEX = 0;
  private static final int FIRST_ANSWER_INDEX = 1;
  private static final String QUESTION_SEPARATER = ", ";

  @Override
  public Question map(String questionLine) {
    List<String> questionArray = Arrays.asList(questionLine.split(QUESTION_SEPARATER));
    String question = questionArray.get(QUESTION_INDEX);
    List<String> options = questionArray.subList(FIRST_ANSWER_INDEX, questionArray.size() - 1);
    String correctAnswer = questionArray.get(questionArray.size() - 1);

    return new Question(question, options, correctAnswer);
  }
}
