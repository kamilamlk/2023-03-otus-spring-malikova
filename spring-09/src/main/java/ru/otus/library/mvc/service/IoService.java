package ru.otus.library.mvc.service;

import java.util.List;
import ru.otus.library.mvc.models.Author;
import ru.otus.library.mvc.models.Book;
import ru.otus.library.mvc.models.Comment;
import ru.otus.library.mvc.models.Genre;

/**
 * Reads and writes into IO.
 */
public interface IoService {
  void writeLine(String line);

  void writeBook(Book book);

  void writeBooks(List<Book> books);

  void writeAuthor(Author author);

  void writeAuthors(List<Author> authors);

  void writeGenre(Genre genre);

  void writeGenres(List<Genre> genres);

  void writeComments(List<Comment> comments);

  void writeComment(Comment comment);
}
