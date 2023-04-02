package ru.otus.csv.mapper;

import ru.otus.csv.model.Question;

/**
 * Maps value to Question.
 */
public interface QuestionMapper<T> {
  Question map(T value);
}
