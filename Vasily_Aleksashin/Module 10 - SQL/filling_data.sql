//Adding initial data into the database

//Users table
INSERT INTO users VALUES (0, 'Battledoom', 'Battledoom@mail.com', '123456');
INSERT INTO users VALUES (1, 'CastBound', 'CastBound@mail.com', 'password');
INSERT INTO users VALUES (2, 'LordOfMud', 'LordOfMud@mail.com', '12345678');
INSERT INTO users VALUES (3, 'Born2Pizza', 'Born2Pizza@mail.com', 'qwerty');
INSERT INTO users VALUES (4, 'DroolingOnU', 'DroolingOnU@mail.com', 'baseball');
INSERT INTO users VALUES (5, 'WarlockOPain', 'WarlockOPain@mail.com', 'dragon');
INSERT INTO users VALUES (6, 'Romeo', 'Romeo@mail.com', 'abc123');
INSERT INTO users VALUES (7, 'e4envy', 'e4envy@mail.com', '111111');
INSERT INTO users VALUES (8, 'Mistalee', 'Mistalee@mail.com', 'superman');
INSERT INTO users VALUES (9, 'Jaycee', 'Jaycee@mail.com', 'batman');

//Cookies table
INSERT INTO cookies SET cookie='Life is too short to hold grudges';
INSERT INTO cookies SET cookie='Make a wish, it might come true';
INSERT INTO cookies SET cookie='Look to your inner being for guidance';
INSERT INTO cookies SET cookie='A frivolous gift is a gift, nonetheless';
INSERT INTO cookies SET cookie='You will have long and healthy life';
INSERT INTO cookies SET cookie='You learn about another person in the process of teaching them something';
INSERT INTO cookies SET cookie='Dream your dream and your dream will dream of you';
INSERT INTO cookies SET cookie='Relish the transitions in your life - they will happen anyway';
INSERT INTO cookies SET cookie='The time is right to make new friends';
INSERT INTO cookies SET cookie='You are filled with a sense of urgency. Be patient or you may end up confused';

//Metadata table
INSERT INTO metadata SET cookie_id=10, user_id=9, time_added=TIMESTAMP '2016-04-27 10:56:25';
INSERT INTO metadata SET cookie_id=1, user_id=8, time_added=TIMESTAMP '2016-03-16 10:56:25';
INSERT INTO metadata SET cookie_id=2, user_id=6, time_added=TIMESTAMP '2016-04-25 10:56:25';
INSERT INTO metadata SET cookie_id=3, user_id=5, time_added=TIMESTAMP '2016-03-01 10:56:25';
INSERT INTO metadata SET cookie_id=4, user_id=7, time_added=TIMESTAMP '2016-04-28 10:56:25';
INSERT INTO metadata SET cookie_id=5, user_id=4, time_added=TIMESTAMP '2016-05-07 10:56:25';
INSERT INTO metadata SET cookie_id=6, user_id=3, time_added=TIMESTAMP '2016-04-11 10:56:25';
INSERT INTO metadata SET cookie_id=7, user_id=2, time_added=TIMESTAMP '2016-04-19 10:56:25';
INSERT INTO metadata SET cookie_id=8, user_id=1, time_added=TIMESTAMP '2016-05-07 10:56:25';
INSERT INTO metadata SET cookie_id=9, user_id=0, time_added=TIMESTAMP '2016-04-09 10:56:25';
INSERT INTO metadata SET cookie_id=10, user_id=0, time_added=TIMESTAMP '2016-04-27 10:56:25';
INSERT INTO metadata SET cookie_id=1, user_id=2, time_added=TIMESTAMP '2016-04-16 10:56:25';
INSERT INTO metadata SET cookie_id=2, user_id=1, time_added=TIMESTAMP '2016-03-25 10:56:25';
INSERT INTO metadata SET cookie_id=3, user_id=4, time_added=TIMESTAMP '2016-04-01 10:56:25';
INSERT INTO metadata SET cookie_id=4, user_id=6, time_added=TIMESTAMP '2016-05-28 10:56:25';