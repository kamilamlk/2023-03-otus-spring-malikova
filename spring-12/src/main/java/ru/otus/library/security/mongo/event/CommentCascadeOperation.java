package ru.otus.library.security.mongo.event;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;
import ru.otus.library.security.dao.BooksDao;
import ru.otus.library.security.models.Book;
import ru.otus.library.security.models.Comment;

/**
 * Cascade operation on comment.
 */
@Component
@AllArgsConstructor
public class CommentCascadeOperation extends AbstractMongoEventListener<Comment> {
  private BooksDao booksDao;

  @Override
  public void onAfterSave(AfterSaveEvent<Comment> event) {
    super.onAfterSave(event);
    var comment = event.getSource();
    Book book = comment.getBook();
    List<Comment> comments = new ArrayList<>(book.getComments());
    if (!comments.contains(comment)) {
      comments.add(comment);
    }
    book.setComments(comments);
    booksDao.save(book);
  }
}
