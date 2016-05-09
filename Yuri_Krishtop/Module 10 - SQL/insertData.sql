INSERT INTO Users (login, name, surname, email, password, dateOfBirth, registerDate, phone) VALUES 
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

INSERT INTO Cookies (name, filling, calories, proteins, fats, carbohydrates, containsSugar) VALUES 
('Biscotti', 'nuts', 380, 16.2, 11.8, 64.6, true),
('Brownies', 'chocolate', 410, 13.7, 16.3, 65.7, true),
('Chocolate Chip', 'raisins', 380, 5.5, 24.7, 64.1, true),
('Shortbread', NULL, 502, 6.1, 24.2, 64.8, true),
('Chocolate Cake', 'Chocolate Frosting', 367, 4.1, 16.4, 54.7, false),
('Angelfood Cake', NULL, 258, 5.9, 0.8, 58.0, false),
('Butter Pound Cake', NULL, 388, 5.5, 20.1, 49.0, false),
('Diet Cheesecake', NULL, 224, 5.7, 9.2, 30.3, true),
('Cheesecake with Fruit', 'fruit', 273, 6.2, 11.9, 32.9, true),
('Sponge Snack Cake', 'creme', 364, 3.1, 11.4, 63.7, true);


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