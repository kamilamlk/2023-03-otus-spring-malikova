package ru.otus.library.jpa.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.jpa.config.Default;
import ru.otus.library.jpa.dao.BooksDao;
import ru.otus.library.jpa.dao.CommentsDao;
import ru.otus.library.jpa.exception.NotFoundException;
import ru.otus.library.jpa.models.Book;
import ru.otus.library.jpa.models.Comment;

/**
 * Implementation of service responsible for operations with Comments.
 */
@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {
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

  @Transactional(readOnly = true)
  @Override
  public List<Comment> getBookComments(long bookId) {
    Book book = booksDao.getById(bookId);
    // second option
    // List<Comment> comments = book.getComments();
    // Hibernate.initialize(comments);
    List<Comment> comments = new ArrayList<>(book.getComments());
    return comments;
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
