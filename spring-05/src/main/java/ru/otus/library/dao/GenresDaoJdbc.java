package ru.otus.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.models.Genre;

/**
 * Implementation of CRUD operations with Genres.
 */
@Repository
public class GenresDaoJdbc implements GenresDao {
  private final NamedParameterJdbcOperations jdbcOperation;

  public GenresDaoJdbc(NamedParameterJdbcOperations jdbcOperation) {
    this.jdbcOperation = jdbcOperation;
  }

  @Override
  public List<Genre> getAll() {
    return jdbcOperation.query("select id, genre_name from genres", new GenreMapper());
  }

  @Override
  public Genre getById(long id) {
    Map<String, Object> params = Map.of("id", id);
    return jdbcOperation.queryForObject(
            "select id, genre_name from genres where id = :id",
            params,
            new GenreMapper()
    );
  }

  private static class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
      long id = rs.getLong("id");
      String name = rs.getString("genre_name");
      return new Genre(id, name);
    }
  }
}
