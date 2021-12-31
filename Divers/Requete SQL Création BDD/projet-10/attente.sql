create table attente
(
	id int auto_increment
		primary key,
	date_mail datetime null,
	mail bit null,
	user_id int not null,
	edition_id int null,
	constraint FK_ATTENTE_ON_EDITION
		foreign key (edition_id) references edition (id),
	constraint FK_ATTENTE_ON_USER
		foreign key (user_id) references user (id)
);

