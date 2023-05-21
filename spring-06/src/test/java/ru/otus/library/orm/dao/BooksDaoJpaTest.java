package ru.otus.library.orm.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.library.orm.models.Author;
import ru.otus.library.orm.models.Book;
import ru.otus.library.orm.models.Genre;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing BooksDao CRUD operations")
@DataJpaTest
@Import(BooksDaoJpa.class)
public class BooksDaoJpaTest {
  private static final long EXISTING_BOOK_ID = 1;

  @Autowired
  private BooksDaoJpa booksDao;

  @Autowired
  private TestEntityManager em;

  @DisplayName("Inserts book")
  @Test
  void shouldInsertBook() {
    Book book = new Book(
            0L,
            "Carrie",
            1974,
            new Author(0L, "King"),
            new Genre(0L, "Fairytale")
    );

    booksDao.insert(book).getId();
    em.detach(book);
    Book resultingBook = em.find(Book.class, book.getId());

    assertThat(resultingBook).isEqualTo(book)
            .matches(r -> r.getId() > 0);
  }

  @DisplayName("Returns book by it's identification")
  @Test
  void shouldGetById() {
    Book resultingBook = booksDao.getById(EXISTING_BOOK_ID);
    Book expectedBook = em.find(Book.class, EXISTING_BOOK_ID);
    assertThat(resultingBook)
            .matches(r -> r.getId() == EXISTING_BOOK_ID)
            .matches(r -> r.getTitle().equals(expectedBook.getTitle()))
            .matches(r -> r.getPublicationYear() == expectedBook.getPublicationYear())
            .matches(r -> r.getAuthor().getId() == expectedBook.getAuthor().getId())
            .matches(r -> r.getAuthor().getName().equals(expectedBook.getAuthor().getName()))
            .matches(r -> r.getGenre().getId() == expectedBook.getGenre().getId())
            .matches(r -> r.getGenre().getName().equals(expectedBook .getGenre().getName()));
  }

  @DisplayName("Updates book information")
  @Test
  void shouldUpdate() {
    String newTitle = "The Shining 2";
    Book book = em.find(Book.class, EXISTING_BOOK_ID);
    String oldTitle = book.getTitle();
    book.setTitle(newTitle);
    em.detach(book);
    booksDao.update(book);
    Book resultingBook = em.find(Book.class, EXISTING_BOOK_ID);
    assertThat(resultingBook.getTitle()).isNotEqualTo(oldTitle).isEqualTo(newTitle);
  }

  @DisplayName("Deletes books by it's identification")
  @Test
  void shouldDeleteById() {
    Book book = em.find(Book.class, EXISTING_BOOK_ID);
    em.detach(book);
    booksDao.deleteById(EXISTING_BOOK_ID);
    assertThat(booksDao.getById(EXISTING_BOOK_ID)).isNull();
  }

  @DisplayName("Returns list of one book")
  @Test
  void shouldReturnBooksList() {
    try(SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                                                .unwrap(SessionFactory.class)) {
      sessionFactory.getStatistics().setStatisticsEnabled(true);

      List<Book> books = booksDao.getAll();

      assertThat(books).isNotNull().hasSize(1)
              .allMatch(b -> b.getId() > 0)
              .allMatch(b -> b.getTitle() != null)
              .allMatch(b -> b.getPublicationYear() > 0)
              .allMatch(b -> b.getGenre() != null)
              .allMatch(b -> b.getAuthor() != null)
      ;

      assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(1);
    }
  }
}
