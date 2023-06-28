package ru.otus.library.mvc.shell;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.mvc.config.Default;
import ru.otus.library.mvc.exception.NotFoundException;
import ru.otus.library.mvc.models.Author;
import ru.otus.library.mvc.models.Book;
import ru.otus.library.mvc.models.Comment;
import ru.otus.library.mvc.models.Genre;
import ru.otus.library.mvc.service.AuthorsService;
import ru.otus.library.mvc.service.BooksService;
import ru.otus.library.mvc.service.CommentsService;
import ru.otus.library.mvc.service.GenresService;
import ru.otus.library.mvc.service.IoService;

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

  /**
   * Shows book.
   */
  @ShellMethod(value = "Show books command", key = {"book"})
  public void showBook(@ShellOption String bookId) {
    try {
      Book book = booksService.getBook(bookId);
      ioService.writeBook(book);
    } catch (NotFoundException e) {
      ioService.writeLine(e.getMessage());
    }
  }

  /**
   * Adds book.
   */
  @ShellMethod(value = "Add book command", key = {"book-add"})
  public void addBook(
          @ShellOption(value = {"-t", "--title"}) String title,
          @ShellOption(value = {"-y", "--year"}) int publicationYear,
          @ShellOption(value = {"-a", "--author"}) String authorId,
          @ShellOption(value = {"-g", "--genre"}) String genreId) {
    try {
      booksService.addBook(title, publicationYear, authorId, genreId);
    } catch (NotFoundException e) {
      ioService.writeLine(e.getMessage());
    }
  }

  /**
   * Updates book's infjpaation.
   */
  @ShellMethod(value = "Updates book's infjpaation", key = {"book-update"})
  public void updateBook(
          @ShellOption String bookId,
          @ShellOption(value = {"-t", "--title"}, defaultValue = Default.NONE) String title,
          @ShellOption(value = {"-y", "--year"}, defaultValue = Default.ZERO_STRING) int year,
          @ShellOption(value = {"-a", "--author"}, defaultValue = Default.NONE) String authorId,
          @ShellOption(value = {"-g", "--genre"}, defaultValue = Default.NONE) String genreId
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
  public void addComment(@ShellOption String bookId, @ShellOption String comment) {
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
  public void showBookComments(@ShellOption String bookId) {
    try {
      List<Comment> comments = commentsService.getBookComments(bookId);
      ioService.writeComments(comments);
    } catch (NotFoundException e) {
      ioService.writeLine(e.getMessage());
    }
  }

  /**
   * Updates book comment.
   */
  @ShellMethod(value = "Updates book comments", key = {"comment-update"})
  public void updateComment(@ShellOption String commentId, @ShellOption String commentText) {
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
  public void deleteComment(@ShellOption String commentId) {
    try {
      commentsService.deleteComment(commentId);
    } catch (NotFoundException e) {
      ioService.writeLine(e.getMessage());
    }
  }

  @ShellMethod(value = "Adds new author", key = {"author-add"})
  public void addAuthor(@ShellOption String authorName) {
    authorsService.addAuthor(authorName);
  }

  @ShellMethod(value = "Update author name", key = {"author-update"})
  public void updateAuthor(@ShellOption String authorId, @ShellOption String authorName) {
    authorsService.updateAuthor(authorId, authorName);
  }
}
