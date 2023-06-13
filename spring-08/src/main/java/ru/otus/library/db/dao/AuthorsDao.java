package ru.otus.library.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.db.models.Author;

/**
 * CRUD operations with Author.
 */
public interface AuthorsDao extends JpaRepository<Author, Long> {
}
