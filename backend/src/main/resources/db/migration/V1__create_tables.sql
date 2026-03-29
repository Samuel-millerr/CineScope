CREATE TABLE actor(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL UNIQUE,
    photo VARCHAR(512) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE director(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE genre(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    genre VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE movie(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(100) NOT NULL UNIQUE,
    duration INTEGER NOT NULL,
    publication_year INTEGER NOT NULL,
    synopsis TEXT NOT NULL,
    poster VARCHAR(512),
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TYPE user_role AS ENUM ('Comum', 'Administrador');

CREATE TABLE cinescope_user(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(100) NOT NULL UNIQUE,
    hashed_password VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role user_role NOT NULL DEFAULT 'Comum',
    created_at DATE NOT NULL DEFAULT NOW(),
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TYPE request_type AS ENUM ('Adição', 'Edição');

CREATE TYPE request_status AS ENUM ('Aprovado', 'Pendente', 'Reprovado');

CREATE TABLE request(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_movie INTEGER NOT NULL,
    id_user INTEGER NOT NULL,
    type request_type NOT NULL,
    request_date DATE NOT NULL,
    request_body JSON NOT NULL,
    status request_status NOT NULL,
    comment TEXT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE review(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_user INTEGER NOT NULL,
    id_movie INTEGER NOT NULL,
    reviewText TEXT NULL,
    reviewRatting DECIMAL(2,1) NOT NULL,
    review_date DATE NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE movie_director(
    id_movie INTEGER NOT NULL,
    id_director INTEGER NOT NULL,
    FOREIGN KEY (id_movie) REFERENCES movie(id) ON DELETE CASCADE,
    FOREIGN KEY (id_director) REFERENCES director(id) ON DELETE CASCADE
);

CREATE TABLE movie_actor(
    id_movie INTEGER NOT NULL,
    id_actor INTEGER NOT NULL,
    FOREIGN KEY (id_movie) REFERENCES movie(id) ON DELETE CASCADE,
    FOREIGN KEY (id_actor) REFERENCES actor(id) ON DELETE CASCADE
);

CREATE TABLE movie_genre (
    id_movie INTEGER NOT NULL,
    id_genre INTEGER NOT NULL,
    FOREIGN KEY (id_movie) REFERENCES movie(id) ON DELETE CASCADE,
    FOREIGN KEY (id_genre) REFERENCES genre(id) ON DELETE CASCADE
);