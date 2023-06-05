package ru.otus.library.jpa.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.library.jpa.models.Genre;

/**
 * CRUD operations with Genres.
 */
public interface GenresDao extends JpaRepository<Genre, Long> {

  List<Genre> findAll();

  Optional<Genre> findById(long id);

  @Modifying
  @Query("update Genre g set g.name = :name where g.id = :id")
  void update(@Param("id") long id, @Param("name") String name);
}
