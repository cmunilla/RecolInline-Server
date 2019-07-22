-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema recolinline
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `recolinline` ;

-- -----------------------------------------------------
-- Schema recolinline
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `recolinline` DEFAULT CHARACTER SET utf8 ;

USE `recolinline` ;

-- -----------------------------------------------------
-- Table `Museum`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Museum` ;

CREATE TABLE IF NOT EXISTS `Museum` (
  `idMuseum` INT NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(256) NOT NULL,
  `column` INT NOT NULL,
  PRIMARY KEY (`idMuseum`))
  
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Contact` ;

CREATE TABLE IF NOT EXISTS `Contact` (
  `idContact` INT NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(256) NOT NULL,
  `address` VARCHAR(256) NOT NULL,
  `email` VARCHAR(256) NOT NULL,
  `phone` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`idContact`))
    
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Domain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Domain` ;

CREATE TABLE IF NOT EXISTS `Domain` (
  `idDomain` INT NOT NULL AUTO_INCREMENT,
  `refDomain` INT NULL DEFAULT 1,
  `label` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`idDomain`),
  CONSTRAINT `refDomain_lk`
    FOREIGN KEY (`refDomain`)
    REFERENCES `Domain` (`idDomain`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
    
ENGINE = InnoDB;

CREATE INDEX `refDomain_lk_idx` ON `Domain` (`refDomain` ASC);

-- -----------------------------------------------------
-- Table `Model`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Model` ;

CREATE TABLE IF NOT EXISTS `Model` (
  `idModel` INT NOT NULL AUTO_INCREMENT,
  `idDomain` INT NOT NULL,
  `label` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`idModel`),
  CONSTRAINT `domain_idDomain_fk`
    FOREIGN KEY (`idDomain`)
    REFERENCES `Domain` (`idDomain`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `domain_idDomain_fk_idx` ON `Model` (`idDomain` ASC);

-- -----------------------------------------------------
-- Table `Type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Type` ;

CREATE TABLE IF NOT EXISTS `Type` (
  `idType` INT NOT NULL AUTO_INCREMENT,
  `idDomain` INT NOT NULL,
  `label` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idType`),
  CONSTRAINT `type_idDomain_fk`
    FOREIGN KEY (`idDomain`)
    REFERENCES `Domain` (`idDomain`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `type_idDomain_fk_idx` ON `Type` (`idDomain` ASC);

-- -----------------------------------------------------
-- Table `Field`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Field` ;

CREATE TABLE IF NOT EXISTS `Field` (
  `idField` INT NOT NULL AUTO_INCREMENT,
  `idModel` INT NOT NULL,
  `idType` INT NOT NULL,
  `label` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`idField`),
  CONSTRAINT `field_idModel_fk`
    FOREIGN KEY (`idModel`)
    REFERENCES `Model` (`idModel`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `field_idType_fk`
    FOREIGN KEY (`idType`)
    REFERENCES `Type` (`idType`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `field_idType_fk_idx` ON `Field` (`idType` ASC);
CREATE INDEX `field_idModel_fk_idx` ON `Field` (`idModel` ASC);

-- -----------------------------------------------------
-- Table `Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Role` ;

CREATE TABLE IF NOT EXISTS `Role` (
  `idRole` INT NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRole`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Visibility`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Visibility` ;

CREATE TABLE IF NOT EXISTS `Visibility` (
  `idVisibility` INT NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idVisibility`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `FieldVisibility`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldVisibility` ;

CREATE TABLE IF NOT EXISTS `FieldVisibility` (
  `idVisibility` INT NOT NULL,
  `idRole` INT NOT NULL,
  `idField` INT NOT NULL,
  PRIMARY KEY (`idVisibility`, `idRole`, `idField`),
  CONSTRAINT `fieldVisibility_idVisibility_fk`
    FOREIGN KEY (`idVisibility`)
    REFERENCES `Visibility` (`idVisibility`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fieldVisibility_idRole_fk`
    FOREIGN KEY (`idRole`)
    REFERENCES `Role` (`idRole`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fieldVisibility_idField_fk`
    FOREIGN KEY (`idField`)
    REFERENCES `Field` (`idField`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
    
ENGINE = InnoDB;

CREATE INDEX `fieldVisibility_idField_fk_idx` ON `FieldVisibility` (`idField` ASC);
CREATE INDEX `fieldVisibility_idRole_fk_idx` ON `FieldVisibility` (`idRole` ASC);

-- -----------------------------------------------------
-- Table `Sheet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sheet` ;

CREATE TABLE IF NOT EXISTS `Sheet` (
  `idSheet` INT NOT NULL AUTO_INCREMENT,
  `idMuseum` INT NOT NULL,
  `idDomain` INT NOT NULL,
  `label` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`idSheet`),
  CONSTRAINT `sheet_idDomain_fk`
    FOREIGN KEY (`idDomain`)
    REFERENCES `Domain` (`idDomain`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `sheet_idMuseum_fk`
    FOREIGN KEY (`idMuseum`)
    REFERENCES `Museum` (`idMuseum`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `sheet_idDomain_fk_idx` ON `Sheet` (`idDomain` ASC);
CREATE INDEX `sheet_idMuseum_fk_idx` ON `Sheet` (`idMuseum` ASC);

-- -----------------------------------------------------
-- Table `Line`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Line` ;

CREATE TABLE IF NOT EXISTS `Line` (
  `idLine` INT NOT NULL AUTO_INCREMENT,
  `idField` INT NOT NULL,
  `idSheet` INT NOT NULL,
  `line` VARCHAR(256) NULL,
  PRIMARY KEY (`idLine`),
  CONSTRAINT `line_idField_fk`
    FOREIGN KEY (`idField`)
    REFERENCES `Field` (`idField`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `line_idSheet_fk`
    FOREIGN KEY (`idSheet`)
    REFERENCES `Sheet` (`idSheet`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `line_idField_fk_idx` ON `Line` (`idField` ASC);
CREATE INDEX `line_idSheet_fk_idx` ON `Line` (`idSheet` ASC);

-- -----------------------------------------------------
-- Table `Operator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Operator` ;

CREATE TABLE IF NOT EXISTS `Operator` (
  `idOperator` INT NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idOperator`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Constraint`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Constraint` ;

CREATE TABLE IF NOT EXISTS `Constraint` (
  `idConstraint` INT NOT NULL AUTO_INCREMENT,
  `idField` INT NOT NULL,
  `idOperator` INT NOT NULL,
  `operand` VARCHAR(256) NULL,
  PRIMARY KEY (`idConstraint`),
  CONSTRAINT `constraint_idField_fk`
    FOREIGN KEY (`idField`)
    REFERENCES `Field` (`idField`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `constraint_idOperator_fk`
    FOREIGN KEY (`idOperator`)
    REFERENCES `Operator` (`idOperator`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `constraint_idField_fk_idx` ON `Constraint` (`idField` ASC);

-- -----------------------------------------------------
-- Table `User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `User` ;

CREATE TABLE IF NOT EXISTS `User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(256) NULL,
  `password` VARCHAR(256) NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;

CREATE INDEX `user_login_fk_idx` ON `User` (`login` ASC);

-- -----------------------------------------------------
-- Table `UserContact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UserContact` ;

CREATE TABLE IF NOT EXISTS `UserContact` (
  `idUser` INT NOT NULL,
  `idContact` INT NOT NULL,
  PRIMARY KEY (`idUser`, `idContact`),
  CONSTRAINT `userContact_idUser_fk`
    FOREIGN KEY (`idUser`)
    REFERENCES `User` (`idUser`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `userContact_idContact_fk`
    FOREIGN KEY (`idContact`)
    REFERENCES `Contact` (`idContact`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
    
ENGINE = InnoDB;

CREATE INDEX `userContact_idUser_fk_idx` ON `UserContact` (`idUser` ASC);

-- -----------------------------------------------------
-- Table `MuseumContact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MuseumContact` ;

CREATE TABLE IF NOT EXISTS `MuseumContact` (
  `idMuseum` INT NOT NULL,
  `idContact` INT NOT NULL,
  PRIMARY KEY (`idMuseum`, `idContact`),
  CONSTRAINT `museumContact_idMuseum_fk`
    FOREIGN KEY (`idMuseum`)
    REFERENCES `Museum` (`idMuseum`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `museumContact_idContact_fk`
    FOREIGN KEY (`idContact`)
    REFERENCES `Contact` (`idContact`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
    
ENGINE = InnoDB;

CREATE INDEX `museumContact_idMuseum_fk_idx` ON `MuseumContact` (`idMuseum` ASC);

-- -----------------------------------------------------
-- Table `MuseumUser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MuseumUser` ;

CREATE TABLE IF NOT EXISTS `MuseumUser` (
  `idMuseum` INT NOT NULL,
  `idUser` INT NOT NULL,
  `idRole` INT NOT NULL,
  PRIMARY KEY (`idMuseum`, `idUser`),
  CONSTRAINT `museumUser_idMuseum_fk`
    FOREIGN KEY (`idMuseum`)
    REFERENCES `Museum` (`idMuseum`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `museumUser_idUser_fk`
    FOREIGN KEY (`idUser`)
    REFERENCES `User` (`idUser`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `museumUser_idRole_fk`
    FOREIGN KEY (`idRole`)
    REFERENCES `Role` (`idRole`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
    
ENGINE = InnoDB;

CREATE INDEX `museumUser_idMuseum_fk_idx` ON `MuseumUser` (`idMuseum` ASC);

-- -----------------------------------------------------
-- Table `Category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Category` ;

CREATE TABLE IF NOT EXISTS `Category` (
  `idCategory` INT NOT NULL AUTO_INCREMENT,
  `idType` INT NOT NULL,
  `label` VARCHAR(256) NOT NULL,
  `cardinality` INT NOT NULL DEFAULT -1,
  PRIMARY KEY (`idCategory`),
  CONSTRAINT `category_idType_fk`
    FOREIGN KEY (`idType`)
    REFERENCES `Type` (`idType`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `category_idType_fk_idx` ON `Category` (`idType` ASC);

-- -----------------------------------------------------
-- Table `CategoryEntry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CategoryEntry` ;

CREATE TABLE IF NOT EXISTS `CategoryEntry` (
  `idCategoryEntry` INT NOT NULL AUTO_INCREMENT,
  `idCategory` INT NOT NULL,
  `label` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`idCategoryEntry`),
  CONSTRAINT `categoryEntry_idCategory_fk`
    FOREIGN KEY (`idCategory`)
    REFERENCES `Category` (`idCategory`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `categoryEntry_idCategory_fk_idx` ON `CategoryEntry` (`idCategory` ASC);

-- -----------------------------------------------------
-- procedure GetTypes
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetTypes`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetTypes`(IN idDomainFk INT)
BEGIN
	DECLARE parentDomain INT;
	SET @@SESSION.max_sp_recursion_depth = 255;
    SELECT refDomain INTO parentDomain FROM Domain WHERE Domain.idDomain = idDomainFk;
    IF parentDomain IS NULL
    THEN
		SELECT idType, idDomain, label FROM Type WHERE Type.idDomain = idDomainFk;
	ELSE
        SELECT GetTypes (parentDomain)
        UNION ALL
        SELECT idType, idDomain, label FROM Type WHERE Type.idDomain = idDomainFk;
	END IF;
END;$$

DELIMITER ;
-- -----------------------------------------------------
-- procedure GetModels
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetModels`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetModels`(IN idDomainFk INT)
BEGIN
	DECLARE parentDomain INT;
	SET @@SESSION.max_sp_recursion_depth = 255;
    SELECT refDomain INTO parentDomain FROM Domain WHERE Domain.idDomain = idDomainFk;
    IF parentDomain IS NULL
    THEN
		SELECT idModel, idDomain, label FROM Model WHERE Model.idDomain = idDomainFk;
	ELSE
        SELECT GetModels (parentDomain)
        UNION ALL
        SELECT idModel, idDomain, label FROM Model WHERE Model.idDomain = idDomainFk;
	END IF;
END;$$

DELIMITER ;
-- -----------------------------------------------------
-- procedure GetSheetsFromMuseum
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetSheetsFromMuseum`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetSheetsFromMuseum`(IN idMuseumFk INT, IN fstIndex INT, IN lstIndex INT)
BEGIN
	DECLARE ind INT;
	SET ind = fstIndex -1;
	SELECT idSheet, idMuseum, idDomain, label FROM Sheet WHERE Sheet.idMuseum = idMuseumFk
	LIMIT ind,lstIndex;
END;$$

DELIMITER ;
-- -----------------------------------------------------
-- procedure GetSheetsCountFromMuseum
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetSheetsCountFromMuseum`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetSheetsCountFromMuseum`(IN idMuseumFk INT)
BEGIN
	SELECT COUNT(*) AS NBR FROM Sheet WHERE Sheet.idMuseum = idMuseumFk;
END;$$

DELIMITER ;
-- -----------------------------------------------------
-- procedure GetSheetsFromDomainFromMuseum
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetSheetsFromDomainFromMuseum`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetSheetsFromDomainFromMuseum`(IN idMuseumFk INT, IN idDomainFk INT, IN fstIndex INT, IN lstIndex INT)
BEGIN
	DECLARE ind INT;
	SET ind = fstIndex -1;
	SELECT idSheet, idMuseum, idDomain, label FROM Sheet
	WHERE Sheet.idMuseum = idMuseumFk
	AND Sheet.idDomain = idDomainFk
	LIMIT ind,lstIndex;
END;$$

DELIMITER ;
-- -----------------------------------------------------
-- procedure GetSheetsCountFromDomainFromMuseum
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetSheetsCountFromDomainFromMuseum`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetSheetsCountFromDomainFromMuseum`(IN idMuseumFk INT,IN idDomainFk INT)
BEGIN
	SELECT COUNT(*) AS NBR FROM Sheet 
	WHERE Sheet.idMuseum = idMuseumFk
	AND Sheet.idDomain = idDomainFk;
END;$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure GetSheet
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetSheet`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetSheet`(IN idSheetPk INT)
BEGIN
	SELECT COUNT(*) AS NBR FROM Sheet WHERE Sheet.idMuseum = idMuseumFk AND Sheet.idDomain = idDomainFk;
END;$$

DELIMITER ;
-- -----------------------------------------------------
-- procedure GetUsers
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetUsers`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetUsers`(IN idMuseumFk INT)
BEGIN	
	SELECT MuseumUser.idMuseum, MuseumUser.idUser, MuseumUser.idRole, User.login 
	FROM User, MuseumUser
	WHERE  MuseumUser.idUser = User.idUser
	AND MuseumUser.idMuseum = idMuseumFk;
END;$$

-- -----------------------------------------------------
-- procedure GetUserContacts
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetUserContacts`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetUserContacts`(IN idUserFk INT)
BEGIN
	SELECT Contact.idContact, Contact.label, Contact.address, Contact.email, Contact.phone FROM Contact, UserContact 
	WHERE UserContact.idContact = Contact.idContact
	AND UserContact.idUser = idUserFk;
END;$$

-- -----------------------------------------------------
-- procedure GetMuseumContacts
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetMuseumContacts`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetMuseumContacts`(IN idMuseumFk INT)
BEGIN
	SELECT Contact.idContact, Contact.label, Contact.address, Contact.email, Contact.phone FROM Contact, MuseumContact 
	WHERE MuseumContact.idContact = Contact.idContact
	AND MuseumContact.idMuseum = idMuseumFk;
END;$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `Domain`
-- -----------------------------------------------------
START TRANSACTION;
USE `recolinline`;
INSERT INTO `Domain` (`refDomain`, `label`) VALUES (NULL, 'ARTIFACT');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Role`
-- -----------------------------------------------------
START TRANSACTION;
USE `recolinline`;
INSERT INTO `Role` (`label`) VALUES ('ADMINISTRATOR');
INSERT INTO `Role` (`label`) VALUES ('VALIDATOR');
INSERT INTO `Role` (`label`) VALUES ('WRITER');
INSERT INTO `Role` (`label`) VALUES ('READER');
INSERT INTO `Role` (`label`) VALUES ('NONE');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Visibility`
-- -----------------------------------------------------
START TRANSACTION;
USE `recolinline`;
INSERT INTO `Visibility` (`label`) VALUES ('HIDDEN');
INSERT INTO `Visibility` (`label`) VALUES ('DISABLED');
INSERT INTO `Visibility` (`label`) VALUES ('ENABLED');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Operator`
-- -----------------------------------------------------
START TRANSACTION;
USE `recolinline`;
INSERT INTO `Operator` ( `label`) VALUES ('EQUAL');
INSERT INTO `Operator` ( `label`) VALUES ('GREATER');
INSERT INTO `Operator` (`label`) VALUES ('GREATER OR EQUAL');
INSERT INTO `Operator` (`label`) VALUES ('LESSER');
INSERT INTO `Operator` (`label`) VALUES ('LESSER OR EQUAL');
INSERT INTO `Operator` (`label`) VALUES ('IN');
INSERT INTO `Operator` ( `label`) VALUES ('ALL');
INSERT INTO `Operator` ( `label`) VALUES ('REGEXP');

COMMIT;


-- -----------------------------------------------------
-- Data for table `User`
-- -----------------------------------------------------
START TRANSACTION;
USE `recolinline`;
INSERT INTO `User` (`idUser`, `login`, `password`) VALUES (1, 'ANONYMOUS', 'ANONYMOUS');

COMMIT;
-- -----------------------------------------------------
-- HIBERNATE
-- -----------------------------------------------------
-- Table `hibernate_sequence`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `hibernate_sequence` ;

CREATE TABLE `hibernate_sequence` (next_val BIGINT) 
ENGINE=InnoDB;

START TRANSACTION;
USE `recolinline`;
INSERT INTO `hibernate_sequence` values ( 1 );

COMMIT;
-- -----------------------------------------------------
-- -----------------------------------------------------