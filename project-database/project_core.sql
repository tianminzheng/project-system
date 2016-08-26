
-- ----------------------------
-- Table structure for core_project
-- ----------------------------
DROP TABLE IF EXISTS core_project;
CREATE TABLE core_project (
  `project_id` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(200),
  `benefit` int,
  `cost` int,
  `risk` int,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for core_plan
-- ----------------------------
DROP TABLE IF EXISTS core_plan;
CREATE TABLE core_plan (
  `plan_id` varchar(100) NOT NULL,
  `project_id` varchar(100) NOT NULL,
  `scheduled_date` Date NOT NULL,
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for core_task
-- ----------------------------
DROP TABLE IF EXISTS core_task;
CREATE TABLE core_task (
  `task_id` varchar(100) NOT NULL,
  `project_id` varchar(100) NOT NULL,
  `task_name` varchar(50) NOT NULL,
  `task_description` varchar(200),
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
