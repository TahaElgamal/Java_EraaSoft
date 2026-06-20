CREATE TABLE Language (
    id NUMBER PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE Teacher (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(50),
    salary NUMBER,
    language_id NUMBER,
    CONSTRAINT fk_language FOREIGN KEY (language_id) REFERENCES Language(id)
);