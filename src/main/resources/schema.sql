create table if not exists device(
  id int not null AUTO_INCREMENT,
  name varchar(100) not null,
  brand varchar(100) not null,
  creation_time TIMESTAMP,
  PRIMARY KEY ( id )
);