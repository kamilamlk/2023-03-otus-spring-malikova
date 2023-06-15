package ru.otus.library.db.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.library.db.models.Author;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing AuthorsDao operations")
@DataMongoTest
public class AuthorsDaoTest {
  private final String ID = "1";

  @Autowired
  private AuthorsDao authorsDao;

  @Test
  void shouldSaveNewAuthor() {
    Author author = new Author(null, "Sample Author");
    authorsDao.save(author);
    assertThat(author.getId()).isNotNull();
  }

  @DisplayName("Should update name")
  @Test
  void shouldUpdateAuthor() {
    Author author = authorsDao.findById(ID).get();
    String oldName = author.getName();
    author.setName("New name");

    authorsDao.save(author);

    Author resultingAuthor = authorsDao.findById(ID).get();
    assertThat(resultingAuthor.getName()).isNotEqualTo(oldName);
  }

  @DisplayName("Should delete")
  @Test
  void shouldDeleteAuthor() {
    Author author = authorsDao.findById(ID).get();
    authorsDao.delete(author);
    assertTrue(authorsDao.findById(ID).isEmpty());
  }
}
