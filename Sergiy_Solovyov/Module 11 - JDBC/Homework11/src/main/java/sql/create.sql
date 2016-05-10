


CREATE  TABLE `users` 
(
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL unique ,
  `email` VARCHAR(60) NOT NULL unique ,
  `password` VARCHAR(30) NOT NULL ,
  `age` INTEGER(2) NOT NULL check (age >= 1),
  PRIMARY KEY (`id`) ,
  CHECK (email like '%@%.%')
);




CREATE  TABLE `cookies`
(
  `id` INT NOT NULL AUTO_INCREMENT ,
  `cookie` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`) 
);


CREATE TABLE `metadata`
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  cookie_id INT NOT NULL,
  user_id INT NOT NULL,
  time_added TIMESTAMP NOT NULL,
  FOREIGN KEY(cookie_id) REFERENCES cookies(id),
  FOREIGN KEY(user_id) REFERENCES users(id)
);
