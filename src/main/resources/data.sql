DROP TABLE IF EXISTS SATELLITE;

create table SATELLITE (
  SAT_NAME VARCHAR(20) primary key,
  SAT_CORD_X DOUBLE,
  SAT_CORD_Y DOUBLE 
);

insert into	SATELLITE (SAT_NAME, SAT_CORD_X, SAT_CORD_Y)
values 	
	('KENOBI', -500.0, -200.0),
	('SKYWALKER', 100.0, -100.0),
	('SATO', 500.0, 100.0);
