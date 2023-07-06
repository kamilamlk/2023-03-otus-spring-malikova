package ru.otus.library.mvc.mongo.event;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.library.mvc.dao.BooksDao;
import ru.otus.library.mvc.models.Author;
import ru.otus.library.mvc.models.Book;

/**
 * Author related operations.
 */
@Component
@RequiredArgsConstructor
public class AuthorCascadeOperation extends AbstractMongoEventListener<Author> {
  private final BooksDao booksDao;

  @Override
  public void onBeforeConvert(BeforeConvertEvent<Author> event) {
    super.onBeforeConvert(event);
    var author = event.getSource();
    List<Book> books = booksDao.findAllByAuthor_Id(author.getId());
    books.forEach(b -> {
      b.setAuthor(author);
      booksDao.save(b);
    });
  }
}
