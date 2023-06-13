package ru.otus.library.db.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.db.models.Book;
import java.util.List;

/**
 * CRUD operations with Books.
 */
public interface BooksDao extends JpaRepository<Book, Long> {
  @Override
  @EntityGraph(attributePaths = {"genre", "author"})
  List<Book> findAll();
}
