create table CERTIFICATE
(
	ID NUMBER(19) not null
		primary key,
	CERTDATE DATE,
	CERTNAME VARCHAR2(255 char),
	CERTNUMBER NUMBER(19),
	COMPANY VARCHAR2(255 char),
	SCAN_ID NUMBER(19)
)
/

create table DEPARTMENT
(
	ID NUMBER(19) not null
		primary key,
	HEADEMPLOYEE_ID NUMBER(19),
	NAME VARCHAR2(255 char) not null,
	PARENTDEPARTMENT_ID NUMBER(19)
		constraint FK3R5ADEO60VGTIE3ISAOJ3J91X
		references DEPARTMENT
)
/

create table EMPLOYEE
(
	ID NUMBER(19) not null
		primary key,
	BIRTHDATE DATE not null,
	FATHERNAME VARCHAR2(255 char),
	NAME VARCHAR2(255 char) not null,
	SALARY NUMBER(10) not null,
	SURNAME VARCHAR2(255 char) not null,
	CERTIFICATE_ID NUMBER(19)
		constraint FK4F2UCRGK8X9WTPDS24LGL29OE
		references CERTIFICATE,
	DEPARTMENT_ID NUMBER(19)
		constraint FK14TIJXQRY9ML17NK86SQFP561
		references DEPARTMENT,
	GRADE_ID NUMBER(19),
	POST_ID NUMBER(19)
)
/

create table GRADE
(
	ID NUMBER(19) not null
		primary key,
	NAME VARCHAR2(255 char) not null
)
/

alter table EMPLOYEE
	add constraint FK1OQ620LAP9D06BH3GNIK89458
foreign key (GRADE_ID) references GRADE
/

create table POST
(
	ID NUMBER(19) not null
		primary key,
	NAME VARCHAR2(255 char) not null
)
/

alter table EMPLOYEE
	add constraint FK5DIOK5AM3DJIFY07Q7CNG480C
foreign key (POST_ID) references POST
/

create table SCAN
(
	ID NUMBER(19) not null
		primary key,
	SCAN BLOB
)
/

alter table CERTIFICATE
	add constraint FKJB3WT91SO91BNUE474IV9EMVU
foreign key (SCAN_ID) references SCAN
/

