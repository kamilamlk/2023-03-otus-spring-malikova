package ru.otus.library.flux.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.flux.dao.BooksDao;
import ru.otus.library.flux.dao.CommentsDao;
import ru.otus.library.flux.exception.NotFoundException;
import ru.otus.library.flux.models.Book;
import ru.otus.library.flux.models.Comment;

/**
 * Implementation of service responsible for operations with Comments.
 */
@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {
  private final CommentsDao commentsDao;
  private final BooksDao booksDao;

  @Override
  public void addComment(String bookId, String commentText) {
    Optional<Book> book = booksDao.findById(bookId);

    if (book.isPresent()) {
      Comment comment = new Comment(null, commentText, book.get());
      commentsDao.save(comment);
    } else {
      throw new NotFoundException("No book found");
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<Comment> getBookComments(String bookId) {
    Optional<Book> book = booksDao.findById(bookId);
    if (book.isPresent()) {
      return new ArrayList<>(book.get().getComments());
    } else {
      throw new NotFoundException("No book found");
    }
  }

  @Override
  public Comment getComment(String commentId) {
    Optional<Comment> comment = commentsDao.findById(commentId);

    if (comment.isPresent()) {
      return comment.get();
    } else {
      throw new NotFoundException("No comment found");
    }
  }

  @Transactional
  @Override
  public void deleteComment(String commentId) {
    Optional<Comment> comment = commentsDao.findById(commentId);

    if (comment.isPresent()) {
      commentsDao.delete(comment.get());
    } else {
      throw new NotFoundException("No comment found");
    }
  }

  @Transactional
  @Override
  public void updateComment(String commentId, String commentText) {
    Optional<Comment> comment = commentsDao.findById(commentId);
    if (comment.isPresent()) {
      comment.get().setCommentText(commentText);
      commentsDao.save(comment.get());
    } else {
      throw new NotFoundException("No comment found");
    }
  }
}
