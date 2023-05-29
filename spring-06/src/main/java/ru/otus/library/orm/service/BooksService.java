package ru.otus.library.orm.service;

import java.util.List;
import ru.otus.library.orm.exception.NotFoundException;
import ru.otus.library.orm.models.Book;

/**
 * Service responsible for operations with Books.
 */
public interface BooksService {
  List<Book> findBooks();

  Book getBook(long bookId);

  void addBook(String title, int publicationYear, long authorId, long genreId);

  void updateBook(
          long id,
          String title,
          int publicationYear,
          long authorId,
          long genreId
  ) throws NotFoundException;
}
