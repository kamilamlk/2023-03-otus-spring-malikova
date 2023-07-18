package ru.otus.library.flux.dao.flux;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.library.flux.models.Author;

/**
 * Flux Dao.
 */
public interface AuthorsFluxDao extends ReactiveMongoRepository<Author, String> {
  Mono<Author> save(Mono<Author> author);
}
