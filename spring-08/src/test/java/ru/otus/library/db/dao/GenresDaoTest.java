package ru.otus.library.db.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.library.db.models.Genre;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing GenreDao operations")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class GenresDaoTest {
  private final long ID = 1L;

  @Autowired
  private GenresDao genreDao;
  @Autowired
  private TestEntityManager em;

  @DisplayName("Should update name")
  @Test
  void shouldUpdateGenre() {
    Genre genre = genreDao.findById(ID).get();
    String oldName = genre.getName();
    genre.setName("New name");
    em.detach(genre);
    genreDao.save(genre);

    Optional<Genre> resultingGenre = genreDao.findById(ID);
    assertThat(resultingGenre)
            .isNotEmpty()
            .matches(g -> !g.get().getName().equals(oldName));
  }
}
