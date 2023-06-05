package ru.otus.library.jpa.dao;

import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.library.jpa.models.Author;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing AuthorsDao operations")
@DataJpaTest
@Import(AuthorsDaoJpa.class)
public class AuthorsDaoJpaTest {
  private final long ID = 1L;

  @Autowired
  private AuthorsDaoJpa authorsDao;
  @Autowired
  private TestEntityManager em;

  @Test
  void shouldSaveNewAuthor() {
    Author author = new Author(0L, "Sample Author");
    authorsDao.save(author);
    assertThat(author.getId()).isGreaterThan(0L);
  }

  @DisplayName("Should return two authors")
  @Test
  void shouldReturnAuthors() {
    TypedQuery<Author> query = em.getEntityManager().createQuery("select a from Author a", Author.class);
    List<Author> expectedAuthors = query.getResultList();

    List<Author> authors = authorsDao.getAll();
    assertThat(authors).containsExactlyInAnyOrderElementsOf(expectedAuthors);
  }

  @DisplayName("Should return the author")
  @Test
  void shouldReturnAuthor() {
    Author expectedAuthor = em.find(Author.class, ID);
    em.detach(expectedAuthor);
    Author author = authorsDao.getById(ID);

    Assertions.assertEquals(expectedAuthor, author);
  }

  @DisplayName("Should update name")
  @Test
  void shouldUpdateAuthor() {
    Author author = authorsDao.getById(ID);
    String oldName = author.getName();
    author.setName("New name");
    em.detach(author);
    authorsDao.update(author);

    Author resultingAuthor = authorsDao.getById(ID);
    assertThat(resultingAuthor.getName()).isNotEqualTo(oldName);
  }

  @DisplayName("Should delete")
  @Test
  void shouldDeleteAuthor() {
    Author author = authorsDao.getById(ID);
    authorsDao.delete(author);
    assertThat(authorsDao.getById(ID)).isNull();
  }
}
