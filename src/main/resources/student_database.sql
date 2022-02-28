ALTER USER 'root'@'localhost' IDENTIFIED BY 'Winthegame';
create database student;
use student;

CREATE USER 'systemuser'@'%' IDENTIFIED BY 'Winthegame';
CREATE USER 'systemuser'@'localhost' identified by 'Winthegame';
GRANT ALL PRIVILEGES ON student. * TO 'systemuser'@'localhost';
GRANT ALL PRIVILEGES ON student. * TO 'systemuser'@'%';



DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `student_id` varchar(50) NOT NULL,
  `phone_no` varchar(50),
  `father_name` varchar(50),
  `group_name` varchar(20),
  `status` varchar(20) NOT NULL,
  `email` varchar(100),
  `dob` varchar(20) NOT NULL,
  `created_on` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(500),
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_id_unique` (`student_id`),
  UNIQUE KEY `phone_no_unique` (`phone_no`),
  UNIQUE KEY `email_unique` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `student_fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_fee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(50) NOT NULL,
  `amount` int default 0,
  `fee_type` varchar(200),
  `status` varchar(20) NOT NULL,
  `created_on` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `yr` int,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `student_cal_due`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_cal_due` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(50) NOT NULL,
  `due_amount` int default 0,
  `status` varchar(20) NOT NULL,
  `created_on` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_ref_id_unique` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

