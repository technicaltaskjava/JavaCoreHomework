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


select * from "Fortune cookies".METADATA ;
select USERNAME , COOOKIE from "Fortune cookies".METADATA 
INNER
join "Fortune cookies".USERS  ON METADATA.USER_ID  = USERS.USER_ID 
INNER 
join "Fortune cookies".COOKIES ON METADATA.COOKIE_ID = COOKIES.COOKIE_ID ;

-- Example 1
insert into "Fortune cookies".METADATA (USER_ID, COOKIE_ID, TIME_ADDED)
values
(1, 1, CURRENT_TIMESTAMP());

select USERNAME , COOOKIE from "Fortune cookies".METADATA 
INNER
join "Fortune cookies".USERS as us  ON METADATA.USER_ID  = us.USER_ID 
INNER 
join "Fortune cookies".COOKIES ON METADATA.COOKIE_ID = COOKIES.COOKIE_ID 
where us.USEREMAIL = 'user1@email.com';

-- Example 2

insert into "Fortune cookies".METADATA (USER_ID, COOKIE_ID, TIME_ADDED)
values
(5, 5, CURRENT_TIMESTAMP());

select USERNAME , COOOKIE from "Fortune cookies".METADATA 
INNER
join "Fortune cookies".USERS as us  ON METADATA.USER_ID  = us.USER_ID 
INNER 
join "Fortune cookies".COOKIES as co ON METADATA.COOKIE_ID = co.COOKIE_ID 
where us.USEREMAIL  = 'user5@email.com' AND co.COOKIE_ID = 5 ;

-- Example 3

delete from "Fortune cookies".METADATA   where USER_ID  = 5;
delete from "Fortune cookies".USERS where USERNAME = 'user5';
select USERNAME  from "Fortune cookies".USERS ;

select USERNAME , COOOKIE from "Fortune cookies".METADATA 
INNER
join "Fortune cookies".USERS as us  ON METADATA.USER_ID  = us.USER_ID 
INNER 
join "Fortune cookies".COOKIES as co ON METADATA.COOKIE_ID = co.COOKIE_ID 
where us.USEREMAIL   = 'user5@email.com' AND co.COOKIE_ID = 5 ;

-- Example 4 
ALTER TABLE "Fortune cookies".METADATA  DROP FOREIGN KEY `users_fk`;
ALTER TABLE "Fortune cookies".METADATA  DROP FOREIGN KEY `cookies_fk`;

ALTER TABLE "Fortune cookies".METADATA 
ADD CONSTRAINT users_fk FOREIGN KEY(User_ID) REFERENCES Users(USER_ID) ON DELETE CASCADE;
ALTER TABLE "Fortune cookies".METADATA 
ADD CONSTRAINT cookies_fk FOREIGN KEY(Cookie_ID) REFERENCES Cookies(COOKIE_ID )ON DELETE CASCADE;

delete from "Fortune cookies".USERS where USEREMAIL  = 'user1@email.com';
select * from "Fortune cookies".METADATA;
select * from "Fortune cookies".USERS;

-- Example 5

select USERNAME , LASTNAME , FIRSTNAME , ADDRESS from "Fortune cookies".USERS where USERNAME between 'user2' and 'user3';

UPDATE "Fortune cookies".USERS 
SET LASTNAME = 'LastName3', FIRSTNAME  = 'FirstName3', ADDRESS = 'Adress 3'
WHERE USERNAME = 'user3';
UPDATE "Fortune cookies".USERS 
SET LASTNAME = 'LastName2', FIRSTNAME  = 'FirstName2', ADDRESS = 'Adress 2'
WHERE USERNAME = 'user2';
insert into "Fortune cookies".METADATA (USER_ID, COOKIE_ID, TIME_ADDED)
values
(2, 5, CURRENT_TIMESTAMP());

select FIRSTNAME, LASTNAME, COOOKIE, TIME_ADDED  from "Fortune cookies".METADATA 
join "Fortune cookies".USERS as u ON METADATA.USER_ID  = u.USER_ID 
join "Fortune cookies".COOKIES ON METADATA.COOKIE_ID = COOKIES.COOKIE_ID 
where u.USERNAME between 'user2' and 'user3';
