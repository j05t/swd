INSERT INTO ROLE(ID, NAME) VALUES(1, 'ADMIN');
INSERT INTO ROLE(ID, NAME) VALUES(2, 'PERSONAL');

INSERT INTO USERS(ID, USERNAME, PASSWORD, ROLE) VALUES (1, 'admin', 'god', 1);

INSERT INTO BETREUER(ID, NAME) VALUES (1, 'Elfriede Blauensteiner');
INSERT INTO BETREUER(ID, NAME) VALUES (2, 'Felix Maier');

INSERT INTO PERSON(ID, FIRST_NAME, LAST_NAME, BETREUER_ID, AGE, COMMENT, ADMISSION_DATE) VALUES (1, 'John', 'Mc Affee', 1 , 42, 'Alkoholiker', '2015-12-24');
INSERT INTO PERSON(ID, FIRST_NAME, LAST_NAME, BETREUER_ID, AGE, COMMENT, ADMISSION_DATE) VALUES (2, 'Frank', 'Buttle', 1, 23, 'Herzschwaeche', '2014-02-11');
INSERT INTO PERSON(ID, FIRST_NAME, LAST_NAME, BETREUER_ID, AGE, ADMISSION_DATE) VALUES (3, 'Fran', 'Bow', 2, 14, '2018-03-02');
INSERT INTO PERSON(ID, FIRST_NAME, LAST_NAME, BETREUER_ID, AGE, ADMISSION_DATE) VALUES (4, 'Michelle', 'Obama', 1, 45, '2017-07-15');

INSERT INTO TERMIN(ID, DATUM, ZEIT) VALUES (1, '2019-01-01', '09:15:00');
INSERT INTO TERMIN(ID, DATUM, ZEIT) VALUES (2, '2019-02-12', '11:00:00');

INSERT INTO PERSON_TERMIN(TERMIN_ID, PERSON_ID) VALUES(1, 1);
INSERT INTO PERSON_TERMIN(TERMIN_ID, PERSON_ID) VALUES(2, 2);
INSERT INTO PERSON_TERMIN(TERMIN_ID, PERSON_ID) VALUES(2, 3);
INSERT INTO PERSON_TERMIN(TERMIN_ID, PERSON_ID) VALUES(2, 4);
