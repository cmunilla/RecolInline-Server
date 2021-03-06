-- MIT License
-- 
--  Copyright (c) 2019 - 2020  Christophe Munilla
--  
--  Permission is hereby granted, free of charge, to any person obtaining a copy
--  of this software and associated documentation files (the "Software"), to deal
--  in the Software without restriction, including without limitation the rights
--  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
--  copies of the Software, and to permit persons to whom the Software is
--  furnished to do so, subject to the following conditions:
--  
--  The above copyright notice and this permission notice shall be included in all
--  copies or substantial portions of the Software.
--  
--  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
--  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
--  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
--  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
--  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
--  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
--  SOFTWARE.

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
  `label` VARCHAR(1024) NOT NULL,
  `column` INT NOT NULL,
  PRIMARY KEY (`idMuseum`))
  
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Contact` ;

CREATE TABLE IF NOT EXISTS `Contact` (
  `idContact` INT NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(1024) NOT NULL,
  `address` VARCHAR(2048) NOT NULL,
  `email` VARCHAR(1024) NOT NULL,
  PRIMARY KEY (`idContact`))
    
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Domain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Domain` ;

CREATE TABLE IF NOT EXISTS `Domain` (
  `idDomain` INT NOT NULL AUTO_INCREMENT,
  `refDomain` INT NULL DEFAULT 1,
  `label` VARCHAR(1024) NOT NULL,
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
  `label` VARCHAR(1024) NOT NULL,
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
  `isText` BOOLEAN NOT NULL,
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
  `label` VARCHAR(1024) NOT NULL,
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
  `label` VARCHAR(1024) NOT NULL,
  `parent` INT NULL, 
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
    ON UPDATE NO ACTION,
  CONSTRAINT `sheet_parent_fk`
    FOREIGN KEY (`parent`)
    REFERENCES `Sheet` (`idSheet`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `sheet_idDomain_fk_idx` ON `Sheet` (`idDomain` ASC);
CREATE INDEX `sheet_idMuseum_fk_idx` ON `Sheet` (`idMuseum` ASC);
CREATE INDEX `sheet_parent_fk_idx` ON `Sheet` (`parent` ASC);

-- -----------------------------------------------------
-- Table `Links`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Links` ;

CREATE TABLE IF NOT EXISTS `Links` (
  `link1` INT NOT NULL,
  `link2` INT NOT NULL,
  PRIMARY KEY (`link1`, `link2`),
  CONSTRAINT `links_link1_fk`
    FOREIGN KEY (`link1`)
    REFERENCES `Sheet` (`idSheet`) 
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `links_link2_fk`
    FOREIGN KEY (`link2`)
    REFERENCES `Sheet` (`idSheet`) 
    ON DELETE CASCADE 
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;

CREATE INDEX `links_link1_fk_idx` ON `Sheet` (`idSheet` ASC);
CREATE INDEX `links_link2_fk_idx` ON `Sheet` (`idSheet` ASC);

DROP TRIGGER IF EXISTS mirror_link;

delimiter // 
CREATE TRIGGER mirror_link AFTER INSERT ON Links
 FOR EACH ROW 
 BEGIN 
	DECLARE counter  INT;
	SELECT COUNT(*) INTO counter FROM Links WHERE Links.link1=NEW.link2 AND Links.link2=NEW.link1;
    IF count = 0 THEN 
		INSERT INTO Links(link1,link2) VALUES (NEW.link2,NEW.link1); 
	END  IF; 
 END; 
 //
 delimiter ;

-- -----------------------------------------------------
-- Table `Line`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Line` ;

CREATE TABLE IF NOT EXISTS `Line` (
  `idField` INT NOT NULL,
  `idSheet` INT NOT NULL,
  `timestamp` BIGINT NOT NULL,
  `line` TEXT NULL,
   PRIMARY KEY (`idField`, `idSheet`, `timestamp`),
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
  `operand` VARCHAR(1024) NULL,
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
  `login` VARCHAR(1024) NULL,
  `password` VARCHAR(1024) NULL,
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
-- Table `DomainUser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DomainUser` ;

CREATE TABLE IF NOT EXISTS `DomainUser` (
  `idDomain` INT NOT NULL,
  `idUser` INT NOT NULL,
  `idRole` INT NOT NULL,
  PRIMARY KEY (`idDomain`, `idUser`),
  CONSTRAINT `domainUser_idDomain_fk`
    FOREIGN KEY (`idDomain`)
    REFERENCES `Domain` (`idDomain`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `domainUser_idUser_fk`
    FOREIGN KEY (`idUser`)
    REFERENCES `User` (`idUser`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `domainUser_idRole_fk`
    FOREIGN KEY (`idRole`)
    REFERENCES `Role` (`idRole`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
    
ENGINE = InnoDB;

CREATE INDEX `domainUser_idDomain_fk_idx` ON `DomainUser` (`idDomain` ASC);

-- -----------------------------------------------------
-- Table `Category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Category` ;

CREATE TABLE IF NOT EXISTS `Category` (
  `idCategory` INT NOT NULL AUTO_INCREMENT,
  `idType` INT NOT NULL,
  `label` VARCHAR(1024) NOT NULL,
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
  `label` VARCHAR(1024) NOT NULL,
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
CREATE PROCEDURE `GetTypes`(IN idDomainFk INT, IN first BOOLEAN)
BEGIN
	DECLARE parentDomain INT;
	IF first THEN SET @@SESSION.max_sp_recursion_depth = 255; END IF;	
    SELECT refDomain INTO parentDomain FROM Domain WHERE Domain.idDomain = idDomainFk;
    IF parentDomain IS NULL
    THEN
		SELECT idType, idDomain, label FROM Type WHERE Type.idDomain = idDomainFk;
	ELSE
        SELECT GetTypes (parentDomain,FALSE)
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
CREATE PROCEDURE `GetModels`(IN idDomainFk INT, IN first BOOLEAN)
BEGIN
	DECLARE parentDomain INT;
	IF first THEN SET @@SESSION.max_sp_recursion_depth = 255; END IF;	
	CREATE TEMPORARY TABLE IF NOT EXISTS Models ( idModel INT, label VARCHAR(1024));	
    SELECT refDomain INTO parentDomain FROM Domain WHERE Domain.idDomain = idDomainFk;
    IF parentDomain IS NOT NULL THEN CALL GetModels (parentDomain, FALSE); END IF;
	INSERT INTO Models SELECT idModel, label FROM Model WHERE Model.idDomain = idDomainFk;
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
-- procedure GetIdRole
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetIdRole`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetIdRole`(IN idMuseumFk INT, IN idDomainFk INT, IN idUserFk INT)
BEGIN
	DECLARE idRoleFk INT;

	SELECT idRole INTO idRoleFk FROM DomainUser 
	WHERE DomainUser.idDomain = idDomainFk AND DomainUser.idUser = idUserFk;
	
	IF ISNULL(idRoleFk) = 1 THEN 
		SELECT idRole INTO idRoleFk FROM MusuemUser 
		WHERE MusuemUser.idMuseum = idMuseumFk AND MuseumUser.idUser = idUserFk;	 
	END  IF; 	
		
	IF ISNULL(idRoleFk) = 1 THEN 	
		SELECT idRole INTO idRoleFk FROM Role WHERE Role.label = 'NONE'; 	
	END  IF; 
	
	SELECT idRoleFk AS RoleIdentifier;
END;$$

DELIMITER ;


-- -----------------------------------------------------
-- procedure GetSheetFields
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetSheetFields`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetSheetFields`(IN idMuseumFk INT, IN idDomainFk INT, IN idUserFk INT)
BEGIN
	DECLARE idModelFk INT;
	DECLARE idRoleFk INT;
	DECLARE idVisibilityFk INT;
	DECLARE labelVisibility VARCHAR(1024);
	DECLARE labelModel VARCHAR(1024);
	DECLARE done BOOLEAN DEFAULT FALSE;
	
	DECLARE cur CURSOR FOR SELECT * FROM Models;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;			
		
	SELECT idRole INTO idRoleFk FROM DomainUser 
	WHERE DomainUser.idDomain = idDomainFk AND DomainUser.idUser = idUserFk;
	
	IF ISNULL(idRoleFk) = 1 THEN 
		SELECT idRole INTO idRoleFk FROM MusuemUser 
		WHERE MusuemUser.idMuseum = idMuseumFk AND MuseumUser.idUser = idUserFk;	 
	END  IF; 	
		
	IF ISNULL(idRoleFk) = 1 THEN 	
		SELECT idRole INTO idRoleFk FROM Role WHERE Role.label = 'NONE'; 	
	END  IF; 
	
	CREATE TEMPORARY TABLE IF NOT EXISTS sheetSkeleton (
		idModel INT, 
		idField INT,
		idType INT,
		idVisibility INT,
		labelModel VARCHAR(1024), 
		labelField VARCHAR(1024),
		labelType VARCHAR(1024),
		labelVisibility VARCHAR(1024),
		constraints TEXT,
		isText BOOLEAN);
		
	OPEN cur;
	read_loop: LOOP
		FETCH cur INTO idModelFk ,labelModel;
		IF done THEN LEAVE read_loop; END IF;
		
		INSERT INTO sheetSkeleton
		SELECT idModelFk, Field.idField, Field.idType, Visibility.idVisibility, labelModel, 
		Field.label, Type.label, Visibility.label, CONCAT('[',GROUP_CONCAT(CONCAT('{','"operator":"',
		Operator.label,'",','"operand":"', `Constraint`.`operand`,'"','}')),']'), Type.isText 
		FROM Field, Type, FieldVisibility, Visibility, `Constraint`, Operator 
		WHERE FieldVisibility.idField = Field.idField 
		AND FieldVisibility.idRole = idRoleFk 
		AND FieldVisibility.idVisibility = Visibility.idVisibility 
		AND Field.idType = Type.idType 
		AND Field.idModel = idModelFk 
		AND `Constraint`.`idField` = Field.idField 
		AND `Constraint`.`idOperator` = Operator.idOperator 
		GROUP BY Field.idField;
	END LOOP;
	CLOSE cur;

	SELECT * FROM sheetSkeleton;
	DROP TEMPORARY TABLE IF EXISTS Models;	
	DROP TEMPORARY TABLE IF EXISTS sheetSkeleton;	
END;$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure GetSheetLines
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetSheetLines`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetSheetLines`(IN idSheetFk INT)
BEGIN	
	SELECT * from Line WHERE Line.idSheet = idSheetFk;
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

-- -----------------------------------------------------
-- procedure GetChildren
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetChildren`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetChildren`(IN idSheetFk INT)
BEGIN
	SELECT Sheet.idSheet, Sheet.label FROM Sheet
	WHERE Sheet.parent = idSheetFk;
END;$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure GetLinked
-- -----------------------------------------------------

USE `recolinline`;
DROP procedure IF EXISTS `GetLinked`;

DELIMITER $$
USE `recolinline`$$
CREATE PROCEDURE `GetLinked`(IN idSheetFk INT)
BEGIN
	SELECT Sheet.idSheet, Sheet.label FROM Sheet, Links
	WHERE Sheet.idSheet = Links.link1
	AND Links.link2 = idSheetFk ;
END;$$

DELIMITER ;

-------------------------------------------------------

-------------------------------------------------------


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
-- INTRODUCE THE ARTEFACT DOMAIN
-- -----------------------------------------------------
-- the artefact domain
INSERT INTO `recolinline`.`Domain`(idDomain, refDomain,label) VALUES (1, NULL,'ARTEFACT');
-- text value type
INSERT INTO `recolinline`.`Type`  (idType, idDomain, isText, label) VALUES (1, 1, true, 'string');
-- text value type specifying a date
INSERT INTO `recolinline`.`Type`  (idType, idDomain, isText, label) VALUES (2, 1, true, 'datetime');
-- standard integer value type 
INSERT INTO `recolinline`.`Type`  (idType, idDomain, isText, label) VALUES (3, 1, false, 'int');
-- long value specifying unix epoch milliseconds formated date
INSERT INTO `recolinline`.`Type`  (idType, idDomain, isText, label) VALUES (4, 1, false, 'epoch');
-- long value specifying unix epoch milliseconds formated date
INSERT INTO `recolinline`.`Type`  (idType, idDomain, isText, label) VALUES (5, 1, false, 'boolean');

-- the historical period Type
INSERT INTO `recolinline`.`Type`  (idType, idDomain, isText, label) VALUES  (6, 1, true, 'time_measure');
INSERT INTO `recolinline`.`Category` (idCategory, idType, label, cardinality) VALUES  (1, 6, 'time_measure', 1);
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (1, 'day');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (1, 'month');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (1, 'year');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (1, 'century');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (1, 'period');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (1, 'era');

-- the historical period Type
INSERT INTO `recolinline`.`Type`  (idType, idDomain, isText, label) VALUES  (7, 1, true, 'historical_period');
INSERT INTO `recolinline`.`Category` (idCategory, idType, label, cardinality) VALUES  (2, 7, 'historical_period', 1);
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (2, 'prehistory');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (2, 'antiquity');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (2, 'middle age');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (2, 'modern age');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (2, 'contemporary period');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (2, 'now');

-- the geological era Type
INSERT INTO `recolinline`.`Type`  (idType, idDomain, isText, label) VALUES  (8, 1, true, 'geological_era');
INSERT INTO `recolinline`.`Category` (idType, label, cardinality) VALUES  (8, 'geological_era', 1);
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (3, '');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (3, '');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (3, '');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (3, '');
INSERT INTO `recolinline`.`CategoryEntry` (idCategory, label) VALUES  (3, '');
  
-- the identity model
INSERT INTO `recolinline`.`Model` (idModel, idDomain, label) VALUES (1, 1, 'IDENTITY');
-- the text name 
INSERT INTO `recolinline`.`Field` (idModel, idType, label) VALUES  (1, 1, 'name');
-- the text description 
INSERT INTO `recolinline`.`Field` (idModel, idType, label) VALUES  (1, 1, 'description'); 
-- the text author
INSERT INTO `recolinline`.`Field` (idModel, idType, label) VALUES  (1, 1, 'author');
-- the time type in use 
INSERT INTO `recolinline`.`Field` (idModel, idType, label) VALUES  (1, 3, 'time_measure'); 
-- the time value 
INSERT INTO `recolinline`.`Field` (idModel, idType, label) VALUES  (1, 1, 'time_value'); 
