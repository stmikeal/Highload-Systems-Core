create table notification_pattern (
    id bigserial primary key,
    name text unique,
    pattern text not null
);

insert into notification_pattern (id, name, pattern)
values (0, 'Greeting', 'Добрый день, ?{name}!');