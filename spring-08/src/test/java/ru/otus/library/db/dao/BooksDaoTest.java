package ru.otus.library.db.dao;

import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.library.db.models.Author;
import ru.otus.library.db.models.Book;
import ru.otus.library.db.models.Comment;
import ru.otus.library.db.models.Genre;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing BooksDao CRUD operations")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BooksDaoTest {
  private static final long EXISTING_BOOK_ID = 1;

  @Autowired
  private BooksDao booksDao;

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
            new Genre(0L, "Fairytale"),
            List.of()
    );

    booksDao.save(book);
    em.detach(book);
    Book resultingBook = em.find(Book.class, book.getId());

    assertThat(resultingBook).isEqualTo(book)
            .matches(r -> r.getId() > 0);
  }

  @DisplayName("Updates book information")
  @Test
  void shouldUpdate() {
    String newTitle = "The Shining 2";
    Book book = em.find(Book.class, EXISTING_BOOK_ID);
    String oldTitle = book.getTitle();
    book.setTitle(newTitle);
    em.detach(book);
    booksDao.save(book);
    Book resultingBook = em.find(Book.class, EXISTING_BOOK_ID);
    assertThat(resultingBook.getTitle()).isNotEqualTo(oldTitle).isEqualTo(newTitle);
  }

  @DisplayName("Deletes books by it's identification")
  @Test
  void shouldDeleteById() {
    Book book = em.find(Book.class, EXISTING_BOOK_ID);
    booksDao.delete(book);
    assertThat(booksDao.findById(EXISTING_BOOK_ID)).isEmpty();

    TypedQuery<Comment> query = em.getEntityManager()
                                        .createQuery(
                                                "select c from Comment c where c.book.id = :id",
                                                Comment.class
                                        );
    query.setParameter("id", EXISTING_BOOK_ID);
    assertThat(query.getResultList()).isEmpty();
  }

  @DisplayName("Returns list of one book")
  @Test
  void shouldReturnBooksList() {
    SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                                                .unwrap(SessionFactory.class);
      sessionFactory.getStatistics().setStatisticsEnabled(true);

      List<Book> books = booksDao.findAll();

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
