CREATE TABLE users (
   id SERIAL PRIMARY KEY,
   username TEXT NOT NULL,
   password TEXT NOT NULL,
   enabled boolean default true
);

CREATE TABLE authorities (
     username TEXT NOT NULL,
     authority TEXT NOT NULL,
     user_id int REFERENCES users(id)
);

