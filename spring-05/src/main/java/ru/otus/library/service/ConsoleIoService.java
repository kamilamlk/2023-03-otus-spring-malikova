package ru.otus.library.service;

import org.springframework.stereotype.Service;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Genre;

/**
 * Reads from and writes to console.
 */
@Service
public class ConsoleIoService implements IoService {
  @Override
  public void writeLine(String line) {
    System.out.println(line);
  }

  @Override
  public void writeBook(Book book) {
    writeLine(String.format("%d. %s", book.getId(), book.getTitle()));
    writeLine(String.format("   %s", book.getAuthor().getName()));
    writeLine(String.format("   %s", book.getGenre().getName()));
    writeLine(String.format("   Published on %d", book.getPublicationYear()));
  }

  @Override
  public void writeAuthor(Author author) {
    writeLine(String.format("%d. %s", author.getId(), author.getName()));
  }

  @Override
  public void writeGenre(Genre genre) {
    writeLine(String.format("%d. %s", genre.getId(), genre.getName()));
  }
}
