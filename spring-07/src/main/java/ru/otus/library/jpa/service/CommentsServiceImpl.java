package ru.otus.library.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
  public List<Comment> getBookComments(long bookId) throws NotFoundException {
    Optional<Book> book = booksDao.findById(bookId);
    if (book.isPresent()) {
      return new ArrayList<>(book.get().getComments());
    } else {
      throw new NotFoundException("No book found");
    }
  }

  @Transactional
  @Override
  public void deleteComment(long commentId) throws NotFoundException {
    Optional<Comment> comment = commentsDao.findById(commentId);

    if (comment.isPresent()) {
      commentsDao.delete(comment.get());
    } else {
      throw new NotFoundException("No comment found");
    }
  }

  @Transactional
  @Override
  public void updateComment(long commentId, String commentText) throws NotFoundException {
    Optional<Comment> comment = commentsDao.findById(commentId);
    if (comment.isPresent()) {
      comment.get().setCommentText(commentText);
      commentsDao.save(comment.get());
    } else {
      throw new NotFoundException("No comment found");
    }
  }
}
