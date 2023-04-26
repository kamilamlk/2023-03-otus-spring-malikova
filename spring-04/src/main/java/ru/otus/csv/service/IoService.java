package ru.otus.csv.service;

import ru.otus.csv.model.Question;

/**
 * Reads and writes into IO.
 */
public interface IoService {
  int readInt();

  String readString();

  void writeQuestion(Question question);

  void writeLine(String line);

  void write(String value);
}
