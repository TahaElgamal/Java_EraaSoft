CREATE TABLE Player(
id NUMBER NOT NULL UNIQUE ,
name varchar(255) UNIQUE ,
age NUMBER
);

-------------------------------------------------------------

CREATE TABLE Manger (
id NUMBER NOT NULL UNIQUE ,
name varchar(255) UNIQUE ,
salary NUMBER
);

-------------------------------------------------------------
CREATE TABLE Manger (
id NUMBER PRIMARY KEY  ,
name varchar(255) UNIQUE ,
age NUMBER
);
