package ru.otus.library.jpa.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.jpa.dao.AuthorsDao;
import ru.otus.library.jpa.exception.NotFoundException;
import ru.otus.library.jpa.models.Author;

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
  public Author getAuthorById(long authorId) throws NotFoundException {
    return authorsDao.findById(authorId)
                   . orElseThrow(() -> new NotFoundException("Author is not found"));
  }
}
