//Proper removal of data from related tables
DELETE FROM metadata WHERE user_id=3;
DELETE FROM users WHERE id=3;