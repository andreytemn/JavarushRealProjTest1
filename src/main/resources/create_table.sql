DROP TABLE IF EXISTS `user`;
CREATE TABLE `test`.`user` (
  `id`          INT(8)      NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(25) NOT NULL,
  `age`         INT(3)      NULL,
  `isAdmin`     BIT(1)      NOT NULL,
  `createdDate` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);
