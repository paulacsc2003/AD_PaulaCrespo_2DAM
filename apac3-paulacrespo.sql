DROP DATABASE IF EXISTS apac3;
CREATE DATABASE apac3;
USE apac3;

CREATE TABLE peliculas(
	id_pelicula INT,
	titulo VARCHAR(50),
	añoLanzamiento INT,
	sinopsis VARCHAR(100),
    PRIMARY KEY(id_pelicula)
);

CREATE TABLE socio(
	id_socio INT,
	dni VARCHAR(9),
	nombre VARCHAR(30),
	fechaNacimiento DATE,
	pelicula INT,
	PRIMARY KEY(id_socio),
	FOREIGN KEY(pelicula) REFERENCES peliculas(id_pelicula)
);


CREATE TABLE videoclub(
    id_videoclub VARCHAR(9),
    nombre VARCHAR(30),
    direccion VARCHAR(50),
    socio VARCHAR(9),
    PRIMARY KEY(id_videoclub),
    FOREIGN KEY(socio) REFERENCES socio(dni)
);

CREATE TABLE estar(
    pelicula VARCHAR(9),
    videoclub VARCHAR(9),
    FOREIGN KEY(pelicula) REFERENCES peliculas(id_pelicula),
    FOREIGN KEY(videoclub) REFERENCES videoclub(id_videoclub)
);

INSERT INTO peliculas (id_pelicula, titulo, añoLanzamiento, sinopsis) VALUES (1, "Titanic", 1997, "Jack es un joven artista que gana un pasaje para viajar a América en el Titanic, el transatlántico más grande y seguro jamás construido. A bordo del buque conoce a Rose, una chica de clase alta que viaja con su madre y su prometido Cal, un millonario engreído a quien solo interesa el prestigio de la familia de su prometida. Jack y Rose se enamoran a pesar de las trabas que ponen la madre de ella y Cal en su relación. Mientras, el lujoso transatlántico se acerca a un inmenso iceberg.");
INSERT INTO peliculas (id_pelicula, titulo, añoLanzamiento, sinopsis) VALUES (2, "Harry Potter y la piedra filosofal", 2001, "El día en que cumple once años, Harry Potter descubre que es hijo de dos conocidos hechiceros, de los que ha heredado poderes mágicos. Deberá acudir entonces a una famosa escuela de magia y hechicería: Howards.");
INSERT INTO peliculas (id_pelicula, titulo, añoLanzamiento, sinopsis) VALUES (3, "Coraline", 2009, "Una niña descubre una puerta secreta en su nueva casa y entra a una realidad alterna que la refleja fielmente de muchas formas.");
INSERT INTO peliculas (id_pelicula, titulo, añoLanzamiento, sinopsis) VALUES (4, "The Addams Family", 1991, "La familia Addams se ve amenazada cuando una madre y su hijo, con ayuda de un abogado sin escrúpulos, conspiran para hacerse con la fortuna familiar.");

INSERT INTO socio (id_socio, dni, nombre, fechaNacimiento, pelicula) VALUES (1, "12345678A", "Alejandro", 2002-06-09, 2);
INSERT INTO socio (id_socio, dni, nombre, fechaNacimiento, pelicula) VALUES (2, "11122233B", "Nerea", 1997-11-22, 1);
INSERT INTO socio (id_socio, dni, nombre, fechaNacimiento, pelicula) VALUES (3, "99900123C", "Frank", 2004-09-16, 4);
INSERT INTO socio (id_socio, dni, nombre, fechaNacimiento, pelicula) VALUES (4, "89891234D", "Sandra", 1975-10-16, 3);

INSERT INTO videoclub (id_videoclub, nombre) VALUES (1, "VideoClub Jaume II")
