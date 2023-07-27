package ru.otus.library.security.dao.flux;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.library.security.models.Genre;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Testing GenresFluxDao operations")
@DataMongoTest()
public class GenresFluxDaoTest {
  private final String ID = "1";

  @Autowired
  private GenresFluxDao genreDao;

  @DisplayName("Should update name")
  @Test
  void shouldUpdateGenre() {
    Mono<Genre> genre = genreDao.findById(ID)
                                .flatMap(g -> genreDao.save(g));

    StepVerifier
            .create(genre)
            .assertNext(a -> assertNotNull(a.getId()))
            .expectComplete()
            .verify();
  }
}
