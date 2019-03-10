create table if not exists users
(
  id       serial primary key not null,
  name     varchar(2000),
  login    varchar(2000),
  password varchar(2000),
  email    varchar(2000),
  date     varchar(2000),
  role     varchar(2000),
  country  varchar(2000),
  city     varchar(2000)
);

create table if not exists country
(
  id           serial primary key not null,
  country_name varchar(200)
);

create table if not exists city
(
  id         serial primary key not null,
  country_id smallint,
  city_name  varchar(200)
);

insert into country(country_name)
values ('Russia');
insert into country(country_name)
values ('Germany');
insert into country(country_name)
values ('USA');

insert into city (country_id, city_name)
values (1, 'Ekaterinburg');
insert into city (country_id, city_name)
values (1, 'Samara');
insert into city (country_id, city_name)
values (1, 'Moskow');
insert into city (country_id, city_name)
values (1, 'Peterburg');
insert into city (country_id, city_name)
values (1, 'Rostov on Don');

insert into city (country_id, city_name)
values (2, 'Berlin');
insert into city (country_id, city_name)
values (2, 'Munchen');

insert into city (country_id, city_name)
values (3, 'Seattle');
insert into city (country_id, city_name)
values (3, 'Portland');
insert into city (country_id, city_name)
values (3, 'Chicago');