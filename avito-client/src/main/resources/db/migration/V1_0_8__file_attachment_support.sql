create table attached_file (
    id bigserial primary key,
    name text not null,
    bytes bytea,
    author bigint references account(id) not null
);