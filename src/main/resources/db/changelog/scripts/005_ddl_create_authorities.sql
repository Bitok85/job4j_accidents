CREATE TABLE IF NOT EXISTS authorities (
   id SERIAL PRIMARY KEY,
   authority TEXT NOT NULL UNIQUE
);

comment on table authorities is 'Права пользователей';
comment on column authorities.id is 'Идентификатор права пользователя';
comment on column authorities.authority is 'Название права пользователя';