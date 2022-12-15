DROP DATABASE IF EXISTS apac3;
CREATE DATABASE apac3;
USE apac3;

CREATE TABLE peliculas(
    id_pelicula VARCHAR(9),
    titulo VARCHAR(50),
    añoLanzamiento INT,
    sinopsis VARCHAR(100),
    PRIMARY KEY(id_pelicula)
);

CREATE TABLE socio(
    dni VARCHAR(9),
    nombre VARCHAR(30),
    fechaNacimiento DATE,
    pelicula VARCHAR(9),
    PRIMARY KEY(dni),
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