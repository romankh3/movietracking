DROP TABLE User IF EXISTS;
DROP TABLE Actor IF EXISTS;
DROP TABLE Movie IF EXISTS;
DROP TABLE User_x_Actor IF EXISTS;
DROP TABLE User_x_Movie IF EXISTS;

CREATE TABLE User (
  id INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  email VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  secret_key VARCHAR(100)
);

CREATE TABLE Actor (
  id INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  thdm_id INTEGER NOT NULL
);

CREATE TABLE User_x_Actor (
  user_id INTEGER,
  actor_id  INTEGER,
  favorite BOOLEAN DEFAULT FALSE NOT NULL,
  UNIQUE (user_id, actor_id)
);
ALTER TABLE User_x_Actor
  ADD CONSTRAINT fk_User_x_Actor_User
  FOREIGN KEY (user_id) REFERENCES User(id);

ALTER TABLE User_x_Actor
  ADD CONSTRAINT fk_User_x_Actor_Actor
  FOREIGN KEY (actor_id) REFERENCES Actor(id);

CREATE TABLE Movie (
  id INTEGER PRIMARY KEY,
  name VARCHAR(100),
  year INTEGER,
  thdm_id INTEGER NOT NULL
);

CREATE TABLE User_x_Movie (
  user_id INTEGER,
  movie_id INTEGER,
  watched BOOLEAN DEFAULT FALSE NOT NULL,
  UNIQUE (user_id, movie_id)
);

ALTER TABLE User_x_Movie
  ADD CONSTRAINT fk_User_x_Movie_User
  FOREIGN KEY (user_id) REFERENCES User(id);

ALTER TABLE User_x_Movie
  ADD CONSTRAINT fk_User_x_Movie_Movie
  FOREIGN KEY (movie_id) REFERENCES Movie(id);




