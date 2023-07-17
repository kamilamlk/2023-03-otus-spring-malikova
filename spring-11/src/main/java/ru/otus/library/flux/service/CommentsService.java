package ru.otus.library.flux.service;

import java.util.List;
import ru.otus.library.flux.exception.NotFoundException;
import ru.otus.library.flux.models.Comment;

/**
 * Service responsible for operations with Comments.
 */
public interface CommentsService {
  void addComment(String bookId, String comment) throws NotFoundException;

  List<Comment> getBookComments(String bookId) throws NotFoundException;

  Comment getComment(String commentId);

  void deleteComment(String commentId) throws NotFoundException;

  void updateComment(String commentId, String commentText) throws NotFoundException;
}
