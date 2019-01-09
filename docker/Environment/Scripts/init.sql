create schema "security" authorization root;

grant all on schema "security" to root;

create table "security"."users"
(
  id serial primary key,
  name varchar(300) not null,
  password varchar(300) not null,
  enabled boolean not null,
  account_non_expired boolean,
  credentials_non_expired boolean,
  account_non_locked boolean
);

grant all on table "security"."users" to root;

create unique index "users_id_uindex" on "security"."users" (id);

create schema "main";

grant all on schema "main" to root;

create table "main"."person"
(
  id            bigserial primary key not null,
  name          varchar(300),
  company       varchar(100),
  title         varchar(10),
  department    varchar(60),
  creation_date timestamp null,
  update_date   timestamp null,
  creation_user varchar(60),
  update_user   varchar(60)
);

grant all on table "main"."person" to root;

create unique index "person_id_uindex" on "main"."person" (id);

create table "main"."person_address"
(
  address_id      bigserial primary key not null,
  person_id       bigint                not null,
  address_street  varchar(200)          not null,
  address_city    varchar(60),
  address_state   varchar(60),
  address_zip     varchar(15),
  address_country varchar(60),
  creation_date   timestamp             null,
  update_date     timestamp             null,
  creation_user   varchar(60),
  update_user     varchar(60)
);

grant all on table "main"."person_address" to root;

create unique index "person_address_address_id_uindex" on "main"."person_address" (address_id);

create table "main"."person_phone"
(
  phone_id      bigserial primary key not null,
  person_id     bigint,
  phone_type    varchar(12),
  phone_number  varchar(30),
  creation_date timestamp             null,
  update_date   timestamp             null,
  creation_user varchar(60),
  update_user   varchar(60)
);

grant all on table "main"."person_phone" to root;

create unique index "person_phone_phone_id_uindex" on "main"."person_phone" (phone_id);
