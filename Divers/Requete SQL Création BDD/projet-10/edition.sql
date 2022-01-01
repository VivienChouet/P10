create table edition
(
	id int auto_increment
		primary key,
	name varchar(255) null,
	book_id int null,
	constraint FK_EDITION_ON_BOOK
		foreign key (book_id) references book (id)
);

