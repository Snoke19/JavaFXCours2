-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema computers
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema computers
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `computers` DEFAULT CHARACTER SET utf8 ;
USE `computers` ;

-- -----------------------------------------------------
-- Table `computers`.`technique`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `computers`.`technique` (
  `idTechnique` INT(11) NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `name_tech` VARCHAR(45) NULL DEFAULT NULL,
  `inventory_numbers` INT(15) NULL DEFAULT NULL,
  `start_cost` DECIMAL(10,2) NULL DEFAULT NULL,
  `actual_cost` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`idTechnique`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `computers`.`enterprise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `computers`.`enterprise` (
  `idEnterprise` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `name_director` VARCHAR(45) NULL DEFAULT NULL,
  `middle_name_director` VARCHAR(45) NULL DEFAULT NULL,
  `last_name_director` VARCHAR(45) NULL DEFAULT NULL,
  `name_accountant` VARCHAR(45) NULL DEFAULT NULL,
  `middle_name_accountant` VARCHAR(45) NULL DEFAULT NULL,
  `last_name_accountant` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idEnterprise`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `computers`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `computers`.`employee` (
  `idEmployee` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `middle_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `post` VARCHAR(45) NULL DEFAULT NULL,
  `liability` TINYINT(1) NULL DEFAULT NULL,
  `number_phone` INT(12) NULL DEFAULT NULL,
  `technique_idTechnique` INT(11) NOT NULL,
  `enterprise_idEnterprise` INT(11) NOT NULL,
  PRIMARY KEY (`idEmployee`),
  INDEX `fk_employee_ technique1_idx` (`technique_idTechnique` ASC),
  INDEX `fk_employee_enterprise1_idx` (`enterprise_idEnterprise` ASC),
  CONSTRAINT `fk_employee_ technique1`
    FOREIGN KEY (`technique_idTechnique`)
    REFERENCES `computers`.`technique` (`idTechnique`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_enterprise1`
    FOREIGN KEY (`enterprise_idEnterprise`)
    REFERENCES `computers`.`enterprise` (`idEnterprise`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
