
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