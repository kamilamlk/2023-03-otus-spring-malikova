package ru.otus.csv.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;
import ru.otus.csv.dao.QuestionsDao;
import ru.otus.csv.model.Question;

/**
 * Prints question to a default output stream.
 */
@Service
public class SimpleQuestionsService implements QuestionsService {
  private static final String ASK_NAME = "How can I call you?";
  private static final String RESPONSE_TEMPLATE = "%s, you have %d correct answers";

  private final QuestionsDao questionsDao;
  private final IoService ioService;

  public SimpleQuestionsService(QuestionsDao questionsDao, IoService ioService) {
    this.questionsDao = questionsDao;
    this.ioService = ioService;
  }

  @Override
  public void quiz() {
    String name = getUsername();
    AtomicInteger correctAnswers = new AtomicInteger();

    List<Question> questions = questionsDao.findQuestions();
    questions.forEach(q -> {
      int selectedOptionId = getAnswerForQuestion(q);
      if (q.isCorrectOption(selectedOptionId)) {
        correctAnswers.getAndIncrement();
      }
    });

    ioService.writeLine(String.format(RESPONSE_TEMPLATE, name, correctAnswers.get()));
  }

  private String getUsername() {
    ioService.writeLine(ASK_NAME);
    return ioService.readString();
  }

  private int getAnswerForQuestion(Question q) {
    ioService.writeQuestion(q);
    ioService.write("Your answer: ");
    return ioService.readInt();
  }
}
