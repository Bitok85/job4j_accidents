create table if not exists accident_type (
    id serial primary key,
    name text
);

comment on table accident_type is 'Типы нарушений';
comment on column accident_type.id is 'Идентификатор нарушения';
comment on column accident_type.name is 'Название нарушения';
