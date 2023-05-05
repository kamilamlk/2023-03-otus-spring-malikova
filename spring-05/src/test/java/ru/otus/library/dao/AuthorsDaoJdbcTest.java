package ru.otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.models.Author;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing AuthorsDao operations")
@JdbcTest
@Import(AuthorsDaoJdbc.class)
public class AuthorsDaoJdbcTest {
  private final Author AUTHOR1 = new Author(1L, "Stephen King");
  private final Author AUTHOR2 = new Author(2L, "George Orwell");

  @Autowired
  private AuthorsDaoJdbc authorsDao;

  @DisplayName("Should return two authors")
  @Test
  void shouldReturnAuthors() {
    List<Author> authors = authorsDao.getAll();

    assertThat(authors).containsExactlyInAnyOrder(AUTHOR1, AUTHOR2);
  }

  @DisplayName("Should return the author")
  @Test
  void shouldReturnAuthor() {
    Author author = authorsDao.getById(AUTHOR1.getId());

    Assertions.assertEquals(AUTHOR1, author);
  }
}
