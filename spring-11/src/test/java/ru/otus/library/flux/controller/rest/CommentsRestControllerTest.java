package ru.otus.library.flux.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.library.flux.controller.dto.BookDto;
import ru.otus.library.flux.controller.dto.CommentDto;
import ru.otus.library.flux.service.CommentsService;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = CommentsRestController.class)
public class CommentsRestControllerTest {
  @Autowired
  private MockMvc mvc;
  @Autowired
  ObjectMapper mapper;
  @MockBean
  private CommentsService commentsService;

  @Test
  @DisplayName("Should edit comment")
  public void testEditComment() throws Exception {
    CommentDto comment = new CommentDto("1", "Some comment");

    doNothing().when(commentsService).updateComment(anyString(), anyString());
    mvc.perform(
            post("/api/book/1/comment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(comment))
            )
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(new BookDto("1"))));
  }

  @Test
  @DisplayName("Should add comment")
  void testAddComment() throws Exception {
    CommentDto comment = new CommentDto(null, "comment");
    doNothing().when(commentsService).addComment(anyString(), anyString());

    mvc.perform(
            post("/api/book/1/comment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(comment))
            )
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(new BookDto("1"))));
  }

  @Test
  @DisplayName("Should delete comment and redirect")
  void testDeleteComment() throws Exception {
    doNothing().when(commentsService).deleteComment(anyString());

    mvc.perform(delete("/api/book/1/comment/1"))
            .andExpect(status().isOk())
            .andExpect(content().string("1"));
  }
}
