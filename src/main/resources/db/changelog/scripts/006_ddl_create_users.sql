CREATE TABLE IF NOT EXISTS users (
     id SERIAL PRIMARY KEY,
     username TEXT NOT NULL UNIQUE,
     password TEXT NOT NULL,
     enabled BOOLEAN DEFAULT TRUE,
     authority_id int NOT NULL REFERENCES authorities(id)
);

comment on table users is 'Пользователи';
comment on column users.id is 'Идентификатор пользователя';
comment on column users.username is 'Имя пользователя';
comment on column users.password is 'Пароль пользователя';
comment on column users.enabled is 'Статус пользователя';
comment on column users.authority_id is 'Права пользователя';
