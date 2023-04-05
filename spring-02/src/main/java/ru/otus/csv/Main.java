package ru.otus.csv;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.csv.service.QuestionsService;

/**
 * Main class.
 */
@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan
public class Main {
  /**
   * Entry point to the application
   * Creates ApplicationContext. Gets required beans and runs the application.
   *
   * @param args user arguments
   *
   */
  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
    QuestionsService questionsService = ctx.getBean(QuestionsService.class);
    questionsService.quiz();
  }
}