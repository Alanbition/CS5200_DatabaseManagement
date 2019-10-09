
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

insert into priviledge(`name`) values ('create');
insert into priviledge(`name`) values ('read');
insert into priviledge(`name`) values ('update');
insert into priviledge(`name`) values ('delete');

insert into `role`(`name`) values ('owner');
insert into `role`(`name`) values ('admin');
insert into `role`(`name`) values ('writer');
insert into `role`(`name`) values ('editor');
insert into `role`(`name`) values ('reviewer');

SET FOREIGN_KEY_CHECKS=0;
DROP TRIGGER IF EXISTS after_website_role_updated;
SET FOREIGN_KEY_CHECKS=1;

DELIMITER $$
create trigger after_website_role_updated
	AFTER UPDATE on website_role
	FOR EACH ROW
	BEGIN
        DELETE from website_priviledge WHERE (website, developer_person_generalization) = (NEW.website, NEW.developer_person_generalization);
		IF NEW.`role` = 'owner' THEN  
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'create');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'read');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'update');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'delete');
	    END IF;  
		IF NEW.`role` = 'admin' THEN  
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'create');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'read');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'update');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'delete');
	    END IF;         
		IF NEW.`role` = 'writer' THEN 
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'read');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'create');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'delete');
	    END IF; 
		IF NEW.`role` = 'editor' THEN  
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'read');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'update');
	    END IF; 
		IF NEW.`role` = 'reviewer' THEN  
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'read');
	    END IF; 			
	END$$

DELIMITER ;

SET FOREIGN_KEY_CHECKS=0;
DROP TRIGGER IF EXISTS after_website_role_deleted;
SET FOREIGN_KEY_CHECKS=1;

DELIMITER $$
create trigger after_website_role_deleted
	AFTER DELETE on website_role
	FOR EACH ROW
	BEGIN
        DELETE from website_priviledge WHERE (website, developer_person_generalization) = (OLD.website, OLD.developer_person_generalization);
	END$$
DELIMITER ;

SET FOREIGN_KEY_CHECKS=0;
DROP TRIGGER IF EXISTS after_website_role_insert;
SET FOREIGN_KEY_CHECKS=1;

DELIMITER $$
create trigger after_website_role_insert
	AFTER INSERT on website_role
	FOR EACH ROW
	BEGIN
		IF NEW.`role` = 'owner' THEN  
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'create');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'read');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'update');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'delete');
	    END IF;  
		IF NEW.`role` = 'admin' THEN  
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'create');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'read');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'update');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'delete');
	    END IF;         
		IF NEW.`role` = 'writer' THEN 
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'read');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'create');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'delete');
	    END IF; 
		IF NEW.`role` = 'editor' THEN  
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'read');
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'update');
	    END IF; 
		IF NEW.`role` = 'reviewer' THEN  
			insert into website_priviledge(website,developer_person_generalization,priviledge) values (NEW.website, NEW.developer_person_generalization,'read');
	    END IF; 			
	END$$
DELIMITER ;

SET FOREIGN_KEY_CHECKS=0;
DROP TRIGGER IF EXISTS after_page_role_updated;
SET FOREIGN_KEY_CHECKS=1;

DELIMITER $$
create trigger after_page_role_updated
	AFTER UPDATE on page_role
	FOR EACH ROW
	BEGIN
        DELETE from page_priviledge WHERE (`page`, developer_person_generalization) = (NEW.`page`, NEW.developer_person_generalization);
		IF NEW.`role` = 'owner' THEN  
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'create');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'read');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'update');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'delete');
	    END IF;  
		IF NEW.`role` = 'admin' THEN  
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'create');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'read');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'update');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'delete');
	    END IF;         
		IF NEW.`role` = 'writer' THEN 
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'read');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'create');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'delete');
	    END IF; 
		IF NEW.`role` = 'editor' THEN  
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'read');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'update');
	    END IF; 
		IF NEW.`role` = 'reviewer' THEN  
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'read');
	    END IF; 			
	END$$

DELIMITER ;

SET FOREIGN_KEY_CHECKS=0;
DROP TRIGGER IF EXISTS after_page_role_deleted;
SET FOREIGN_KEY_CHECKS=1;

DELIMITER $$
create trigger after_page_role_deleted
	AFTER DELETE on page_role
	FOR EACH ROW
	BEGIN
        DELETE from page_priviledge WHERE (`page`, developer_person_generalization) = (OLD.`page`, OLD.developer_person_generalization);
	END$$
DELIMITER ;

SET FOREIGN_KEY_CHECKS=0;
DROP TRIGGER IF EXISTS after_page_role_inserted;
SET FOREIGN_KEY_CHECKS=1;

DELIMITER $$
create trigger after_page_role_inserted
	AFTER INSERT on page_role
	FOR EACH ROW
	BEGIN
		IF NEW.`role` = 'owner' THEN  
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'create');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'read');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'update');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'delete');
	    END IF;  
		IF NEW.`role` = 'admin' THEN  
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'create');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'read');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'update');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'delete');
	    END IF;         
		IF NEW.`role`= 'writer' THEN 
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'read');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'create');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'delete');
	    END IF; 
		IF NEW.`role` = 'editor' THEN  
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'read');
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'update');
	    END IF; 
		IF NEW.`role` = 'reviewer' THEN  
			insert into page_priviledge(`page`,developer_person_generalization,priviledge) values (NEW.`page`, NEW.developer_person_generalization,'read');
	    END IF; 			
	END$$
DELIMITER ;

INSERT INTO person(id, first_name, last_name, username, `password`, email) 
	VALUES (12, 'Alice','Wonder','alice', 'alice','alice@wonder.com');
    
INSERT INTO developer_person_generalization(developer_key, id) VALUES ('4321rewq',12);

/*SELECT * FROM developer_person_generalization;*/

INSERT INTO person(id, first_name, last_name, username, `password`, email) 
	VALUES (23, 'Bob','Marley','bob', 'bob','bob@marley.com');
    
INSERT INTO developer_person_generalization(developer_key, id) VALUES ('5432trew',23);

INSERT INTO person(id, first_name, last_name, username, `password`, email) 
	VALUES (34, 'Charles','Garcia','charlie', 'charlie','chuch@garcia.com');
    
INSERT INTO developer_person_generalization(developer_key, id) VALUES ('6543ytre',34);

/*SELECT * FROM developer_person_generalization;*/

INSERT INTO person(id, first_name, last_name, username, `password`, email) 
	VALUES (45, 'Dan','Martin','dan', 'dan','dan@martin.com');
    
INSERT INTO user_person_generalization(user_agreement, id) VALUES (1,45);

INSERT INTO person(id, first_name, last_name, username, `password`, email) 
	VALUES (56, 'Ed','Karaz','ed', 'ed','ed@kar.com');
    
INSERT INTO user_person_generalization(user_agreement, id) VALUES (1,56);

/*SELECT * FROM developer_person_generalization;*/

INSERT INTO website(id, `name`, `description`, created, updated, visits)
	VALUES (123, 'Facebok', 'an online social media and social networking service', NOW(), NOW(), 1234234);
    
/*SELECT * FROM website;*/


INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('owner', 123, 12);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('editor', 123, 23);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('admin', 123, 34);

INSERT INTO website(id, `name`, `description`, created, updated, visits)
	VALUES (234, 'Twitter', 'an online news and social networking service', NOW(), NOW(), 4321543);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('owner', 234, 23);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('editor', 234, 34);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('admin', 234, 12);

INSERT INTO website(id, `name`, `description`, created, updated, visits)
	VALUES (345, 'Wikipedia', 'a free online encyclopedia', NOW(), NOW(), 3456654);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('owner', 345, 34);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('editor', 345, 12);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('admin', 345, 23);

INSERT INTO website(id, `name`, `description`, created, updated, visits)
	VALUES (456, 'CNN', 'an American basic cable and satellite television news channel', NOW(), NOW(), 6543345);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('owner', 456, 12);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('editor', 456, 23);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('admin', 456, 34);

INSERT INTO website(id, `name`, `description`, created, updated, visits)
	VALUES (567, 'CNET', 'an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics', NOW(), NOW(), 5433455);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('owner', 567, 23);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('editor', 567, 34);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('admin', 567, 12);


INSERT INTO website(id, `name`, `description`, created, updated, visits)
	VALUES (678, 'Gizmodo', 'a design, technology, science and science fiction website that also writes articles on politics', NOW(), NOW(), 4322345);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('owner', 678, 34);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('editor', 678, 12);

INSERT INTO website_role(`role`, website, developer_person_generalization) VALUE ('admin', 678, 23);

INSERT INTO `page`(id, title, `description`, created, updated, views, website)
	VALUES (123, 'Home', 'Landing page', '2019-09-03', '2019-10-09', 123434, 567);

INSERT INTO page_role(`role`, `page`, developer_person_generalization) VALUE ('editor', 123, 12);

INSERT INTO page_role(`role`,`page`, developer_person_generalization) VALUE ('reviewer', 123, 23);

INSERT INTO page_role(`role`,`page`, developer_person_generalization) VALUE ('writer', 123, 34);


INSERT INTO `page`(id, title, `description`, created, updated, views, website)
	VALUES (234, 'About', 'Website description', '2019-09-03', '2019-10-09', 234545, 678);

INSERT INTO page_role(`role`, `page`, developer_person_generalization) VALUE ('editor', 234, 23);

INSERT INTO page_role(`role`,`page`, developer_person_generalization) VALUE ('reviewer', 234, 34);

INSERT INTO page_role(`role`,`page`, developer_person_generalization) VALUE ('writer', 234, 12);

INSERT INTO `page`(id, title, `description`, created, updated, views, website)
	VALUES (345, 'Contact', 'Addresses, phones, and contact info', '2019-09-03', '2019-10-09', 345656, 345);

INSERT INTO page_role(`role`, `page`, developer_person_generalization) VALUE ('editor', 345, 34);

INSERT INTO page_role(`role`,`page`, developer_person_generalization) VALUE ('reviewer', 345, 12);

INSERT INTO page_role(`role`,`page`, developer_person_generalization) VALUE ('writer', 345, 23);

INSERT INTO `page`(id, title, `description`, created, updated, views, website)
	VALUES (456, 'Preferences', 'Where users can configure their preferences', '2019-09-03', '2019-10-09', 456776, 456);

INSERT INTO page_role(`role`, `page`, developer_person_generalization) VALUE ('editor', 456, 12);

INSERT INTO page_role(`role`,`page`, developer_person_generalization) VALUE ('reviewer', 456, 23);

INSERT INTO page_role(`role`,`page`, developer_person_generalization) VALUE ('writer', 456, 34);

INSERT INTO `page`(id, title, `description`, created, updated, views, website)
	VALUES (567, 'Profile', 'Users can configure their personal information', '2019-09-03', '2019-10-09', 567878, 567);

INSERT INTO page_role(`role`, `page`, developer_person_generalization) VALUE ('editor', 567, 23);

INSERT INTO page_role(`role`,`page`, developer_person_generalization) VALUE ('reviewer', 567, 34);

INSERT INTO page_role(`role`,`page`, developer_person_generalization) VALUE ('writer', 567, 12);

insert into widget_dtype(`name`) values ('youtube');
insert into widget_dtype(`name`) values ('image');
insert into widget_dtype(`name`) values ('html');
insert into widget_dtype(`name`) values ('heading');

INSERT INTO widget(widget_dtype, id, `name`, `text`, `order`,`page`)
	VALUES ('heading', 123, 'head123', 'Welcome', 0, 123);

INSERT INTO widget(widget_dtype, id, `name`, `text`, `order`,`page`)
	VALUES ('html', 234, 'post234', '<p>Lorem</p>', 0, 234);

INSERT INTO widget(widget_dtype, id, `name`, `text`, `order`,`page`)
	VALUES ('heading', 345, 'head345', 'Hi', 1, 345);
    
INSERT INTO widget(widget_dtype, id, `name`, `text`, `order`,`page`)
	VALUES ('html', 456, 'intro456', '<h1>Hi</h1>', 2, 345);

INSERT INTO widget(widget_dtype, id, `name`, `order`,`page`, width, height, url)
	VALUES ('image', 567, 'image345',  3, 345, 50, 100, '/img/567.png');


INSERT INTO widget(widget_dtype, id, `name`, `order`,`page`, width, height, url)
	VALUES ('youtube', 678, 'video456', 0, 456, 400, 300, 'https://youtu.be/h67VX51QXiQ');    

INSERT INTO phone(phone,`primary`,person)
	VALUES ('123-234-3456', 1, 12);

INSERT INTO phone(phone,`primary`,person)
	VALUES ('234-345-4566', 0, 12);

INSERT INTO address(street1,city,zip,`primary`,person)
	VALUES ('123 Adam St.', 'Alton', '01234', 1, 12);
    
INSERT INTO address(street1,city,zip,`primary`,person)
	VALUES ('234 Birch St.', 'Boston', '02345', 0, 12);
    
INSERT INTO phone(phone,`primary`,person)
	VALUES ('345-456-5677', 1, 23);

INSERT INTO address(street1,city,zip,`primary`,person)
	VALUES ('345 Charles St.', 'Chelms', '03455', 1, 23);
    
INSERT INTO address(street1,city,zip,`primary`,person)
	VALUES ('456 Down St.', 'Dalton', '04566', 0, 23);
    
INSERT INTO address(street1,city,zip,`primary`,person)
	VALUES ('543 East St.', 'Everett', '01112', 0, 23);
    
INSERT INTO phone(phone,`primary`,person)
	VALUES ('321-432-5435', 1, 34);

INSERT INTO phone(phone,`primary`,person)
	VALUES ('432-432-5433', 0, 34);
    
INSERT INTO phone(phone,`primary`,person)
	VALUES ('543-543-6544', 0, 34);

INSERT INTO address(street1,city,zip,`primary`,person)
	VALUES ('654 Frank St.', 'Foulton', '04322', 1, 34);


SET FOREIGN_KEY_CHECKS=0;
DROP VIEW IF EXISTS deleveloper_roles_and_privileges;
SET FOREIGN_KEY_CHECKS=1;

CREATE VIEW deleveloper_roles_and_privileges AS
	SELECT p.first_name, p.last_name, p.username, p.email,web.`name`, web.visits, web.updated as website_updated , wr.`role` as website_role , wp.priviledge as website_priviledge, 
			pa.title, pa.views, pa.updated as page_updated, pr.`role` as page_role, pp.priviledge as page_priviledge
    FROM developer_person_generalization d 
		 LEFT JOIN person p ON d.id = p.id
		 LEFT JOIN website_role wr on d.id = wr.developer_person_generalization
         LEFT JOIN website web on wr.website = web.id
         LEFT JOIN website_priviledge wp ON d.id = wp.developer_person_generalization and wp.website = web.id
         LEFT JOIN `page` pa on pa.website = web.id
         lEFT JOIN page_role pr on d.id = pr.developer_person_generalization and pr.`page` = pa.id
         LEFT JOIN page_priviledge pp ON d.id = pp.developer_person_generalization and pp.`page` = pa.id;
         

SELECT * FROM deleveloper_roles_and_privileges;


/*Q1*/
/*a*/
SELECT p.username FROM developer_person_generalization d, person p WHERE d.id = p.id;

/*b*/
SELECT p.username FROM developer_person_generalization d, person p WHERE p.id = 34 and d.id = 34;

/*c*/
SELECT p.username FROM developer_person_generalization d, website_role wr, person p 
	WHERE wr.website = (SELECT website.id FROM website WHERE website.`name` = 'Twitter') 
    and  wr.`role` not like 'owner' and d.id = wr.developer_person_generalization and d.id = p.id;

/*d*/
SELECT p.username FROM `page` pa, developer_person_generalization d, page_role pr, person p 
	WHERE pa.views < 300000 and pr.`page` = pa.id and pr.`role` = 'reviewer' and pr.developer_person_generalization = d.id  and d.id = p.id;
    
/*e*/
SELECT person.username FROM person WHERE person.id IN (SELECT page_role.developer_person_generalization FROM page_role
WHERE page_role.`role` = 'writer' and page_role.`page` = (SELECT widget.`page` FROM widget WHERE widget.widget_dtype = 'heading'
and widget.`page` = (SELECT `page`.id FROM `page` WHERE `page`.title = 'Home')));
    
/*Q2*/
/*a*/
SELECT website.`name` FROM website WHERE website.visits = (SELECT min(website.visits) FROM website) ;

/*b*/
SELECT website.`name` FROM website WHERE website.id = 678;

/*c*/
SELECT website.`name` FROM website WHERE website.id IN (SELECT `page`.website FROM `page` WHERE `page`.id IN 
(SELECT page_role.`page` FROM page_role WHERE page_role.`role` = 'reviewer' and
page_role.developer_person_generalization = (SELECT person.id FROM person WHERE person.username = 'bob') and 
page_role.`page` = (SELECT widget.`page` FROM widget WHERE widget.`widget_dtype` = 'youtube')));

/*d*/
SELECT website.`name` FROM website WHERE website.id IN (SELECT website_role.website FROM website_role 
WHERE website_role.`role` = 'owner' and website_role.developer_person_generalization = 
(SELECT person.id FROM person WHERE person.username = 'alice'));

/*e*/
SELECT website.`name` FROM website WHERE website.id IN (SELECT website_role.website FROM website_role WHERE 
website_role.`role` = 'admin' and  website_role.developer_person_generalization = 
(SELECT person.id FROM person WHERE person.username = 'charlie') and website_role.website = (SELECT website.id FROM website WHERE
website.visits > 6000000));

/*Q3*/
/*a*/
SELECT `page`.title FROM `page` WHERE `page`.views = (SELECT max(`page`.views) FROM `page`);

/*b*/
SELECT `page`.title FROM `page` WHERE `page`.id = 234;

/*c*/
SELECT p.title FROM `page` p, page_role pr WHERE pr.developer_person_generalization IN 
	(SELECT d.id FROM person p, developer_person_generalization d WHERE
    p.username = 'alice' and d.id = p.id) and pr.`role` = 'editor' and p.id = pr.`page`;

/*d*/
SELECT SUM(p.views) FROM `page` p WHERE p.website = (SELECT w.id FROM website w WHERE w.`name` = 'CNET');

/*e*/
SELECT AVG(p.views) FROM `page` p WHERE p.website = (SELECT w.id FROM website w WHERE w.`name` = 'wikipedia');

/*Q4*/
/*a*/
SELECT wi.`name` FROM widget wi WHERE wi.`page` IN (SELECT `page`.id FROM `page` WHERE `page`.title = 'Home' and `page`.website = 
(SELECT website.id FROM website WHERE website.`name` = 'CNET'));

/*b*/
SELECT widget.`name` FROM widget WHERE widget.widget_dtype = 'youtube' and widget.`page` IN (SELECT `page`.id FROM `page` WHERE `page`.website = 
(SELECT website.id FROM website WHERE website.`name` = 'CNN'));

/*c*/
SELECT widget.`name` FROM widget WHERE widget.widget_dtype = 'image' and widget.`page` IN (SELECT page_role.`page` FROM page_role 
WHERE page_role.`role` = 'reviewer' and page_role.developer_person_generalization = (SELECT person.id FROM person WHERE
    person.username = 'alice'));

/*d*/
SELECT COUNT(widget.id) FROM widget WHERE widget.`page` IN (SELECT `page`.id FROM `page` WHERE `page`.website = 
(SELECT website.id FROM website WHERE website.`name` = 'Wikipedia'));


/*Q5*/
/*a*/
SELECT website.`name` FROM website WHERE website.id IN (SELECT website_priviledge.website FROM website_priviledge
WHERE website_priviledge.priviledge = 'delete' and website_priviledge.developer_person_generalization = 
(SELECT person.id FROM person WHERE person.username = 'bob'));

/*b*/
SELECT `page`.title FROM `page` WHERE `page`.id IN (SELECT page_priviledge.`page` FROM page_priviledge WHERE
page_priviledge.priviledge = 'create' and page_priviledge.developer_person_generalization = 
(SELECT person.id FROM person WHERE person.username = 'charlie'));


/*UPDATE*/
/*1*/
UPDATE phone
SET phone = '333-444-5555' WHERE `primary` = 1 and person = (SELECT person.id FROM person WHERE person.username = 'charlie');

/*2*/
SET SQL_SAFE_UPDATES = 0;
UPDATE widget
SET `order` = 3 WHERE `name` = 'head345';
UPDATE widget
SET `order` = `order` - 1 WHERE (`order` < 3 and `order` > 0 or `order` = 3) and `name` not like 'head345';
UPDATE widget
SET `order` = `order` + 1 WHERE `order` > 3 and `name` not like 'head345';
SET SQL_SAFE_UPDATES = 1;

/*3*/
UPDATE `page`
SET title = concat('CNET - ', title) WHERE website = (SELECT website.id FROM website WHERE website.`name` = 'CNET');

/*4*/
SET @pr1 := (SELECT page_role.`role` FROM page_role WHERE page_role.developer_person_generalization = 
(SELECT person.id FROM person WHERE person.username = 'charlie') and page_role.`page` = (SELECT `page`.id FROM `page`
WHERE `page`.title = 'Home' and `page`.website = (SELECT w.id FROM website w WHERE w.`name` = 'CNET')));

SET @pr2 := (SELECT page_role.`role` FROM page_role WHERE page_role.developer_person_generalization = 
(SELECT person.id FROM person WHERE person.username = 'bob') and page_role.`page` = (SELECT `page`.id FROM `page`
WHERE `page`.title = 'Home' and `page`.website = (SELECT w.id FROM website w WHERE w.`name` = 'CNET')));

UPDATE page_role
SET `role` = @pr1 WHERE developer_person_generalization = (SELECT person.id FROM person WHERE person.username = 'bob') and `page` = (SELECT `page`.id FROM `page`
WHERE `page`.title = 'Home' and `page`.website = (SELECT w.id FROM website w WHERE w.`name` = 'CNET'));

UPDATE page_role
SET `role` = @pr2 WHERE developer_person_generalization = (SELECT person.id FROM person WHERE person.username = 'charlie') and `page` = (SELECT `page`.id FROM `page`
WHERE `page`.title = 'Home' and `page`.website = (SELECT w.id FROM website w WHERE w.`name` = 'CNET'));


/*DELETE*/
/*1*/
DELETE FROM address WHERE `primary` = 1 and person = (SELECT person.id FROM person WHERE person.username = 'charlie');

/*2*/
DELETE FROM widget ORDER BY `order` DESC LIMIT 1;

/*3*/
SET FOREIGN_KEY_CHECKS=0; 
DELETE FROM `page` WHERE website = (SELECT w.id FROM website w WHERE w.`name` = 'wikipedia') ORDER BY `page`.updated DESC limit 1;
SET FOREIGN_KEY_CHECKS=1;

/*4*/
SET SQL_SAFE_UPDATES = 0;
DELETE FROM website WHERE website.`name` = 'CNET';
SET SQL_SAFE_UPDATES = 1;

/*test*/
/*
SELECT * from address;

SELECT * from phone;

SELECT * FROM page_role;

SELECT * FROM page_priviledge;

SELECT * FROM widget;

SELECT * FROM website_priviledge;

SELECT * FROM `page`;

SELECT * FROM website;

SELECT * FROM website_role;
*/
