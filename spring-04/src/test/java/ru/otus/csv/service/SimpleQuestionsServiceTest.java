package ru.otus.csv.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.csv.config.QuizProperties;
import ru.otus.csv.dao.QuestionsDao;
import ru.otus.csv.model.Question;
import java.util.List;
import java.util.Locale;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class SimpleQuestionsServiceTest {
  private static final String EMPTY_STRING = "";
  private static final String QUESTIONS = "quiz.questions";
  private static final String NAME = "quiz.name";
  private static final String RESULT = "quiz.result";
  private static final String ANSWER = "quiz.answer";
  private static final String USERNAME = "User";
  private static final int SELECTED_ANSWER = 0;

  @MockBean
  private QuestionsDao questionsDao;
  @MockBean
  private IoService ioService;
  @MockBean
  private QuizProperties properties;
  @Autowired
  private SimpleQuestionsService questionsService;

  @Test
  void testQuiz() {
    List<Question> questions = List.of(
            new Question("In which Italian city can you find the Colosseum?", List.of("Rome", "Venice", "Naples", "Milan"), "Rome"),
            new Question("Who wrote Catch-22?", List.of("Joseph Heller", "Ernest Hemingway", "Charles Dickens", "Mark Twain"), "Joseph Heller")
    );

    int correctAnswers = 2;

    String[] expectedResponseArray = new String[]{USERNAME, String.valueOf(correctAnswers)};

    doReturn(questions).when(questionsDao).findQuestions(anyString(), any());
    doReturn(EMPTY_STRING).when(questionsDao).getMessage(anyString(), any());

    doNothing().when(ioService).writeLine(anyString());
    doNothing().when(ioService).writeQuestion(any());
    doNothing().when(ioService).write(anyString());

    doReturn(USERNAME).when(ioService).readString();
    doReturn(SELECTED_ANSWER).when(ioService).readInt();

    doReturn(Locale.ENGLISH).when(properties).getLocale();
    doReturn(RESULT).when(properties).getResultCode();
    doReturn(ANSWER).when(properties).getAnswerCode();
    doReturn(NAME).when(properties).getNameCode();
    doReturn(QUESTIONS).when(properties).getQuestionsCode();

    questionsService.quiz();

    verify(questionsDao, times(1))
            .getMessageFromTemplate(RESULT, expectedResponseArray, Locale.ENGLISH);
  }
}
