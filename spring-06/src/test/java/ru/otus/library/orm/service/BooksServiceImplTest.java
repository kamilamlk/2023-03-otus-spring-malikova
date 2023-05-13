package ru.otus.library.orm.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.library.orm.config.Default;
import ru.otus.library.orm.dao.BooksDao;
import ru.otus.library.orm.models.Author;
import ru.otus.library.orm.models.Book;
import ru.otus.library.orm.models.Genre;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = BooksServiceImpl.class)
public class BooksServiceImplTest {
  private final String TITLE = "Test book";
  private final Author AUTHOR = new Author(1L, "Test Author");
  private final Genre GENRE = new Genre(1L, "Test Genre");
  private final Book BOOK = new Book(1L, TITLE, 2000, AUTHOR, GENRE);

  @MockBean
  private BooksDao booksDao;
  @MockBean
  private AuthorsService authorsService;
  @MockBean
  private GenresService genresService;
  @MockBean
  private IoService ioService;

  @Autowired
  private BooksServiceImpl booksService;

  @DisplayName("Prints books in ioService")
  @Test
  void shouldPrintBooks() {
    List<Book> books = List.of(BOOK);
    doReturn(books).when(booksDao).getAll();
    doNothing().when(ioService).writeBook(BOOK);
    booksService.showBooks();
    verify(ioService).writeBooks(books);

    verify(ioService, times(1)).writeBooks(any());
  }

  @DisplayName("Correctly builds book to insert")
  @Test
  void shouldCorrectlyAddBook() {
    doReturn(BOOK.getId()).when(booksDao).getNextId();
    doReturn(AUTHOR).when(authorsService).getAuthorById(AUTHOR.getId());
    doReturn(GENRE).when(genresService).getGenreById(GENRE.getId());

    booksService.addBook(BOOK.getTitle(), BOOK.getPublicationYear(), AUTHOR.getId(), GENRE.getId());

    verify(booksDao).insert(BOOK);
  }

  @DisplayName("Updates book's title")
  @Test
  void shouldUpdateTitle() {
    String newTitle = "New Title";
    Book expectedBook = new Book(BOOK.getId(), newTitle, BOOK.getPublicationYear(), AUTHOR, GENRE);

    doReturn(BOOK).when(booksDao).getById(BOOK.getId());
    doReturn(GENRE).when(genresService).getGenreById(GENRE.getId());
    doReturn(AUTHOR).when(authorsService).getAuthorById(AUTHOR.getId());

    booksService.updateBook(BOOK.getId(), newTitle, 0, Default.ZERO, Default.ZERO);
    verify(booksDao).update(expectedBook);
  }

  @DisplayName("Updates book's publication year")
  @Test
  void shouldUpdatePublicationYear() {
    int newYear = 2005;
    Book expectedBook = new Book(BOOK.getId(), BOOK.getTitle(), newYear, AUTHOR, GENRE);

    doReturn(BOOK).when(booksDao).getById(BOOK.getId());
    doReturn(GENRE).when(genresService).getGenreById(GENRE.getId());
    doReturn(AUTHOR).when(authorsService).getAuthorById(AUTHOR.getId());

    booksService.updateBook(BOOK.getId(), Default.NONE, newYear, Default.ZERO, Default.ZERO);
    verify(booksDao).update(expectedBook);
  }

  @DisplayName("Updates book's author")
  @Test
  void shouldUpdateAuthor() {
    Author newAuthor = new Author(3L, "New Author");
    Book expectedBook = new Book(BOOK.getId(), BOOK.getTitle(), BOOK.getPublicationYear(), newAuthor, GENRE);

    doReturn(BOOK).when(booksDao).getById(BOOK.getId());
    doReturn(GENRE).when(genresService).getGenreById(GENRE.getId());
    doReturn(newAuthor).when(authorsService).getAuthorById(newAuthor.getId());

    booksService.updateBook(BOOK.getId(), Default.NONE, Default.ZERO, newAuthor.getId(), Default.ZERO);
    verify(booksDao).update(expectedBook);
  }

  @DisplayName("Updates book's genre")
  @Test
  void shouldUpdateGenre() {
    Genre newGenre = new Genre(3L, "New Genre");
    Book expectedBook = new Book(BOOK.getId(), BOOK.getTitle(), BOOK.getPublicationYear(), AUTHOR, newGenre);

    doReturn(BOOK).when(booksDao).getById(BOOK.getId());
    doReturn(newGenre).when(genresService).getGenreById(newGenre.getId());
    doReturn(AUTHOR).when(authorsService).getAuthorById(AUTHOR.getId());

    booksService.updateBook(BOOK.getId(), Default.NONE, Default.ZERO, Default.ZERO, newGenre.getId());
    verify(booksDao).update(expectedBook);
  }

  @DisplayName("Updates book's all information")
  @Test
  void shouldUpdateAllParams() {
    String newTitle = "New Title";
    int newYear = 2005;
    Author newAuthor = new Author(3L, "New Author");
    Genre newGenre = new Genre(3L, "New Genre");

    Book expectedBook = new Book(BOOK.getId(), newTitle, newYear, newAuthor, newGenre);

    doReturn(BOOK).when(booksDao).getById(BOOK.getId());
    doReturn(newGenre).when(genresService).getGenreById(newGenre.getId());
    doReturn(newAuthor).when(authorsService).getAuthorById(newAuthor.getId());

    booksService.updateBook(BOOK.getId(), newTitle, newYear, newAuthor.getId(), newGenre.getId());
    verify(booksDao).update(expectedBook);
  }
}
