drop all objects;
CREATE SCHEMA "Fortune cookies";
SET SCHEMA "Fortune cookies";
CREATE TABLE Users
(
User_ID bigint auto_increment NOT NULL,
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

INSERT INTO "Fortune cookies".COOKIES (COOKIE_ID, COOOKIE)
VALUES 
(1, 'Your trouble will pass away soon'), 
(2, 'Beauty will surround you - open your eyes to see it.'), 
(3, 'Grasp the opportunities that come your way.'), 
(4, 'Love comes quickly, whatever you do.'), 
(5, 'Jealousy is unattractive - show some other emotion.'), 
(6, 'Tomorrow, take a moment to do something just for yourself.'), 
(7, 'An idea is not responsible for the people who believe in it.'), 
(8, 'You are special in a way you will soon begin to understand.'), 
(9, 'A loved one is of utmost importance at this time.'), 
(10, 'Do not be hasty, prosperity will knock on your door soon.');
