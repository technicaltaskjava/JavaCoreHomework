drop all objects;
CREATE SCHEMA "Fortune cookies";
SET SCHEMA "Fortune cookies";
CREATE TABLE Users
(
User_ID bigint NOT NULL,
UserEmail varchar(255) NOT NULL UNIQUE,
UserName varchar(255) NOT NULL UNIQUE,
UserPassword varchar(55) NOT NULL,
LastName varchar(255),
FirstName varchar(255),
Address varchar(255),
City varchar(255) DEFAULT 'Dnepropetrovsk',
Country varchar(255) DEFAULT 'Ukraine',
PRIMARY KEY (User_ID),
CONSTRAINT check_User CHECK (UserEmail LIKE '%_@__%.__%' AND UserPassword LIKE '______%')
);


CREATE TABLE Cookies 
( 
Cookie_Id bigint NOT NULL, 
Coookie varchar(255) NOT NULL UNIQUE, 
PRIMARY KEY (Cookie_Id), 
CONSTRAINT check_Cookie CHECK (Cookie_Id>0) 
);

CREATE TABLE Metadata
( 
User_ID bigint NOT NULL, 
Cookie_ID bigint NOT NULL, 
Time_added timestamp NOT NULL,
CONSTRAINT users_fk FOREIGN KEY(User_ID) REFERENCES Users(USER_ID),
CONSTRAINT cookies_fk FOREIGN KEY(Cookie_ID) REFERENCES Cookies(COOKIE_ID ),
);
INSERT INTO "Fortune cookies".COOKIES (COOKIE_ID, COOOKIE)
VALUES 
(1, 'text1'), 
(2, 'text2'), 
(3, 'text3'), 
(4, 'text4'), 
(5, 'text5'), 
(6, 'text6'), 
(7, 'text7'), 
(8, 'text8'), 
(9, 'text9'), 
(10, 'text10');
select * from "Fortune cookies".COOKIES ;

insert into "Fortune cookies".USERS (USER_ID, USEREMAIL, USERNAME, USERPASSWORD  )
values
(1, 'user1@email.com', 'user1', 'password'),
(2, 'user2@email.com', 'user2', 'password'),
(3, 'user3@email.com', 'user3', 'password'),
(4, 'user4@email.com', 'user4', 'password'),
(5, 'user5@email.com', 'user5', 'password'),
(6, 'user6@email.com', 'user6', 'password'),
(7, 'user7@email.com', 'user7', 'password'),
(8, 'user8@email.com', 'user8', 'password'),
(9, 'user9@email.com', 'user9', 'password'),
(10, 'user10@email.com', 'user10', 'password');
select * from "Fortune cookies".USERS ;

insert into "Fortune cookies".METADATA (USER_ID, COOKIE_ID, TIME_ADDED)
values
(3, 1, CURRENT_TIMESTAMP()),
(2, 2, CURRENT_TIMESTAMP()),
(3, 4, CURRENT_TIMESTAMP()),
(3, 2, CURRENT_TIMESTAMP()),
(3, 3, CURRENT_TIMESTAMP()),
(3, 1, CURRENT_TIMESTAMP()),
(3, 1, CURRENT_TIMESTAMP()),
(3, 1, CURRENT_TIMESTAMP()),
(3, 1, CURRENT_TIMESTAMP()),
(3, 1, CURRENT_TIMESTAMP());