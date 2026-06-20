CREATE TABLE Doctor (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(50),
    salary NUMBER
);

CREATE TABLE Patient (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(50),
    age NUMBER
);

CREATE TABLE Doctor_Patient (
    doctor_id NUMBER,
    patient_id NUMBER,
    
    CONSTRAINT fk_doc FOREIGN KEY (doctor_id) REFERENCES Doctor(id),
    CONSTRAINT fk_pat FOREIGN KEY (patient_id) REFERENCES Patient(id),
    CONSTRAINT comp_pat UNIQUE  (doctor_id, patient_id)
);