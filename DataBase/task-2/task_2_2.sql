
CREATE TABLE Employee (
EmployeeID NUMBER(10),
FirstName   varchar(30),
LastName    varchar(30),
Department  varchar(10),
Salary     NUMBER(30)
);

INSERT INTO Employee VALUES (101, 'John1', 'Doe1', 'HR', 20000);
INSERT INTO Employee VALUES (102, 'John2', 'Doe2', 'IT', 50000);
INSERT INTO Employee VALUES (103, 'John3', 'Doe3', 'CS', 40000);
INSERT INTO Employee VALUES (104, 'John4', 'Doe4', 'IT', 10000);
INSERT INTO Employee VALUES (105, 'John5', 'Doe5', 'ZX', 30000);

UPDATE Employee SET salary =60000 WHERE EmployeeID=101;

SELECT * FROM Employee WHERE Department = 'IT';

SELECT 
EmployeeID,
FirstName || ' ' || LastName AS FullName,
Department,
Salary
FROM Employee;
