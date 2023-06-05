package ru.otus.library.jpa.dao;

import ru.otus.library.jpa.models.Comment;

/**
 * CRUD operations with Comment.
 */
public interface CommentsDao {
  Comment save(Comment comment);

  Comment getById(long id);

  void update(Comment comment);

  void delete(Comment comment);
}
