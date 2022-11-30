CREATE TABLE users (
   id SERIAL PRIMARY KEY,
   username TEXT NOT NULL UNIQUE ,
   password TEXT NOT NULL,
   enabled boolean default true
);

CREATE TABLE authorities (
     username TEXT NOT NULL UNIQUE ,
     authority TEXT NOT NULL,
     user_id int REFERENCES users(id)
);

select * from authorities;
