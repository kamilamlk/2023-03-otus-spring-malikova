package ru.otus.csv.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.csv.model.Question;
import java.util.List;

public class CsvQuestionsDaoTest {
  private static final String CSV_FILE_NAME = "questions.csv";
  @Test
  void testFindQuestions() {
      CsvQuestionsDao dao = new CsvQuestionsDao(CSV_FILE_NAME);

      List<Question> questions = dao.findQuestions();

      Assertions.assertEquals(5, questions.size());
  }
}
