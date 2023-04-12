package ru.otus.csv.config;

import java.util.Locale;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Gets quiz properties from application.properties.
 */
@ConfigurationProperties(prefix = "application.quiz")
public class QuizProperties {
  private String questionsCode;
  private String nameCode;
  private String resultCode;
  private String answerCode;
  private Locale locale;

  public String getQuestionsCode() {
    return questionsCode;
  }

  public void setQuestionsCode(String questionsCode) {
    this.questionsCode = questionsCode;
  }

  public String getNameCode() {
    return nameCode;
  }

  public void setNameCode(String nameCode) {
    this.nameCode = nameCode;
  }

  public String getResultCode() {
    return resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public String getAnswerCode() {
    return answerCode;
  }

  public void setAnswerCode(String answerCode) {
    this.answerCode = answerCode;
  }

  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }
}
