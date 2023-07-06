package ru.otus.library.ajax.service;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.library.ajax.dao.AuthorsDao;
import ru.otus.library.ajax.models.Author;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = AuthorsServiceImpl.class)
public class AuthorsServiceImplTest {
  private final Author AUTHOR = new Author("1", "Test Author");

  @MockBean
  private AuthorsDao authorsDao;

  @Autowired
  private AuthorsServiceImpl authorsService;

  @DisplayName("Prints authors in ioService")
  @Test
  void shouldPrintAuthors() {
    List<Author> authors = List.of(AUTHOR);
    doReturn(authors).when(authorsDao).findAll();

    List<Author> result = authorsService.findAuthors();
    assertThat(result).containsExactlyInAnyOrderElementsOf(authors);
  }

  @DisplayName("Saves new author")
  @Test
  void shouldSaveAuthor() {
    Author newAuthor = new Author("Sample Author");
    authorsService.addAuthor(newAuthor.getName());
    verify(authorsDao).save(newAuthor);
  }
}
