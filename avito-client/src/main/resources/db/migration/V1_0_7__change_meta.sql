delete from notification_pattern where id in (0, 1);

insert into notification_pattern (id, name, pattern)
values (0, 'Greeting', 'Добрый день, {name}!');

insert into notification_pattern (id, name, pattern)
values (1, 'Seen', 'Добрый день, {name}! Ваше объявление {title} было просмотрено.');