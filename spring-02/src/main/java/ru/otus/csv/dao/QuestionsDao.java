package ru.otus.csv.dao;

import ru.otus.csv.model.Question;
import java.util.List;

/**
 * Reads questions from a datasource.
 */
public interface QuestionsDao {
  List<Question> findQuestions();
}
