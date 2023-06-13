package ru.otus.library.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.db.models.Genre;

/**
 * CRUD operations with Genres.
 */
public interface GenresDao extends JpaRepository<Genre, Long> {
}
