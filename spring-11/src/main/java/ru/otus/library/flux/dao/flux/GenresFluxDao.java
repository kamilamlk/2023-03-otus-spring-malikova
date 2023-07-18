package ru.otus.library.flux.dao.flux;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.library.flux.models.Genre;

/**
 * Flux dao.
 */
public interface GenresFluxDao extends ReactiveMongoRepository<Genre, String> {
  Mono<Genre> save(Mono<Genre> author);
}
