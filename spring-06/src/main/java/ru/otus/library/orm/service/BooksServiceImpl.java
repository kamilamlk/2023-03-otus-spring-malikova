package ru.otus.library.orm.service;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.orm.config.Default;
import ru.otus.library.orm.dao.BooksDao;
import ru.otus.library.orm.exception.NotFoundException;
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

  @Override
  public List<Book> findBooks() {
    return booksDao.getAll();
  }

  @Override
  public Book getBook(long bookId) {
    return booksDao.getById(bookId);
  }

  @Transactional
  @Override
  public void addBook(String title, int publicationYear, long authorId, long genreId) {
    Author author = authorsService.getAuthorById(authorId);
    Genre genre = genreService.getGenreById(genreId);

    Book book = new Book(Default.ZERO, title, publicationYear, author, genre, List.of());
    booksDao.save(book);
  }

  @Transactional
  @Override
  public void updateBook(
          long id,
          String title,
          int publicationYear,
          long authorId,
          long genreId
  ) throws NotFoundException {
    Book book = booksDao.getById(id);
    if (book == null) {
      throw new NotFoundException("No book found");
    }
    Book.BookBuilder builder = book.getBuilder();

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
}
