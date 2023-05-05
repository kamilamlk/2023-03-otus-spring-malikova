package ru.otus.library.service;

/**
 * Service responsible for operations with Books.
 */
public interface BooksService {
  void showBooks();

  void showBook(long bookId);

  void addBook(String title, int publicationYear, long authorId, long genreId);

  void updateBook(
          long id,
          String title,
          int publicationYear,
          long authorId,
          long genreId
  );
}
