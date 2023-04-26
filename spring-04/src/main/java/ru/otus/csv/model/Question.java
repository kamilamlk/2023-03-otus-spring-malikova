package ru.otus.csv.model;

import java.util.List;

/**
 * Test question.
 */
public class Question {
  private final String question;
  private final List<String> options;
  private final String correctAnswer;

  /**
   * Default constructor.
   */
  public Question(String question, List<String> options, String correctAnswer) {
    this.question = question;
    this.options = options;
    this.correctAnswer = correctAnswer;
  }

  public String getQuestion() {
    return question;
  }

  public List<String> getOptions() {
    return options;
  }

  public String getCorrectAnswer() {
    return correctAnswer;
  }

  public boolean isCorrectOption(int optionId) {
    return options.get(optionId).equals(correctAnswer);
  }
}
