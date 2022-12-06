create table if not exists rule (
    id serial primary key,
    name text
);

comment on table rule is 'Правила';
comment on column rule.id is 'Идентификатор правила';
comment on column rule.name is 'Название правила';