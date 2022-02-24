CREATE TABLE USERS( 
	Username varchar(12) not null PRIMARY KEY,
	Password varchar(15) not null,
	Name varchar(12) not null,
	Lastname varchar(15) not null,
	Role varchar (10) not null
);

CREATE TABLE STUDENTS(
	Username varchar(12) not null PRIMARY KEY ,
	Semester int(2) not null,
	AM int(2) not null
);

CREATE TABLE PROFESSORS(
	Professor_id int not null AUTO_INCREMENT PRIMARY KEY ,
	Username varchar(12) not null,
	Course varchar(15) not null		

	
);


