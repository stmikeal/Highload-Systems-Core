create table account (
    id bigint primary key,
    name text not null,
    avatar_url text,
    birth timestamp not null,
    modified_timestamp timestamp default now(),
    created_timestamp timestamp default now()
);

create table login (
    id bigint primary key,
    account bigint references account(id) not null,
    login text unique not null,
    password text not null,
    last_auth timestamp default now(),
    modified_timestamp timestamp default now(),
    created_timestamp timestamp default now()
);

create table account_role (
    id bigint primary key,
    code bigint unique not null,
    name text unique not null,
    description text
);

create table document (
    id bigint primary key,
    url text not null,
    title text not null,
    description text,
    modified_timestamp timestamp default now(),
    created_timestamp timestamp default now()
);

create table account_role_confirmation (
    id bigint primary key,
    signature text not null,
    description text,
    confirm_document bigint references document(id) not null,
    modified_timestamp timestamp default now(),
    created_timestamp timestamp default now()
);

create table account_role_link (
    id bigint primary key,
    account bigint references account(id) not null,
    role bigint references account_role(code) not null,
    confirm bigint references account_role_confirmation(id)
);

create table company (
    id bigint primary key,
    name text unique not null,
    avatars_url text,
    modified_timestamp timestamp default now(),
    created_timestamp timestamp default now()
);

create table advert_type (
    id bigint primary key,
    code bigint unique not null,
    name text unique not null,
    description text
);

create table currency_type (
    id bigint primary key,
    code bigint unique not null,
    name text unique not null,
    short_name text unique not null
);

create table advert (
    id bigint primary key,
    author bigint references account(id) not null,
    company bigint references company(id),
    type bigint references advert_type(code) not null,
    title text not null,
    description text,
    price bigint,
    currency bigint references currency_type(code),
    modified_timestamp timestamp default now(),
    created_timestamp timestamp default now()
);

create table advert_respond (
    id bigint primary key,
    applicant bigint references account(id) not null,
    advert bigint references advert(id) not null,
    title text not null,
    description text,
    modified_timestamp timestamp default now(),
    created_timestamp timestamp default now()
);

create table document_respond_link (
    id bigint primary key,
    respond bigint references advert_respond(id) not null,
    document bigint references document(id) not null
);

create table advert_sale (
    id bigint primary key,
    applicant bigint references account(id) not null,
    advert bigint references advert(id) not null,
    delivery bigint references company(id),
    title text not null,
    price bigint,
    currency bigint references currency_type(code),
    modified_timestamp timestamp default now(),
    created_timestamp timestamp default now()
);

create table tariff (
    id bigint primary key,
    company bigint references company(id) not null ,
    limit_weight bigint,
    price bigint,
    currency bigint references currency_type(code),
    modified_timestamp timestamp default now(),
    created_timestamp timestamp default now()
);

create table access (
    id bigint primary key,
    code bigint unique not null,
    title text unique not null,
    description text
);

create table account_company_link (
    id bigint primary key,
    account bigint references account(id) not null,
    company bigint references company(id) not null,
    position text not null,
    access bigint references access(code) not null
);

create table area (
    id bigint primary key,
    code bigint unique not null,
    name text unique not null,
    description text
);

create table company_area_link (
    id bigint primary key,
    company bigint references company(id) not null,
    area bigint references area(code) not null
);
