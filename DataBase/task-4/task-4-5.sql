SELECT 
    name,
    marks,
    DECODE(
        TRUNC(marks/10),
        10, 'A',
        9,  'A',
        8,  'B',
        7,  'C',
        'F'
    ) AS grade
FROM STUDENTS;

CREATE TABLE ORDERS (status CHAR(1));

INSERT INTO ORDERS VALUES ('P');
INSERT INTO ORDERS VALUES ('S');
INSERT INTO ORDERS VALUES ('D');

SELECT 
    status,
    DECODE(
        status,
        'P','Pending',
        'S','Shipped',
        'D','Delivered',
        'Unknown'
    ) AS status_full
FROM ORDERS;