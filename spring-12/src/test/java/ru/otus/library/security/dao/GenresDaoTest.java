package ru.otus.library.security.dao;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.library.security.models.Genre;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing GenreDao operations")
@DataMongoTest
public class GenresDaoTest {
  private final String ID = "1";

  @Autowired
  private GenresDao genreDao;

  @DisplayName("Should update name")
  @Test
  void shouldUpdateGenre() {
    Genre genre = genreDao.findById(ID).get();
    String oldName = genre.getName();
    genre.setName("New name");

    genreDao.save(genre);

    Optional<Genre> resultingGenre = genreDao.findById(ID);
    assertThat(resultingGenre)
            .isNotEmpty()
            .matches(g -> !g.get().getName().equals(oldName));
  }
}
