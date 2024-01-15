alter table account add column email text;

insert into notification_pattern (id, name, pattern)
values (1, 'Seen', 'Добрый день, ?{name}! Ваше объявление ?{title} было просмотрено.');