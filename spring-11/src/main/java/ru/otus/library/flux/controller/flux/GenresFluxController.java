package ru.otus.library.flux.controller.flux;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.library.flux.controller.dto.GenreDto;
import ru.otus.library.flux.dao.flux.GenresFluxDao;

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
