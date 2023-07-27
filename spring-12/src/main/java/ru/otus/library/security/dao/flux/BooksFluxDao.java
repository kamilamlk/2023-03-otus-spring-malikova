package ru.otus.library.security.dao.flux;

import java.util.List;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.library.security.models.Book;

/**
 * Flux Dao.
 */
public interface BooksFluxDao extends ReactiveMongoRepository<Book, String> {
  List<Book> findAllByAuthor_Id(String authorId);

  Mono<Book> save(Mono<Book> book);
}
