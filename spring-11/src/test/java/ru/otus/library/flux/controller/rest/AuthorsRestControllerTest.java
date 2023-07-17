package ru.otus.library.flux.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.library.flux.controller.dto.AuthorDto;
import ru.otus.library.flux.models.Author;
import ru.otus.library.flux.service.AuthorsService;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = AuthorsRestController.class)
public class AuthorsRestControllerTest {
  @Autowired
  private MockMvc mvc;
  @Autowired
  private ObjectMapper mapper;

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
    mvc.perform(get("/api/author"))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
  }

  @Test
  @DisplayName("Should add author")
  void testAddAuthor() throws Exception {
    String id = "1";
    Author author = new Author(null, "Author 1");

    doReturn(id).when(authorsService).addAuthor(anyString());

    mvc.perform(
            post("/api/author")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(author))
            )
            .andExpect(status().isOk())
            .andExpect(content().string(id));
  }

  @Test
  @DisplayName("Should return the author")
  public void testGetAuthor() throws Exception {
    Author author = new Author("1", "Author");
    doReturn(author).when(authorsService).getAuthorById(anyString());

    AuthorDto expectedResult = AuthorDto.toDto(author);

    mvc.perform(get("/api/author/1"))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
  }

  @Test
  @DisplayName("Should edit author")
  public void testEditAuthor() throws Exception {
    String id = "1";
    Author author = new Author(id, "Author 1");

    doNothing().when(authorsService).updateAuthor(anyString(), anyString());

    mvc.perform(
            post("/api/author")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(author))
            )
            .andExpect(status().isOk())
            .andExpect(content().string(id));
  }
}
