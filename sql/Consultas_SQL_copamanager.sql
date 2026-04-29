-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema copamanagerbd
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema copamanagerbd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `copamanagerbd` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `copamanagerbd` ;

-- -----------------------------------------------------
-- Table `copamanagerbd`.`campeonatos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `copamanagerbd`.`campeonatos` (
  `idCampeonato` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `anio` INT NOT NULL,
  `estado` VARCHAR(50) NOT NULL,
  `puntosVictoria` INT NOT NULL DEFAULT '3',
  `puntosEmpate` INT NOT NULL DEFAULT '1',
  `puntosDerrota` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`idCampeonato`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `copamanagerbd`.`entrenadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `copamanagerbd`.`entrenadores` (
  `idEntrenador` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `edad` INT NOT NULL,
  `nacionalidad` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idEntrenador`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `copamanagerbd`.`equipos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `copamanagerbd`.`equipos` (
  `idEquipo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `pais` VARCHAR(50) NOT NULL,
  `idEntrenador` INT NULL DEFAULT NULL,
  `idCampeonato` INT NOT NULL,
  PRIMARY KEY (`idEquipo`),
  INDEX `idEntrenador` (`idEntrenador` ASC) VISIBLE,
  INDEX `fk_equipos_campeonatos` (`idCampeonato` ASC) VISIBLE,
  CONSTRAINT `equipos_ibfk_1`
    FOREIGN KEY (`idEntrenador`)
    REFERENCES `copamanagerbd`.`entrenadores` (`idEntrenador`),
  CONSTRAINT `fk_equipos_campeonatos`
    FOREIGN KEY (`idCampeonato`)
    REFERENCES `copamanagerbd`.`campeonatos` (`idCampeonato`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `copamanagerbd`.`partidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `copamanagerbd`.`partidos` (
  `idPartido` INT NOT NULL AUTO_INCREMENT,
  `idEquipoLocal` INT NOT NULL,
  `idEquipoVisitante` INT NOT NULL,
  `fechaHora` DATETIME NOT NULL,
  `golesLocal` INT NULL DEFAULT '0',
  `golesVisitante` INT NULL DEFAULT '0',
  `estado` VARCHAR(50) NOT NULL,
  `idCampeonato` INT NOT NULL,
  PRIMARY KEY (`idPartido`),
  INDEX `idEquipoLocal` (`idEquipoLocal` ASC) VISIBLE,
  INDEX `idEquipoVisitante` (`idEquipoVisitante` ASC) VISIBLE,
  INDEX `fk_partidos_campeonatos` (`idCampeonato` ASC) VISIBLE,
  CONSTRAINT `fk_partidos_campeonatos`
    FOREIGN KEY (`idCampeonato`)
    REFERENCES `copamanagerbd`.`campeonatos` (`idCampeonato`),
  CONSTRAINT `partidos_ibfk_1`
    FOREIGN KEY (`idEquipoLocal`)
    REFERENCES `copamanagerbd`.`equipos` (`idEquipo`),
  CONSTRAINT `partidos_ibfk_2`
    FOREIGN KEY (`idEquipoVisitante`)
    REFERENCES `copamanagerbd`.`equipos` (`idEquipo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `copamanagerbd`.`registro_posicion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `copamanagerbd`.`registro_posicion` (
  `idRegistroPosicion` INT NOT NULL AUTO_INCREMENT,
  `idEquipo` INT NOT NULL,
  `puntos` INT NOT NULL DEFAULT '0',
  `jugados` INT NOT NULL DEFAULT '0',
  `ganados` INT NOT NULL DEFAULT '0',
  `empatados` INT NOT NULL DEFAULT '0',
  `perdidos` INT NOT NULL DEFAULT '0',
  `golesAFavor` INT NOT NULL DEFAULT '0',
  `golesEnContra` INT NOT NULL DEFAULT '0',
  `diferenciaDeGol` INT NOT NULL DEFAULT '0',
  `idCampeonato` INT NOT NULL,
  PRIMARY KEY (`idRegistroPosicion`),
  UNIQUE INDEX `idEquipo` (`idEquipo` ASC) VISIBLE,
  UNIQUE INDEX `idEquipo_2` (`idEquipo` ASC, `idCampeonato` ASC) VISIBLE,
  INDEX `fk_registro_posicion_campeonatos` (`idCampeonato` ASC) VISIBLE,
  CONSTRAINT `fk_registro_posicion_campeonatos`
    FOREIGN KEY (`idCampeonato`)
    REFERENCES `copamanagerbd`.`campeonatos` (`idCampeonato`),
  CONSTRAINT `registro_posicion_ibfk_1`
    FOREIGN KEY (`idEquipo`)
    REFERENCES `copamanagerbd`.`equipos` (`idEquipo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
