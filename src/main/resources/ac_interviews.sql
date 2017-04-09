DROP DATABASE IF EXISTS ac_interviews;
CREATE DATABASE ac_interviews;
USE ac_interviews;

CREATE TABLE users(
  user_id int not null auto_increment,
  username char(50) not null unique,
  password char(50) not null,
  email char(100) not null unique,
  primary key(user_id)
);

CREATE TABLE interviews(
  interview_id int not null auto_increment,
  user_id int not null,
  interview_date char(20),
  hour char(10),
  company char(50) not null,
  interviewer char(50),
  interview_type enum('NA', 'General', 'Technical', 'Proposal'),
  comments text,
  status enum('Prospect', 'Interview', 'Hired', 'Not Hired', 'Future'),

  primary key(interview_id),
  foreign key(user_id) references users(user_id)
);

INSERT INTO users (username, password, email) values ('admin','1234','admin@admin.com');
INSERT INTO users (username, password, email) values ('rodas','rodas','rodas@rodas.com');
INSERT INTO users (username, password, email) values ('zeca','1234','zeca@zeca.com');
INSERT INTO users (username, password, email) values ('joana','1234','joana@zeca.com');


INSERT INTO interviews (user_id, interview_date, hour, company, interviewer, interview_type, comments, status) values (3, '2017-04-11','14:00','Novabase','','General','This is a testing','Prospect');
INSERT INTO interviews (user_id, interview_date, hour, company, interviewer, interview_type, comments, status) values (4, '2017-05-11','12:00','Edge','','General','This is a testing','Prospect');
