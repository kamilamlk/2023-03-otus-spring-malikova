package ru.otus.csv.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.csv.dao.QuestionsDao;
import ru.otus.csv.model.Question;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimpleQuestionsServiceTest {
  private static final String RESPONSE_TEMPLATE = "%s, you have %d correct answers";
  private static final String USERNAME = "User";
  private static final int SELECTED_ANSWER = 0;

  @Mock
  private QuestionsDao questionsDao;
  @Mock
  private IoService ioService;

  @InjectMocks
  private SimpleQuestionsService questionsService;

  @Test
  void testQuiz() {
    List<Question> questions = List.of(
            new Question("In which Italian city can you find the Colosseum?", List.of("Rome", "Venice", "Naples", "Milan"), "Rome"),
            new Question("Who wrote Catch-22?", List.of("Joseph Heller", "Ernest Hemingway", "Charles Dickens", "Mark Twain"), "Joseph Heller")
    );
    int correctAnswers = 2;
    String expectingString = String.format(RESPONSE_TEMPLATE, USERNAME, correctAnswers);

    when(questionsDao.findQuestions())
            .thenReturn(questions);

    doNothing().when(ioService).writeLine(anyString());
    doNothing().when(ioService).writeQuestion(any());
    doNothing().when(ioService).write(anyString());
    when(ioService.readString())
            .thenReturn(USERNAME);
    when(ioService.readInt())
            .thenReturn(SELECTED_ANSWER);

    questionsService.quiz();

    verify(ioService, times(1)).writeLine(expectingString);
  }
}
