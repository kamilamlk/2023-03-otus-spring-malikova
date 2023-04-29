package ru.otus.csv.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.csv.mapper.QuestionMapper;
import ru.otus.csv.mapper.StringToQuestionMapper;
import ru.otus.csv.model.Question;
import java.util.List;
import java.util.Locale;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResourceQuestionsDaoTest {
  private final String rawQuestions = "In which Italian city can you find the Colosseum?, Venice, Rome, Naples, Milan, Rome;Who wrote Catch-22?, Mark Twain, Ernest Hemingway, Charles Dickens, Joseph Heller, Joseph Heller";

  private final QuestionMapper<String> questionMapper = new StringToQuestionMapper();

  @Mock
  private MessageSource messageSource;

  @Test
  void testFindQuestions() {
    when(messageSource.getMessage(anyString(), any(), any()))
            .thenReturn(rawQuestions);

    ResourceQuestionsDao dao = new ResourceQuestionsDao(questionMapper, messageSource);

    List<Question> questions = dao.findQuestions("question", Locale.ENGLISH);

    Assertions.assertEquals(2, questions.size());
  }
}
