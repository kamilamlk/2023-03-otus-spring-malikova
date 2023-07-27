package ru.otus.library.security.controller.flux;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.library.security.controller.dto.GenreDto;
import ru.otus.library.security.dao.flux.GenresFluxDao;

/**
 * Flux controller.
 */
@RestController
@RequiredArgsConstructor
public class GenresFluxController {
  private final GenresFluxDao genresDao;

  /**
   * Returns genres.
   */
  @GetMapping("/api/flux/genre")
  public Flux<GenreDto> findBooks() {
    return genresDao.findAll()
                   .map(GenreDto::toDto);
  }
}
