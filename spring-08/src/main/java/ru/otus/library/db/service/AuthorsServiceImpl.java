package ru.otus.library.db.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.db.dao.AuthorsDao;
import ru.otus.library.db.exception.NotFoundException;
import ru.otus.library.db.models.Author;
import java.util.List;

/**
 * Implementation of service responsible for operations with Authors.
 */
@Service
@AllArgsConstructor
public class AuthorsServiceImpl implements AuthorsService {
  private final AuthorsDao authorsDao;

  @Override
  public List<Author> findAuthors() {
    return authorsDao.findAll();
  }

  @Override
  public Author getAuthorById(long authorId) {
    return authorsDao.findById(authorId)
                   . orElseThrow(() -> new NotFoundException("Author is not found"));
  }
}
