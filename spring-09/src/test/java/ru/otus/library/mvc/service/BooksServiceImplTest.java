package ru.otus.library.mvc.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.library.mvc.config.Default;
import ru.otus.library.mvc.dao.BooksDao;
import ru.otus.library.mvc.models.Author;
import ru.otus.library.mvc.models.Book;
import ru.otus.library.mvc.models.Genre;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = BooksServiceImpl.class)
public class BooksServiceImplTest {
  private final String TITLE = "Test book";
  private final Author AUTHOR = new Author("1", "Test Author");
  private final Genre GENRE = new Genre("1", "Test Genre");
  private final Book BOOK = new Book("0", TITLE, 2000, AUTHOR, GENRE, List.of());
  private final Book NEW_BOOK = new Book(null, TITLE, 2000, AUTHOR, GENRE, List.of());

  @MockBean
  private BooksDao booksDao;
  @MockBean
  private AuthorsService authorsService;
  @MockBean
  private GenresService genresService;

  @Autowired
  private BooksServiceImpl booksService;

  @DisplayName("Prints books in ioService")
  @Test
  void shouldPrintBooks() {
    List<Book> books = List.of(BOOK);
    doReturn(books).when(booksDao).findAll();

    List<Book> result = booksService.findBooks();
    assertThat(result).containsExactlyInAnyOrderElementsOf(books);
  }

  @DisplayName("Correctly builds book to insert")
  @Test
  void shouldCorrectlyAddBook() {
    doReturn(AUTHOR).when(authorsService).getAuthorById(AUTHOR.getId());
    doReturn(GENRE).when(genresService).getGenreById(GENRE.getId());

    booksService.addBook(BOOK.getTitle(), BOOK.getPublicationYear(), AUTHOR.getId(), GENRE.getId());

    verify(booksDao).save(NEW_BOOK);
  }

  @DisplayName("Updates book's title")
  @Test
  void shouldUpdateTitle() {
    String newTitle = "New Title";
    Book expectedBook = new Book(BOOK.getId(), newTitle, BOOK.getPublicationYear(), AUTHOR, GENRE, List.of());

    doReturn(Optional.of(BOOK)).when(booksDao).findById(BOOK.getId());
    doReturn(GENRE).when(genresService).getGenreById(GENRE.getId());
    doReturn(AUTHOR).when(authorsService).getAuthorById(AUTHOR.getId());

    booksService.updateBook(BOOK.getId(), newTitle, 0, Default.NONE, Default.NONE);
    verify(booksDao).save(expectedBook);
  }

  @DisplayName("Updates book's publication year")
  @Test
  void shouldUpdatePublicationYear() {
    int newYear = 2005;
    Book expectedBook = new Book(BOOK.getId(), BOOK.getTitle(), newYear, AUTHOR, GENRE, List.of());

    doReturn(Optional.of(BOOK)).when(booksDao).findById(BOOK.getId());
    doReturn(GENRE).when(genresService).getGenreById(GENRE.getId());
    doReturn(AUTHOR).when(authorsService).getAuthorById(AUTHOR.getId());

    booksService.updateBook(BOOK.getId(), Default.NONE, newYear, Default.NONE, Default.NONE);
    verify(booksDao).save(expectedBook);
  }

  @DisplayName("Updates book's author")
  @Test
  void shouldUpdateAuthor() {
    Author newAuthor = new Author("3L", "New Author");
    Book expectedBook = new Book(BOOK.getId(), BOOK.getTitle(), BOOK.getPublicationYear(), newAuthor, GENRE, List.of());

    doReturn(Optional.of(BOOK)).when(booksDao).findById(BOOK.getId());
    doReturn(GENRE).when(genresService).getGenreById(GENRE.getId());
    doReturn(newAuthor).when(authorsService).getAuthorById(newAuthor.getId());

    booksService.updateBook(BOOK.getId(), Default.NONE, Default.ZERO, newAuthor.getId(), Default.NONE);
    verify(booksDao).save(expectedBook);
  }

  @DisplayName("Updates book's genre")
  @Test
  void shouldUpdateGenre() {
    Genre newGenre = new Genre("3L", "New Genre");
    Book expectedBook = new Book(BOOK.getId(), BOOK.getTitle(), BOOK.getPublicationYear(), AUTHOR, newGenre, List.of());

    doReturn(Optional.of(BOOK)).when(booksDao).findById(BOOK.getId());
    doReturn(newGenre).when(genresService).getGenreById(newGenre.getId());
    doReturn(AUTHOR).when(authorsService).getAuthorById(AUTHOR.getId());

    booksService.updateBook(BOOK.getId(), Default.NONE, Default.ZERO, Default.NONE, newGenre.getId());
    verify(booksDao).save(expectedBook);
  }

  @DisplayName("Updates book's all infjpaation")
  @Test
  void shouldUpdateAllParams() {
    String newTitle = "New Title";
    int newYear = 2005;
    Author newAuthor = new Author("3L", "New Author");
    Genre newGenre = new Genre("3L", "New Genre");

    Book expectedBook = new Book(BOOK.getId(), newTitle, newYear, newAuthor, newGenre, List.of());

    doReturn(Optional.of(BOOK)).when(booksDao).findById(BOOK.getId());
    doReturn(newGenre).when(genresService).getGenreById(newGenre.getId());
    doReturn(newAuthor).when(authorsService).getAuthorById(newAuthor.getId());

    booksService.updateBook(BOOK.getId(), newTitle, newYear, newAuthor.getId(), newGenre.getId());
    verify(booksDao).save(expectedBook);
  }
}
