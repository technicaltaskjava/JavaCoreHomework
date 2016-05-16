CREATE TABLE USERS (
  id INT  AUTO_INCREMENT,
  password VARCHAR(20) CHECK (LENGTH(password) > 4),
  email VARCHAR(30) CHECK(email LIKE ('%@%.%')),
  PRIMARY KEY (id)
);

INSERT INTO USERS (PASSWORD , EMAIL ) VALUES ('16007','den@on.com') ,
  ('60099','ben@on.com',), ('64367','jo@on.com'), ('25467','bob@on.com'),
  ('18707','jain@on.com') , ('67799','kris@on.com'), ('78657','jorji@on.com'),
  ('67567','ketrin@on.com'), ('18037','kent@on.com') , ('60879','janin@on.com');