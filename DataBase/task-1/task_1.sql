CREATE TABLE manager(
id NUMBER(3),
name varchar(50),
age NUMBER(3),
birth_date DATE ,
address varchar(50)
);

ALTER TABLE manager DROP COLUMN address;

ALTER TABLE manager ADD (
city_address varchar(50),
street varchar(50));

ALTER TABLE manager RENAME COLUMN name TO full_name;

ALTER TABLE manager READ ONLY;

CREATE TABLE owner AS 
SELECT id , full_name , birth_date FROM manager ;

ALTER TABLE manager RENAME TO master;

DROP TABLE MASTER;
DROP TABLE OWNER;
