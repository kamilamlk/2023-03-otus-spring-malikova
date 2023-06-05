package ru.otus.library.jpa.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.library.jpa.models.Author;
import ru.otus.library.jpa.models.Book;
import ru.otus.library.jpa.models.Comment;
import ru.otus.library.jpa.models.Genre;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing CommentsDaoJpa CRUD operations")
@DataJpaTest
@Import(CommentsDaoJpa.class)
public class CommentsDaoJpaTest {
  private final long EXCISTING_ID = 1;
  private final Book BOOK = new Book(
          EXCISTING_ID,
          "The Shining",
          1977,
          new Author(1L, "Stephen King"),
          new Genre(1L, "FICTION"),
          List.of()
  );
  @Autowired
  private CommentsDaoJpa commentsDaoJpa;

  @Autowired
  private TestEntityManager em;

  @DisplayName("Inserts comment")
  @Test
  void shouldInsertComment() {
    Comment comment = new Comment(0, "Sample comment", BOOK);
    commentsDaoJpa.save(comment);
    em.detach(comment);
    Comment resultingComment = em.find(Comment.class, comment.getId());
    assertThat(resultingComment)
            .matches(r -> r.getId() > 0)
            .matches(r -> r.getCommentText().equals(comment.getCommentText()));
  }

  @DisplayName("Returns comment by it's identification")
  @Test
  void shouldGetById() {
    Comment resultingComment = commentsDaoJpa.getById(EXCISTING_ID);
    Comment expectedComment = em.find(Comment.class, EXCISTING_ID);
    assertThat(resultingComment).isEqualTo(expectedComment)
            .matches(r -> r.getId() == EXCISTING_ID);
  }

  @DisplayName("Updates comment information")
  @Test
  void shouldUpdate() {
    String commentText = "new comment";
    Comment comment = em.find(Comment.class, EXCISTING_ID);
    em.detach(comment);

    comment.setCommentText(commentText);
    commentsDaoJpa.update(comment);

    Comment resultingComment = em.find(Comment.class, EXCISTING_ID);
    assertThat(resultingComment)
            .matches(r -> r.getId() == EXCISTING_ID)
            .matches(r -> r.getCommentText().equals(commentText));
  }

  @DisplayName("Deletes comment by it's identification")
  @Test
  void shouldDeleteById() {
    Comment comment = em.find(Comment.class, EXCISTING_ID);
    Book book = comment.getBook();
    commentsDaoJpa.delete(comment);

    assertThat(commentsDaoJpa.getById(EXCISTING_ID)).isNull();
    assertThat(em.find(Book.class, book.getId())).isNotNull();
  }
}
