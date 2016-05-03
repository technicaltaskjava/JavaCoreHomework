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
  Title  VARCHAR(20),
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

