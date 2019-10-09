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