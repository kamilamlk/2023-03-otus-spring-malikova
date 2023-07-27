package ru.otus.library.security.dao;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.security.models.Author;
import ru.otus.library.security.models.Book;
import ru.otus.library.security.models.Genre;
import ru.otus.library.security.mongo.event.BookCascadeDeleteOperation;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing BooksDao CRUD operations")
@DataMongoTest
@Import(BookCascadeDeleteOperation.class)
public class BooksDaoTest {
  private static final String EXISTING_BOOK_ID = "1";

  @Autowired
  private BooksDao booksDao;

  @DisplayName("Inserts book")
  @Test
  void shouldInsertBook() {
    Book book = new Book(
            null,
            "Carrie",
            1974,
            new Author("0", "King"),
            new Genre("0", "Fairytale"),
            List.of()
    );

    booksDao.save(book);

    Optional<Book> resultingBook = booksDao.findById(book.getId());
    assertThat(resultingBook).isPresent();

    Book result = resultingBook.get();
    assertThat(result)
            .matches(b -> b.getId() != null)
            .matches(b -> b.getTitle().equals(book.getTitle()))
            .matches(b -> b.getPublicationYear() == book.getPublicationYear());
  }

  @DisplayName("Updates book information")
  @Test
  void shouldUpdate() {
    String newTitle = "The Shining 2";
    Optional<Book> optionalBook = booksDao.findById(EXISTING_BOOK_ID);

    assertThat(optionalBook).isNotEmpty();
    Book book = optionalBook.get();

    book.setTitle(newTitle);
    booksDao.save(book);

    Optional<Book> resultingBook = booksDao.findById(EXISTING_BOOK_ID);
    assertThat(resultingBook)
            .isPresent()
            .matches(b -> b.get().getTitle().equals(newTitle));
  }

  @DisplayName("Deletes books by it's identification")
  @Test
  void shouldDeleteById() {
    Optional<Book> book = booksDao.findById(EXISTING_BOOK_ID);
    assertThat(book).isPresent();
    booksDao.delete(book.get());
    assertThat(booksDao.findById(EXISTING_BOOK_ID)).isEmpty();
    booksDao.save(book.get());
  }

  @DisplayName("Returns list of one book")
  @Test
  void shouldReturnBooksList() {
      List<Book> books = booksDao.findAll();

      assertThat(books).isNotNull().hasSize(1)
              .allMatch(b -> b.getId() != null)
              .allMatch(b -> b.getTitle() != null)
              .allMatch(b -> b.getPublicationYear() > 0)
              .allMatch(b -> b.getGenre() != null)
              .allMatch(b -> b.getAuthor() != null);
  }
}
