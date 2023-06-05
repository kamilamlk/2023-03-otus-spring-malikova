package ru.otus.library.jpa.dao;

import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.library.jpa.models.Genre;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing GenreDaoJdbc operations")
@DataJpaTest
@Import(GenresDaoJpa.class)
public class GenresDaoJpaTest {
  private final long ID = 1L;

  @Autowired
  private GenresDaoJpa genreDao;
  @Autowired
  private TestEntityManager em;

  @Test
  void shouldSaveNewGenre() {
    Genre genre = new Genre(0L, "Sample Genre");
    genreDao.save(genre);
    assertThat(genre.getId()).isGreaterThan(0L);
  }

  @DisplayName("Should return two genres")
  @Test
  void shouldReturnAuthors() {
    TypedQuery<Genre> query = em.getEntityManager().createQuery("select g from Genre g", Genre.class);
    List<Genre> expected = query.getResultList();

    List<Genre> genres = genreDao.getAll();

    assertThat(genres).containsExactlyInAnyOrderElementsOf(expected);
  }

  @DisplayName("Should return the genre")
  @Test
  void shouldReturnAuthor() {
    Genre expected = em.find(Genre.class, ID);
    Genre genre = genreDao.getById(ID);

    assertThat(genre).isEqualTo(expected);
  }

  @DisplayName("Should update name")
  @Test
  void shouldUpdateGenre() {
    Genre genre = genreDao.getById(ID);
    String oldName = genre.getName();
    genre.setName("New name");
    em.detach(genre);
    genreDao.update(genre);

    Genre resultingGenre = genreDao.getById(ID);
    assertThat(resultingGenre.getName()).isNotEqualTo(oldName);
  }

  @DisplayName("Should delete")
  @Test
  void shouldDeleteGenre() {
    Genre genre = genreDao.getById(ID);
    genreDao.delete(genre);
    assertThat(genreDao.getById(ID)).isNull();
  }
}
