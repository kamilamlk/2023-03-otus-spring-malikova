package ru.otus.library.db.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.db.models.Book;
import ru.otus.library.db.models.Comment;
import ru.otus.library.db.mongo.event.CommentCascadeOperation;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing CommentsDaoJpa CRUD operations")
@DataMongoTest
@Import(CommentCascadeOperation.class)
public class CommentsDaoTest {
  private final String EXCISTING_ID = "1";

  @Autowired
  private CommentsDao commentsDao;

  @Autowired
  private BooksDao booksDao;

  @DisplayName("Deletes comment by it's identification")
  @Test
  void shouldDeleteById() {
    Optional<Comment> optionalComment = commentsDao.findById(EXCISTING_ID);
    assertThat(optionalComment).isPresent();
    Comment comment = optionalComment.get();
    Book book = comment.getBook();
    commentsDao.delete(comment);

    assertThat(commentsDao.findById(EXCISTING_ID)).isEmpty();

    assertThat(booksDao.findById(book.getId())).isPresent()
            .matches(b -> !b.get().getComments().contains(comment));
  }
}
