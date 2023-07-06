package ru.otus.library.ajax.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.library.ajax.dao.BooksDao;
import ru.otus.library.ajax.dao.CommentsDao;
import ru.otus.library.ajax.models.Author;
import ru.otus.library.ajax.models.Book;
import ru.otus.library.ajax.models.Comment;
import ru.otus.library.ajax.models.Genre;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = CommentsServiceImpl.class)
public class CommentsServiceImplTest {
  private final String COMMENT_TEXT = "Test comment";
  private final String TITLE = "Test book";
  private final Author AUTHOR = new Author("1", "Test Author");
  private final Genre GENRE = new Genre("1", "Test Genre");
  private final Book BOOK = new Book(
          "1", TITLE, 2000, AUTHOR,
          GENRE, List.of());
  private final Comment COMMENT = new Comment("1", COMMENT_TEXT, BOOK);
  private final Comment NEW_COMMENT = new Comment(null, COMMENT_TEXT, BOOK);

  @MockBean
  private CommentsDao commentsDao;
  @MockBean
  private BooksDao booksDao;

  @Autowired
  private CommentsServiceImpl commentsService;

  @DisplayName("Checks that comment is added")
  @Test
  void shouldAddComment() {
    doReturn(Optional.of(BOOK)).when(booksDao).findById(anyString());
    doReturn(NEW_COMMENT).when(commentsDao).save(any());
    commentsService.addComment(BOOK.getId(), COMMENT_TEXT);
    verify(commentsDao, times(1)).save(NEW_COMMENT);
  }

  @DisplayName("Checks that comments are printed in ioService")
  @Test
  void shouldShowComments() {
    List<Comment> comments = List.of(COMMENT);
    Book book = new Book("1L", TITLE, 2000, AUTHOR, GENRE, comments);
    doReturn(Optional.of(book)).when(booksDao).findById(anyString());
    List<Comment> result = commentsService.getBookComments(book.getId());
    assertThat(result).containsExactlyInAnyOrderElementsOf(comments);
  }

  @DisplayName("Checks that comment is deleted")
  @Test
  void shouldDeleteComment() {
    doReturn(Optional.of(COMMENT)).when(commentsDao).findById(anyString());
    commentsService.deleteComment(COMMENT.getId());
    verify(commentsDao, times(1)).delete(COMMENT);
  }

  @DisplayName("Checks that comment is updated")
  @Test
  void shouldUpdateComment() {
    String commentText = "Updated test";
    Comment expectedComment = new Comment(COMMENT.getId(), commentText, BOOK);
    doReturn(Optional.of(COMMENT)).when(commentsDao).findById(anyString());
    commentsService.updateComment(COMMENT.getId(), commentText);
    verify(commentsDao, times(1)).save(expectedComment);
  }
}
