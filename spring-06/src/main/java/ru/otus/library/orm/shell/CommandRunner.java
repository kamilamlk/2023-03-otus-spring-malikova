package ru.otus.library.orm.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.orm.config.Default;
import ru.otus.library.orm.service.AuthorsService;
import ru.otus.library.orm.service.BooksService;
import ru.otus.library.orm.service.GenresService;

/**
 * Shell command line runner.
 */
@ShellComponent
@AllArgsConstructor
public class CommandRunner {
  private final BooksService booksService;
  private final AuthorsService authorsService;
  private final GenresService genreService;

  @ShellMethod(value = "Show books command", key = "books")
  public void showBooks() {
    booksService.showBooks();
  }

  @ShellMethod(value = "Show authors command", key = "authors")
  public void showAuthors() {
    authorsService.showAuthors();
  }

  @ShellMethod(value = "Show genres command", key = "genres")
  public void showGenres() {
    genreService.showGenres();
  }

  @ShellMethod(value = "Show books command", key = {"book"})
  public void showBook(@ShellOption long bookId) {
    booksService.showBook(bookId);
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
   * Updates book's information.
   */
  @ShellMethod(value = "Updates book's information", key = {"book-update"})
  public void updateBook(
          @ShellOption long bookId,
          @ShellOption(value = {"-t", "--title"}, defaultValue = Default.NONE) String title,
          @ShellOption(value = {"-y", "--year"}, defaultValue = Default.ZERO_STRING) int year,
          @ShellOption(value = {"-a", "--author"}, defaultValue = Default.ZERO_STRING) long authorId,
          @ShellOption(value = {"-g", "--genre"}, defaultValue = Default.ZERO_STRING) long genreId
  ) {
    booksService.updateBook(
            bookId,
            title,
            year,
            authorId,
            genreId
    );
  }
}
