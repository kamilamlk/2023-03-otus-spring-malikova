package ru.otus.library.security.dao.flux;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.library.security.models.Author;
import ru.otus.library.security.models.Book;
import ru.otus.library.security.models.Genre;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing BooksFluxDaoTest operations")
@DataMongoTest()
public class BooksFluxDaoTest {
  private static final String EXISTING_BOOK_ID = "1";

  @Autowired
  private BooksFluxDao booksDao;

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

    Mono<Book> operation = booksDao.save(book);

    StepVerifier
            .create(operation)
            .assertNext(result -> assertThat(result)
                                          .matches(b -> b.getId() != null)
                                          .matches(b -> b.getTitle().equals(book.getTitle()))
                                          .matches(b -> b.getPublicationYear() == book.getPublicationYear())
            ).expectComplete()
            .verify();
  }

  @DisplayName("Updates book information")
  @Test
  void shouldUpdate() {
    String newTitle = "The Shining 2";
    Mono<Book> operation = booksDao.findById(EXISTING_BOOK_ID)
                                      .map(b -> {
                                        b.setTitle(newTitle);
                                        return b;
                                      })
                                   .flatMap(b -> booksDao.save(b));
    StepVerifier
            .create(operation)
            .assertNext(result -> assertThat(result)
                                          .matches(b -> b.getTitle().equals(newTitle))
            ).expectComplete()
            .verify();
  }

  @DisplayName("Deletes books by it's identification")
  @Test
  void shouldDeleteById() {
    Mono<Void> operation = booksDao.findById(EXISTING_BOOK_ID)
                                   .flatMap(book -> booksDao.delete(book));
    StepVerifier
            .create(operation)
            .expectNextCount(0)
            .expectComplete()
            .verify();
  }

  @DisplayName("Returns list of one book")
  @Test
  void shouldReturnBooksList() {
    Flux<Book> operation = booksDao.findAll();

    StepVerifier
            .create(operation)
            .assertNext(book -> assertThat(book)
                                       .matches(b -> b.getId() != null)
                                       .matches(b -> b.getTitle() != null)
                                       .matches(b -> b.getPublicationYear() > 0)
                                       .matches(b -> b.getGenre() != null)
                                       .matches(b -> b.getAuthor() != null))
            .expectComplete()
            .verify();
  }
}
