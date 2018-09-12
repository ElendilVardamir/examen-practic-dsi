DROP TABLE if EXISTS user_lab;
CREATE TABLE user_lab
(
  username VARCHAR (55) PRIMARY KEY,
  name VARCHAR (55),
  second_name VARCHAR (55),
  email VARCHAR (100),
  token VARCHAR (80)
);