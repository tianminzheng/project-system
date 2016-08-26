
DROP TABLE IF EXISTS `usercenter_user`;
CREATE TABLE `usercenter_user` (
    `id` bigint(20) NOT NULL auto_increment,--自动递增
    `enablement_enabled` tinyint(1) NOT NULL,
    `enablement_end_date` datetime,
    `enablement_start_date` datetime,
    `password` varchar(32) NOT NULL,
    `username` varchar(250) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `usercenter_person`;
CREATE TABLE `usercenter_person` (
    -- primary key is my parent's pk
    `id` bigint(20) NOT NULL,
    `contact_information_email_address_address` varchar(100) NOT NULL,
    `contact_information_postal_address_city` varchar(100) NOT NULL,
    `contact_information_postal_address_country_code` varchar(2) NOT NULL,
    `contact_information_postal_address_postal_code` varchar(12) NOT NULL,
    `contact_information_postal_address_state_province` varchar(100) NOT NULL,
    `contact_information_postal_address_street_address` varchar(100),
    `contact_information_primary_telephone_number` varchar(20) NOT NULL,
    `contact_information_secondary_telephone_number` varchar(20) NOT NULL,
    `name_first_name` varchar(50) NOT NULL,
    `name_last_name` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;