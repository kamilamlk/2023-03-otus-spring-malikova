package ru.otus.library.orm.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.library.orm.dao.BooksDao;
import ru.otus.library.orm.dao.CommentsDao;
import ru.otus.library.orm.models.Author;
import ru.otus.library.orm.models.Book;
import ru.otus.library.orm.models.Comment;
import ru.otus.library.orm.models.Genre;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = CommentsServiceImpl.class)
public class CommentsServiceImplTest {
  private final String COMMENT_TEXT = "Test comment";
  private final String TITLE = "Test book";
  private final Author AUTHOR = new Author(1L, "Test Author");
  private final Genre GENRE = new Genre(1L, "Test Genre");
  private final Book BOOK = new Book(1L, TITLE, 2000, AUTHOR, GENRE);
  private final Comment COMMENT = new Comment(0L, COMMENT_TEXT, BOOK);

  @MockBean
  private CommentsDao commentsDao;
  @MockBean
  private BooksDao booksDao;
  @MockBean
  private IoService ioService;

  @Autowired
  private CommentsServiceImpl commentsService;

  @DisplayName("Checks that comment is added")
  @Test
  void shouldAddComment() {
    doReturn(BOOK).when(booksDao).getById(anyLong());
    doReturn(COMMENT).when(commentsDao).save(any());
    commentsService.addComment(BOOK.getId(), COMMENT_TEXT);
    verify(commentsDao, times(1)).save(COMMENT);
  }

  @DisplayName("Checks that comments are printed in ioService")
  @Test
  void shouldShowComments() {
    List<Comment> comments = List.of(COMMENT);
    doReturn(comments).when(commentsDao).findByBookId(anyLong());
    commentsService.showBookComments(BOOK.getId());
    verify(ioService, times(1)).writeComments(comments);
  }

  @DisplayName("Checks that comment is deleted")
  @Test
  void shouldDeleteComment() {
    commentsService.deleteComment(COMMENT.getId());
    verify(commentsDao, times(1)).deleteById(COMMENT.getId());
  }

  @DisplayName("Checks that comment is updated")
  @Test
  void shouldUpdateComment() {
    String commentText = "Updated test";
    Comment expectedComment = new Comment(COMMENT.getId(), commentText, BOOK);
    doReturn(COMMENT).when(commentsDao).getById(anyLong());
    commentsService.updateComment(COMMENT.getId(), commentText);
    verify(commentsDao, times(1)).update(expectedComment);
  }
}
