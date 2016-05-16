DROP TABLE IF EXISTS User;
CREATE TABLE User (
  ID       INT PRIMARY KEY,
  UserName VARCHAR(20),
  Password VARCHAR(20),
  email    VARCHAR(20) CHECK (email LIKE '%@%.%'),
  DOB      INT NOT NULL
);

DROP TABLE IF EXISTS Cookies;
CREATE TABLE Cookies (
  ID     INT PRIMARY KEY,
  MESSAGE  VARCHAR(60),
  Time   TIME,
  Cooked BOOLEAN
);

DROP TABLE IF EXISTS MetaData;
CREATE TABLE MetaData (
  UserID     INT,
  FOREIGN KEY (UserID) REFERENCES User (ID),
  CookiesID  INT,
  FOREIGN KEY (CookiesID) REFERENCES Cookies (ID),
  Time_added TIME
);

INSERT INTO User (ID, UserName, password, email, DOB) VALUES (1, 'Petrenko', '233', 'petrov@gmail.ru', 1980);
INSERT INTO User (ID, UserName, password, email, DOB) VALUES (2, 'Onoprienko', '233', 'ivanov@mail.com', 1975);
INSERT INTO User (ID, UserName, password, email, DOB) VALUES (3, 'Sidorov', '533', 'sidorov@sd.ru', 1985);
INSERT INTO User (ID, UserName, password, email, DOB) VALUES (4, 'Popov', '243', 'popov@sd.ru', 1990);
INSERT INTO User (ID, UserName, password, email, DOB) VALUES (5, 'Kuznecov', '833', 'kuznecov@sd.ru', 1982);
INSERT INTO User (ID, UserName, password, email, DOB) VALUES (6, 'Kolesnik', '203', 'kolesnik@sd.ru', 1982);
INSERT INTO User (ID, UserName, password, email, DOB) VALUES (7, 'Kovalenko', '933', 'kovalenko@sd.ru', 1986);
INSERT INTO User (ID, UserName, password, email, DOB) VALUES (8, 'Emelianov', '733', 'emelianov@sd.ru', 1980);
INSERT INTO User (ID, UserName, password, email, DOB) VALUES (9, 'Bobrichev', '273', 'bobrishev@sd.ru', 1981);
INSERT INTO User (ID, UserName, password, email, DOB) VALUES (10, 'Yagnuykov', '283', 'yagnyukov@sd.ru', 1987);

ALTER TABLE User
  ADD FirstName VARCHAR(30) DEFAULT '';
UPDATE USER
SET FirstName = 'Nikola'
WHERE ID = 1;

UPDATE USER
SET FirstName = 'Alex'
WHERE ID IN (2, 4,  7);

UPDATE USER
SET FirstName = 'Sergey'
WHERE ID IN (3, 5);

UPDATE USER
SET FirstName = 'Igor'
WHERE ID IN (9,8);

INSERT INTO COOKIES (ID, MESSAGE, TIME, COOKED) VALUES (1, 'Life is too short to hold grudges.', CURRENT_TIME, TRUE);
INSERT INTO COOKIES (ID, MESSAGE, TIME, COOKED) VALUES (2, 'An unexpected aquaintance will resurface.', CURRENT_TIME, FALSE);
INSERT INTO COOKIES (ID, MESSAGE, TIME, COOKED) VALUES (3, 'Romance follows you if you can only see it.', CURRENT_TIME, TRUE);
INSERT INTO COOKIES (ID, MESSAGE, TIME, COOKED) VALUES (4, 'Next time you have the opportunity, go on a rollercoaster.', CURRENT_TIME, FALSE);
INSERT INTO COOKIES (ID, MESSAGE, TIME, COOKED) VALUES (5, 'Every solution breeds new problems.', CURRENT_TIME, FALSE);
INSERT INTO COOKIES (ID, MESSAGE, TIME, COOKED) VALUES (6, 'Expand your horizons.', CURRENT_TIME, TRUE);
INSERT INTO COOKIES (ID, MESSAGE, TIME, COOKED) VALUES (7, 'Look to your inner being for guidance.', CURRENT_TIME, TRUE);
INSERT INTO COOKIES (ID, MESSAGE, TIME, COOKED) VALUES (8, 'Keep your eyes open. You never know what you might see.', CURRENT_TIME, TRUE);
INSERT INTO COOKIES (ID, MESSAGE, TIME, COOKED) VALUES (9, 'Demonstrate refinement in everything you do.', CURRENT_TIME, TRUE);
INSERT INTO COOKIES (ID, MESSAGE, TIME, COOKED) VALUES (10, 'Your talents will be recognized and suitably rewarded.', CURRENT_TIME, TRUE);

ALTER TABLE COOKIES
  ADD TITLE VARCHAR(30) DEFAULT '';

UPDATE COOKIES
SET TITLE = 'Funny';

UPDATE COOKIES
SET TITLE = 'Happy'
WHERE ID IN (1, 5);

INSERT INTO METADATA (USERID, COOKIESID, TIME_ADDED) VALUES (1, 2, NOW());
INSERT INTO METADATA (USERID, COOKIESID, TIME_ADDED) VALUES (2, 3, NOW());
INSERT INTO METADATA (USERID, COOKIESID, TIME_ADDED) VALUES (3, 5, NOW());
INSERT INTO METADATA (USERID, COOKIESID, TIME_ADDED) VALUES (4, 6, NOW());
INSERT INTO METADATA (USERID, COOKIESID, TIME_ADDED) VALUES (5, 9, NOW());
INSERT INTO METADATA (USERID, COOKIESID, TIME_ADDED) VALUES (6, 10, NOW());
INSERT INTO METADATA (USERID, COOKIESID, TIME_ADDED) VALUES (7, 6, NOW());
INSERT INTO METADATA (USERID, COOKIESID, TIME_ADDED) VALUES (8, 6, NOW());
INSERT INTO METADATA (USERID, COOKIESID, TIME_ADDED) VALUES (9, 1, NOW());
INSERT INTO METADATA (USERID, COOKIESID, TIME_ADDED) VALUES (10, 8, NOW());

