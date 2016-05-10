CREATE TABLE Users
(
id INT NOT NULL AUTO_INCREMENT,
login VARCHAR(30) NOT NULL UNIQUE,
name VARCHAR(30) NOT NULL,
surname VARCHAR(30) NOT NULL,
email VARCHAR(30)  NOT NULL UNIQUE CHECK(email LIKE '%@%.%'),
pass VARCHAR(30) NOT NULL CHECK(LENGTH(pass) > 5),
DOB DATE,
regDate DATE,
phone VARCHAR(20),
PRIMARY KEY (id)
);

INSERT INTO Users (login, name, surname, email, pass, DOB, regDate, phone) VALUES 
('Nata', 'Nataly', 'Sanders', 'sanders@hotmail.com', 'tj84hj', '1984-08-09', '2015-01-02', '+380971250934'),
('AA789', 'Alex', 'Gray', 'Alex777@gmail.com', 'qwerty', '1997-05-10', '2015-01-03', '+380667892309'),
('Bob_J', 'Bob', 'Jonson', 'b_jonson@gmail.com', '123456', '1987-07-12', '2014-12-29', '+380505430976'),
('Pit90', 'Pit', 'Smit', 'pit1990@gmail.com', 'pitpass', '1990-02-19', '2015-09-22', '+380978935681'),
('Pol_Jksn', 'Pol', 'Jakson', 'jakson_pol@hotmail.com', 'batman', '1993-07-12', '2015-03-14', '+380502258466'),
('vaska', 'Vasya', 'Ivanov', 'ivanov1996@mail.ru', 'mylovepass', '1996-04-02', '2014-11-27', '+380509923402'),
('petya', 'Petr', 'Sidorov', 'sidorov_p@mail.ru', 'iukh232', '1999-10-07', '2015-02-23', '+380972385582'),
('tukrod', 'Andriy', 'Petrenko', 'petrenko@i.ua', '8923pav', '1988-04-24', '2014-07-11', '+380679823345'),
('podko22', 'Vlad', 'Karpenko', 'karp_vl@ukr.net', 'karyy11', '1991-09-02', '2015-12-27', '+380669003427'),
('Igoryok', 'Igor', 'Dryuk', 'dryuk@i.ua', '892387', '1998-01-04', '2015-12-21', '+380977231190');

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
('It takes ten times as many muscles to frown as it does to smile.');

CREATE TABLE Metadata
(
id INT NOT NULL AUTO_INCREMENT,
user_id INT,
cookie_id INT NOT NULL,
time_added DATE NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (user_id) 
REFERENCES Users (id)
ON UPDATE CASCADE,
FOREIGN KEY (cookie_id)
REFERENCES Cookies (id)
ON UPDATE CASCADE,
);

INSERT INTO Metadata (user_id, cookie_id, time_added) VALUES 
(1, 1, '2014-10-02'),
(3, 2, '2015-01-12'),
(1, 3, '2015-01-14'),
(1, 4, '2015-02-09'),
(2, 5, '2015-02-16'),
(3, 6, '2015-02-25'),
(2, 7, '2015-03-04'),
(4, 8, '2015-03-10'),
(5, 9, '2015-03-29'),
(6, 10,'2015-04-13');