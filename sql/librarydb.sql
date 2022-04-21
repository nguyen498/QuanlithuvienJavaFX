-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: librarydb
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(100) NOT NULL,
  `gender` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthdate` datetime NOT NULL,
  `accountType` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'Hà Trường Nguyên','123456','nguyen498','Nam','2001-05-20 00:00:00',0),(3,'admin','123','admin','Nam','2001-05-20 00:00:00',0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author_book`
--

DROP TABLE IF EXISTS `author_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author_book` (
  `authorID` int NOT NULL,
  `bookID` int NOT NULL,
  PRIMARY KEY (`authorID`,`bookID`),
  KEY `bookID` (`bookID`),
  CONSTRAINT `author_book_ibfk_1` FOREIGN KEY (`authorID`) REFERENCES `author` (`id`),
  CONSTRAINT `author_book_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author_book`
--

LOCK TABLES `author_book` WRITE;
/*!40000 ALTER TABLE `author_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `author_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` float NOT NULL DEFAULT '0',
  `dateOfPurcharse` datetime NOT NULL,
  `publicationPlace` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'ABC','ABC',200,'2022-05-04 00:00:00','HCM',0),(5,'Dac nhan tam','Dac nhan tam',200,'2022-05-04 00:00:00','HCM',1),(6,'Sach xin','Đây Là Quyển Sách',50000,'2021-02-23 00:00:00','HN',1),(7,'Sách Không Xịn','Đây là sách không xịn',2000,'2111-11-01 00:00:00','HN',1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'ngu ngon');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_book`
--

DROP TABLE IF EXISTS `category_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_book` (
  `categoryID` int NOT NULL,
  `bookID` int NOT NULL,
  PRIMARY KEY (`categoryID`,`bookID`),
  KEY `bookID` (`bookID`),
  CONSTRAINT `category_book_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `category` (`id`),
  CONSTRAINT `category_book_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_book`
--

LOCK TABLES `category_book` WRITE;
/*!40000 ALTER TABLE `category_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lending_detail`
--

DROP TABLE IF EXISTS `lending_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lending_detail` (
  `dueDate` datetime NOT NULL,
  `amount` float NOT NULL DEFAULT '0',
  `bookID` int NOT NULL,
  `lendingID` int NOT NULL,
  PRIMARY KEY (`bookID`,`lendingID`),
  KEY `lendingID` (`lendingID`),
  CONSTRAINT `lending_detail_ibfk_1` FOREIGN KEY (`bookID`) REFERENCES `book` (`id`),
  CONSTRAINT `lending_detail_ibfk_2` FOREIGN KEY (`lendingID`) REFERENCES `lendingticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lending_detail`
--

LOCK TABLES `lending_detail` WRITE;
/*!40000 ALTER TABLE `lending_detail` DISABLE KEYS */;
INSERT INTO `lending_detail` VALUES ('2022-05-21 00:00:00',200,5,20),('2022-05-21 00:00:00',50000,6,20),('2022-05-21 00:00:00',2000,7,19),('2022-05-21 00:00:00',2000,7,20);
/*!40000 ALTER TABLE `lending_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lendingticket`
--

DROP TABLE IF EXISTS `lendingticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lendingticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dateLending` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `totalBookLended` int NOT NULL DEFAULT '0',
  `accountID` int NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `accountID` (`accountID`),
  CONSTRAINT `lendingticket_ibfk_1` FOREIGN KEY (`accountID`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lendingticket`
--

LOCK TABLES `lendingticket` WRITE;
/*!40000 ALTER TABLE `lendingticket` DISABLE KEYS */;
INSERT INTO `lendingticket` VALUES (19,'0026-10-13 00:00:00',1,1,1),(20,'0026-10-13 00:00:00',3,3,1);
/*!40000 ALTER TABLE `lendingticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `librarycard`
--

DROP TABLE IF EXISTS `librarycard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `librarycard` (
  `cardNumber` int NOT NULL AUTO_INCREMENT,
  `issuedAt` datetime NOT NULL,
  `active` int NOT NULL DEFAULT '0',
  `account_id` int DEFAULT NULL,
  PRIMARY KEY (`cardNumber`),
  UNIQUE KEY `account_id` (`account_id`),
  CONSTRAINT `librarycard_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `librarycard`
--

LOCK TABLES `librarycard` WRITE;
/*!40000 ALTER TABLE `librarycard` DISABLE KEYS */;
/*!40000 ALTER TABLE `librarycard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `total` float NOT NULL DEFAULT '0',
  `fine` float NOT NULL DEFAULT '0',
  `createdDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `accountID` int NOT NULL,
  `lendingID` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `accountID` (`accountID`),
  KEY `lendingID` (`lendingID`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`accountID`) REFERENCES `account` (`id`),
  CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`lendingID`) REFERENCES `lendingticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_detail`
--

DROP TABLE IF EXISTS `reservation_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_detail` (
  `dueDate` datetime NOT NULL,
  `bookID` int NOT NULL,
  `reservationID` int NOT NULL,
  PRIMARY KEY (`bookID`,`reservationID`),
  KEY `reservationID` (`reservationID`),
  CONSTRAINT `reservation_detail_ibfk_1` FOREIGN KEY (`bookID`) REFERENCES `book` (`id`),
  CONSTRAINT `reservation_detail_ibfk_2` FOREIGN KEY (`reservationID`) REFERENCES `reservationticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_detail`
--

LOCK TABLES `reservation_detail` WRITE;
/*!40000 ALTER TABLE `reservation_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservationticket`
--

DROP TABLE IF EXISTS `reservationticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservationticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int NOT NULL DEFAULT '0',
  `totalBookReserved` int NOT NULL DEFAULT '0',
  `accountID` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `accountID` (`accountID`),
  CONSTRAINT `reservationticket_ibfk_1` FOREIGN KEY (`accountID`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservationticket`
--

LOCK TABLES `reservationticket` WRITE;
/*!40000 ALTER TABLE `reservationticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservationticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-21 17:21:34
