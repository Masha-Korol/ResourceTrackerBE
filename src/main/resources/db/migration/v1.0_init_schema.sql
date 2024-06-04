CREATE TABLE concerts (
	concert_id serial NOT NULL,
	artist_id integer NOT NULL,
	venue_id integer,
	event_date date,
	start_time date,
	status VARCHAR(255),
	file_id integer,
	CONSTRAINT concert_pk PRIMARY KEY (concert_id)
);



CREATE TABLE artists (
	artist_id serial NOT NULL,
	artist_name VARCHAR(255) NOT NULL,
	file_id integer,
	CONSTRAINT artists_pk PRIMARY KEY (artist_id)
);



CREATE TABLE venues (
	venue_id serial NOT NULL,
	venue_name VARCHAR(255) NOT NULL,
	file_id integer,
	location_id integer,
	CONSTRAINT venues_pk PRIMARY KEY (venue_id)
);



CREATE TABLE users (
	user_id serial NOT NULL,
	login VARCHAR(255) NOT NULL,
	password VARCHAR(255),
	email VARCHAR(255),
	file_id integer,
	location_id integer,
	CONSTRAINT users_pk PRIMARY KEY (user_id)
);



CREATE TABLE users_concerts (
	user_id integer NOT NULL,
	concert_id integer NOT NULL
);



CREATE TABLE friends (
	user_id integer NOT NULL,
	friend_user_id integer NOT NULL
);



CREATE TABLE files (
	file_id serial NOT NULL,
	data bytea NOT NULL,
	CONSTRAINT files_pk PRIMARY KEY (file_id)
);



create table locations (
    location_id serial NOT NULL,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    CONSTRAINT locations_pk PRIMARY KEY (location_id)
);



CREATE TABLE user_authorization_roles
(
    role_id   serial NOT NULL,
    role_name varchar(100),
    CONSTRAINT user_authorization_roles_pk PRIMARY KEY (role_id)
);


CREATE TABLE user_roles
(
    user_id integer NOT NULL,
    role_id integer NOT NULL
);



ALTER TABLE concerts ADD CONSTRAINT concert_fk0 FOREIGN KEY (artist_id) REFERENCES artists(artist_id);
ALTER TABLE concerts ADD CONSTRAINT concert_fk1 FOREIGN KEY (venue_id) REFERENCES venues(venue_id);
ALTER TABLE concerts ADD CONSTRAINT concert_fk2 FOREIGN KEY (file_id) REFERENCES files(file_id);

ALTER TABLE artists ADD CONSTRAINT artists_fk0 FOREIGN KEY (file_id) REFERENCES files(file_id);

ALTER TABLE venues ADD CONSTRAINT venues_fk0 FOREIGN KEY (file_id) REFERENCES files(file_id);
ALTER TABLE venues ADD CONSTRAINT venues_fk1 FOREIGN KEY (location_id) REFERENCES locations(location_id);

ALTER TABLE users ADD CONSTRAINT users_fk0 FOREIGN KEY (file_id) REFERENCES files(file_id);
ALTER TABLE users ADD CONSTRAINT users_fk1 FOREIGN KEY (location_id) REFERENCES locations(location_id);

ALTER TABLE users_concerts ADD CONSTRAINT users_concerts_fk0 FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE users_concerts ADD CONSTRAINT users_concerts_fk1 FOREIGN KEY (concert_id) REFERENCES concerts(concert_id);

ALTER TABLE friends ADD CONSTRAINT friends_fk0 FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE friends ADD CONSTRAINT friends_fk1 FOREIGN KEY (friend_user_id) REFERENCES users(user_id);

ALTER TABLE user_roles ADD CONSTRAINT user_roles_fk0 FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE user_roles ADD CONSTRAINT user_roles_fk1 FOREIGN KEY (role_id) REFERENCES user_authorization_roles (role_id);

insert into user_authorization_roles (role_name) values ('ROLE_USER');
insert into user_authorization_roles (role_name) values ('ROLE_MODERATOR');
insert into user_authorization_roles (role_name) values ('ROLE_ADMIN');
