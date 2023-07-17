package ru.otus.library.flux.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.library.flux.controller.dto.CommentDto;
import ru.otus.library.flux.service.CommentsService;

/**
 * Controller for operations with comment.
 */
@Controller
@AllArgsConstructor
public class CommentsController {
  private final CommentsService commentsService;

  /**
   * Shows a comment.
   */
  @GetMapping("/book/{id}/comment/{commentId}")
  public String showCommentEditPage(@PathVariable("id") String bookId,
                                    @PathVariable("commentId") String commentId,
                                    Model model) {
    CommentDto comment = CommentDto.toDto(commentsService.getComment(commentId));
    model.addAttribute("comment", comment);
    model.addAttribute("id", bookId);
    return "edit-comment";
  }

  @PostMapping("/book/{id}/comment/{commentId}")
  public String editComment(@PathVariable("id") String bookId,
                            @PathVariable("commentId") String commentId,
                            @ModelAttribute CommentDto commentDto) {
    commentsService.updateComment(commentId, commentDto.getCommentText());
    return String.format("redirect:/book/%s", bookId);
  }

  @PostMapping(value = "/book/{id}/comment")
  public String addComment(@PathVariable("id") String bookId,
                           @ModelAttribute CommentDto comment) {
    commentsService.addComment(bookId, comment.getCommentText());
    return String.format("redirect:/book/%s", bookId);
  }

  /**
   * Delete book comment.
   */
  @GetMapping("/book/{id}/comment/{commentId}/delete")
  public String deleteComment(@PathVariable("id") String bookId,
                              @PathVariable("commentId") String commentId) {
    commentsService.deleteComment(commentId);
    return String.format("redirect:/book/%s", bookId);
  }
}
