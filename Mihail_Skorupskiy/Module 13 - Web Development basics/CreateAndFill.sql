create table Users
(username varchar(64) primary key check length(username) > 4,
email varchar(64) check email like '%@%.%',
password varchar(21) check length(password) > 5);

insert into Users values
('User1', 'asd@zx.c', 'Aa1111'),
('User2', 'das@cz.x', 'Bb2222'),
('User3', 'as@zxz.c', 'Cc3333'),
('User4', 'ssd@zx.z', 'Dd4444'),
('User5', 'asd@z.cc', 'Ee5555'),
('Vasya', 'asd@zx.x', 'Vasya1'),
('Borya', 'asd@zx.c', 'Borya1'),
('Petya', 'cxz@as.x', 'Petya1'),
('Kolya', 'ahg@zx.c', 'Kolya1'),
('Guest', 'none@none.none', 'Guest1');

create table Cookies
(id int primary key auto_increment,
message varchar(128) default 'no message');

insert into Cookies(message) values 
('Today it''s up to you to create the peacefulness you long for.'),
('A friend asks only for your time not your money.'),
('If you refuse to accept anything but the best, you very often get it.'),
('Your high-minded principles spell success.'),
('Hard work pays off in the future, laziness pays off now.'),
('Change can hurt, but it leads a path to something better.'),
('A chance meeting opens new doors to success and friendship.'),
('You cannot love life until you live the life you love.'),
('Meeting adversity well is the source of your strength.'),
('Our deeds determine us, as much as we determine our deeds.');

create table Metadata
(cookie_id int not null,
user_id varchar(64) not null,
time_added timestamp default current_timestamp,
foreign key(cookie_id) references Cookies(id),
foreign key(user_id) references Users(username));