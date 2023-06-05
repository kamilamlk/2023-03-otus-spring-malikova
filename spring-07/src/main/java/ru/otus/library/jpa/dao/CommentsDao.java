package ru.otus.library.jpa.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.jpa.models.Comment;

/**
 * CRUD operations with Comment.
 */
public interface CommentsDao extends JpaRepository<Comment, Long> {

  Optional<Comment> findById(long id);

  void deleteById(Long id);
}
