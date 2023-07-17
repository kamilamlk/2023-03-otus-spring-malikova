package ru.otus.library.flux.controller.rest;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.library.flux.controller.dto.AuthorDto;
import ru.otus.library.flux.service.AuthorsService;

/**
 * Rest controller.
 */
@RequiredArgsConstructor
@RestController
public class AuthorsRestController {
  private final AuthorsService authorsService;

  /**
   * Returns all authors.
   */
  @GetMapping("/api/author")
  public List<AuthorDto> findAuthors() {
    return authorsService.findAuthors()
                   .stream()
                   .map(AuthorDto::toDto).toList();
  }

  /**
   * Edits or adds a new author.
   */
  @PostMapping("/api/author")
  public String editAuthor(@RequestBody AuthorDto author) {
    if (author.getId() == null) {
      return authorsService.addAuthor(author.getName());
    } else {
      authorsService.updateAuthor(author.getId(), author.getName());
      return author.getId();
    }
  }

  /**
   * Returns the author.
   */
  @GetMapping("/api/author/{id}")
  public AuthorDto getAuthor(@PathVariable("id") String authorId) {
    return AuthorDto.toDto(authorsService.getAuthorById(authorId));
  }
}
