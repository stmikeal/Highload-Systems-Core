ALTER TABLE account ADD permission bigint not null default 0;
UPDATE account SET permission = 2 where id = 0;