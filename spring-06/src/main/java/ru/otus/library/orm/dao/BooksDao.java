package ru.otus.library.orm.dao;

import java.util.List;
import ru.otus.library.orm.models.Book;

/**
 * CRUD operations with Books.
 */
public interface BooksDao {
  Book save(Book book);

  Book getById(long id);

  void update(Book book);

  void deleteById(long id);

  List<Book> getAll();
}
