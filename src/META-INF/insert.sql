INSERT INTO ROLE(ID, NAME) VALUES(1, 'ADMIN');
INSERT INTO ROLE(ID, NAME) VALUES(2, 'PERSONAL');

INSERT INTO USERS(ID, USERNAME, PASSWORD, ROLE) VALUES (1, 'admin', 'god', 1);

INSERT INTO BETREUER(ID, NAME) VALUES (1, 'Elfriede Blauensteiner');
INSERT INTO BETREUER(ID, NAME) VALUES (2, 'Felix Maier');

INSERT INTO PERSON(ID, FIRST_NAME, LAST_NAME, BETREUER_ID) VALUES (1, 'John', 'Mc Affee', 1);
INSERT INTO PERSON(ID, FIRST_NAME, LAST_NAME, BETREUER_ID) VALUES (2, 'Frank', 'Buttle', 1);
INSERT INTO PERSON(ID, FIRST_NAME, LAST_NAME, BETREUER_ID) VALUES (3, 'Fran', 'Bow', 2);
INSERT INTO PERSON(ID, FIRST_NAME, LAST_NAME, BETREUER_ID) VALUES (4, 'Michelle', 'Obama', 1);





