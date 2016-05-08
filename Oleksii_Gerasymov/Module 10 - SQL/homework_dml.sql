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

/* select count of active cookie*/
SELECT count(*)
FROM cookies 
WHERE active=true;

/* cookies popularity table*/
SELECT c.cookie, count(m.cookie_id) as rate 
FROM cookies AS c 
LEFT JOIN metadata AS m ON c.id=m.cookie_id 
GROUP BY c.cookie;

/* sorted result without nulls*/
SELECT c.cookie, count(m.cookie_id) as rate 
FROM cookies AS c 
INNER JOIN metadata AS m ON c.id=m.cookie_id 
GROUP BY c.cookie 
ORDER BY rate DESC;