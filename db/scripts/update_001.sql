create table if not exists users
(
  id    serial primary key not null,
  name  varchar(2000),
  login varchar(2000),
  password varchar(2000),
  email varchar(2000),
  date  varchar(2000)
);