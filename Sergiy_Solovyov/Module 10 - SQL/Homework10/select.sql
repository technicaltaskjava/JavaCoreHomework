SELECT * from COOKIES; 

SELECT * from METADATA; 

SELECT * from USERS; 

SELECT * FROM USERS where age between 36 and 99 order by age;

SELECT * FROM USERS where username like 'M%';

SELECT * FROM COOKIES  WHERE LENGTH(cookie) > 70;

SELECT * FROM METADATA ORDER BY TIME_ADDED;

SELECT users.age, users.username, cookies.cookie
FROM METADATA ,COOKIES, USERS 
WHERE metadata.cookie_id=cookies.id AND metadata.user_id=users.id
ORDER BY users.age;

select USERNAME, EMAIL  from USERS
where age between 40 and 80 or length(PASSWORD) > 4;

select USERNAME, EMAIL  from USERS where id > 5
union
select USERNAME, EMAIL  from USERS where age < 50;

select TIME_ADDED, COOKIE 
from metadata inner join cookies  
on  metadata .COOKIE_ID =cookies .ID;

select USERNAME , PASSWORD  from USERS where id in (2,6);

select max(age) as MAX_AGE from users;