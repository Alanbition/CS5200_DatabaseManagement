
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