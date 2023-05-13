insert into genres (id, genre_name) values (1, 'FICTION');
insert into genres (id, genre_name) values (2, 'NON-FICTION');
insert into genres (id, genre_name) values (3, 'HISTORICAL FICTION');
insert into genres (id, genre_name) values (4, 'SCIENCE FICTION');
insert into genres (id, genre_name) values (5, 'NOVEL' );

insert into authors (id, author_name) values (1, 'Stephen King');
insert into authors (id, author_name) values (2, 'George Orwell');
insert into authors (id, author_name) values (3, 'Hilary Mantel');
insert into authors (id, author_name) values (4, 'Frank Herbert');
insert into authors (id, author_name) values (5, 'Joanne Rowling');
insert into authors (id, author_name) values (6, 'Agatha Christie');

insert into books (title, publication_year, author_id, genre_id) values ('The Shining', 1977, 1, 1)