create table user
(
	id int auto_increment
		primary key,
	name varchar(255) null,
	email varchar(255) null,
	password varchar(255) null,
	token varchar(255) null,
	admin bit null
);

