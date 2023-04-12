package ru.otus.csv.service;

import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import ru.otus.csv.model.Question;

/**
 * Reads from and writes to console.
 */
@Service
public class ConsoleIoService implements IoService {
  private final Scanner reader = new Scanner(System.in);

  @Override
  public int readInt() {
    return Integer.parseInt(reader.nextLine());
  }

  @Override
  public String readString() {
    return reader.nextLine();
  }

  @Override
  public void writeQuestion(Question question) {
    System.out.println(question.getQuestion());
    List<String> options = question.getOptions();
    for (int i = 0; i < options.size(); i++) {
      System.out.println(i + " " + options.get(i));
    }
  }

  @Override
  public void writeLine(String line) {
    System.out.println(line);
  }

  @Override
  public void write(String value) {
    System.out.print(value);
  }
}
