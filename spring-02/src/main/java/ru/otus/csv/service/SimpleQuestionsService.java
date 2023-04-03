package ru.otus.csv.service;

import ru.otus.csv.dao.QuestionsDao;
import ru.otus.csv.model.Question;
import java.util.List;

/**
 * Prints question to a default output stream.
 */
public class SimpleQuestionsService implements QuestionsService {
  private final QuestionsDao questionsDao;

  public SimpleQuestionsService(QuestionsDao questionsDao) {
    this.questionsDao = questionsDao;
  }

  @Override
  public void showQuestions() {
    List<Question> questions = questionsDao.findQuestions();

    questions.forEach(q -> {
      System.out.println(q.getQuestion());
      List<String> options = q.getOptions();
      for (int i = 0; i < options.size(); i++) {
        System.out.println(i + " " + options.get(i));
      }
      System.out.println();
    });
  }
}
