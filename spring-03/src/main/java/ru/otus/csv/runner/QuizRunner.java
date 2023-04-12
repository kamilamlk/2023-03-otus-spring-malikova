package ru.otus.csv.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.csv.service.QuestionsService;

/**
 * Runs QuestionsService after the Spring application context has been initialized.
 */
@Component
public class QuizRunner implements CommandLineRunner {
  private final QuestionsService questionsService;

  public QuizRunner(QuestionsService questionsService) {
    this.questionsService = questionsService;
  }

  @Override
  public void run(String... args) {
    questionsService.quiz();
  }
}
