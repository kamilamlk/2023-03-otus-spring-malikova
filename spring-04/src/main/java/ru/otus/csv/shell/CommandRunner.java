package ru.otus.csv.shell;

import java.util.Locale;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.csv.config.QuizProperties;
import ru.otus.csv.service.QuestionsService;

/**
 * Shell command line runner.
 */
@ShellComponent
public class CommandRunner {
  private final QuestionsService questionsService;
  private final QuizProperties quizProperties;

  public CommandRunner(QuestionsService questionsService, QuizProperties quizProperties) {
    this.questionsService = questionsService;
    this.quizProperties = quizProperties;
  }

  /**
   * Runs quiz.
   */
  @ShellMethod(value = "Quiz command", key = {"q", "quiz"})
  public void quiz() {
    questionsService.quiz();
  }

  /**
   * Updates language.
   *
   * @param language new language.
   */
  @ShellMethod(value = "Change quiz language", key = {"l", "language"})
  public String language(@ShellOption String language) {
    Locale locale = Locale.forLanguageTag(language);
    quizProperties.setLocale(locale);
    return "Language updated";
  }
}
