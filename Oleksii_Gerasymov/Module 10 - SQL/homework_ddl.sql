CREATE TABLE users
(
id int NOT NULL AUTO_INCREMENT,
userName varchar(30) NOT NULL UNIQUE,
email varchar(30) CHECK (email like '%@%'),
password varchar(30) NOT NULL,
dateofbirth date,
PRIMARY KEY (Id)
);

INSERT INTO  users (username, email, password, dateofbirth) VALUES ('admin', 'admin@company.com', '12345', '1980-04-15');
INSERT INTO  users (username, email, password, dateofbirth) VALUES ('boss', 'boss@company.com', '11111', '1978-01-01');
INSERT INTO  users (username, email, password, dateofbirth) VALUES ('account', 'account@company.com', '22222', '1989-03-21');
INSERT INTO  users (username, email, password, dateofbirth) VALUES ('user1', 'user1@company.com', '11111', '1990-01-18');
INSERT INTO  users (username, email, password, dateofbirth) VALUES ('user2', 'user2@company.com', '12345', '1990-02-19');
INSERT INTO  users (username, email, password, dateofbirth) VALUES ('user3', 'user3@company.com', '00000', '1989-03-20');
INSERT INTO  users (username, email, password, dateofbirth) VALUES ('user4', 'user4@company.com', '121212', '1987-04-01');
INSERT INTO  users (username, email, password, dateofbirth) VALUES ('user5', 'user5@company.com', '12345', '1987-05-03');
INSERT INTO  users (username, email, password, dateofbirth) VALUES ('user6', 'user6@company.com', '99999', '1986-09-04');
INSERT INTO  users (username, email, password, dateofbirth) VALUES ('user7', 'user7@company.com', '88888', '1989-02-04');
INSERT INTO  users (username, email, password, dateofbirth) VALUES ('user8', 'user8@company.com', '11111', '1980-01-07');

DELETE FROM users WHERE id=11;

CREATE TABLE cookies 
( 
id int NOT NULL AUTO_INCREMENT, 
cookie varchar(200) NOT NULL UNIQUE, 
active boolean NOT NULL DEFAULT TRUE, 
PRIMARY KEY (Id) 
);

INSERT INTO  cookies (cookie) VALUES ('Examine the situation before you act impulsively.');
INSERT INTO  cookies (cookie) VALUES ('Grasp the opportunities that come your way.');
INSERT INTO  cookies (cookie) VALUES ('Your trouble will pass away soon.');
INSERT INTO  cookies (cookie, active) VALUES ('Be cautious in your daily affairs.', true);
INSERT INTO  cookies (cookie, active) VALUES ('Accept the next proposition you hear.', false);
INSERT INTO  cookies (cookie, active) VALUES ('Remember yesterday, but live for today.', true);
INSERT INTO  cookies (cookie) VALUES ('Demonstrate refinement in everything you do.');
INSERT INTO  cookies (cookie) VALUES ('Financial prosperity is coming your way!');
INSERT INTO  cookies (cookie) VALUES ('You are a person of culture, cultivate it.');
INSERT INTO  cookies (cookie, active) VALUES ('A cheerful letter or message is on its way to you.', false);

CREATE TABLE metadata
(
cookie_id int NOT NULL REFERENCES cookies(id),
user_id int NOT NULL,
time_added timestamp
);

ALTER TABLE metadata 
ADD FOREIGN KEY (user_id) REFERENCES users(id);


INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (1, 1, NOW());
INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (4, 3, NOW());
INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (2, 4, NOW());
INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (5, 2, NOW());
INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (7, 6, NOW());
INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (2, 1, NOW());
INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (7, 4, NOW());
INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (7, 3, NOW());
INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (8, 7, NOW());
INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (9, 4, NOW());