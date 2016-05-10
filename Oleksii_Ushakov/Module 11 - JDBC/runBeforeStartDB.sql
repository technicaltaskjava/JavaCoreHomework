CREATE TABLE Users (
	id INT IDENTITY(1,1),
	first_name VARCHAR(50) NOT NULL CHECK LENGTH(first_name) > 2,
	second_name VARCHAR(50) NOT NULL CHECK LENGTH(second_name) > 2,
	password VARCHAR(50) NOT NULL CHECK LENGTH(password) > 8,
	email VARCHAR(50) NOT NULL CHECK email LIKE '__%@%_.__%',
	reg_date DATE NOT NULL DEFAULT CURRENT_DATE,

	CONSTRAINT pk_users PRIMARY KEY (id) 
);

CREATE TABLE Cookies (
	id INT IDENTITY(1,1),
	cookie VARCHAR(200) NOT NULL CHECK LENGTH(cookie) > 10,
	expiration_date DATE NOT NULL,
	price DOUBLE NOT NULL DEFAULT 1.00 CHECK price >= 1.00,

	CONSTRAINT pk_cookies PRIMARY KEY (id) 
);

CREATE TABLE Metadata (
	cookie_id INT NOT NULL,
	user_id INT NOT NULL,
	time_added DATE NOT NULL DEFAULT CURRENT_DATE,

	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT fk_cookie_id FOREIGN KEY (cookie_id) REFERENCES Cookies(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- INSERT INTO Users
INSERT INTO Users(first_name, second_name, password, email, reg_date) VALUES ('first_name01','second_name01','password01','email01@gmail.com', CURRENT_DATE-5);
INSERT INTO Users(first_name, second_name, password, email, reg_date) VALUES ('first_name02','second_name02','password02','email02@gmail.com', CURRENT_DATE-7);
INSERT INTO Users(first_name, second_name, password, email, reg_date) VALUES ('first_name03','second_name03','password03','email03@gmail.com', CURRENT_DATE-2);
INSERT INTO Users(first_name, second_name, password, email, reg_date) VALUES ('first_name04','second_name04','password04','email04@gmail.com', CURRENT_DATE-1);
INSERT INTO Users(first_name, second_name, password, email, reg_date) VALUES ('first_name05','second_name05','password05','email05@gmail.com', CURRENT_DATE-9);
INSERT INTO Users(first_name, second_name, password, email, reg_date) VALUES ('first_name06','second_name06','password06','email06@gmail.com', CURRENT_DATE-2);
INSERT INTO Users(first_name, second_name, password, email, reg_date) VALUES ('first_name07','second_name07','password07','email07@gmail.com', CURRENT_DATE-6);
INSERT INTO Users(first_name, second_name, password, email, reg_date) VALUES ('first_name08','second_name08','password08','email08@gmail.com', CURRENT_DATE-3);
INSERT INTO Users(first_name, second_name, password, email, reg_date) VALUES ('first_name09','second_name09','password09','email09@gmail.com', CURRENT_DATE-4);
INSERT INTO Users(first_name, second_name, password, email, reg_date) VALUES ('first_name10','second_name10','password10','email10@gmail.com', CURRENT_DATE-8);
INSERT INTO Users(first_name, second_name, password, email, reg_date) VALUES ('greedy_first_name','greedy_second_name','greedypassword11','no_greedy@gmail.com', CURRENT_DATE-8);

-- INSERT INTO Cookies
INSERT INTO Cookies(cookie, expiration_date) VALUES ('You are a person of culture, cultivate it', CURRENT_DATE + 2);
INSERT INTO Cookies(cookie, expiration_date) VALUES ('You will be an inspiration to others', CURRENT_DATE + 3);
INSERT INTO Cookies(cookie, expiration_date) VALUES ('Take the time to be considerate of others', CURRENT_DATE + 3);
INSERT INTO Cookies(cookie, expiration_date) VALUES ('It is not advisable to leap before you look, but that may be all you have time for', CURRENT_DATE + 2);
INSERT INTO Cookies(cookie, expiration_date) VALUES ('This is the time for caution, but not for fear', CURRENT_DATE + 4);
INSERT INTO Cookies(cookie, expiration_date) VALUES ('Do what you can to prolong your life, in the hope that someday you''ll learn what it''s for', CURRENT_DATE + 4);
INSERT INTO Cookies(cookie, expiration_date) VALUES ('Demonstrate refinement in everything you do', CURRENT_DATE + 1);
INSERT INTO Cookies(cookie, expiration_date) VALUES ('Pursue your work with all due seriousness', CURRENT_DATE + 4);
INSERT INTO Cookies(cookie, expiration_date) VALUES ('Opportunity is knocking at your front door', CURRENT_DATE + 2);
INSERT INTO Cookies(cookie, expiration_date) VALUES ('Try everything once, even the things you don''t think you will like', CURRENT_DATE + 0);
INSERT INTO Cookies(cookie, expiration_date) VALUES ('Сookie that nobody buys', CURRENT_DATE + 2);

-- INSERT INTO Metadata
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (10, 2, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (8, 2, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (3, 5, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (10, 6, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (1, 10, CURRENT_DATE + 0);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (10, 5, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (5, 5, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (3, 6, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (7, 7, CURRENT_DATE + 0);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (8, 9, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (4, 2, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (5, 4, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (7, 3, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (2, 1, CURRENT_DATE + 0);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (2, 10, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (2, 6, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (2, 6, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (7, 2, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (7, 10, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (6, 1, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (3, 3, CURRENT_DATE + 0);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (8, 4, CURRENT_DATE + 0);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (1, 3, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (9, 7, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (5, 4, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (4, 9, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (9, 5, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (7, 3, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (10, 6, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (7, 1, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (3, 9, CURRENT_DATE + 0);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (6, 4, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (10, 9, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (4, 6, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (1, 8, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (1, 5, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (5, 4, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (7, 5, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (3, 5, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (9, 6, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (2, 7, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (9, 4, CURRENT_DATE + 0);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (4, 8, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (2, 6, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (6, 1, CURRENT_DATE + 0);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (9, 9, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (1, 5, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (10, 8, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (8, 1, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (10, 8, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (9, 4, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (1, 1, CURRENT_DATE + 0);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (6, 4, CURRENT_DATE + 0);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (1, 1, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (8, 7, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (9, 1, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (4, 8, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (10, 9, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (10, 5, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (8, 2, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (5, 7, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (6, 9, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (1, 7, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (8, 3, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (4, 8, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (7, 6, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (1, 4, CURRENT_DATE + 0);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (4, 9, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (5, 5, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (2, 10, CURRENT_DATE + 3);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (9, 5, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (7, 7, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (3, 8, CURRENT_DATE + 0);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (5, 9, CURRENT_DATE + 1);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (6, 2, CURRENT_DATE + 4);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (6, 10, CURRENT_DATE + 2);
INSERT INTO Metadata(cookie_id, user_id, time_added) VALUES (3, 1, CURRENT_DATE + 0);