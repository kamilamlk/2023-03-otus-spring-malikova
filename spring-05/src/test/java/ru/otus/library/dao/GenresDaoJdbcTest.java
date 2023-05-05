package ru.otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.models.Genre;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing GenreDaoJdbc operations")
@JdbcTest
@Import(GenresDaoJdbc.class)
public class GenresDaoJdbcTest {
  Genre GENRE1 = new Genre(1L, "FICTION");
  Genre GENRE2 = new Genre(2L, "NON-FICTION");

  @Autowired
  private GenresDaoJdbc genreDao;

  @DisplayName("Should return two genres")
  @Test
  void shouldReturnAuthors() {
    List<Genre> genres = genreDao.getAll();

    assertThat(genres).containsExactlyInAnyOrder(GENRE1, GENRE2);
  }

  @DisplayName("Should return the genre")
  @Test
  void shouldReturnAuthor() {
    Genre genre = genreDao.getById(GENRE1.getId());

    Assertions.assertEquals(GENRE1, genre);
  }
}
