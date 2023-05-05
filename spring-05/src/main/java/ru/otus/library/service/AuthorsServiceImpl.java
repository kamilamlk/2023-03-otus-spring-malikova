package ru.otus.library.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dao.AuthorsDao;
import ru.otus.library.models.Author;


/**
 * Implementation of service responsible for operations with Authors.
 */
@Service
@AllArgsConstructor
public class AuthorsServiceImpl implements AuthorsService {
  private final AuthorsDao authorsDao;
  private final IoService ioService;

  @Override
  public void showAuthors() {
    List<Author> authors = authorsDao.getAll();
    authors.forEach(ioService::writeAuthor);
  }

  @Override
  public Author getAuthorById(long authorId) {
    return authorsDao.getById(authorId);
  }
}
