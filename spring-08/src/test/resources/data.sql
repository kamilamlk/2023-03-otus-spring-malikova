insert into genres (genre_name) values ('FICTION');
insert into genres (genre_name) values ('NON-FICTION');

insert into authors (author_name) values ('Stephen King');
insert into authors (author_name) values ('George Orwell');

insert into books (title, publication_year, author_id, genre_id) values ('The Shining', 1977, 1, 1);

insert into comments(comment_text, book_id) values ('comment', 1);