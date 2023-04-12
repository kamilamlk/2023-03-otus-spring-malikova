package ru.otus.csv.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.csv.mapper.QuestionMapper;
import ru.otus.csv.mapper.StringToQuestionMapper;
import ru.otus.csv.model.Question;
import java.util.List;

public class CsvQuestionsDaoTest {
  private static final String CSV_FILE_NAME = "questions.csv";
  private final QuestionMapper<String> questionMapper = new StringToQuestionMapper();

  @Test
  void testFindQuestions() {
    CsvQuestionsDao dao = new CsvQuestionsDao(CSV_FILE_NAME, questionMapper);

    List<Question> questions = dao.findQuestions();

    Assertions.assertEquals(2, questions.size());
  }
}
