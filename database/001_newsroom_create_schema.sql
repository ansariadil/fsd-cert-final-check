SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `newsroom` ;
CREATE SCHEMA IF NOT EXISTS `newsroom` DEFAULT CHARACTER SET latin1 ;
USE `newsroom` ;

-- -----------------------------------------------------
-- Table `newsroom`.`article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newsroom`.`article` ;

CREATE  TABLE IF NOT EXISTS `newsroom`.`article` (
  `ar_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `ar_author` VARCHAR(250) NULL DEFAULT NULL ,
  `ar_title` VARCHAR(250) NULL DEFAULT NULL ,
  `ar_description` VARCHAR(500) NULL DEFAULT NULL ,
  `ar_published_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  `ar_content` LONGTEXT NULL DEFAULT NULL ,
  `ar_url` VARCHAR(150) NOT NULL ,
  `ar_url_to_image` VARCHAR(350) NULL DEFAULT NULL ,
  `ar_source` VARCHAR(100) NULL DEFAULT NULL ,
  PRIMARY KEY (`ar_id`) ,
  INDEX `ar_so_id_idx` (`ar_source` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `newsroom`.`language`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newsroom`.`language` ;

CREATE  TABLE IF NOT EXISTS `newsroom`.`language` (
  `ln_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `ln_code` VARCHAR(5) NOT NULL ,
  `ln_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ln_id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `newsroom`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newsroom`.`role` ;

CREATE  TABLE IF NOT EXISTS `newsroom`.`role` (
  `ur_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `ur_name` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`ur_id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `newsroom`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newsroom`.`user` ;

CREATE  TABLE IF NOT EXISTS `newsroom`.`user` (
  `us_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `us_name` VARCHAR(45) NOT NULL ,
  `us_password` VARCHAR(45) NOT NULL ,
  `us_email` VARCHAR(45) NOT NULL ,
  `us_ur_id` INT(11) NOT NULL DEFAULT '1' ,
  `us_active` VARCHAR(2) NOT NULL DEFAULT 'Y' ,
  `us_ln_id` INT(11) NOT NULL ,
  PRIMARY KEY (`us_id`) ,
  UNIQUE INDEX `us_email_UNIQUE` (`us_email` ASC) ,
  INDEX `us_ln_id` (`us_ln_id` ASC) ,
  INDEX `us_ur_id` (`us_ur_id` ASC) ,
  CONSTRAINT `us_ln_id`
    FOREIGN KEY (`us_ln_id` )
    REFERENCES `newsroom`.`language` (`ln_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `us_ur_id`
    FOREIGN KEY (`us_ur_id` )
    REFERENCES `newsroom`.`role` (`ur_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `newsroom`.`user_article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newsroom`.`user_article` ;

CREATE  TABLE IF NOT EXISTS `newsroom`.`user_article` (
  `ua_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `ua_ar_id` INT(11) NULL DEFAULT NULL ,
  `ua_us_id` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ua_id`) ,
  INDEX `ua_us_id_idx` (`ua_us_id` ASC) ,
  INDEX `ua_ar_id_idx` (`ua_ar_id` ASC) ,
  CONSTRAINT `ua_ar_id`
    FOREIGN KEY (`ua_ar_id` )
    REFERENCES `newsroom`.`article` (`ar_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ua_us_id`
    FOREIGN KEY (`ua_us_id` )
    REFERENCES `newsroom`.`user` (`us_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
