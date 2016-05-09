create table Users
(username varchar(32) primary key check length(username) > 4,
email varchar(64) check email like '%@%.%',
password varchar(32) check length(password) > 5);

insert into Users values
('User1', 'asd@zx.c', 'asdzxc'),
('User2', 'das@cz.x', '123456'),
('User3', 'as@zxz.c', 'asgfxc'),
('User4', 'ssd@zx.z', 'asdzhf'),
('User5', 'asd@z.cc', 'asdzas'),
('Vasya', 'asd@zx.x', 'asdzgf'),
('Borya', 'asd@zx.c', 'asdzxc'),
('Petya', 'cxz@as.x', 'asdzgf'),
('Kolya', 'ahg@zx.c', 'asdzxc'),
('User10', 'adf@z.cx', 'asdzxc');

create table Cookies
(id int primary key auto_increment,
cookie varchar(32) not null,
price int not null default 0 check price >= 0,
message varchar(128) default 'no message');

insert into Cookies(cookie, price) values 
('Chocolate cookie', 10),
('Vanilla cookie', 8),
('Mega cookie', 50),
('Mini cookie', 1),
('Crunchy cookie', 10);
insert into Cookies(cookie) values
('Undercooked cookie'),
('Moldy cookie'),
('New recipe cookie'),
('Free cookie'),
('Yesterday''s cookie');

update Cookies set price = 20 where cookie like '%New%';
update Cookies set message = 'Lady Luck is smiling' where price > 0;
update Cookies set message = 'Free stuff isn''t always good' where price = 0;
update Cookies set message = 'But sometimes free stuff is good!' where cookie like '%Free%';
update cookies set message = 'Lady Luck is smiling big time' where price = (select max(price) from Cookies);

create table Metadata
(cookie_id int not null,
user_id varchar(32) not null,
time_added timestamp default current_timestamp,
foreign key(cookie_id) references Cookies(id),
foreign key(user_id) references Users(username));

insert into Metadata(cookie_id, user_id) values
(select id from Cookies where price = 20, select username from Users where email like '%.z'),
(3,  select username from Users where password = '123456'),
(select id from Cookies where message like '%is good%', select user_id from Metadata where cookie_id = 3),
(5, 'Vasya'),
(7, 'User10'),
(select id from Cookies where cookie like 'New%', select user_id from Metadata where cookie_id = 7),
(10, 'User1'),
(10, 'User1'),
(10, 'User1'),
(1, 'User1');