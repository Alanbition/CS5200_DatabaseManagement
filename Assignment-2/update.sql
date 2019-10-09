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