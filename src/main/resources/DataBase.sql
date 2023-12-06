CREATE DATABASE  IF NOT EXISTS `game` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `game`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: game
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `传感器数据`
--

DROP TABLE IF EXISTS `传感器数据`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `传感器数据` (
  `列数` varchar(45) NOT NULL,
  `车辆id` varchar(45) DEFAULT NULL,
  `运行轮数` varchar(45) DEFAULT NULL,
  `setting1` varchar(45) DEFAULT NULL,
  `setting2` varchar(45) DEFAULT NULL,
  `setting3` varchar(45) DEFAULT NULL,
  `风扇进口总温度` varchar(45) DEFAULT NULL,
  `低压压气机出口总温度` varchar(100) DEFAULT NULL,
  `高压压气机出口总温度` varchar(100) DEFAULT NULL,
  `低压涡轮出口总温度` varchar(45) DEFAULT NULL,
  `风扇进口压强` varchar(45) DEFAULT NULL,
  `旁路管道总压强` varchar(45) DEFAULT NULL,
  `高压压气机出口总压强` varchar(100) DEFAULT NULL,
  `风扇物理转速` varchar(45) DEFAULT NULL,
  `核心机物理转速` varchar(45) DEFAULT NULL,
  `发动机压力比` varchar(45) DEFAULT NULL,
  `高压压气机出口静压(Ps30)` varchar(45) DEFAULT NULL,
  `燃油流量与高压压气机出口静压的比率` varchar(45) DEFAULT NULL,
  `风扇换算转速` varchar(45) DEFAULT NULL,
  `核心机换算转速` varchar(45) DEFAULT NULL,
  `涵道比` varchar(45) DEFAULT NULL,
  `燃烧器油气比` varchar(45) DEFAULT NULL,
  `抽汽焓` varchar(45) DEFAULT NULL,
  `需求风扇转速` varchar(45) DEFAULT NULL,
  `需求风扇换算转速` varchar(45) DEFAULT NULL,
  `高压涡轮冷气流量` varchar(45) DEFAULT NULL,
  `低压涡轮冷气流量` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `传感器数据`
--

LOCK TABLES `传感器数据` WRITE;
/*!40000 ALTER TABLE `传感器数据` DISABLE KEYS */;
INSERT INTO `传感器数据` VALUES ('0','1','1','0.001','0.0001','100','518.67','642.48','1592.32','1397.77','14.62','21.61','554.34','2388.02','9059.13','1.3','47.36','522.32','2388.03','8132.32','8.3974','0.03','392','2388','100','39.1','23.3774'),('1','1','2','0.0019','-0.0003','100','518.67','642.15','1591.82','1403.14','14.62','21.61','553.75','2388.04','9044.07','1.3','47.49','522.28','2388.07','8131.49','8.4318','0.03','392','2388','100','39','23.4236'),('2','1','3','-0.0043','0.0003','100','518.67','642.35','1587.99','1404.2','14.62','21.61','554.26','2388.08','9052.94','1.3','47.27','522.42','2388.03','8133.23','8.4178','0.03','390','2388','100','38.95','23.3442'),('3','1','4','0.0007','0','100','518.67','642.35','1582.79','1401.87','14.62','21.61','554.45','2388.11','9049.48','1.3','47.13','522.86','2388.08','8133.83','8.3682','0.03','392','2388','100','38.88','23.3739'),('4','1','5','-0.0019','-0.0002','100','518.67','642.37','1582.85','1406.22','14.62','21.61','554','2388.06','9055.15','1.3','47.28','522.19','2388.04','8133.8','8.4294','0.03','393','2388','100','38.9','23.4044'),('5','1','6','-0.0043','-0.0001','100','518.67','642.1','1584.47','1398.37','14.62','21.61','554.67','2388.02','9049.68','1.3','47.16','521.68','2388.03','8132.85','8.4108','0.03','391','2388','100','38.98','23.3669'),('7','1','8','-0.0034','0.0003','100','518.67','642.56','1582.96','1400.97','14.62','21.61','553.85','2388','9040.8','1.3','47.24','522.47','2388.03','8131.07','8.4076','0.03','391','2388','100','38.97','23.3106');
/*!40000 ALTER TABLE `传感器数据` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `用户`
--

DROP TABLE IF EXISTS `用户`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `用户` (
  `用户id` int NOT NULL AUTO_INCREMENT,
  `用户名` varchar(100) NOT NULL,
  `密码` varchar(100) NOT NULL,
  `邮箱` varchar(100) NOT NULL,
  PRIMARY KEY (`用户id`)
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `用户`
--

LOCK TABLES `用户` WRITE;
/*!40000 ALTER TABLE `用户` DISABLE KEYS */;
INSERT INTO `用户` VALUES (1001,'jiulipuv','1234',''),(1002,'cyc','1263','1234'),(1003,'zzj','1233',''),(1004,'123','123','2693394068@qq.com'),(1005,'1233','1231','2693394068@qq.com'),(1006,'329320','123','2693394068@qq.com'),(1007,'18856991195','123','2693394068@qq.com'),(1008,'3293203','123','2693394068@qq.com');
/*!40000 ALTER TABLE `用户` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `车辆状态`
--

DROP TABLE IF EXISTS `车辆状态`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `车辆状态` (
  `车辆id` varchar(50) NOT NULL,
  `维护状态` varchar(50) NOT NULL,
  `剩余维护时间（分钟）` int NOT NULL,
  `故障状态` varchar(50) NOT NULL,
  `上次维护时间` varchar(45) NOT NULL,
  `维护次数` varchar(45) NOT NULL,
  `数据文件` mediumblob,
  PRIMARY KEY (`车辆id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `车辆状态`
--

LOCK TABLES `车辆状态` WRITE;
/*!40000 ALTER TABLE `车辆状态` DISABLE KEYS */;
INSERT INTO `车辆状态` VALUES ('1','无',10,'无','','99',NULL),('2','无',14,'无','','',_binary '767\r\n'),('3','ww',3,'ww','ww','1',''),('niji','w',11,'w','2022-3-3','4',NULL),('粤A-37632','正在维护',33,'无','2022-3-3','1',NULL);
/*!40000 ALTER TABLE `车辆状态` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-05 21:34:37
