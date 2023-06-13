package ru.otus.library.db.service;

import ru.otus.library.db.exception.NotFoundException;
import ru.otus.library.db.models.Comment;
import java.util.List;

/**
 * Service responsible for operations with Comments.
 */
public interface CommentsService {
  void addComment(long bookId, String comment) throws NotFoundException;

  List<Comment> getBookComments(long bookId) throws NotFoundException;

  void deleteComment(long commentId) throws NotFoundException;

  void updateComment(long commentId, String commentText) throws NotFoundException;
}
