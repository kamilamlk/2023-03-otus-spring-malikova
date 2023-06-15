package ru.otus.library.db.service;

import java.util.List;
import ru.otus.library.db.exception.NotFoundException;
import ru.otus.library.db.models.Comment;

/**
 * Service responsible for operations with Comments.
 */
public interface CommentsService {
  void addComment(String bookId, String comment) throws NotFoundException;

  List<Comment> getBookComments(String bookId) throws NotFoundException;

  void deleteComment(String commentId) throws NotFoundException;

  void updateComment(String commentId, String commentText) throws NotFoundException;
}
