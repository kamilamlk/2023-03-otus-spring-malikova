package ru.otus.library.mvc.mongo.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import ru.otus.library.mvc.dao.AuthorsDao;
import ru.otus.library.mvc.dao.BooksDao;
import ru.otus.library.mvc.dao.CommentsDao;
import ru.otus.library.mvc.dao.GenresDao;
import ru.otus.library.mvc.models.Author;
import ru.otus.library.mvc.models.Book;
import ru.otus.library.mvc.models.Comment;
import ru.otus.library.mvc.models.Genre;

/**
 * Change log.
 */
@ChangeLog(order = "001")
public class InitDbDataChangeLog {
  private static final Genre GENRE = new Genre("1", "FICTION");
  private static final Author AUTHOR = new Author("1", "Stephen King");
  private static final Book BOOK = new Book(
          "1",
          "The Shining",
          1977,
          AUTHOR,
          GENRE,
          List.of()
  );

  /**
   * Drops database.
   */
  @ChangeSet(order = "00", id = "dropDb", author = "admin", runAlways = true)
  public void dropDb(MongoDatabase database) {
    database.drop();
  }

  /**
   * Inits author's collection.
   */
  @ChangeSet(order = "01", id = "initAuthors", author = "admin", runAlways = true)
  public void initAuthors(AuthorsDao authorsDao) {
    List<Author> authors = List.of(
            AUTHOR,
            new Author("2", "George Orwell"),
            new Author("3", "Hilary Mantel"),
            new Author("4", "Frank Herbert"),
            new Author("5", "Joanne Rowling"),
            new Author("6", "Agatha Christie"));

    authorsDao.saveAll(authors);
  }

  /**
   * Inits genre's collection.
   */
  @ChangeSet(order = "02", id = "initGenres", author = "admin", runAlways = true)
  public void initGenres(GenresDao genresDao) {
    List<Genre> genres = List.of(
            GENRE,
            new Genre("2", "NON-FICTION"),
            new Genre("3", "HISTORICAL FICTION"),
            new Genre("4", "SCIENCE FICTION"),
            new Genre("5", "NOVEL"));

    genresDao.saveAll(genres);
  }

  /**
   * Inits book's collection.
   */
  @ChangeSet(order = "03", id = "initBooks", author = "admin", runAlways = true)
  public void initBooks(BooksDao booksDao) {
    booksDao.save(BOOK);
  }

  /**
   * Inits comment's collection.
   */
  @ChangeSet(order = "04", id = "initComments", author = "admin", runAlways = true)
  public void initComments(CommentsDao commentsDao, BooksDao booksDao) {
    Comment comment = new Comment("1", "Fascinating book", BOOK);
    commentsDao.save(comment);
  }
}
