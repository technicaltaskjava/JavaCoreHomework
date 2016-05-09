UPDATE Users SET email='greedy@gmail.com' where first_name='greedy_first_name';

SELECT * FROM Users WHERE reg_date > CURRENT_DATE-4;

SELECT id,cookie FROM Cookies WHERE id BETWEEN 4 AND 8;

SELECT cookie FROM Cookies WHERE id = 8;

SELECT id,cookie FROM Cookies WHERE cookie LIKE '%you%';

SELECT user.first_name AS "First name",user.second_name AS "Second name",data.time_added AS "When bought",cookie.cookie AS "cookie",cookie.expiration_date AS "Expiration date"
	FROM Users user
	INNER JOIN Metadata data  ON data.user_id = user.id
	INNER JOIN 	Cookies cookie ON data.cookie_id = cookie.id
ORDER BY data.time_added DESC;

SELECT first_name AS "First name",second_name AS "Second name" FROM Users WHERE id NOT IN (SELECT user_id FROM Metadata); -- Users who do not buy anything

SELECT cookie AS "Cookie prediction" FROM Cookies WHERE id NOT IN (SELECT cookie_id FROM Metadata); -- Cookies that nobody buys

SELECT user.first_name AS "First name",user.second_name AS "Second name",COUNT(*) AS "Count" 
   FROM Metadata data
   INNER JOIN Users user ON data.user_id=user.id
GROUP BY user_id HAVING COUNT(*)>5
ORDER BY COUNT(*) DESC;

SELECT cookie.expiration_date AS "Expiration date",COUNT(*) AS "Count" 
	FROM Cookies cookie WHERE cookie.expiration_date BETWEEN CURRENT_DATE AND CURRENT_DATE+2
GROUP BY cookie.expiration_date
ORDER BY COUNT(*) DESC;

