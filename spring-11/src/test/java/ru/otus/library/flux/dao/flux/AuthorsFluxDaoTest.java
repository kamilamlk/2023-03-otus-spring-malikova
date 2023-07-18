package ru.otus.library.flux.dao.flux;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.library.flux.models.Author;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Testing AuthorsFluxDao operations")
@DataMongoTest()
public class AuthorsFluxDaoTest {
  @Autowired
  private AuthorsFluxDao authorsDao;

  @Test
  void shouldSaveNewAuthor() {
    Author author = new Author(null, "Sample Author");
    Mono<Author> authorMono = authorsDao.save(author);
    StepVerifier
            .create(authorMono)
            .assertNext(a -> assertNotNull(a.getId()))
            .expectComplete()
            .verify();
  }


  @DisplayName("Should delete")
  @Test
  void shouldDeleteAuthor() {
    String ID = "1";
    Mono<Void> operation = authorsDao.findById(ID)
                                  .then(authorsDao.deleteById(ID));
    StepVerifier
            .create(operation)
            .expectComplete()
            .verify();
  }
}
