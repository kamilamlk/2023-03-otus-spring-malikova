package ru.otus.library.orm.service;

import java.util.List;
import ru.otus.library.orm.models.Author;
import ru.otus.library.orm.models.Book;
import ru.otus.library.orm.models.Comment;
import ru.otus.library.orm.models.Genre;

/**
 * Reads and writes into IO.
 */
public interface IoService {
  void writeLine(String line);

  void writeBook(Book book);

  void writeBooks(List<Book> books);

  void writeAuthor(Author author);

  void writeGenre(Genre genre);

  void writeComments(List<Comment> comments);

  void writeComment(Comment comment);
}
