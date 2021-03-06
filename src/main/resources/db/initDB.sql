DROP INDEX users_unique_email_idx IF EXISTS;
DROP INDEX user_votes_unique_user_date_idx IF EXISTS;
DROP TABLE dishes IF EXISTS;
DROP TABLE user_votes IF EXISTS;
DROP TABLE restaurants IF EXISTS;
DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;

CREATE TABLE users
(
  id               BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
  name             VARCHAR(255) NOT NULL,
  email            VARCHAR(255) NOT NULL,
  password         VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);
CREATE UNIQUE INDEX users_unique_email_idx ON users(email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY ( user_id ) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE user_votes
(
  id                BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
  date         DATE DEFAULT CURRENT_DATE,
  time         TIME DEFAULT CURRENT_TIME,
  chosen_restaurant_id INTEGER NOT NULL,
  user_id           INTEGER NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (chosen_restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX user_votes_unique_user_date_idx ON user_votes(user_id, date);

CREATE TABLE dishes
(
  id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
  name VARCHAR(255) NOT NULL,
  restaurant_id INTEGER NOT NULL,
  price INTEGER NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX dishes_unique_id_restaurant_id_idx ON dishes(id, restaurant_id);