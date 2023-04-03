package ru.otus.csv;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.csv.service.QuestionsService;

/**
 * Main class.
 */
public class Main {
  /**
   * Entry point to the application
   * Creates ApplicationContext. Gets required beans and runs the application.
   *
   * @param args user arguments
   *
   */
  public static void main(String[] args) {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
    QuestionsService questionsService = ctx.getBean(QuestionsService.class);
    questionsService.showQuestions();
  }
}