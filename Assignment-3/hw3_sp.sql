
CREATE DATABASE IF NOT EXISTS localDB_hw3; 
USE localDB_hw3;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `Widget`;
SET FOREIGN_KEY_CHECKS=1;

create table `Widget`(
`id` int AUTO_INCREMENT,
`name` varchar(255) default NULL,
`width` int default NULL,
`height` int default NULL,
`cssClass` varchar(255) default NULL,
`cssStype` varchar(255) default NULL,
`text`DATE default NULL,
`order` int default NULL,
PRIMARY KEY (`id`)
);


SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `User`;
SET FOREIGN_KEY_CHECKS=1;

create table `User`(
`id` int NOT NULL AUTO_INCREMENT,
`approvedUser` bit(1) default 0,
`userAggreement` bit(1) default 0,
PRIMARY KEY (`id`)
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `Answer`;
SET FOREIGN_KEY_CHECKS=1;

create table `Answer`(
`id` int AUTO_INCREMENT,
`text` varchar(255) default NULL,
`postedBy` int not NULL,
`correctAnswer` bit(1) default 0,
`upVotes` int default NULL,
`downVotes` int default NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`id`) REFERENCES 
	`Widget` (`id`),
FOREIGN KEY (`postedBy`) REFERENCES 
	`User` (`id`)
 ON DELETE CASCADE
);



SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `Module`;
SET FOREIGN_KEY_CHECKS=1;

create table `Module`(
`name` varchar(255) NOT NULL,
PRIMARY KEY (`name`)
);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `Question`;
SET FOREIGN_KEY_CHECKS=1;

create table `Question`(
`id` int AUTO_INCREMENT,
`text` varchar(255) default NULL,
`askedBy` int not NULL,
`postedOn` Date default NULL,
`length` int default NULL,
`views` int default NULL,
`endorsedByInstructor` bit(1) default 0,
`module` varchar(255) not NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`module`) REFERENCES 
	`Module` (`name`),
FOREIGN KEY (`id`) REFERENCES 
	`Widget` (`id`),
FOREIGN KEY (`askedBy`) REFERENCES 
	`User` (`id`)
 ON DELETE CASCADE
);


SET FOREIGN_KEY_CHECKS=0;
DROP procedure IF EXISTS getUnansweredQuestions;
SET FOREIGN_KEY_CHECKS=1;

DELIMITER // 
CREATE PROCEDURE getUnansweredQuestions ()  
	BEGIN
	    SELECT q.`text`, max(a.`id`) FROM Question q, Answer a, `User` u
					WHERE q.`askedBy` = a.`poastedBy` and a.`correctAnswer` = 0 
                    Group by q.`module`;
	END //
