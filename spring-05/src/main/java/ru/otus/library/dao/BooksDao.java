package ru.otus.library.dao;

import java.util.List;
import ru.otus.library.models.Book;

/**
 * CRUD operations with Books.
 */
public interface BooksDao {
  long getNextId();

  void insert(Book book);

  Book getById(long id);

  void update(Book book);

  void deleteById(long id);

  List<Book> getAll();
}
