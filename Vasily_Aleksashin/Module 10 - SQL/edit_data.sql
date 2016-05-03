//Some changes to the database

//Users table
ALTER TABLE users ADD COLUMN
	(
		first_name VARCHAR(30),
		last_name VARCHAR(30),
		age INT
	);

ALTER TABLE users ADD CONSTRAINT AGE_CHECK CHECK (age BETWEEN 1 AND 99);

UPDATE users SET first_name='John', last_name='Smith', age=26 WHERE id=0;
UPDATE users SET first_name='Chris', last_name='Johnson', age=19 WHERE id=1;
UPDATE users SET first_name='Mark', last_name='Williams', age=40 WHERE id=2;
UPDATE users SET first_name='William', last_name='Jones', age=31 WHERE id=3;
UPDATE users SET first_name='George', last_name='Brown', age=21 WHERE id=4;
UPDATE users SET first_name='Peter', last_name='Devis', age=56 WHERE id=5;
UPDATE users SET first_name='Richard', last_name='Miller', age=39 WHERE id=6;
UPDATE users SET first_name='Jack', last_name='Wilson', age=27 WHERE id=7;
UPDATE users SET first_name='Tom', last_name='Moore', age=50 WHERE id=8;
UPDATE users SET first_name='Kevin', last_name='Taylor', age=45 WHERE id=9;

ALTER TABLE users ALTER COLUMN first_name SET NOT NULL;
ALTER TABLE users ALTER COLUMN last_name SET NOT NULL;
ALTER TABLE users ALTER COLUMN age SET NOT NULL;

//Cookies table
ALTER TABLE cookies ADD COLUMN IF NOT EXISTS lucky_number INT;
ALTER TABLE cookies ADD CONSTRAINT LUCKY_NOT_NEGATIVE CHECK (lucky_number > 0);
ALTER TABLE cookies ADD CONSTRAINT LUCKY_UNIQUE UNIQUE(lucky_number);

UPDATE cookies SET lucky_number=13 WHERE id=1;
UPDATE cookies SET lucky_number=7 WHERE id=2;
UPDATE cookies SET lucky_number=666 WHERE id=3;
UPDATE cookies SET lucky_number=69 WHERE id=4;
UPDATE cookies SET lucky_number=777 WHERE id=5;
UPDATE cookies SET lucky_number=21 WHERE id=6;
UPDATE cookies SET lucky_number=999 WHERE id=7;
UPDATE cookies SET lucky_number=12 WHERE id=8;
UPDATE cookies SET lucky_number=25 WHERE id=9;
UPDATE cookies SET lucky_number=100 WHERE id=10;

ALTER TABLE cookies ALTER COLUMN lucky_number SET NOT NULL;

//Metadata table
DELETE FROM metadata WHERE user_id=2;