-- MySQL dump 10.13  Distrib 5.5.30, for Win32 (x86)
--
-- Host: localhost    Database: crm
-- ------------------------------------------------------
-- Server version	5.5.30

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
-- Table structure for table `classinfo`
--

DROP TABLE IF EXISTS `classinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `inputTime` date DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classinfo`
--

LOCK TABLES `classinfo` WRITE;
/*!40000 ALTER TABLE `classinfo` DISABLE KEYS */;
INSERT INTO `classinfo` VALUES (1,'java14','2017-08-09',1,1),(2,'h51','2017-08-22',0,1),(3,'mac','2017-08-09',0,1),(4,'mac1','2017-08-22',1,1),(5,'1','2017-08-21',0,1),(6,'2','2017-08-01',1,2),(7,'3','2017-08-22',1,2),(8,'4','2017-08-22',1,1),(9,'5','2017-08-16',1,2),(10,'6','2017-08-16',1,1),(11,'7','2017-08-09',1,2);
/*!40000 ALTER TABLE `classinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `manager_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'yanfa','研发部',1,4,1),(2,'renshi','人事部',27,4,0),(3,'xiaoshou','销售部',NULL,4,1),(4,'zongjing','总经办',NULL,NULL,1),(8,'dongshihui','董事会',28,NULL,1);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `realname` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  `inputtime` date DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'will','龙17','666','13713713711','will@',1,'2017-08-20',1,0),(26,'33','33','22','22','22',1,'2017-08-21',0,0),(28,'11','11','11','11','11',1,'2017-08-21',0,0),(29,'33','33','33','33','33',2,'2017-08-21',0,0),(32,'1','1','1111','1','1',2,'2017-08-23',1,1),(33,'2','2','2','2','2',2,'2017-08-23',1,0),(34,'neld','neld','f1fc61a739cbf3656a106786603adc4c','123','123',1,'2017-08-24',1,1),(35,'w','will','a2835e8f7c0d8a4280c8959582440054','123','123',2,'2017-08-24',1,0);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_role`
--

DROP TABLE IF EXISTS `employee_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_role` (
  `employee_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_role`
--

LOCK TABLES `employee_role` WRITE;
/*!40000 ALTER TABLE `employee_role` DISABLE KEYS */;
INSERT INTO `employee_role` VALUES (1,24),(1,9),(34,24),(34,25),(35,9);
/*!40000 ALTER TABLE `employee_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_test`
--

DROP TABLE IF EXISTS `employee_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_test`
--

LOCK TABLES `employee_test` WRITE;
/*!40000 ALTER TABLE `employee_test` DISABLE KEYS */;
INSERT INTO `employee_test` VALUES (1,'will'),(2,'neld');
/*!40000 ALTER TABLE `employee_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expend`
--

DROP TABLE IF EXISTS `expend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `payTime` date DEFAULT NULL,
  `item` varchar(50) DEFAULT NULL,
  `bills` varchar(50) DEFAULT NULL,
  `amount` decimal(10,0) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `auditorTime` date DEFAULT NULL,
  `status` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expend`
--

LOCK TABLES `expend` WRITE;
/*!40000 ALTER TABLE `expend` DISABLE KEYS */;
/*!40000 ALTER TABLE `expend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(30) DEFAULT NULL,
  `url` varchar(30) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'员工管理','/employee.do',4,7),(2,'部门管理','/department.do',4,11),(3,'角色管理','/role.do',4,NULL),(4,'系统管理',NULL,NULL,NULL),(5,'客户服务(售前)',NULL,NULL,NULL),(7,'学员管理(售后)',NULL,NULL,NULL),(8,'教务管理',NULL,NULL,NULL),(9,'日常办公',NULL,NULL,NULL),(10,'公共信息',NULL,NULL,NULL),(11,'日程安排',NULL,10,NULL),(12,'企业文化',NULL,10,NULL),(13,'重大消息',NULL,10,NULL),(14,'潜在学员管理',NULL,5,NULL),(15,'移交历史',NULL,5,NULL),(16,'潜在客户池',NULL,5,NULL),(17,'考试管理',NULL,5,NULL),(18,'正式学员管理',NULL,7,NULL),(19,'学员升班留级',NULL,7,NULL),(20,'收款管理',NULL,7,NULL),(21,'学员流失',NULL,7,NULL),(22,'支出管理',NULL,7,NULL),(23,'课程安排管理',NULL,7,NULL),(24,'班级管理',NULL,8,NULL),(25,'课程表',NULL,8,NULL),(26,'教室管理',NULL,8,NULL),(27,'工资管理',NULL,9,NULL),(28,'考勤管理',NULL,9,NULL),(29,'权限管理',NULL,4,NULL),(30,'菜单管理',NULL,4,NULL);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `resource` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (4,'员工保存','employee:save'),(5,'员工修改','employee:update'),(6,'员工离职','employee:bye'),(7,'员工主页','employee:view'),(8,'员工全查','employee:selectAll'),(9,'员工列表','employee:selectPage'),(10,'员工角色','employee:getRolesByEmpId'),(11,'部门主页','department:view');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `potenclient`
--

DROP TABLE IF EXISTS `potenclient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `potenclient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `potenstudent_id` bigint(20) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `putTime` date DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `potenclient`
--

LOCK TABLES `potenclient` WRITE;
/*!40000 ALTER TABLE `potenclient` DISABLE KEYS */;
/*!40000 ALTER TABLE `potenclient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `potenstudent`
--

DROP TABLE IF EXISTS `potenstudent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `potenstudent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `qq` varchar(15) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `lastTime` date DEFAULT NULL,
  `nextTime` date DEFAULT NULL,
  `source` bigint(20) DEFAULT NULL,
  `likeLevek` bigint(20) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `status` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `potenstudent`
--

LOCK TABLES `potenstudent` WRITE;
/*!40000 ALTER TABLE `potenstudent` DISABLE KEYS */;
/*!40000 ALTER TABLE `potenstudent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) DEFAULT NULL,
  `receiptTime` date DEFAULT NULL,
  `receiptAmount` decimal(10,0) DEFAULT NULL,
  `bills` varchar(50) DEFAULT NULL,
  `receipt` varchar(50) DEFAULT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (5,'1','1'),(6,'role','角色管理'),(7,'boss','大佬'),(9,'test','测试'),(24,'emp','员工管理'),(25,'admin','超管');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (24,8),(24,7),(24,6),(24,5),(24,4),(24,9),(24,10),(7,7);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `tuition` decimal(10,0) DEFAULT NULL,
  `feesStatus` int(20) DEFAULT NULL,
  `entranceTime` date DEFAULT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentloss`
--

DROP TABLE IF EXISTS `studentloss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentloss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) DEFAULT NULL,
  `lostTime` date DEFAULT NULL,
  `cause` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentloss`
--

LOCK TABLES `studentloss` WRITE;
/*!40000 ALTER TABLE `studentloss` DISABLE KEYS */;
/*!40000 ALTER TABLE `studentloss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemlog`
--

DROP TABLE IF EXISTS `systemlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `opuser_id` bigint(20) DEFAULT NULL,
  `optime` datetime DEFAULT NULL,
  `opip` varchar(50) DEFAULT NULL,
  `function` varchar(500) DEFAULT NULL,
  `params` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemlog`
--

LOCK TABLES `systemlog` WRITE;
/*!40000 ALTER TABLE `systemlog` DISABLE KEYS */;
INSERT INTO `systemlog` VALUES (75,34,'2017-08-25 15:28:22','0:0:0:0:0:0:0:1','DepartmentServiceImpl:selectAll','[]'),(76,34,'2017-08-25 15:28:22','0:0:0:0:0:0:0:1','RoleServiceImpl:selectAll','[]'),(77,34,'2017-08-25 15:28:22','0:0:0:0:0:0:0:1','EmployeeServiceImpl:selectPage','[{\"page\":1,\"rows\":10,\"id\":null,\"beginIndex\":0}]'),(78,34,'2017-08-25 15:28:24','0:0:0:0:0:0:0:1','EmployeeServiceImpl:selectAll','[]'),(79,34,'2017-08-25 15:28:24','0:0:0:0:0:0:0:1','DepartmentServiceImpl:selectAll','[]'),(80,34,'2017-08-25 15:28:24','0:0:0:0:0:0:0:1','DepartmentServiceImpl:selectPage','[{\"page\":1,\"rows\":10,\"beginIndex\":0}]'),(81,34,'2017-08-25 15:29:38','0:0:0:0:0:0:0:1','DepartmentServiceImpl:selectAll','[]'),(82,34,'2017-08-25 15:29:38','0:0:0:0:0:0:0:1','EmployeeServiceImpl:selectAll','[]'),(83,34,'2017-08-25 15:29:38','0:0:0:0:0:0:0:1','DepartmentServiceImpl:selectPage','[{\"page\":1,\"rows\":10,\"beginIndex\":0}]'),(84,34,'2017-08-25 15:35:51','0:0:0:0:0:0:0:1','EmployeeServiceImpl:selectAll','[]'),(85,34,'2017-08-25 15:35:51','0:0:0:0:0:0:0:1','DepartmentServiceImpl:selectAll','[]'),(86,34,'2017-08-25 15:35:51','0:0:0:0:0:0:0:1','DepartmentServiceImpl:selectPage','[{\"page\":1,\"rows\":10,\"beginIndex\":0}]'),(87,NULL,'2017-08-25 17:16:06',NULL,'EmployeeServiceImpl:getEmployeeByUsername','[\"neld\"]'),(88,34,'2017-08-25 17:16:08','127.0.0.1','MenuServiceImpl:getRootMenu','[]'),(89,NULL,'2017-08-29 15:47:40',NULL,'EmployeeServiceImpl:getEmployeeByUsername','[\"root\"]'),(90,NULL,'2017-08-29 15:47:46',NULL,'EmployeeServiceImpl:getEmployeeByUsername','[\"will\"]'),(91,NULL,'2017-08-29 15:47:50','0:0:0:0:0:0:0:1','EmployeeServiceImpl:getEmployeeByUsername','[\"will\"]'),(92,NULL,'2017-08-29 15:48:28',NULL,'EmployeeServiceImpl:getEmployeeByUsername','[\"will\"]'),(93,NULL,'2017-08-29 15:48:34',NULL,'EmployeeServiceImpl:getEmployeeByUsername','[\"neld\"]'),(94,NULL,'2017-08-29 15:48:37',NULL,'EmployeeServiceImpl:getEmployeeByUsername','[\"neld\"]'),(95,34,'2017-08-29 15:48:38','0:0:0:0:0:0:0:1','MenuServiceImpl:getRootMenu','[]');
/*!40000 ALTER TABLE `systemlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `potenstudent_id` bigint(20) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  `testTime` date DEFAULT NULL,
  `testResult` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferhistory`
--

DROP TABLE IF EXISTS `transferhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transferhistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `potenstudent_id` bigint(20) DEFAULT NULL,
  `oldEmployee_id` bigint(20) DEFAULT NULL,
  `transTime` date DEFAULT NULL,
  `cause` varchar(50) DEFAULT NULL,
  `newEmployee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferhistory`
--

LOCK TABLES `transferhistory` WRITE;
/*!40000 ALTER TABLE `transferhistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `transferhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upgrade`
--

DROP TABLE IF EXISTS `upgrade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upgrade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `upgradeTime` date DEFAULT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  `status` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upgrade`
--

LOCK TABLES `upgrade` WRITE;
/*!40000 ALTER TABLE `upgrade` DISABLE KEYS */;
/*!40000 ALTER TABLE `upgrade` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-31 15:27:50
