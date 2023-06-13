package ru.otus.library.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.db.models.Comment;

/**
 * CRUD operations with Comment.
 */
public interface CommentsDao extends JpaRepository<Comment, Long> {
}
