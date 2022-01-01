create table book
(
	id int auto_increment
		primary key,
	title varchar(255) null,
	author varchar(255) null,
	publication datetime null,
	resume longtext null
);

