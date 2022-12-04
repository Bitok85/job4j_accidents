CREATE TABLE IF NOT EXISTS authorities (
    id SERIAL PRIMARY KEY,
    authority VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE,
    authority_id int NOT NULL REFERENCES authorities(id)
);

--insert into authorities (authority) values ('ROLE_USER');
--insert into authorities (authority) values ('ROLE_ADMIN');

--insert into users (username, enabled, password, authority_id)
--values ('root', true, '$2a$10$wY1twJhMQjGVxv4y5dBC5ucCBlzkzT4FIGa4FNB/pS9GaXC2wm9/W',
        --(select id from authorities where authority = 'ROLE_ADMIN'));




