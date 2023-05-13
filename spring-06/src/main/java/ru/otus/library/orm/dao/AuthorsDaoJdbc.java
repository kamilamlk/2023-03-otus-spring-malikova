package ru.otus.library.orm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.orm.models.Author;

/**
 * Implementation of CRUD operations with Author.
 */
@Repository
public class AuthorsDaoJdbc implements AuthorsDao {
  private final NamedParameterJdbcOperations jdbcOperation;

  public AuthorsDaoJdbc(NamedParameterJdbcOperations jdbcOperation) {
    this.jdbcOperation = jdbcOperation;
  }

  @Override
  public List<Author> getAll() {
    return jdbcOperation.query("select id, author_name from authors", new AuthorMapper());
  }

  @Override
  public Author getById(long id) {
    Map<String, Object> params = Map.of("id", id);
    return jdbcOperation.queryForObject(
            "select id, author_name from authors where id = :id",
            params,
            new AuthorMapper()
    );
  }

  private static class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
      long id = rs.getLong("id");
      String name = rs.getString("author_name");
      return new Author(id, name);
    }
  }
}
