package ru.otus.library.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.jpa.models.Genre;

/**
 * CRUD operations with Genres.
 */
public interface GenresDao extends JpaRepository<Genre, Long> {
}
