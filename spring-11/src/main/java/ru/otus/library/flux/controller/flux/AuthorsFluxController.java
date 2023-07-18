package ru.otus.library.flux.controller.flux;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.flux.controller.dto.AuthorDto;
import ru.otus.library.flux.dao.flux.AuthorsFluxDao;

/**
 * Flux controller.
 */
@RequiredArgsConstructor
@RestController
public class AuthorsFluxController {
  private final AuthorsFluxDao authorsDao;

  /**
   * Returns all authors.
   */
  @GetMapping("/api/flux/author")
  public Flux<AuthorDto> findAuthors() {
    return authorsDao.findAll()
                   .map(AuthorDto::toDto);
  }

  /**
   * Edits or adds a new author.
   */
  @PostMapping("/api/flux/author")
  public Mono<AuthorDto> editAuthor(@RequestBody AuthorDto author) {
    return authorsDao.save(author.toAuthor()).map(AuthorDto::toDto);
  }

  /**
   * Returns the author.
   */
  @GetMapping("/api/flux/author/{id}")
  public Mono<AuthorDto> getAuthor(@PathVariable("id") String authorId) {
    return authorsDao.findById(authorId).map(AuthorDto::toDto);
  }
}
