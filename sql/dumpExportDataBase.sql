-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: computers
-- ------------------------------------------------------
-- Server version	5.7.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `idEmployee` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `post` varchar(45) DEFAULT NULL,
  `liability` tinyint(1) DEFAULT NULL,
  `number_phone` int(12) DEFAULT NULL,
  `technique_idTechnique` int(11) NOT NULL,
  `enterprise_idEnterprise` int(11) NOT NULL,
  PRIMARY KEY (`idEmployee`),
  KEY `fk_employee_ technique1_idx` (`technique_idTechnique`),
  KEY `fk_employee_enterprise1_idx` (`enterprise_idEnterprise`),
  CONSTRAINT `fk_employee_ technique1` FOREIGN KEY (`technique_idTechnique`) REFERENCES `technique` (`idTechnique`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_enterprise1` FOREIGN KEY (`enterprise_idEnterprise`) REFERENCES `enterprise` (`idEnterprise`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Давид','Глібович','Товстошкірих','оперератор',1,809654654,6,1),(2,'Єлизавета','Елізаровна','Грибкова','адміністратор',1,809543534,1,1),(3,'Аза','Станіславівна','Кайназарова','адміністратор',1,809453453,7,2),(4,'Злата','Юліївна','Яблочкина','кассир',1,80954633,2,2),(5,'Стела','Юліївна','Камалова','кассир',1,809674754,9,2),(6,'Еміль','Єрофійович','Пережогин','системний адмінстратор',1,809654654,5,1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprise`
--

DROP TABLE IF EXISTS `enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enterprise` (
  `idEnterprise` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `name_director` varchar(45) DEFAULT NULL,
  `middle_name_director` varchar(45) DEFAULT NULL,
  `last_name_director` varchar(45) DEFAULT NULL,
  `name_accountant` varchar(45) DEFAULT NULL,
  `middle_name_accountant` varchar(45) DEFAULT NULL,
  `last_name_accountant` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEnterprise`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprise`
--

LOCK TABLES `enterprise` WRITE;
/*!40000 ALTER TABLE `enterprise` DISABLE KEYS */;
INSERT INTO `enterprise` VALUES (1,'Велес','Львів','вул. Трулейбусна3','Нікітевіч','Арнаутов','Ізмаїл','Афанасіївна','Еліашева','Анна'),(2,'Велес','Івано-Франківськ','вул. Вовчинецька','Дуболазова','Наталя','Борисівна','Дуров','Наум','Рофійович');
/*!40000 ALTER TABLE `enterprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technique`
--

DROP TABLE IF EXISTS `technique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technique` (
  `idTechnique` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `name_tech` varchar(45) DEFAULT NULL,
  `inventory_numbers` int(15) DEFAULT NULL,
  `start_cost` decimal(10,2) DEFAULT NULL,
  `actual_cost` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idTechnique`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technique`
--

LOCK TABLES `technique` WRITE;
/*!40000 ALTER TABLE `technique` DISABLE KEYS */;
INSERT INTO `technique` VALUES (1,'ПК','Lenovo IdeaCentre H535',532532423,6799.00,5000.00),(2,'ПК','ARTLINE Gaming X55 v01',645643537,20249.00,19000.00),(5,'ПК','Apple Mac Pro A1481',654544645,103999.00,100000.00),(6,'Ноутбук','HP Notebook 15-ac162ur',988967865,8999.00,5000.00),(7,'Ноутбук','Lenovo G70-80',423423432,3850.00,2867.00),(9,'Ноутбук','HP LaserJet M125a',654745754,4320.00,3000.00),(10,'Прінтер','Samsung SCX-4650N',765756765,3850.00,3000.00);
/*!40000 ALTER TABLE `technique` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-13  4:03:16
