package ru.otus.library.security.dao.flux;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.library.security.models.Comment;

/**
 * Flux Dao.
 */
public interface CommentsFluxDao extends ReactiveMongoRepository<Comment, String> {
  Flux<Comment> findAllByBook_Id(String bookId);
}
