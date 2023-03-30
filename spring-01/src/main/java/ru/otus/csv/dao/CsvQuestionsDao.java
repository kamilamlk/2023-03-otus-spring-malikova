package ru.otus.csv.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import ru.otus.csv.model.Question;

/**
 * Reads questions from csv file.
 */
public class CsvQuestionsDao implements QuestionsDao {
  private static final int QUESTION_INDEX = 0;
  private static final int FIRST_ANSWER_INDEX = 1;
  private static final String QUESTION_SEPARATER = ",";

  private final String csvFileName;

  public CsvQuestionsDao(String csvFileName) {
    this.csvFileName = csvFileName;
  }

  @Override
  public List<Question> findQuestions() {
    try (InputStream is = getFileFromResourceAsStream(csvFileName)) {
      Scanner scanner = new Scanner(is);
      List<Question> questions = new ArrayList<>();
      while (scanner.hasNext()) {
        String questionLine = scanner.nextLine();
        questions.add(getQuestion(questionLine));
      }
      return questions;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private InputStream getFileFromResourceAsStream(String fileName) {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
    if (inputStream == null) {
      throw new IllegalArgumentException("file not found! " + fileName);
    } else {
      return inputStream;
    }
  }

  private Question getQuestion(String questionLine) {
    List<String> questionArray = Arrays.asList(questionLine.split(QUESTION_SEPARATER));
    String question = questionArray.get(QUESTION_INDEX);
    List<String> options = questionArray.subList(FIRST_ANSWER_INDEX, questionArray.size());
    return new Question(question, options);
  }
}
