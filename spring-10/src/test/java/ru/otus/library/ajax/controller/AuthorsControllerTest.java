package ru.otus.library.ajax.controller;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.library.ajax.controller.dto.AuthorDto;
import ru.otus.library.ajax.models.Author;
import ru.otus.library.ajax.service.AuthorsService;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = AuthorsController.class)
public class AuthorsControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private AuthorsService authorsService;

  @Test
  @DisplayName("Should return authors")
  void testFindAuthors() throws Exception {
    List<Author> authors = List.of(
            new Author("1", "Author 1"),
            new Author("2", "Author 2"),
            new Author("3", "Author 3")
    );
    doReturn(authors).when(authorsService).findAuthors();
    List<AuthorDto> expectedResult = authors.stream()
                                             .map(AuthorDto::toDto)
                                             .toList();
    mvc.perform(get("/authors"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("authors", expectedResult));
  }

  @Test
  @DisplayName("Should add author and redirect")
  void testAddAuthor() throws Exception {
    Author author = new Author("1", "Author 1");

    doNothing().when(authorsService).addAuthor(anyString());

    mvc.perform(
            post("/author")
                    .param("name", author.getName())
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/authors"));
  }

  @Test
  @DisplayName("Should show edit page")
  public void testShowEditPage() throws Exception {
    Author author = new Author("1", "Author");
    doReturn(author).when(authorsService).getAuthorById(anyString());

    AuthorDto expectedResult = AuthorDto.toDto(author);

    mvc.perform(get("/author/1"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("author", expectedResult));
  }

  @Test
  @DisplayName("Should edit author")
  public void testEditAuthor() throws Exception {
    doNothing().when(authorsService).updateAuthor(anyString(), anyString());
    mvc.perform(
            post("/author/1")
                    .param("name", "Some test")
                    .param("id", "1")
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/authors"));
  }
}
