-- a copy of this file has to be in /WebContent/META-INF for deployment
INSERT INTO ROLE(ID, NAME) VALUES(1, 'Admin');
INSERT INTO ROLE(ID, NAME) VALUES(2, 'User');
INSERT INTO ROLE(ID, NAME) VALUES(3, 'Arzt');
INSERT INTO ROLE(ID, NAME) VALUES(4, 'Pflege');

INSERT INTO USERS(ID, USERNAME, PASSWORD, ROLE) VALUES (1, 'admin', 'god', 1);

INSERT INTO ARZT(ID, NAME, FACHGEBIET, DURCHWAHL, ROLE_ID) VALUES (1, 'Elfriede Blauensteiner', 'Palliativbehandlung', '555-1234', 4);
INSERT INTO ARZT(ID, NAME, FACHGEBIET, DURCHWAHL, ROLE_ID) VALUES (2, 'Felix Maier', 'Chirurgie', '555-1211',3);

INSERT INTO PATIENT(ID, FIRST_NAME, LAST_NAME, ARZT_ID, AGE, COMMENT, ADMISSION_DATE, BIRTHDATE, STREET, CITY, ZIP) VALUES (1, 'John', 'Mc Affee', 1 , 42, 'Alkoholiker', '2015-12-24', '1972-07-15','Eggenberger Allee 1337', 'Graz', '8010');
INSERT INTO PATIENT(ID, FIRST_NAME, LAST_NAME, ARZT_ID, AGE, COMMENT, ADMISSION_DATE, BIRTHDATE, STREET, CITY, ZIP) VALUES  (2, 'Frank', 'Buttle', 1, 23, 'Herzschwaeche', '2014-02-11', '1999-07-15','Eggenberger Allee 1337', 'Graz', '8010');
INSERT INTO PATIENT(ID, FIRST_NAME, LAST_NAME, ARZT_ID, AGE, ADMISSION_DATE, BIRTHDATE, STREET, CITY, ZIP) VALUES (3, 'Fran', 'Bow', 2, 14, '2018-03-02', '2013-07-15','Eggenberger Allee 1337', 'Graz', '8010');
INSERT INTO PATIENT(ID, FIRST_NAME, LAST_NAME, ARZT_ID, AGE, ADMISSION_DATE, BIRTHDATE, STREET, CITY, ZIP) VALUES (4, 'Michelle', 'Obama', 1, 45, '2017-07-15', '1990-07-15','Eggenberger Allee 1337', 'Graz', '8010');

insert into medikament (id, Bezeichnung) values (1, 'Parkemed');
insert into medikament (id, Bezeichnung) values (2, 'Voltaren Schmerzgel');
insert into medikament (id, Bezeichnung) values (3, 'Immodium Akut');
insert into medikament (id, Bezeichnung) values (4, 'L-Thyroxin Henning');
insert into medikament (id, Bezeichnung) values (5, 'Beloc-Zoc mite');
insert into medikament (id, Bezeichnung) values (6, 'Pantozol 20mg');
insert into medikament (id, Bezeichnung) values (7, 'Nexium mups');
insert into medikament (id, Bezeichnung) values (8, 'Diclofenac-ratiopharm');
insert into medikament (id, Bezeichnung) values (9, 'Euthyrox 25');
insert into medikament (id, Bezeichnung) values (10, 'Olynth');

insert into diagnose (id, Bezeichnung) values (1,'Bakterielle Meningitis');
insert into diagnose (id, Bezeichnung) values (2,'Diabetes mellitus Typ 2');
insert into diagnose (id, Bezeichnung) values (3,'Arthrose');
insert into diagnose (id, Bezeichnung) values (4,'Bindehautentzündung');
insert into diagnose (id, Bezeichnung) values (5,'Thrombose');
insert into diagnose (id, Bezeichnung) values (6,'Pulmonale Hypertonie');
insert into diagnose (id, Bezeichnung) values (7,'Akute allergische Konjunktivitis');
insert into diagnose (id, Bezeichnung) values (8,'Postinfektiöse Hypothyreose');
insert into diagnose (id, Bezeichnung) values (9,'Diabetes mellitus Typ 1');
insert into diagnose (id, Bezeichnung) values (10,'Polymyositis');

INSERT INTO TERMIN(ID, DATUM, ZEIT) VALUES (1, '2019-01-01', '09:15:00');
INSERT INTO TERMIN(ID, DATUM, ZEIT) VALUES (2, '2012-03-18', '12:00:00');
INSERT INTO TERMIN(ID, DATUM, ZEIT) VALUES (3, '2013-04-17', '13:00:00');
INSERT INTO TERMIN(ID, DATUM, ZEIT) VALUES (4, '2014-05-16', '14:00:00');
INSERT INTO TERMIN(ID, DATUM, ZEIT) VALUES (5, '2015-06-15', '15:00:00');
INSERT INTO TERMIN(ID, DATUM, ZEIT) VALUES (6, '2016-07-14', '16:00:00');
INSERT INTO TERMIN(ID, DATUM, ZEIT) VALUES (7, '2017-08-13', '17:00:00');
INSERT INTO TERMIN(ID, DATUM, ZEIT) VALUES (8, '2017-08-13', '17:00:00');

INSERT INTO TERMIN_DIAGNOSE(TERMIN_ID, DIAGNOSEN_ID) VALUES (1, 1);
INSERT INTO TERMIN_DIAGNOSE(TERMIN_ID, DIAGNOSEN_ID) VALUES (1, 2);
INSERT INTO TERMIN_DIAGNOSE(TERMIN_ID, DIAGNOSEN_ID) VALUES (1, 3);

INSERT INTO TERMIN_MEDIKAMENT(TERMIN_ID, MEDIKATION_ID) VALUES (1, 1);
INSERT INTO TERMIN_MEDIKAMENT(TERMIN_ID, MEDIKATION_ID) VALUES (1, 2);

INSERT INTO PATIENT_TERMIN(TERMIN_ID, PATIENT_ID) VALUES(1, 1);
INSERT INTO PATIENT_TERMIN(TERMIN_ID, PATIENT_ID) VALUES(2, 2);
INSERT INTO PATIENT_TERMIN(TERMIN_ID, PATIENT_ID) VALUES(3, 3);
INSERT INTO PATIENT_TERMIN(TERMIN_ID, PATIENT_ID) VALUES(4, 4);
INSERT INTO PATIENT_TERMIN(TERMIN_ID, PATIENT_ID) VALUES(5, 2);
INSERT INTO PATIENT_TERMIN(TERMIN_ID, PATIENT_ID) VALUES(6, 2);
INSERT INTO PATIENT_TERMIN(TERMIN_ID, PATIENT_ID) VALUES(7, 2);
INSERT INTO PATIENT_TERMIN(TERMIN_ID, PATIENT_ID) VALUES(8, 2);

INSERT INTO VITALPARAMETER (ID, BLUTDRUCKDIASTOLISCH, BLUTDRUCKSYSTOLISCH, DIAGNOSEDATUM, MAXIMALSCHMERZ, BELASTUNGSSCHMERZ, RUHESCHMERZ, PULS, TEMPERATUR) VALUES (1,120, 40, '2018-12-24', 200,100, 60, 42, 35);
INSERT INTO VITALPARAMETER (ID, BLUTDRUCKDIASTOLISCH, BLUTDRUCKSYSTOLISCH, DIAGNOSEDATUM, MAXIMALSCHMERZ, BELASTUNGSSCHMERZ, RUHESCHMERZ, PULS, TEMPERATUR) VALUES (2,120, 40, '2017-12-22', 200,100, 60, 42, 35);
INSERT INTO VITALPARAMETER (ID, BLUTDRUCKDIASTOLISCH, BLUTDRUCKSYSTOLISCH, DIAGNOSEDATUM, MAXIMALSCHMERZ, BELASTUNGSSCHMERZ, RUHESCHMERZ, PULS, TEMPERATUR) VALUES (3,120, 40, '2015-12-21', 200,100, 60, 42, 35);
INSERT INTO VITALPARAMETER (ID, BLUTDRUCKDIASTOLISCH, BLUTDRUCKSYSTOLISCH, DIAGNOSEDATUM, MAXIMALSCHMERZ, BELASTUNGSSCHMERZ, RUHESCHMERZ, PULS, TEMPERATUR) VALUES (4,120, 40, '2014-12-12', 200,100, 60, 42, 35);
INSERT INTO VITALPARAMETER (ID, BLUTDRUCKDIASTOLISCH, BLUTDRUCKSYSTOLISCH, DIAGNOSEDATUM, MAXIMALSCHMERZ, BELASTUNGSSCHMERZ, RUHESCHMERZ, PULS, TEMPERATUR) VALUES (5,120, 40, '2013-12-10', 200,100, 60, 42, 35);
INSERT INTO VITALPARAMETER (ID, BLUTDRUCKDIASTOLISCH, BLUTDRUCKSYSTOLISCH, DIAGNOSEDATUM, MAXIMALSCHMERZ, BELASTUNGSSCHMERZ, RUHESCHMERZ, PULS, TEMPERATUR) VALUES (6,120, 40, '2014-12-11', 200,100, 60, 42, 35);
INSERT INTO VITALPARAMETER (ID, BLUTDRUCKDIASTOLISCH, BLUTDRUCKSYSTOLISCH, DIAGNOSEDATUM, MAXIMALSCHMERZ, BELASTUNGSSCHMERZ, RUHESCHMERZ, PULS, TEMPERATUR) VALUES (7,120, 40, '2012-12-11', 200,100, 60, 42, 35);
INSERT INTO VITALPARAMETER (ID, BLUTDRUCKDIASTOLISCH, BLUTDRUCKSYSTOLISCH, DIAGNOSEDATUM, MAXIMALSCHMERZ, BELASTUNGSSCHMERZ, RUHESCHMERZ, PULS, TEMPERATUR) VALUES (8,120, 40, '2011-12-11', 200,100, 60, 42, 35);

INSERT INTO PATIENT_VITALPARAMETER(PATIENT_ID,  VITALPARAMETER_ID) VALUES (1,1);
INSERT INTO PATIENT_VITALPARAMETER(PATIENT_ID,  VITALPARAMETER_ID) VALUES (1,2);
INSERT INTO PATIENT_VITALPARAMETER(PATIENT_ID,  VITALPARAMETER_ID) VALUES (2,3);
INSERT INTO PATIENT_VITALPARAMETER(PATIENT_ID,  VITALPARAMETER_ID) VALUES (2,4);
INSERT INTO PATIENT_VITALPARAMETER(PATIENT_ID,  VITALPARAMETER_ID) VALUES (2,5);
INSERT INTO PATIENT_VITALPARAMETER(PATIENT_ID,  VITALPARAMETER_ID) VALUES (3,6);
INSERT INTO PATIENT_VITALPARAMETER(PATIENT_ID,  VITALPARAMETER_ID) VALUES (4,7);
INSERT INTO PATIENT_VITALPARAMETER(PATIENT_ID,  VITALPARAMETER_ID) VALUES (4,8);

