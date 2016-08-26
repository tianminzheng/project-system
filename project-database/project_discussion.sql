
DROP TABLE IF EXISTS `discussion_discussion`;
CREATE TABLE `discussion_discussion` (
  `discussion_id` varchar(100) NOT NULL,
  `project_id` varchar(100) NOT NULL,
  `owner_email_address` varchar(100) NOT NULL,
  `owner_identity` varchar(50) NOT NULL,
  `owner_name` varchar(200) NOT NULL,
  `closed` tinyint(1) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `scheduled_date` date DEFAULT NULL,
  PRIMARY KEY (`discussion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;