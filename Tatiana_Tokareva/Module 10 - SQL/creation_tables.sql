CREATE TABLE users 
(
id integer(3) AUTO_INCREMENT,
lastname varchar(35),
firstname varchar(25),
email varchar(25) check(email like('%@%.%')) ,
password varchar(60 ) check(length(password)>5),
PRIMARY KEY(id),
);

CREATE TABLE cookies
(
id integer(3) AUTO_INCREMENT,
cookie varchar(100),
PRIMARY KEY(id),
);


CREATE TABLE metadata
(
id integer AUTO_INCREMENT,
cookie_id int not null,
user_id int not null,
time_added timestamp not null,
FOREIGN KEY (cookie_id) REFERENCES cookies(id),
FOREIGN KEY (user_id) REFERENCES users(id),
PRIMARY KEY(id)
);

