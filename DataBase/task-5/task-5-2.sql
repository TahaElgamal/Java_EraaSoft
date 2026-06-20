
CREATE TABLE Doctor (
id NUMBER NOT NULL UNIQUE ,
name varchar(255) UNIQUE ,
salary NUMBER
);

CREATE TABLE Patient (
id NUMBER NOT NULL UNIQUE ,
name varchar(255) UNIQUE ,
salary NUMBER
);

CREATE TABLE Doctor_Patient (
Doc_id NUMBER  ,
Pat_id NUMBER  ,
CONSTRAINT Doc_id_fk  FOREIGN KEY (Doc_id) REFERENCES Doctor (id),
CONSTRAINT Pat_id_fk  FOREIGN KEY (Pat_id) REFERENCES Patient (id),
CONSTRAINT comp_fk  UNIQUE (Doc_id,Pat_id)
);






