package ru.otus.library.db.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.db.config.Default;
import ru.otus.library.db.dao.BooksDao;
import ru.otus.library.db.dao.CommentsDao;
import ru.otus.library.db.exception.NotFoundException;
import ru.otus.library.db.models.Book;
import ru.otus.library.db.models.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of service responsible for operations with Comments.
 */
@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {
  private final CommentsDao commentsDao;
  private final BooksDao booksDao;

  @Override
  public void addComment(long bookId, String commentText) {
    Optional<Book> book = booksDao.findById(bookId);
    if (book.isPresent()) {
      Comment comment = new Comment(Default.ZERO, commentText, book.get());
      commentsDao.save(comment);
    } else {
      throw new NotFoundException("No book found");
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<Comment> getBookComments(long bookId) {
    Optional<Book> book = booksDao.findById(bookId);
    if (book.isPresent()) {
      return new ArrayList<>(book.get().getComments());
    } else {
      throw new NotFoundException("No book found");
    }
  }

  @Transactional
  @Override
  public void deleteComment(long commentId) {
    Optional<Comment> comment = commentsDao.findById(commentId);

    if (comment.isPresent()) {
      commentsDao.delete(comment.get());
    } else {
      throw new NotFoundException("No comment found");
    }
  }

  @Transactional
  @Override
  public void updateComment(long commentId, String commentText) {
    Optional<Comment> comment = commentsDao.findById(commentId);
    if (comment.isPresent()) {
      comment.get().setCommentText(commentText);
      commentsDao.save(comment.get());
    } else {
      throw new NotFoundException("No comment found");
    }
  }
}
