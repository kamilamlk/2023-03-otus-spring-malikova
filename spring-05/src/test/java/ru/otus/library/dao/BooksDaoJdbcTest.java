package ru.otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Genre;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Testing BooksDao CRUD operations")
@JdbcTest
@Import(BooksDaoJdbc.class)
public class BooksDaoJdbcTest {
  private static final long EXISTING_BOOK_ID = 1;

  @Autowired
  private BooksDaoJdbc booksDao;

  @DisplayName("Returns two as next identification")
  @Test
  void shouldReturnTwoAsNextId() {
    long id = booksDao.getNextId();

    Assertions.assertEquals(2, id);
  }

  @DisplayName("Inserts book")
  @Test
  void shouldInsertBook() {
    long bookId = 2;
    Book book = new Book(
            bookId,
            "Carrie",
            1974,
            new Author(1L, "Stephen King"),
            new Genre(1L, "FICTION")
    );
    booksDao.insert(book);
    Book resultingBook = booksDao.getById(bookId);
    Assertions.assertEquals(book, resultingBook);
  }

  @DisplayName("Returns book by it's identification")
  @Test
  void shouldGetById() {
    Book book = new Book(
            EXISTING_BOOK_ID,
            "The Shining",
            1977,
            new Author(1L, "Stephen King"),
            new Genre(1L, "FICTION")
    );
    Book resultingBook = booksDao.getById(EXISTING_BOOK_ID);
    Assertions.assertEquals(book, resultingBook);
  }

  @DisplayName("Updates book information")
  @Test
  void shouldUpdate() {
    Book book = new Book(
            EXISTING_BOOK_ID,
            "The Shining 2",
            1974,
            new Author(2L, "George Orwell"),
            new Genre(2L, "NON-FICTION")
    );
    booksDao.update(book);
    Book resultingBook = booksDao.getById(EXISTING_BOOK_ID);
    Assertions.assertEquals(book, resultingBook);
  }

  @DisplayName("Deletes books by it's identification")
  @Test
  void shouldDeleteById() {
    Assertions.assertDoesNotThrow(() -> booksDao.getById(EXISTING_BOOK_ID));

    booksDao.deleteById(EXISTING_BOOK_ID);

    Assertions.assertThrows(
            EmptyResultDataAccessException.class,
            () -> booksDao.getById(EXISTING_BOOK_ID)
    );
  }

  @DisplayName("Returns list of one book")
  @Test
  void shouldReturnBooksList() {
    Book existingBook = new Book(
            EXISTING_BOOK_ID,
            "The Shining",
            1977,
            new Author(1L, "Stephen King"),
            new Genre(1L, "FICTION")
    );

    List<Book> books = booksDao.getAll();

    assertThat(books).containsExactlyInAnyOrder(existingBook);
  }
}
