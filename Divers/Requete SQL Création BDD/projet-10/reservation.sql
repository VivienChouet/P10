create table reservation
(
	id int auto_increment
		primary key,
	date_debut datetime null,
	date_fin datetime null,
	ended bit null,
	extension bit null,
	batch bit null,
	attente_id int null,
	recuperer bit null,
	user_id int null,
	exemplaire_id int null,
	constraint FK_RESERVATION_ON_ATTENTE
		foreign key (attente_id) references attente (id),
	constraint FK_RESERVATION_ON_EXEMPLAIRE
		foreign key (exemplaire_id) references exemplaire (id),
	constraint FK_RESERVATION_ON_USER
		foreign key (user_id) references user (id)
);

