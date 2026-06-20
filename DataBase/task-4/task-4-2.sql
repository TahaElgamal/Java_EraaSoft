SELECT REPLACE('database','a','@') FROM dual;
SELECT REPLACE('This is old system','old','new')FROM DUAL;
CREATE TABLE PRODUCT (    product_name VARCHAR2(50)); 

INSERT INTO PRODUCT VALUES ('Laptop');
INSERT INTO PRODUCT VALUES ('Mouse');
INSERT INTO PRODUCT VALUES ('Keyboard');

SELECT LPAD(product_name,15,'*')FROM PRODUCT;

SELECT RPAD(product_name,15,'#')FROM PRODUCT;