package ru.otus.library.jpa.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.library.jpa.models.Author;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing AuthorsDao operations")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AuthorsDaoTest {
  private final long ID = 1L;

  @Autowired
  private AuthorsDao authorsDao;
  @Autowired
  private TestEntityManager em;

  @Test
  void shouldSaveNewAuthor() {
    Author author = new Author(0L, "Sample Author");
    authorsDao.save(author);
    assertThat(author.getId()).isGreaterThan(0L);
  }

  @DisplayName("Should update name")
  @Test
  void shouldUpdateAuthor() {
    Author author = authorsDao.findById(ID).get();
    String oldName = author.getName();
    author.setName("New name");
    em.detach(author);
    authorsDao.update(author.getId(), author.getName());

    Author resultingAuthor = authorsDao.findById(ID).get();
    assertThat(resultingAuthor.getName()).isNotEqualTo(oldName);
  }

  @DisplayName("Should delete")
  @Test
  void shouldDeleteAuthor() {
    Author author = authorsDao.findById(ID).get();
    authorsDao.deleteById(author.getId());
    assertTrue(authorsDao.findById(ID).isEmpty());
  }
}
