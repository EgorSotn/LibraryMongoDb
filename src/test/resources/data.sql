insert into author (id_author, name_author, year_author ) values (1, 'Pushkin', '1993-05-11');
insert into genre (id_genre, name_genre) values (1, 'drama');
insert into genre (id_genre, name_genre) values (2, 'horror');

insert into book (id_book, name_book, year_book,  id_author) values(1, 'evangelion', '1999-05-11', 1);
insert into book (id_book, name_book, year_book,  id_author) values (2, 'eva', '1999-05-10', 1);

insert into book_genre (id_book, id_genre) values (1, 1);
insert into book_genre (id_book, id_genre) values (1, 2);

insert into book_genre (id_book, id_genre) values (2, 1);

insert into comment(id_comment, text, id_book) values(1, 'adscxvcxvxvg', 1)