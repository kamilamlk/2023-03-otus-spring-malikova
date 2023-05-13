package ru.otus.library.orm.service;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.orm.config.Default;
import ru.otus.library.orm.dao.BooksDao;
import ru.otus.library.orm.models.Author;
import ru.otus.library.orm.models.Book;
import ru.otus.library.orm.models.Genre;

/**
 * Implementation of service responsible for operations with Books.
 */
@Service
@AllArgsConstructor
public class BooksServiceImpl implements BooksService {
  private final BooksDao booksDao;

  private final AuthorsService authorsService;
  private final GenresService genreService;
  private final IoService ioService;

  @Override
  public void showBooks() {
    List<Book> books = booksDao.getAll();
    ioService.writeBooks(books);
  }

  @Override
  public void showBook(long bookId) {
    Book book = booksDao.getById(bookId);
    ioService.writeBook(book);
  }

  @Override
  public void addBook(String title, int publicationYear, long authorId, long genreId) {
    long id = booksDao.getNextId();
    Author author = authorsService.getAuthorById(authorId);
    Genre genre = genreService.getGenreById(genreId);

    Book book = new Book(id, title, publicationYear, author, genre);
    booksDao.insert(book);
  }

  @Override
  public void updateBook(
          long id,
          String title,
          int publicationYear,
          long authorId,
          long genreId
  ) {
    Book book = booksDao.getById(id);

    BookBuilder builder = new BookBuilder(book);
    if (!Objects.equals(title, Default.NONE)) {
      builder.title(title);
    }

    if (publicationYear != Default.ZERO) {
      builder.publicationYear(publicationYear);
    }

    if (authorId != Default.ZERO) {
      Author author = authorsService.getAuthorById(authorId);
      builder.author(author);
    }

    if (genreId != Default.ZERO) {
      Genre genre = genreService.getGenreById(genreId);
      builder.genre(genre);
    }

    Book resultingBook = builder.build();
    booksDao.update(resultingBook);
  }

  private static class BookBuilder {
    private final Long id;
    private String title;
    private int publicationYear;
    private Author author;
    private Genre genre;

    BookBuilder(Book book) {
      id = book.getId();
      title = book.getTitle();
      publicationYear = book.getPublicationYear();
      author = book.getAuthor();
      genre = book.getGenre();
    }

    BookBuilder title(String title) {
      this.title = title;
      return this;
    }

    BookBuilder publicationYear(int publicationYear) {
      this.publicationYear = publicationYear;
      return this;
    }

    BookBuilder author(Author author) {
      this.author = author;
      return this;
    }

    BookBuilder genre(Genre genre) {
      this.genre = genre;
      return this;
    }

    Book build() {
      return new Book(id, title, publicationYear, author, genre);
    }
  }
}
