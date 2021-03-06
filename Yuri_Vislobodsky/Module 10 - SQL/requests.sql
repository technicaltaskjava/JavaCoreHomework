SELECT u.ID, u.USERNAME, g.NAME as GENDER_NAME, o.NAME as OCCUPATION_NAME 
FROM USERS u
LEFT JOIN GENDER g ON g.ID = u.GENDER_ID
LEFT JOIN OCCUPATION o ON o.ID = u.OCCUPATION_ID
WHERE u.GENDER_ID=1 AND u.OCCUPATION_ID=1;


SELECT u.USERNAME, c.COOKIE, m.TIME_ADDED 
FROM METADATA m
LEFT JOIN USERS u ON u.ID = m.USERS_ID
LEFT JOIN COOKIES c ON c.ID = m.COOKIES_ID
WHERE m.TIME_ADDED BETWEEN '2016-04-21' AND '2016-04-26'
ORDER BY u.USERNAME;


SELECT u.USERNAME, COUNT(*) as AMOUNT
FROM METADATA m
LEFT JOIN USERS u ON u.ID = m.USERS_ID
GROUP BY u.USERNAME;


SELECT c.COOKIE, COUNT(*) as AMOUNT
FROM METADATA m
LEFT JOIN COOKIES c ON c.ID = m.COOKIES_ID
GROUP BY c.COOKIE
HAVING AMOUNT>1;


SELECT * FROM COOKIES WHERE EXPIRED < CURRENT_TIMESTAMP;

SELECT * FROM USERS WHERE USERNAME LIKE 'S%';


SELECT *
FROM METADATA m
LEFT JOIN USERS u ON u.ID = m.USERS_ID
WHERE m.USERS_ID IN (2,4,6) OR LENGTH(EMAIL) < 18;


SELECT USERNAME from USERS WHERE OCCUPATION_ID=2
UNION
SELECT DISTINCT u.USERNAME FROM METADATA m
LEFT JOIN USERS u ON u.ID = m.USERS_ID;


SELECT c.* FROM COOKIES c
WHERE c.EXPIRED >= CURRENT_TIMESTAMP 
      AND NOT EXISTS(SELECT 1 FROM METADATA m WHERE m.COOKIES_ID=c.ID);


SELECT u.USERNAME FROM USERS u
WHERE u.ID IN (SELECT m.USERS_ID FROM METADATA m WHERE m.COOKIES_ID>4);


UPDATE METADATA SET ENABLED = FALSE WHERE USERS_ID = 2;


INSERT INTO METADATA (USERS_ID, COOKIES_ID, TIME_ADDED)
SELECT USERS_ID, 2, CURRENT_TIMESTAMP FROM METADATA WHERE ENABLED = TRUE;


DELETE FROM METADATA WHERE TIME_ADDED BETWEEN CURRENT_DATE AND DATEADD('DAY', 1, CURRENT_DATE); 


SELECT u.USERNAME, g.NAME AS GENDER, o.NAME AS OCCUPATION, 
       c.COOKIE, m.TIME_ADDED, m.ENABLED 
FROM METADATA m
LEFT JOIN USERS u ON u.ID = m.USERS_ID
LEFT JOIN GENDER g ON g.ID = u.GENDER_ID
LEFT JOIN OCCUPATION o ON o.ID = u.OCCUPATION_ID
LEFT JOIN COOKIES c ON c.ID = m.COOKIES_ID;
