package ru.otus.library.orm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.orm.models.Author;
import ru.otus.library.orm.models.Book;
import ru.otus.library.orm.models.Genre;

/**
 * Implementation of CRUD operations with Books.
 */
@Repository
public class BooksDaoJdbc implements BooksDao {
  private final JdbcOperations jdbc;
  private final NamedParameterJdbcOperations jdbcOperation;

  public BooksDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations jdbcOperation) {
    this.jdbc = jdbc;
    this.jdbcOperation = jdbcOperation;
  }

  @Override
  public long getNextId() {
    Long maxId = jdbc.queryForObject("select max(id) from books", Long.class);
    return maxId != null ? maxId + 1 : 1;
  }

  @Override
  public void insert(Book book) {
    Map<String, Object> params = Map.of(
            "id", book.getId(),
            "title", book.getTitle(),
            "publication_year", book.getPublicationYear(),
            "author_id", book.getAuthor().getId(),
            "genre_id", book.getGenre().getId()
    );

    jdbcOperation.update(
            """
            insert into books ( id,  title,  publication_year,  author_id,  genre_id)
                       values (:id, :title, :publication_year, :author_id, :genre_id)
            """, params
    );
  }

  @Override
  public Book getById(long id) {
    Map<String, Object> params = Map.of("id", id);

    return jdbcOperation.queryForObject(
            """
            select b.id, b.title, b.publication_year,
                   b.author_id, a.author_name,
                   b.genre_id, g.genre_name 
              from books b
              join authors a
                on a.id = b.author_id
              join genres g 
                on g.id = b.genre_id  
             where b.id = :id
             """,
            params,
            new BookMapper()
    );
  }

  @Override
  public void update(Book book) {
    Map<String, Object> params = Map.of("id", book.getId(),
            "title", book.getTitle(),
            "publication_year", book.getPublicationYear(),
            "author_id", book.getAuthor().getId(),
            "genre_id", book.getGenre().getId()
    );

    jdbcOperation.update(
           """
           update books
              set title            = :title,
                  publication_year = :publication_year,
                  author_id        = :author_id,
                  genre_id         = :genre_id
             where id = :id
           """, params
    );
  }

  @Override
  public void deleteById(long id) {
    Map<String, Object> params = Map.of("id", id);

    jdbcOperation.update("delete from books where id = :id", params);
  }

  @Override
  public List<Book> getAll() {
    return jdbcOperation.query(
            """
            select b.id, b.title, b.publication_year,
                   b.author_id, a.author_name,
                   b.genre_id, g.genre_name 
              from books b
              join authors a
                on a.id = b.author_id
              join genres g 
                on g.id = b.genre_id  
            """, new BookMapper()
    );
  }

  private static class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
      long id = rs.getLong("id");
      String title = rs.getString("title");
      int publicationYear = rs.getInt("publication_year");
      Author author = new Author(
              rs.getLong("author_id"),
              rs.getString("author_name")
      );

      Genre genre = new Genre(
              rs.getLong("genre_id"),
              rs.getString("genre_name")
      );

      return new Book(id, title, publicationYear, author, genre);
    }

  }

}
