ALTER TABLE Metadata 
MODIFY user_id INT NOT NULL;

ALTER TABLE cookies 
CHANGE containssugar containsugar BOOL NOT NULL;

UPDATE Users
SET name = 'Alexey'
WHERE name IN ('Alex', 'Al');

SELECT * 
FROM Users 
ORDER BY name;

SELECT * 
FROM Cookies 
ORDER BY calories 
DESC;

SELECT * 
FROM Cookies 
WHERE filling IS NOT NULL;

SELECT * 
FROM Users 
WHERE email 
LIKE '%@gmail.com';

SELECT id, name, calories, containsugar 
FROM cookies 
WHERE calories 
BETWEEN 200 AND 300;

SELECT * 
FROM Cookies 
WHERE fats < 10
UNION
SELECT * 
FROM Cookies 
WHERE containsugar = false;

SELECT DISTINCT name, email, phone 
FROM Users 
INNER JOIN Metadata ON user_id=Users.id;

SELECT Users.name, email, Cookies.name 
FROM Users 
INNER JOIN Metadata ON user_id=Users.id
INNER JOIN Cookies ON cookie_id=Cookies.id;

SELECT Cookies.id,  Cookies.name, calories, containsugar, user_id, time_added 
FROM Cookies 
LEFT OUTER JOIN Metadata ON cookie_id=Cookies.id;

SELECT name, surname, COUNT(Metadata.id) AS "count of added cookies" 
FROM Users 
RIGHT OUTER JOIN Metadata ON  user_id=Users.id 
GROUP BY name;

SELECT name, surname, COUNT(Metadata.id) AS count 
FROM Users LEFT OUTER JOIN Metadata ON  user_id=Users.id
WHERE dateOfBirth > '1990-01-01' 
GROUP BY name HAVING count > 0;

CREATE VIEW "Last cookies" AS SELECT * 
FROM 
	(SELECT Cookies.name, calories, Users.id, surname, email, time_added  
	FROM Users 
	INNER JOIN Metadata ON user_id=Users.id 
	INNER JOIN Cookies ON cookie_id=Cookies.id) 
WHERE time_added > '2015-02-01';

SELECT * FROM "Last cookies";

DROP VIEW "Last cookies";

DROP TABLE Cookies;
DROP TABLE Metadata;
DROP TABLE Users;