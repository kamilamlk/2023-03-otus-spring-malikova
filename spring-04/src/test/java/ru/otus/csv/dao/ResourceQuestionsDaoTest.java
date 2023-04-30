package ru.otus.csv.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.csv.dao.source.Source;
import ru.otus.csv.model.Question;
import java.util.List;
import java.util.Locale;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class ResourceQuestionsDaoTest {
  private final String rawQuestions = "In which Italian city can you find the Colosseum?, Venice, Rome, Naples, Milan, Rome;Who wrote Catch-22?, Mark Twain, Ernest Hemingway, Charles Dickens, Joseph Heller, Joseph Heller";

  @MockBean
  private Source source;
  @Autowired
  private ResourceQuestionsDao dao;

  @Test
  void testFindQuestions() {
    doReturn(rawQuestions).when(source).getMessage(anyString(), any());

    List<Question> questions = dao.findQuestions("question", Locale.ENGLISH);
    Assertions.assertEquals(2, questions.size());
  }
}
