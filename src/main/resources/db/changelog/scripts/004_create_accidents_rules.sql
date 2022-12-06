create table if not exists accidents_rules (
   id serial primary key,
   accident_id int not null references accident(id),
   rule_id int not null references rule(id)
);

comment on table accidents_rules is 'Нарушения-правила';
comment on column accidents_rules.accident_id is 'Идентификатор нарушения';
comment on column accidents_rules.rule_id is 'Идентификатор правила';