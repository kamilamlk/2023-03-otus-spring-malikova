insert into genres (id, genre_name) values (1, 'FICTION');
insert into genres (id, genre_name) values (2, 'NON-FICTION');

insert into authors (id, author_name) values (1, 'Stephen King');
insert into authors (id, author_name) values (2, 'George Orwell');

insert into books (id, title, publication_year, author_id, genre_id) values (1, 'The Shining', 1977, 1, 1);