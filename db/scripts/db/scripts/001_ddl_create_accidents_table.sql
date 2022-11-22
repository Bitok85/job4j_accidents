create table if not exists accident_type (
    id serial primary key,
    name text
);

CREATE TABLE if not exists accident (
    id serial primary key,
    name text,
    text text,
    address text,
    accident_type_id int REFERENCES accident_type(id)
);

create table if not exists rule (
    id serial primary key,
    name text,
    accident_id int references accident(id)
);

