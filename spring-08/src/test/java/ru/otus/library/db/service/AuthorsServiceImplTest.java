package ru.otus.library.db.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.library.db.dao.AuthorsDao;
import ru.otus.library.db.models.Author;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

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
}
