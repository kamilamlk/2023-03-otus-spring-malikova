package ru.otus.library.mvc.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.mvc.models.Author;
import ru.otus.library.mvc.models.Book;
import ru.otus.library.mvc.mongo.event.AuthorCascadeOperation;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing AuthorsDao operations")
@DataMongoTest
@EnableConfigurationProperties
@Import({AuthorCascadeOperation.class})
public class AuthorsDaoTest {
  private final String ID = "1";

  @Autowired
  private AuthorsDao authorsDao;
  @Autowired
  private BooksDao booksDao;

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
    List<Book> books = booksDao.findAllByAuthor_Id(ID);
    assertThat(books).allMatch(b -> b.getAuthor().equals(resultingAuthor));
  }

  @DisplayName("Should delete")
  @Test
  void shouldDeleteAuthor() {
    Author author = authorsDao.findById(ID).get();
    authorsDao.delete(author);
    assertTrue(authorsDao.findById(ID).isEmpty());
  }
}
