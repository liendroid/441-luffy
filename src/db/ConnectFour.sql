CREATE TABLE users
(
	username VARCHAR(15) NOT NULL,
	password VARCHAR(15) NOT NULL,
	wins INTEGER,
	losses INTEGER,
	PRIMARY KEY (username)
);