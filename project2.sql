DROP DATABASE IF EXISTS `project2`;
CREATE DATABASE  IF NOT EXISTS `project2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `project2`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: project2
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `tblfriend`
--

DROP TABLE IF EXISTS `tblfriend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblfriend` (
  `user_name_1` varchar(100) NOT NULL,
  `user_name_2` varchar(100) NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_name_1`,`user_name_2`),
  KEY `user_name_2` (`user_name_2`),
  CONSTRAINT `tblfriend_ibfk_1` FOREIGN KEY (`user_name_2`) REFERENCES `tbluser` (`user_name`),
  CONSTRAINT `tblfriend_ibfk_2` FOREIGN KEY (`user_name_1`) REFERENCES `tbluser` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblfriend`
--

LOCK TABLES `tblfriend` WRITE;
/*!40000 ALTER TABLE `tblfriend` DISABLE KEYS */;
INSERT INTO `tblfriend` VALUES ('cuong','duc',0),('cuong','hieu',1),('duc','hieu',0),('phuong','cuong',0),('phuong','duc',0);
/*!40000 ALTER TABLE `tblfriend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblgroup`
--

DROP TABLE IF EXISTS `tblgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblgroup` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  KEY `user_name` (`user_name`),
  CONSTRAINT `tblgroup_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `tbluser` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblgroup`
--

LOCK TABLES `tblgroup` WRITE;
/*!40000 ALTER TABLE `tblgroup` DISABLE KEYS */;
INSERT INTO `tblgroup` VALUES (2,'Group test 123','phuong'),(3,'Group test 123','phuong');
/*!40000 ALTER TABLE `tblgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblicon`
--

DROP TABLE IF EXISTS `tblicon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblicon` (
  `icon_text` varchar(20) NOT NULL,
  `icon_image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`icon_text`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblicon`
--

LOCK TABLES `tblicon` WRITE;
/*!40000 ALTER TABLE `tblicon` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblicon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbluser`
--

DROP TABLE IF EXISTS `tbluser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbluser` (
  `user_name` varchar(100) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `pass_word` varchar(100) DEFAULT NULL,
  `avartar` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbluser`
--

LOCK TABLES `tbluser` WRITE;
/*!40000 ALTER TABLE `tbluser` DISABLE KEYS */;
INSERT INTO `tbluser` VALUES 
('cuong', 'Hùng Cường', '1234', 'cuong.jpg'),
('duc', 'Đình Đức', '1234', 'duc.jpg'),
('hieu', 'Trung Hiếu', '1234', 'hieu.jpg'),
('phuong', 'Tri Phương', '1234', 'phuong.jpg');
/*!40000 ALTER TABLE `tbluser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbluser_group`
--

DROP TABLE IF EXISTS `tbluser_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbluser_group` (
  `group_id` int(11) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `date_time` datetime DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`group_id`,`user_name`),
  KEY `user_name` (`user_name`),
  CONSTRAINT `tbluser_group_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `tbluser` (`user_name`),
  CONSTRAINT `tbluser_group_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `tblgroup` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbluser_group`
--

LOCK TABLES `tbluser_group` WRITE;
/*!40000 ALTER TABLE `tbluser_group` DISABLE KEYS */;
INSERT INTO `tbluser_group` VALUES (3,'phuong','2017-11-26 16:17:07','HI!!!');
/*!40000 ALTER TABLE `tbluser_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbluser_user`
--

DROP TABLE IF EXISTS `tbluser_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbluser_user` (
  `user_name_1` varchar(100) NOT NULL,
  `user_name_2` varchar(100) NOT NULL,
  `date_time` datetime NOT NULL,
  `content` text,
  `status` tinyint(4) DEFAULT NULL,
  `sas` text,
  PRIMARY KEY (`user_name_1`,`user_name_2`,`date_time`),
  KEY `user_name_2` (`user_name_2`),
  CONSTRAINT `tbluser_user_ibfk_1` FOREIGN KEY (`user_name_1`) REFERENCES `tbluser` (`user_name`),
  CONSTRAINT `tbluser_user_ibfk_2` FOREIGN KEY (`user_name_2`) REFERENCES `tbluser` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbluser_user`
--

LOCK TABLES `tbluser_user` WRITE;
/*!40000 ALTER TABLE `tbluser_user` DISABLE KEYS */;
INSERT INTO `tbluser_user` VALUES ('cuong','hieu','2017-12-12 08:01:00','Hello',1,NULL),('cuong','phuong','2017-11-25 21:32:38','Hello !!!',0,NULL),('hieu','cuong','2017-12-12 08:01:30','Hi',1,NULL),('Phương','Cường','2017-12-10 11:49:57','hello đm các bạn',0,NULL),('phuong','hieu','2017-11-26 16:35:59','ajbsbiui',1,NULL),('phuong','hieu','2017-12-02 17:26:21','hellojabsfjbjb',1,NULL),('phuong','hieu','2017-12-03 15:51:44','hellojabsfjbjb',1,NULL),('phuong','hieu','2017-12-10 16:01:40','abc xyz 0',1,NULL),('phuong','hieu','2017-12-10 16:02:12','abc xyz 1',1,NULL),('phuong','hieu','2017-12-10 16:02:22','abc xyz 2',1,NULL),('phuong','hieu','2017-12-10 16:02:29','abc xyz 2',1,NULL),('phuong','hieu','2017-12-10 16:02:33','abc xyz 2',1,NULL),('phuong','hieu','2017-12-10 16:02:37','abc xyz 2',1,NULL),('phuong','hieu','2017-12-10 16:58:39','đm hiếu chó',1,'string sas');
/*!40000 ALTER TABLE `tbluser_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-10 17:39:01
