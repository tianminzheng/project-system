
-- ----------------------------
-- Table structure for common_stored_event
-- ----------------------------
DROP TABLE IF EXISTS `common_stored_event`;
CREATE TABLE `common_stored_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `event_body` text NOT NULL,
  `occurred_on` datetime NOT NULL,
  `type_name` varchar(200) NOT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
