package ru.otus.library.flux.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.library.flux.controller.dto.CommentDto;
import ru.otus.library.flux.models.Comment;
import ru.otus.library.flux.service.CommentsService;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = CommentsController.class)
public class CommentsControllerTest {
  @Autowired
  private MockMvc mvc;
  @MockBean
  private CommentsService commentsService;

  @Test
  @DisplayName("Should show comment page")
  public void testShowEditPage() throws Exception {
    Comment comment = new Comment("1", "Comment", null);
    doReturn(comment).when(commentsService).getComment(anyString());

    CommentDto expectedResult = CommentDto.toDto(comment);

    mvc.perform(get("/book/1/comment/1"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("comment", expectedResult))
            .andExpect(model().attribute("id", "1"));
  }

  @Test
  @DisplayName("Should edit comment")
  public void testEditComment() throws Exception {
    doNothing().when(commentsService).updateComment(anyString(), anyString());
    mvc.perform(
                    post("/book/1/comment/1")
                            .param("commentText", "Some test")
                            .param("id", "1")
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/book/1"));
  }

  @Test
  @DisplayName("Should add comment")
  void testAddComment() throws Exception {
    CommentDto commentDto = new CommentDto(null, "comment");
    doNothing().when(commentsService).addComment(anyString(), anyString());

    mvc.perform(
            post("/book/1/comment")
                    .param("comment.commentText", commentDto.getCommentText())
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/book/1"));
  }

  @Test
  @DisplayName("Should delete comment and redirect")
  void testDeleteComment() throws Exception {
    doNothing().when(commentsService).deleteComment(anyString());


    mvc.perform(get("/book/1/comment/1/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/book/1"));
  }
}
