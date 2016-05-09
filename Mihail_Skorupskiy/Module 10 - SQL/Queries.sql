--all users that have cookies, and when did they get them
select c.cookie, m.user_id as user, m.time_added as time from Cookies c join Metadata m on c.id = m.cookie_id;

--Info on users without cookies
select distinct u.* from Users u join Metadata m on u.username not in (
	select distinct user_id from Metadata
);

--cookies with unoriginal messages
select distinct cookie, message from Cookies where message in (
	select distinct message from Cookies group by message having count(*) > 1
);

--the most expensive cookie owned by a user
select c.cookie, c.price, m.user_id as user from Metadata m join Cookies c on c.id = m.cookie_id where m.cookie_id = (
	select id from Cookies where price = (
		select max(price) from (
			select distinct price from Cookies join Metadata on id = cookie_id
		)
	)
);

--user who has the most free cookies
select username, email from Users where username = (
	select user_id from Metadata where cookie_id in (
		select id from Cookies where price = 0
	) group by user_id order by count(user_id) desc limit 1
);