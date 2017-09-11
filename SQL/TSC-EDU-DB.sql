create table CERTIFICATES
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

create table DEPARTMENTS
(
	ID NUMBER(19) not null
		primary key,
	LEADER NUMBER(19),
	NAME VARCHAR2(255 char) not null,
	PARENTDEPARTMENT_ID NUMBER(19)
		constraint FK3R5ADEO60VGTIE3ISAOJ3J91X
		references DEPARTMENTS
)
/

create table EMPLOYEES
(
	ID NUMBER(19) not null
		primary key,
	BIRTHDATE DATE not null,
	PATRONYMIC VARCHAR2(255 char),
	NAME VARCHAR2(255 char) not null,
	SALARY NUMBER(10) not null,
	SURNAME VARCHAR2(255 char) not null,
	HIST_ID NUMBER(19) not null,
	IS_ACTIVE NUMBER(1) not null,
	START_DATE DATE not null,
	END_DATE DATE,
	CERTIFICATE_ID NUMBER(19)
		constraint FK4F2UCRGK8X9WTPDS24LGL29OE
		references CERTIFICATES,
	DEPARTMENT_ID NUMBER(19)
		constraint FK14TIJXQRY9ML17NK86SQFP561
		references DEPARTMENTS,
	GRADE_ID NUMBER(19),
	POST_ID NUMBER(19)
)

/

alter table EMPLOYEES
	add constraint FK1OQ620LAP9D06UE3GNIK89458
foreign key (GRADE_ID) references GRADES
/

create table GRADES
(
	ID NUMBER(19) not null
		primary key,
	NAME VARCHAR2(255 char) not null
)
/

alter table EMPLOYEES
	add constraint FK1OQ620LAP9D06BH3GNIK89458
foreign key (GRADE_ID) references GRADES
/

create table POSTS
(
	ID NUMBER(19) not null
		primary key,
	NAME VARCHAR2(255 char) not null
)
/

alter table EMPLOYEES
	add constraint FK5DIOK5AM3DJIFY07Q7CNG480C
foreign key (POST_ID) references POSTS
/

create table SCANS
(
	ID NUMBER(19) not null
		primary key,
	SCAN BLOB
)
/

alter table CERTIFICATES
	add constraint FKJB3WT91SO91BNUE474IV9EMVU
foreign key (SCAN_ID) references SCANS
/

