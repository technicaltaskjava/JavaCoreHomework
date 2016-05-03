/* change email for user5 */
UPDATE users 
SET email='user5@company2.com' where username='user5';

/* select user from another company */
SELECT username 
FROM users 
WHERE email NOT IN 
(SELECT email 
FROM users 
WHERE email LIKE '%@company.com');

/* select min, max and average cost of cookie from menu*/
SELECT min(price) AS minprice, 
max(price) AS maxprice, 
avg(price) AS avgprice 
FROM cookies 
WHERE menu=true;

/* cookies popularity table*/
SELECT c.cookie, sum(m.amount) as rate 
FROM cookies AS c 
LEFT JOIN metadata AS m ON c.id=m.cookie_id 
GROUP BY c.cookie;

/* sorted result without nulls*/
SELECT c.cookie, sum(m.amount) as rate 
FROM cookies AS c 
INNER JOIN metadata AS m ON c.id=m.cookie_id 
GROUP BY c.cookie 
ORDER BY rate DESC;

/* select user who order max amount of cookies */
SELECT username 
FROM users 
WHERE id IN
(SELECT user_id
FROM metadata
WHERE amount IN 
(SELECT max(amount) 
FROM metadata));