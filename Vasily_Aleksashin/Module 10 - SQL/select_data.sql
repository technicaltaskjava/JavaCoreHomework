//Some queries to the database

//Users table
SELECT * FROM users;

SELECT * FROM users 
ORDER BY last_name;

SELECT * FROM users 
ORDER BY last_name DESC;

SELECT * FROM users 
ORDER BY age;

SELECT last_name, first_name 
FROM users 
WHERE age > 25 
ORDER BY first_name;

SELECT last_name, first_name, age 
FROM users 
WHERE age > 25 OR last_name LIKE 'J%' 
ORDER BY last_name;

SELECT last_name, first_name, age, email 
FROM users 
WHERE age > 25 AND last_name LIKE 'J%' 
ORDER BY first_name;

SELECT COUNT(*) rows_count FROM users;

//Cookies table
SELECT * FROM cookies;

SELECT * FROM cookies 
ORDER BY cookie;

SELECT * FROM cookies 
ORDER BY lucky_number;

SELECT * FROM cookies 
WHERE LENGTH(cookie) > 40 AND lucky_number >= 100;

//Metadata table
SELECT * FROM metadata;

SELECT * FROM metadata 
ORDER BY time_added;

SELECT * 
FROM metadata 
WHERE MONTH(time_added) = 4 
ORDER BY time_added;

SELECT * FROM metadata 
WHERE cookie_id + user_id = 9;

SELECT * 
FROM metadata 
WHERE cookie_id + user_id BETWEEN
	(
		SELECT lucky_number 
		FROM cookies 
		WHERE lucky_number = 7
	) + 2
	AND
	(
		SELECT lucky_number 
		FROM cookies 
		WHERE lucky_number = 13
	);

//Summary table
SELECT u.username, u.first_name, u.last_name, c.cookie, m.time_added
FROM metadata m
JOIN cookies c ON m.cookie_id=c.id
JOIN users u ON m.user_id=u.id
WHERE MONTH(m.time_added)=4
ORDER BY u.last_name, m.time_added;

SELECT m.user_id, m.time_added, c.cookie
FROM metadata m
INNER JOIN cookies c
ON m.cookie_id = c.id;

SELECT m.user_id, m.time_added, c.cookie
FROM cookies c
LEFT JOIN metadata m
ON c.id=m.cookie_id;

SELECT m.user_id, m.time_added, c.cookie
FROM cookies c
LEFT JOIN metadata m
ON c.id=m.cookie_id
WHERE m.cookie_id IS NULL;

SELECT m.user_id, m.time_added, c.cookie
FROM cookies c
RIGHT JOIN metadata m
ON c.id=m.cookie_id;