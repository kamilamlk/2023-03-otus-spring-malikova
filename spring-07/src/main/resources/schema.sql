create table authors (
      id bigserial,
      author_name varchar(255),
      primary key (id)
);


create table genres (
      id bigserial,
      genre_name varchar(255),
      primary key (id)
);

create table books(
    id bigserial,
    title varchar(255),
    publication_year integer,
    author_id bigint references authors(id),
    genre_id bigint references genres(id),
    primary key (id)
);

create table comments (
    id bigserial,
    comment_text varchar(255),
    book_id bigint references books(id) on delete cascade,
    primary key (id)
);
