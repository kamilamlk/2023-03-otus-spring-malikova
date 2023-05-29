package ru.otus.library.orm.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.orm.config.Default;
import ru.otus.library.orm.dao.BooksDao;
import ru.otus.library.orm.dao.CommentsDao;
import ru.otus.library.orm.exception.NotFoundException;
import ru.otus.library.orm.models.Book;
import ru.otus.library.orm.models.Comment;

/**
 * Implementation of service responsible for operations with Comments.
 */
@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {
  private static final String BOOKS_COMMENTS_GRAPH = "book-comments-entity-graph";

  private final CommentsDao commentsDao;
  private final BooksDao booksDao;

  @Transactional
  @Override
  public void addComment(long bookId, String commentText) throws NotFoundException {
    Book book = booksDao.getById(bookId);
    if (book != null) {
      Comment comment = new Comment(Default.ZERO, commentText, book);
      commentsDao.save(comment);
    } else {
      throw new NotFoundException("No book found");
    }
  }

  @Override
  public List<Comment> getBookComments(long bookId) {
    return booksDao.getByIdEagerly(bookId, BOOKS_COMMENTS_GRAPH).getComments();
  }

  @Transactional
  @Override
  public void deleteComment(long commentId) throws NotFoundException {
    Comment comment = commentsDao.getById(commentId);
    if (comment != null) {
      commentsDao.delete(comment);
    } else {
      throw new NotFoundException("No comment found");
    }
  }

  @Transactional
  @Override
  public void updateComment(long commentId, String commentText) throws NotFoundException {
    Comment comment = commentsDao.getById(commentId);
    if (comment != null) {
      comment.setCommentText(commentText);
      commentsDao.update(comment);
    } else {
      throw new NotFoundException("No comment found");
    }
  }
}
