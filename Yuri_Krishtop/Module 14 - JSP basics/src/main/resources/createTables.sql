DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Cookies;
DROP TABLE IF EXISTS Metadata;

CREATE TABLE Users
(
id INT NOT NULL AUTO_INCREMENT,
login VARCHAR(30) NOT NULL UNIQUE,
pass VARCHAR(30) NOT NULL CHECK(LENGTH(pass) > 2),
email VARCHAR(30)  NOT NULL UNIQUE CHECK(email LIKE '%@%.%'),
PRIMARY KEY (id)
);

INSERT INTO Users (login, pass, email, ) VALUES 
('Nata', 'tj84hj', 'sanders@hotmail.com'),
('AA789', 'qwerty', 'Alex777@gmail.com'),
('Bob_J', '123456', 'b_jonson@gmail.com'),
('Pit90', 'pitpass', 'pit1990@gmail.com'),
('Pol_Jksn', 'mypass', 'jakson_pol@hotmail.com'),
('vaska', 'mylovepass', 'ivanov1996@mail.ru'),
('petya', 'iukh232', 'sidorov_p@mail.ru'),
('tukrod', '8923pav', 'petrenko@i.ua'),
('podko22', 'karyy11', 'karp_vl@ukr.net'),
('admin', 'admin', 'admin@admin.com');

CREATE TABLE Cookies
(
id INT NOT NULL AUTO_INCREMENT,
cookie VARCHAR NOT NULL,
PRIMARY KEY (id)
);

INSERT INTO Cookies (cookie) VALUES 
('Follow that restless urge to find yourself.'),
('Begin trusting your intuitions.'),
('Ask a friend to join you on your next journey.'),
('Shoot for the moon! If you miss you will still be amongst the stars.'),
('Good things are on their way.'),
('Today is the first day of the rest of your life.'),
('The big issues are career, status or work right now.'),
('Your good nature will bring you unbounded happiness.'),
('Your greatest fortune is the friends and family you have.'),
('An unexpected aquaintance will resurface.'),
('Embrace change; do not fight it.'),
('A golden egg of opportunity falls into your lap this week.'),
('Try everything once, even the things you do not think you will like.'),
('Wisdom comes from experience.'),
('An unexpected relationship will become permanent.'),
('Now is the time to try something new.'),
('You will be called upon to help a friend in trouble.'),
('You will be an inspiration to others.'),
('Conquer your fears, or they will conquer you.'),
('In order to have great friends, you must first learn to be a great friend.'),
('It takes ten times as many muscles to frown as it does to smile.');

CREATE TABLE Metadata
(
id INT NOT NULL AUTO_INCREMENT,
user_id INT,
cookie_id INT NOT NULL,
time_added TIMESTAMP NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (user_id) 
REFERENCES Users (id)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (cookie_id)
REFERENCES Cookies (id)
ON DELETE CASCADE 
ON UPDATE CASCADE,
);

INSERT INTO Metadata (user_id, cookie_id, time_added) VALUES 
(1, 1,  CURRENT_TIMESTAMP),
(3, 2,  CURRENT_TIMESTAMP),
(1, 3,  CURRENT_TIMESTAMP),
(1, 4,  CURRENT_TIMESTAMP),
(2, 5,  CURRENT_TIMESTAMP),
(3, 6,  CURRENT_TIMESTAMP),
(2, 7,  CURRENT_TIMESTAMP),
(4, 8,  CURRENT_TIMESTAMP),
(5, 9,  CURRENT_TIMESTAMP),
(6, 10, CURRENT_TIMESTAMP),
(1, 11,  CURRENT_TIMESTAMP),
(3, 12,  CURRENT_TIMESTAMP),
(1, 13,  CURRENT_TIMESTAMP),
(1, 14,  CURRENT_TIMESTAMP),
(2, 15,  CURRENT_TIMESTAMP),
(3, 16,  CURRENT_TIMESTAMP),
(2, 17,  CURRENT_TIMESTAMP),
(4, 18,  CURRENT_TIMESTAMP),
(5, 19,  CURRENT_TIMESTAMP),
(6, 20, CURRENT_TIMESTAMP),
(5, 21,  CURRENT_TIMESTAMP);