package ru.otus.library.jpa.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.library.jpa.models.Author;
import ru.otus.library.jpa.models.Book;
import ru.otus.library.jpa.models.Comment;
import ru.otus.library.jpa.models.Genre;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing CommentsDaoJpa CRUD operations")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CommentsDaoTest {
  private final long EXCISTING_ID = 1;

  @Autowired
  private CommentsDao commentsDao;

  @Autowired
  private TestEntityManager em;

  @DisplayName("Deletes comment by it's identification")
  @Test
  void shouldDeleteById() {
    Comment comment = em.find(Comment.class, EXCISTING_ID);
    Book book = comment.getBook();
    commentsDao.delete(comment);

    assertThat(commentsDao.findById(EXCISTING_ID)).isEmpty();
    assertThat(em.find(Book.class, book.getId())).isNotNull();
  }
}
