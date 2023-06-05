package ru.otus.library.jpa.service;

import java.util.List;
import ru.otus.library.jpa.exception.NotFoundException;
import ru.otus.library.jpa.models.Comment;

/**
 * Service responsible for operations with Comments.
 */
public interface CommentsService {
  void addComment(long bookId, String comment) throws NotFoundException;

  List<Comment> getBookComments(long bookId);

  void deleteComment(long commentId) throws NotFoundException;

  void updateComment(long commentId, String commentText) throws NotFoundException;
}
