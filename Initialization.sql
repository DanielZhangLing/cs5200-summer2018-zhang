Person:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`Person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `dob` DATETIME NULL,
  PRIMARY KEY (`id`));

Developer:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`Developer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `developerKey` VARCHAR(45) NULL,
  `person` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `person_UNIQUE` (`person` ASC),
  CONSTRAINT `developer_person_generation`
    FOREIGN KEY (`person`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

User:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userAgreement` TINYINT NULL DEFAULT 0,
  `userKey` VARCHAR(45) NULL,
  `person` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `person_UNIQUE` (`person` ASC),
  CONSTRAINT `user_person_generation`
    FOREIGN KEY (`person`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Person` (`id`)
    ON DELETE CASCADE
ON UPDATE CASCADE);


(5pts) Create tables website, page, widget, heading, html, youtube, image. Implement generalization using a single table. Use a new field called type to discriminate for the type. Use the class name as the values of the field

Website:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`Website` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` LONGTEXT NULL,
  `created` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `visits` INT NULL,
  `developer` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `website_developer_aggregation_idx` (`developer` ASC),
  CONSTRAINT `website_developer_aggregation`
    FOREIGN KEY (`developer`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Developer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

Page:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`Page` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` LONGTEXT NULL,
  `created` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `views` INT NULL,
  `website` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `page_website_composition_idx` (`website` ASC),
  CONSTRAINT `page_website_composition`
    FOREIGN KEY (`website`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Website` (`id`)
    ON DELETE CASCADE
ON UPDATE CASCADE);

Widget:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`Widget` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `width` INT NULL,
  `height` INT NULL,
  `cssClass` VARCHAR(45) NULL,
  `cssStyle` VARCHAR(45) NULL,
  `text` LONGTEXT NULL,
  `order` INT NULL,
  `page` INT NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `widget_page_compostion_idx` (`page` ASC),
  CONSTRAINT `widget_page_compostion`
    FOREIGN KEY (`page`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Page` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

Heading:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`Heading` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `size` INT NULL DEFAULT 2,
  `widget` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `widget_UNIQUE` (`widget` ASC),
  CONSTRAINT `heading_widget_generalization`
    FOREIGN KEY (`widget`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Widget` (`id`)
    ON DELETE CASCADE
ON UPDATE CASCADE);

HTML:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`Html` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `html` LONGTEXT NULL,
  `widget` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `widget_UNIQUE` (`widget` ASC),
  CONSTRAINT `html_widget_generalization`
    FOREIGN KEY (`widget`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Widget` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

Youtube:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`Youtube` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `url` LONGTEXT NULL,
  `sharable` TINYINT NULL DEFAULT 0,
  `expandable` TINYINT NULL DEFAULT 0,
  `widget` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `widget_UNIQUE` (`widget` ASC),
  CONSTRAINT `youtube_widget_generalization`
    FOREIGN KEY (`widget`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Widget` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

Image:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`Image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `src` LONGTEXT NULL,
  `widget` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `widget_UNIQUE` (`widget` ASC),
  CONSTRAINT `image_widget_generalization`
    FOREIGN KEY (`widget`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Widget` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

(5pts) Create tables address and phones

Phone:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`Phone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(45) NULL,
  `primary` TINYINT NULL DEFAULT 0,
  `person` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `phone_person_composition_idx` (`person` ASC),
  CONSTRAINT `phone_person_composition`
    FOREIGN KEY (`person`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

Address:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`Address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street1` VARCHAR(50) NULL,
  `street2` VARCHAR(50) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  `primary` TINYINT NULL DEFAULT 0,
  `person` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `phone_person_composition_idx` (`person` ASC),
  CONSTRAINT `address_person_composition`
    FOREIGN KEY (`person`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

(5pts) Create tables for website and page roles, and enumerations

Priviledge:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`Priviledge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `enumeration` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

Role:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`Role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `enumeration` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
	
Website Priviledge:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`WebsitePriviledge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `priviledge` INT NULL,
  `developer` INT NULL,
  `website` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `websitepriviledge_developer_association_idx` (`developer` ASC),
  INDEX `websitepriviledge_priviledge_association_idx` (`priviledge` ASC),
  INDEX `websitepriviledge_website_association_idx` (`website` ASC),
  CONSTRAINT `websitepriviledge_developer_association`
    FOREIGN KEY (`developer`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Developer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websitepriviledge_priviledge_association`
    FOREIGN KEY (`priviledge`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Priviledge` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websitepriviledge_website_association`
    FOREIGN KEY (`website`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Website` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

Page Priviledge:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`PagePriviledge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `privilege` INT NULL,
  `developer` INT NULL,
  `page` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `pagepriviledge_priviledge_association_idx` (`privilege` ASC),
  INDEX `pagepriviledge_developer_association_idx` (`developer` ASC),
  INDEX `pagepriviledge_page_association_idx` (`page` ASC),
  CONSTRAINT `pagepriviledge_priviledge_association`
    FOREIGN KEY (`privilege`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Priviledge` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagepriviledge_developer_association`
    FOREIGN KEY (`developer`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Developer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagepriviledge_page_association`
    FOREIGN KEY (`page`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Page` (`id`)
    ON DELETE CASCADE
ON UPDATE CASCADE);

Website Role:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`WebsiteRole` (
  `int` INT NOT NULL AUTO_INCREMENT,
  `role` INT NULL,
  `developer` INT NULL,
  `website` INT NULL,
  PRIMARY KEY (`int`),
  INDEX `websiterole_role_association_idx` (`role` ASC),
  INDEX `websiterole_website_association_idx` (`website` ASC),
  INDEX `websiterole_developer_association_idx` (`developer` ASC),
  CONSTRAINT `websiterole_role_association`
    FOREIGN KEY (`role`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websiterole_website_association`
    FOREIGN KEY (`website`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Website` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websiterole_developer_association`
    FOREIGN KEY (`developer`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Developer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

Page Role:
CREATE TABLE `hw3_zhang_ling_summer_2018`.`PageRole` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` INT NULL,
  `page` INT NULL,
  `developer` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `pagerole_role_association_idx` (`role` ASC),
  INDEX `pagerole_page_association_idx` (`page` ASC),
  INDEX `pagerole_developer_association_idx` (`developer` ASC),
  CONSTRAINT `pagerole_role_association`
    FOREIGN KEY (`role`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagerole_page_association`
    FOREIGN KEY (`page`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Page` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagerole_developer_association`
    FOREIGN KEY (`developer`)
    REFERENCES `hw3_zhang_ling_summer_2018`.`Developer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `hw3_zhang_ling_summer_2018`.`Role`
(`id`,
`enumeration`)
VALUES
(1,
owner);
INSERT INTO `hw3_zhang_ling_summer_2018`.`Role`
(`id`,
`enumeration`)
VALUES
(2,
admin);
INSERT INTO `hw3_zhang_ling_summer_2018`.`Role`
(`id`,
`enumeration`)
VALUES
(3,
writer);
INSERT INTO `hw3_zhang_ling_summer_2018`.`Role`
(`id`,
`enumeration`)
VALUES
(4,
editor);
INSERT INTO `hw3_zhang_ling_summer_2018`.`Role`
(`id`,
`enumeration`)
VALUES
(5,
reviewer);
