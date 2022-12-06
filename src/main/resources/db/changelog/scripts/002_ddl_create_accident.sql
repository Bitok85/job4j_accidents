CREATE TABLE if not exists accident (
    id serial primary key,
    name text,
    text text,
    address text,
    accident_type_id int REFERENCES accident_type(id)
);

comment on table accident is 'Нарушения';
comment on column accident.id is 'Идентификатор нарушения';
comment on column accident.name is 'Название нарушения';
comment on column accident.text is 'Описание нарушения';
comment on column accident.address is 'Адрес нарушения';
comment on column accident.accident_type_id is 'Идентификатор типа нарушения';
