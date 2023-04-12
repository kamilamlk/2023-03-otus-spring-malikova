package ru.otus.csv.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.otus.csv.config.QuizProperties;
import ru.otus.csv.dao.QuestionsDao;
import ru.otus.csv.model.Question;

/**
 * Prints question to a default output stream.
 */
@Service
public class SimpleQuestionsService implements QuestionsService {
  private final QuestionsDao questionsDao;
  private final IoService ioService;
  private final QuizProperties properties;

  /**
   * Default constructor.
   *
   * @param questionsDao retrieves questions from dataSource
   * @param ioService prints in and reads from the provided stream
   * @param properties keeps quiz related properties
   */
  public SimpleQuestionsService(
          QuestionsDao questionsDao,
          IoService ioService,
          QuizProperties properties) {
    this.questionsDao = questionsDao;
    this.ioService = ioService;
    this.properties = properties;
  }

  @Override
  public void quiz() {
    String name = getUsername();
    int correctAnswers = 0;

    List<Question> questions = questionsDao.findQuestions(
            properties.getQuestionsCode(),
            properties.getLocale()
    );
    for (Question q : questions) {
      int selectedOptionId = getAnswerForQuestion(q);
      if (q.isCorrectOption(selectedOptionId)) {
        correctAnswers++;
      }
    }

    String response = questionsDao.getMessageFromTemplate(
            properties.getResultCode(),
            new String[] {name, String.valueOf(correctAnswers)},
            properties.getLocale()
    );

    ioService.writeLine(response);
  }

  private String getUsername() {
    String questionName = questionsDao.getMessage(
            properties.getNameCode(),
            properties.getLocale()
    );

    ioService.writeLine(questionName);
    return ioService.readString();
  }

  private int getAnswerForQuestion(Question q) {
    String questionAnswer = questionsDao.getMessage(
            properties.getAnswerCode(),
            properties.getLocale()
    );

    ioService.writeQuestion(q);
    ioService.write(questionAnswer);
    return ioService.readInt();
  }
}
