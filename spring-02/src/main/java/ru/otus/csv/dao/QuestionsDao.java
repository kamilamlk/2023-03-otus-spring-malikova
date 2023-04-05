package ru.otus.csv.dao;

import java.util.List;
import ru.otus.csv.model.Question;

/**
 * Reads questions from a datasource.
 */
public interface QuestionsDao {
  List<Question> findQuestions();
}
