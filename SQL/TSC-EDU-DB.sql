create table CERTIFICATE
(
	ID NUMBER(19) not null
		primary key,
	CERTDATE DATE,
	CERTNAME VARCHAR2(255 char),
	CERTNUMBER NUMBER(19),
	COMPANY VARCHAR2(255 char),
	SCANID_ID NUMBER(19)
)
/

create table DEPARTMENT
(
	ID NUMBER(19) not null
		primary key,
	HEADEMPLOYEEID NUMBER(19),
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
	CERTIFICATEID_ID NUMBER(19)
		constraint FKEXNC9S72SKJTW51UA7M2WWIHP
		references CERTIFICATE,
	DEPARTMENTID_ID NUMBER(19)
		constraint FKNPQROCH3547CWR52EEC1SEBS0
		references DEPARTMENT,
	GRADEID_ID NUMBER(19),
	POSTID_ID NUMBER(19)
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
	add constraint FK1Y85UPW2YCSBFG3KFTY8D9ELG
foreign key (GRADEID_ID) references GRADE
/

create table POST
(
	ID NUMBER(19) not null
		primary key,
	NAME VARCHAR2(255 char) not null
)
/

alter table EMPLOYEE
	add constraint FK6FO3WH5D42XI8CEISMWO6NC45
foreign key (POSTID_ID) references POST
/

create table SCAN
(
	ID NUMBER(19) not null
		primary key,
	SCAN BLOB
)
/

alter table CERTIFICATE
	add constraint FKNSTY3RXAR46P7O4BFLPDYPYYD
foreign key (SCANID_ID) references SCAN
/

