package ru.otus.library.orm.service;

/**
 * Service responsible for operations with Comments.
 */
public interface CommentsService {
  void addComment(long bookId, String comment);

  void showBookComments(long bookId);

  void deleteComment(long commentId);

  void updateComment(long commentId, String commentText);
}
