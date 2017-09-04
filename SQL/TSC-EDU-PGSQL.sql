CREATE TABLE CERTIFICATE (
  ID INTEGER PRIMARY KEY,
  RECEIVE_DATE DATE,
  COMPANY VARCHAR(90),
  NAME VARCHAR(90) NOT NULL,
  CERT_NUMBER INTEGER,
  SCAN_ID INTEGER NOT NULL
);

CREATE TABLE SCAN (
  ID INTEGER PRIMARY KEY,
  SCAN INTEGER NOT NULL
);

CREATE TABLE GRADE (
  ID INTEGER PRIMARY KEY,
  NAME VARCHAR(90) NOT NULL
);

CREATE TABLE POST (
  ID INTEGER PRIMARY KEY,
  NAME VARCHAR(90) NOT NULL
);

CREATE TABLE DEPARTMENT (
  ID INTEGER PRIMARY KEY,
  NAME VARCHAR(90) NOT NULL,
  HEAD_EMPLOYEE_ID INTEGER
);

CREATE TABLE EMPLOYEE (
  ID INTEGER PRIMARY KEY,
  NAME VARCHAR(90) NOT NULL,
  SURNAME VARCHAR(90) NOT NULL,
  FATHER_NAME VARCHAR(90),
  BIRTH_DATE DATE NOT NULL,
  SALARY INTEGER,
  POST_ID INTEGER,
  GRADE_ID INTEGER,
  DEPARTMENT_ID INTEGER,
  CERTIFICATE_ID INTEGER
);

CREATE TABLE DEP_HAS_PARENT_DEP (
  DEPARTMENT_ID INTEGER NOT NULL,
  PARENT_DEPARTMENT_ID INTEGER NOT NULL
);

ALTER TABLE DEPARTMENT ADD CONSTRAINT DEPARTMENT_HAS_HEAD_FK
FOREIGN KEY (HEAD_EMPLOYEE_ID) REFERENCES EMPLOYEE (ID);

ALTER TABLE CERTIFICATE ADD	CONSTRAINT CERTIFICATE_HAS_SCAN_FK
FOREIGN KEY (SCAN_ID) REFERENCES SCAN (ID);

ALTER TABLE EMPLOYEE ADD CONSTRAINT EMPLOYEE_HAS_POST_FK
FOREIGN KEY (POST_ID) REFERENCES POST (ID);
ALTER TABLE EMPLOYEE ADD CONSTRAINT EMPLOYEE_HAS_GRADE_FK
FOREIGN KEY (GRADE_ID) REFERENCES GRADE (ID);
ALTER TABLE EMPLOYEE ADD CONSTRAINT EMPLOYEE_HAS_DEPARTMENT_FK
FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENT (ID);
ALTER TABLE EMPLOYEE ADD CONSTRAINT EMPLOYEE_HAS_CERTIFICATE_FK
FOREIGN KEY (CERTIFICATE_ID) REFERENCES CERTIFICATE (ID);

ALTER TABLE DEP_HAS_PARENT_DEP ADD	CONSTRAINT DEPARTMENT_FK
FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENT (ID);
ALTER TABLE DEP_HAS_PARENT_DEP ADD	CONSTRAINT PARENT_FK
FOREIGN KEY (PARENT_DEPARTMENT_ID) REFERENCES DEPARTMENT (ID);