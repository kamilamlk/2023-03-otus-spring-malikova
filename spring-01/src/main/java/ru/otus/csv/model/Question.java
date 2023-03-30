package ru.otus.csv.model;

import java.util.List;

/**
 * Test question.
 */
public class Question {
  private final String question;
  private final List<String> options;

  public Question(String question, List<String> options) {
    this.question = question;
    this.options = options;
  }

  public String getQuestion() {
    return question;
  }

  public List<String> getOptions() {
    return options;
  }
}
