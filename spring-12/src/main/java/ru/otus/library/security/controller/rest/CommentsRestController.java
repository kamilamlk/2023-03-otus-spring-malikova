package ru.otus.library.security.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.library.security.controller.dto.BookDto;
import ru.otus.library.security.controller.dto.CommentDto;
import ru.otus.library.security.service.CommentsService;

/**
 * Rest controller.
 */
@RequiredArgsConstructor
@RestController
public class CommentsRestController {
  private final CommentsService commentsService;

  /**
   * Edits a comment.
   */
  @PostMapping("/api/book/{id}/comment")
  public BookDto editComment(@PathVariable("id") String bookId,
                             @RequestBody CommentDto comment) {
    if (comment.getId() == null) {
      commentsService.addComment(bookId, comment.getCommentText());
    } else {
      commentsService.updateComment(comment.getId(), comment.getCommentText());
    }
    return new BookDto(bookId);
  }

  /**
   * Delete book comment.
   */
  @DeleteMapping("/api/book/{id}/comment/{commentId}")
  public String deleteComment(@PathVariable("id") String bookId,
                              @PathVariable("commentId") String commentId) {
    commentsService.deleteComment(commentId);
    return bookId;
  }
}
