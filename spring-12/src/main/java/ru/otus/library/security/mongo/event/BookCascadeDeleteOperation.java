package ru.otus.library.security.mongo.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.library.security.dao.CommentsDao;
import ru.otus.library.security.models.Book;

/**
 * Cascade operations on book delete.
 */
@Component
@RequiredArgsConstructor
public class BookCascadeDeleteOperation extends AbstractMongoEventListener<Book> {
  private final CommentsDao commentsDao;

  @Override
  public void onAfterDelete(AfterDeleteEvent<Book> event) {
    super.onAfterDelete(event);
    var source = event.getSource();
    String id = source.get("_id").toString();
    commentsDao.deleteByBook_Id(id);
  }
}
