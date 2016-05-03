CREATE TABLE users
(
id int NOT NULL AUTO_INCREMENT,
userName varchar(30) NOT NULL UNIQUE,
email varchar(30) CHECK (email like '%@%'),
password varchar(30) NOT NULL,
PRIMARY KEY (Id)
)

ALTER TABLE users
ADD dateofbirth date;

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
cookie varchar(30) NOT NULL UNIQUE, 
menu boolean NOT NULL DEFAULT TRUE, 
price real NOT NULL, 
PRIMARY KEY (Id) 
);

ALTER TABLE cookies
ADD CONSTRAINT chk_price CHECK (price>0);

INSERT INTO  cookies (cookie, price) VALUES ('cookie1', 10);
INSERT INTO  cookies (cookie, price) VALUES ('cookie2', 12);
INSERT INTO  cookies (cookie, price) VALUES ('cookie3', 8.5);
INSERT INTO  cookies (cookie, menu, price) VALUES ('cookie4', false,12.5);
INSERT INTO  cookies (cookie, menu, price) VALUES ('cookie5', false, 20);
INSERT INTO  cookies (cookie, menu, price) VALUES ('cookie6', false, 18);
INSERT INTO  cookies (cookie, price) VALUES ('cookie7', 10);
INSERT INTO  cookies (cookie, price) VALUES ('cookie8', 12);
INSERT INTO  cookies (cookie, price) VALUES ('cookie9', 18);
INSERT INTO  cookies (cookie, menu, price) VALUES ('cookie10', false, 10);

CREATE TABLE metadata
(
cookie_id int NOT NULL REFERENCES cookies(id),
user_id int NOT NULL,
time_added timestamp,
amount int NOT NULL DEFAULT 1
);

ALTER TABLE metadata 
ADD FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE metadata
ADD CONSTRAINT chk_amount CHECK (amount>0);

INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (1, 1, NOW());
INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (4, 3, NOW());
INSERT INTO  metadata (cookie_id, user_id, time_added, amount) VALUES (2, 4, NOW(), 3);
INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (5, 2, NOW());
INSERT INTO  metadata (cookie_id, user_id, time_added, amount) VALUES (7, 6, NOW(), 3);
INSERT INTO  metadata (cookie_id, user_id, time_added, amount) VALUES (2, 1, NOW(), 2);
INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (7, 2, NOW());
INSERT INTO  metadata (cookie_id, user_id, time_added, amount) VALUES (7, 3, NOW(), 2);
INSERT INTO  metadata (cookie_id, user_id, time_added, amount) VALUES (8, 7, NOW(), 2);
INSERT INTO  metadata (cookie_id, user_id, time_added) VALUES (9, 4, NOW());