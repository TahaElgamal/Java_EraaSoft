SELECT AVG(salary)FROM employees;

SELECT COUNT(*)FROM employees;

SELECT MAX(salary)FROM employees;

SELECT MIN(salary)FROM employees;

SELECT SUM(salary)FROM employees;

SELECT 
    department_id,
    AVG(salary)
FROM employees
GROUP BY department_id;

SELECT 
    job_id,
    COUNT(*)
FROM employees
GROUP BY job_id;

SELECT 
    department_id,
    SUM(salary)
FROM employees
GROUP BY department_id
HAVING SUM(salary) > 50000;

SELECT 
    AVG(commission_pct)
FROM employees
WHERE commission_pct IS NOT NULL;

SELECT 
    COUNT(*)
FROM employees
WHERE salary > 10000;

SELECT 
    job_id,
    MAX(salary),
    MIN(salary)
FROM employees
GROUP BY job_id;

SELECT 
    manager_id,
    SUM(salary)
FROM employees
GROUP BY manager_id;

SELECT 
    department_id,
    job_id,
    SUM(salary)
FROM employees
GROUP BY 
    department_id,
    job_id;

SELECT 
    job_id,
    COUNT(*)
FROM employees
GROUP BY job_id
HAVING COUNT(*) > 5;

SELECT 
    department_id,
    COUNT(*) AS total_emp,
    AVG(salary),
    MAX(salary),
    MIN(salary)
FROM employees
GROUP BY department_id;

SELECT 
    department_id,
    AVG(salary),
    COUNT(*)
FROM employees
GROUP BY department_id
HAVING 
    AVG(salary) > 8000
AND COUNT(*) < 10;

SELECT 
    department_id,
    SUM(salary)
FROM employees
GROUP BY department_id
ORDER BY SUM(salary) DESC
FETCH FIRST 1 ROW ONLY;

SELECT 
    department_id,
    SUM(salary) AS Total_Salary,
    AVG(salary) AS Average_Salary
FROM employees
GROUP BY department_id;