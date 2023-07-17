package ru.otus.library.flux.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.flux.dao.AuthorsDao;
import ru.otus.library.flux.exception.NotFoundException;
import ru.otus.library.flux.models.Author;

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
  public String addAuthor(String authorName) {
    Author author = new Author(authorName);
    Author resultingAuthor = authorsDao.save(author);
    return resultingAuthor.getId();
  }

  @Override
  public void updateAuthor(String authorId, String authorName) {
    Author author = getAuthorById(authorId);
    author.setName(authorName);
    authorsDao.save(author);
  }
}
