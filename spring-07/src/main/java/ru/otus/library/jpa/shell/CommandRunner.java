package ru.otus.library.jpa.shell;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.jpa.config.Default;
import ru.otus.library.jpa.exception.NotFoundException;
import ru.otus.library.jpa.models.Author;
import ru.otus.library.jpa.models.Book;
import ru.otus.library.jpa.models.Comment;
import ru.otus.library.jpa.models.Genre;
import ru.otus.library.jpa.service.AuthorsService;
import ru.otus.library.jpa.service.BooksService;
import ru.otus.library.jpa.service.CommentsService;
import ru.otus.library.jpa.service.GenresService;
import ru.otus.library.jpa.service.IoService;

/**
 * Shell command line runner.
 */
@ShellComponent
@AllArgsConstructor
public class CommandRunner {
  private final BooksService booksService;
  private final AuthorsService authorsService;
  private final GenresService genreService;
  private final CommentsService commentsService;
  private final IoService ioService;

  @ShellMethod(value = "Show books command", key = "books")
  public void showBooks() {
    List<Book> books = booksService.findBooks();
    ioService.writeBooks(books);
  }

  @ShellMethod(value = "Show authors command", key = "authors")
  public void showAuthors() {
    List<Author> authors = authorsService.findAuthors();
    ioService.writeAuthors(authors);
  }

  @ShellMethod(value = "Show genres command", key = "genres")
  public void showGenres() {
    List<Genre> genres = genreService.findGenres();
    ioService.writeGenres(genres);
  }

  @ShellMethod(value = "Show books command", key = {"book"})
  public void showBook(@ShellOption long bookId) {
    Book book = booksService.getBook(bookId);
    ioService.writeBook(book);
  }

  @ShellMethod(value = "Add book command", key = {"book-add"})
  public void addBook(
          @ShellOption(value = {"-t", "--title"}) String title,
          @ShellOption(value = {"-y", "--year"}) int publicationYear,
          @ShellOption(value = {"-a", "--author"}) long authorId,
          @ShellOption(value = {"-g", "--genre"}) long genreId) {
    booksService.addBook(title, publicationYear, authorId, genreId);
  }

  /**
   * Updates book's infjpaation.
   */
  @ShellMethod(value = "Updates book's infjpaation", key = {"book-update"})
  public void updateBook(
          @ShellOption long bookId,
          @ShellOption(value = {"-t", "--title"}, defaultValue = Default.NONE) String title,
          @ShellOption(value = {"-y", "--year"}, defaultValue = Default.ZERO_STRING) int year,
          @ShellOption(value = {"-a", "--author"}, defaultValue = Default.ZERO_STRING) long authorId,
          @ShellOption(value = {"-g", "--genre"}, defaultValue = Default.ZERO_STRING) long genreId
  ) {
    try {
      booksService.updateBook(
              bookId,
              title,
              year,
              authorId,
              genreId
      );
    } catch (NotFoundException e) {
      ioService.writeLine(e.getMessage());
    }
  }

  /**
   * Adds book comment.
   */
  @ShellMethod(value = "Adds book comment", key = {"comment-add"})
  public void addComment(@ShellOption long bookId, @ShellOption String comment) {
    try {
      commentsService.addComment(bookId, comment);
    } catch (NotFoundException e) {
      ioService.writeLine(e.getMessage());
    }
  }

  /**
   * Shows book comments.
   */
  @ShellMethod(value = "Shows book comments", key = {"comments"})
  public void showBookComments(@ShellOption long bookId) {
    List<Comment> comments = commentsService.getBookComments(bookId);
    ioService.writeComments(comments);
  }

  /**
   * Updates book comment.
   */
  @ShellMethod(value = "Updates book comments", key = {"comment-update"})
  public void updateComment(@ShellOption long commentId, @ShellOption String commentText) {
    try {
      commentsService.updateComment(commentId, commentText);
    } catch (NotFoundException e) {
      ioService.writeLine(e.getMessage());
    }
  }

  /**
   * Delete book comment.
   */
  @ShellMethod(value = "Deleted book comments", key = {"comment-delete"})
  public void deleteComment(@ShellOption long commentId) {
    try {
      commentsService.deleteComment(commentId);
    } catch (NotFoundException e) {
      ioService.writeLine(e.getMessage());
    }
  }

}
