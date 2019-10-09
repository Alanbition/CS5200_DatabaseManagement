insert into priviledge(`name`) values ('create');
insert into priviledge(`name`) values ('read');
insert into priviledge(`name`) values ('update');
insert into priviledge(`name`) values ('delete');

insert into `role`(`name`) values ('owner');
insert into `role`(`name`) values ('admin');
insert into `role`(`name`) values ('writer');
insert into `role`(`name`) values ('editor');
insert into `role`(`name`) values ('reviewer');

INSERT INTO person(id, first_name, last_name, username, `password`, email) 
	VALUES (12, 'Alice','Wonder','alice', 'alice','alice@wonder.com');
    
INSERT INTO developer_person_generalization(developer_key, id) VALUES ('4321rewq',12);


INSERT INTO person(id, first_name, last_name, username, `password`, email) 
	VALUES (23, 'Bob','Marley','bob', 'bob','bob@marley.com');
    
INSERT INTO developer_person_generalization(developer_key, id) VALUES ('5432trew',23);

INSERT INTO person(id, first_name, last_name, username, `password`, email) 
	VALUES (34, 'Charles','Garcia','charlie', 'charlie','chuch@garcia.com');
    
INSERT INTO developer_person_generalization(developer_key, id) VALUES ('6543ytre',34);


INSERT INTO person(id, first_name, last_name, username, `password`, email) 
	VALUES (45, 'Dan','Martin','dan', 'dan','dan@martin.com');
    
INSERT INTO user_person_generalization(user_agreement, id) VALUES (1,45);

INSERT INTO person(id, first_name, last_name, username, `password`, email) 
	VALUES (56, 'Ed','Karaz','ed', 'ed','ed@kar.com');
    
INSERT INTO user_person_generalization(user_agreement, id) VALUES (1,56);


INSERT INTO website(id, `name`, `description`, created, updated, visits)
	VALUES (123, 'Facebok', 'an online social media and social networking service', NOW(), NOW(), 1234234);
    

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