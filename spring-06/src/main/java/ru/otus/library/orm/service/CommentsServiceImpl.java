package ru.otus.library.orm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.orm.config.Default;
import ru.otus.library.orm.dao.BooksDao;
import ru.otus.library.orm.dao.CommentsDao;
import ru.otus.library.orm.models.Book;
import ru.otus.library.orm.models.Comment;

/**
 * Implementation of service responsible for operations with Comments.
 */
@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {
  private final CommentsDao commentsDao;
  private final BooksDao booksDao;

  private final IoService ioService;

  @Transactional
  @Override
  public void addComment(long bookId, String commentText) {
    Book book = booksDao.getById(bookId);
    if (book != null) {
      Comment comment = new Comment(Default.ZERO, commentText, book);
      commentsDao.save(comment);
    } else {
      ioService.writeLine("No book found");
    }
  }

  @Override
  public void showBookComments(long bookId) {
    Book book = booksDao.getById(bookId);
    ioService.writeComments(book.getComments());
  }

  @Transactional
  @Override
  public void deleteComment(long commentId) {
    Comment comment = commentsDao.getById(commentId);
    commentsDao.delete(comment);
  }

  @Transactional
  @Override
  public void updateComment(long commentId, String commentText) {
    Comment comment = commentsDao.getById(commentId);
    if (comment != null) {
      comment.setCommentText(commentText);
      commentsDao.update(comment);
    } else {
      ioService.writeLine("No comment found");
    }
  }
}
