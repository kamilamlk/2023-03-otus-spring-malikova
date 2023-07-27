package ru.otus.library.security.dao.flux;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.library.security.models.Author;

/**
 * Flux Dao.
 */
public interface AuthorsFluxDao extends ReactiveMongoRepository<Author, String> {
  Mono<Author> save(Mono<Author> author);
}
