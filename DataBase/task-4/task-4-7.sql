CREATE TABLE customers (
    full_name VARCHAR2(50)
);

INSERT INTO customers VALUES ('   Ahmed   ');
INSERT INTO customers VALUES ('   Mohamed');
INSERT INTO customers VALUES ('Ali   ');

SELECT TRIM(full_name)FROM customers;

SELECT LTRIM(full_name)FROM customers;

SELECT RTRIM(full_name)FROM customers;

SELECT LTRIM(RTRIM(full_name,'$#*'),'$#*') as MOD FROM customers;


-------------------------------------------------------------------
SELECT REPLACE('promotion','o','0')FROM DUAL;

SELECT REPLACE(
    'This is a basic course',
    'basic',
    'advanced'
)
FROM DUAL;

CREATE TABLE departments_test (
    dept_name VARCHAR2(50)
);

INSERT INTO departments_test VALUES ('HR');
INSERT INTO departments_test VALUES ('IT');
INSERT INTO departments_test VALUES ('Sales');

SELECT LPAD(dept_name,15,'*')FROM departments_test;

SELECT RPAD(dept_name,15,'-')FROM departments_test;
-------------------------------------------------------------------
SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY')FROM DUAL;

SELECT TO_CHAR(
    SYSDATE,
    'Day, Month YYYY'
)
FROM DUAL;

SELECT TO_CHAR(
    1234567.89,
    '9,999,999.99'
)
FROM DUAL;

SELECT TO_CHAR(
    5000,
    '$9,999'
)
FROM DUAL;

SELECT TO_CHAR(
    SYSDATE,
    'YYYY/MM/DD HH24:MI:SS'
)
FROM DUAL;
---------------------------------------------------------------------------
CREATE TABLE students (
    name VARCHAR2(50),
    score NUMBER
);

INSERT INTO students VALUES ('Ahmed',95);
INSERT INTO students VALUES ('Ali',85);
INSERT INTO students VALUES ('Omar',75);
INSERT INTO students VALUES ('Sara',65);
INSERT INTO students VALUES ('Mona',55);

SELECT 
    name,
    score,
    CASE
        WHEN score >= 90 THEN 'A'
        WHEN (score >= 80 AND  score < 90) THEN 'B'
        WHEN (score >= 70 AND score < 80) THEN 'C'
        ELSE 'F'
    END AS grade
FROM students;

SELECT 
    name,
    score,
    CASE
        WHEN score >= 60 THEN 'Pass'
        ELSE 'Fail'
    END AS result
FROM students;

SELECT 
    name,
    score,
    CASE
        WHEN score >= 90 THEN 'Excellent'
        WHEN (score >= 80 AND  score < 90) THEN 'Good'
        WHEN (score >= 70 AND score < 80) THEN 'Average'
        ELSE 'Needs Improvement'
    END AS message
FROM students;

SELECT  'Today is ' || TO_CHAR(SYSDATE,'Day')FROM DUAL;

---------------------------------------------------------------------------------
SELECT 
    name,
    score,
    DECODE(
        TRUNC(score/10),
        10, 'A',
        9,  'A',
        8,  'B',
        7,  'C',
        'F'
    ) AS grade
FROM STUDENTS;

CREATE TABLE status_log (
    status_code CHAR(1)
);

INSERT INTO status_log VALUES ('N');
INSERT INTO status_log VALUES ('I');
INSERT INTO status_log VALUES ('C');

SELECT 
    status_code,
    DECODE(
        status_code,
        'N','New',
        'I','In Progress',
        'C','Completed'
    ) AS status_name
FROM status_log;

SELECT 
    department,
    DECODE(
        department,
        'HR',500,
        'IT',1000,
        'Sales',1500,
        300
    ) AS bonus
FROM employees;

