
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