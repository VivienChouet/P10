create table exemplaire
(
	id int auto_increment
		primary key,
	available bit null,
	edition_id int null,
	constraint FK_EXEMPLAIRE_ON_EDITION
		foreign key (edition_id) references edition (id)
);

