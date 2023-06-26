package ru.otus.library.db.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.db.dao.AuthorsDao;
import ru.otus.library.db.exception.NotFoundException;
import ru.otus.library.db.models.Author;

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
  public Author getAuthorById(String authorId) {
    return authorsDao.findById(authorId)
                   .orElseThrow(() -> new NotFoundException("Author is not found"));
  }

  @Override
  public void addAuthor(String authorName) {
    Author author = new Author(authorName);
    authorsDao.save(author);
  }

  @Override
  public void updateAuthor(String authorId, String authorName) {
    Author author = getAuthorById(authorId);
    author.setName(authorName);
    authorsDao.save(author);
  }
}
