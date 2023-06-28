package ru.otus.library.mvc.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.otus.library.mvc.models.Author;
import ru.otus.library.mvc.models.Book;
import ru.otus.library.mvc.models.Comment;
import ru.otus.library.mvc.models.Genre;

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
    writeLine(String.format("%s. %s", book.getId(), book.getTitle()));
    writeLine(String.format("   %s", book.getAuthor().getName()));
    writeLine(String.format("   %s", book.getGenre().getName()));
    writeLine(String.format("   Published on %d", book.getPublicationYear()));
  }

  @Override
  public void writeBooks(List<Book> books) {
    books.forEach(this::writeBook);
  }

  @Override
  public void writeAuthor(Author author) {
    writeLine(String.format("%s. %s", author.getId(), author.getName()));
  }

  @Override
  public void writeAuthors(List<Author> authors) {
    authors.forEach(this::writeAuthor);
  }

  @Override
  public void writeGenre(Genre genre) {
    writeLine(String.format("%s. %s", genre.getId(), genre.getName()));
  }

  @Override
  public void writeGenres(List<Genre> genres) {
    genres.forEach(this::writeGenre);
  }

  @Override
  public void writeComments(List<Comment> comments) {
    comments.forEach(this::writeComment);
  }

  @Override
  public void writeComment(Comment comment) {
    writeLine(String.format("%s. %s", comment.getId(), comment.getCommentText()));
  }
}
