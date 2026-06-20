CREATE TABLE EMP_TEST (name varchar(50));

INSERT INTO EMP_TEST values('   Taha    ');
INSERT INTO EMP_TEST values('   Ahmed    ');
INSERT INTO EMP_TEST values('   Elgamal    ');

SELECT TRIM(name) trm FROM EMP_TEST;

SELECT LTRIM(name)  trm FROM EMP_TEST;
SELECT RTRIM(name)  trm FROM EMP_TEST;

SELECT LTRIM(RTRIM(name,'*#'),'*#') tm FROM EMP_TEST;
