DROP DATABASE IF EXISTS cinescope;
CREATE DATABASE cinescope;

USE cinescope;

CREATE TABLE director(
	id_director INTEGER AUTO_INCREMENT UNIQUE,
    director_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE actor (
	id_actor INTEGER AUTO_INCREMENT UNIQUE,
    actor_name VARCHAR(255) NOT NULL UNIQUE,
    actor_photo VARCHAR(510) NOT NULL
);

CREATE TABLE genre (
	id_genre INTEGER AUTO_INCREMENT UNIQUE,
    genre VARCHAR(255) NOT NULL
);

CREATE TABLE movie (
	id_movie INTEGER AUTO_INCREMENT UNIQUE,
    movie_title VARCHAR(255) NOT NULL UNIQUE, 
    duration_time TIME NOT NULL,
    publication_year YEAR NOT NULL,
    movie_synopsis TEXT NOT NULL,
    movie_poster VARCHAR(510)
);

CREATE TABLE movie_director(
	id_movie_director INTEGER AUTO_INCREMENT UNIQUE,
    id_movie INTEGER NOT NULL,
    id_director INTEGER NOT NULL,
    FOREIGN KEY (id_movie) REFERENCES movie(id_movie) ON DELETE CASCADE,
    FOREIGN KEY (id_director) REFERENCES director(id_director) ON DELETE CASCADE
);

CREATE TABLE movie_actor(
	id_movie_actor INTEGER AUTO_INCREMENT UNIQUE,
    id_movie INTEGER NOT NULL,
    id_actor INTEGER NOT NULL,
    FOREIGN KEY (id_movie) REFERENCES movie(id_movie) ON DELETE CASCADE,
    FOREIGN KEY (id_actor) REFERENCES actor(id_actor) ON DELETE CASCADE
);

CREATE TABLE movie_genre (
	id_movie_actor INTEGER AUTO_INCREMENT UNIQUE,
    id_movie INTEGER NOT NULL,
    id_genre INTEGER NOT NULL,
    FOREIGN KEY (id_movie) REFERENCES movie(id_movie) ON DELETE CASCADE,
    FOREIGN KEY (id_genre) REFERENCES genre(id_genre) ON DELETE CASCADE
);

CREATE TABLE user (
    id_user INTEGER AUTO_INCREMENT UNIQUE,
    user VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_role ENUM("Comum", "Administrador") NOT NULL DEFAULT "Comum",
    user_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_at DATE
);
CREATE TABLE review (
	id_review INTEGER AUTO_INCREMENT UNIQUE, 
    id_movie INTEGER NOT NULL,
    id_user INTEGER NOT NULL,
    review_text TEXT NOT NULL,
    review_rating DECIMAL(2,1) NOT NULL,
    review_date DATE NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user(id_user) ON DELETE CASCADE,
    FOREIGN KEY (id_movie) REFERENCES movie(id_movie) ON DELETE CASCADE
);

CREATE TABLE request (
    id_request INTEGER AUTO_INCREMENT UNIQUE,
    id_user INTEGER NOT NULL,
    id_movie INTEGER NULL,
    request_type ENUM("Adicão", "Edição") NOT NULL,
    request_date DATE NOT NULL,
    request_status ENUM("Aprovado", "Pendente", "Reprovado") NOT NULL,
    request_body JSON NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user(id_user) ON DELETE CASCADE,
    FOREIGN KEY (id_movie) REFERENCES movie(id_movie) ON DELETE CASCADE
);

CREATE TABLE user_collection (
	id_collection INTEGER AUTO_INCREMENT UNIQUE,
    id_movie INTEGER NOT NULL,
    id_user INTEGER NOT NULL,
    added_at DATE NOT NULL,
	FOREIGN KEY (id_user) REFERENCES user(id_user) ON DELETE CASCADE,
    FOREIGN KEY (id_movie) REFERENCES movie(id_movie) ON DELETE CASCADE
);

CREATE TABLE watched_movies (
	id_watched_movies INTEGER AUTO_INCREMENT UNIQUE,
	id_movie INTEGER NOT NULL,
    id_user INTEGER NOT NULL,
    watched_at DATE NOT NULL,
	FOREIGN KEY (id_user) REFERENCES user(id_user) ON DELETE CASCADE,
    FOREIGN KEY (id_movie) REFERENCES movie(id_movie) ON DELETE CASCADE
);