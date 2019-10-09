CREATE DATABASE IF NOT EXISTS localDB; 
USE localDB;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS person;
SET FOREIGN_KEY_CHECKS=1;

create table person(
id int NOT NULL AUTO_INCREMENT,
first_name varchar(255) not NULL,
last_name varchar(255) not NULL,
username varchar(255) not NULL,
`password` varchar(255) not NULL,
email varchar(255) not NULL,
dob DATE default NULL,
PRIMARY KEY (id)
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS user_person_generalization;
SET FOREIGN_KEY_CHECKS=1;

create table user_person_generalization(
id int NOT NULL AUTO_INCREMENT,
user_agreement bit(1) default 0,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES 
	person (id)
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS developer_person_generalization;
SET FOREIGN_KEY_CHECKS=1;

create table developer_person_generalization(
id int NOT NULL AUTO_INCREMENT,
developer_key varchar(255) not NULL,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES 
	person (id)
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS website;
SET FOREIGN_KEY_CHECKS=1;

create table website(
id int not NULL AUTO_INCREMENT,
`name` varchar(255) not NULL,
`description` varchar(255) not NULL,
created DATE not NULL,
updated DATE not NULL,
visits int not NULL,
/*developer_person_generalization int not NULL,*/
PRIMARY KEY (id)
/*FOREIGN KEY (developer_person_generalization) REFERENCES
	developer_person_generalization (id)*/
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `page`;
SET FOREIGN_KEY_CHECKS=1;

create table `page`(
id int not NULL AUTO_INCREMENT,
title varchar(255) not NULL,
`description` varchar(255) not NULL,
created DATE not NULL,
updated DATE not NULL,
views int not NULL,
website int not NULL,
PRIMARY KEY (id),
FOREIGN KEY (website) references website (id)
 ON DELETE CASCADE
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS widget_dtype;
SET FOREIGN_KEY_CHECKS=1;

create table widget_dtype(
`name` varchar(255) NOT NULL,
PRIMARY KEY (`name`)
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS widget;
SET FOREIGN_KEY_CHECKS=1;

create table widget(
widget_dtype varchar(31) not NULL,
id int not null AUTO_INCREMENT,
`name` varchar(255) default NULL,
width int default NULL,
height int default NULL,
css_class varchar(255) default NULL,
css_style varchar(255) default NULL,
`text` varchar(255) default NULL,
`order` int default NULL,
`page` int default NULL,
url varchar(255) default NULL,
shareble bit(1) default NULL,
expandable bit(1) default NULL,
src varchar(255) default NULL,
size int default 2,
html varchar(255) default NULL,
PRIMARY KEY (id),
FOREIGN KEY (widget_dtype) REFERENCES widget_dtype (`name`),
FOREIGN KEY (`page`) REFERENCES `page` (id)
	On DELETE CASCADE
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS priviledge;
SET FOREIGN_KEY_CHECKS=1;

create table priviledge(
`name` varchar(255) NOT NULL,
PRIMARY KEY (`name`)
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `role`;
SET FOREIGN_KEY_CHECKS=1;

create table `role`(
`name` varchar(255) NOT NULL,
PRIMARY KEY (`name`)
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS website_priviledge;
SET FOREIGN_KEY_CHECKS=1;

create table website_priviledge(
id int AUTO_INCREMENT,
priviledge varchar(255) default NULL,
website int not NULL,
developer_person_generalization int not NULL,
PRIMARY KEY (id),
FOREIGN KEY (priviledge) REFERENCES priviledge (`name`),
FOREIGN KEY (developer_person_generalization) REFERENCES developer_person_generalization (id),
FOREIGN KEY (website) REFERENCES website (id)
	On DELETE CASCADE
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS page_priviledge;
SET FOREIGN_KEY_CHECKS=1;

create table page_priviledge(
id int AUTO_INCREMENT,
priviledge varchar(255) default NULL,
`page` int not NULL,
developer_person_generalization int not NULL,
PRIMARY KEY (id),
FOREIGN KEY (priviledge) REFERENCES priviledge (`name`),
FOREIGN KEY (developer_person_generalization) REFERENCES developer_person_generalization (id),
FOREIGN KEY (`page`) REFERENCES `page` (id)
	On DELETE CASCADE
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS website_role;
SET FOREIGN_KEY_CHECKS=1;

create table website_role(
id int AUTO_INCREMENT,
`role` varchar(255) default NULL,
website int not NULL,
developer_person_generalization int not NULL,
PRIMARY KEY (id),
FOREIGN KEY (`role`) REFERENCES `role` (`name`),
FOREIGN KEY (developer_person_generalization) REFERENCES developer_person_generalization (id),
FOREIGN KEY (website) REFERENCES website (id)
	On DELETE CASCADE
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS page_role;
SET FOREIGN_KEY_CHECKS=1;

create table page_role(
id int AUTO_INCREMENT,
`role` varchar(255) default NULL,
`page` int not NULL,
developer_person_generalization int not NULL,
PRIMARY KEY (id),
FOREIGN KEY (`role`) REFERENCES `role` (`name`),
FOREIGN KEY (developer_person_generalization) REFERENCES developer_person_generalization (id),
FOREIGN KEY (`page`) REFERENCES `page` (id)
	On DELETE CASCADE
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS phone;
SET FOREIGN_KEY_CHECKS=1;

create table phone(
id int AUTO_INCREMENT,
phone varchar(255) not NULL,
`primary` bit(1) not NULL,
person int not NULL,
PRIMARY KEY (id),
FOREIGN KEY (person) REFERENCES person(id)
	On DELETE CASCADE
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS address;
SET FOREIGN_KEY_CHECKS=1;

create table address(
id int AUTO_INCREMENT,
street1 varchar(255) default NULL,
street2 varchar(255) default NULL,
city varchar(255) default NULL,
state varchar(255) default NULL,
zip varchar(255) default NULL,
`primary` bit(1) not NULL,
person int not NULL,
PRIMARY KEY (id),
FOREIGN KEY (person) REFERENCES person(id)
	On DELETE CASCADE
);