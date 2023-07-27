package ru.otus.library.security.dao.flux;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.library.security.models.Genre;

/**
 * Flux dao.
 */
public interface GenresFluxDao extends ReactiveMongoRepository<Genre, String> {
  Mono<Genre> save(Mono<Genre> author);
}
