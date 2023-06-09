package ru.otus.library.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.jpa.models.Author;

/**
 * CRUD operations with Author.
 */
public interface AuthorsDao extends JpaRepository<Author, Long> {
}
