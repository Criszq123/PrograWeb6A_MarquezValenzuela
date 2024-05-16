CREATE TABLE users(
  id int auto_increment primary key,
  fullname varchar(100) not null,
  email varchar(100) not null,
  username varchar(100) not null,
  password varchar(100) not null,
);