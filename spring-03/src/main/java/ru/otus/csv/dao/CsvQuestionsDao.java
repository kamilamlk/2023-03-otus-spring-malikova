package ru.otus.csv.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.csv.mapper.QuestionMapper;
import ru.otus.csv.model.Question;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads questions from csv file.
 */
@Repository
public class CsvQuestionsDao implements QuestionsDao {
  private final String csvFileName;
  private final QuestionMapper<String> mapper;

  public CsvQuestionsDao(
          @Value("${question.csvFilename}") String csvFileName,
          QuestionMapper<String> mapper
  ) {
    this.csvFileName = csvFileName;
    this.mapper = mapper;
  }

  @Override
  public List<Question> findQuestions() {
    try (InputStream is = getFileFromResourceAsStream(csvFileName)) {
      Scanner scanner = new Scanner(is);
      List<Question> questions = new ArrayList<>();
      while (scanner.hasNext()) {
        String questionLine = scanner.nextLine();
        questions.add(mapper.map(questionLine));
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
}
