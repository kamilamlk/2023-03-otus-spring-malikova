create table authors(
    id bigint primary key,
    author_name varchar(255)
);

create table genres(
    id bigint primary key,
    genre_name varchar(255)
);

create table books(
    id bigint primary key auto_increment,
    title varchar(255),
    publication_year integer,
    author_id integer references authors(id),
    genre_id integer references genres(id)
);
