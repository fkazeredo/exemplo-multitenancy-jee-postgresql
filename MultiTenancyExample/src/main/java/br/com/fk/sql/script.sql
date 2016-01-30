SET search_path TO public;

CREATE TABLE pessoa(
	id SERIAL,
	nome CHARACTER VARYING(50),
	PRIMARY KEY (id)
);

SET search_path TO schema1;

CREATE TABLE pessoa(
	id SERIAL,
	nome CHARACTER VARYING(50),
	PRIMARY KEY (id)
);

SET search_path TO schema2;

CREATE TABLE pessoa(
	id SERIAL,
	nome CHARACTER VARYING(50),
	PRIMARY KEY (id)
);