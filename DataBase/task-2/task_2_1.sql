CREATE TABLE Doctor (
id NUMBER(3) NOT NULL ,
name varchar(50),
salary NUMBER(10),
address varchar(50)
);

INSERT INTO DOCTOR VALUES (1,'taha',10000,'bla bla bla ');
INSERT INTO Doctor VALUES (2, 'Mohamed Hassan', 2000, 'Giza');
INSERT INTO Doctor VALUES (3, 'Sara Khaled', 3000, 'Alexandria');
INSERT INTO Doctor VALUES (4, 'Omar Nabil', 4000, 'Mansoura');
INSERT INTO Doctor VALUES (5, 'Mona Adel', 5000, 'Tanta');
INSERT INTO Doctor VALUES (6, 'Hassan Mostafa', 6000, 'Aswan');
INSERT INTO Doctor VALUES (7, 'Nour Sameh', 7000, 'Luxor');
INSERT INTO Doctor VALUES (8, 'Yara Ibrahim', 8000, 'Sohag');
INSERT INTO Doctor VALUES (9, 'Khaled Samir', 9000, 'Minya');
INSERT INTO Doctor VALUES (10, 'Aya Mahmoud', 10000, 'Zagazig');

UPDATE doctor
SET salary=20000
WHERE id=3;

DELETE FROM doctor WHERE id=9;

SELECT name ||' - '||salary AS name_salary FROM doctor;

SELECT id, name , salary*2, address FROM doctor ;

SELECT * FROM doctor WHERE salary IN (1000,2000,3000) ;

RENAME Doctor TO PRD_DOCTOR;
