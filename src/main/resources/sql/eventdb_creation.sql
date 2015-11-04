-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: eventsdb
-- ------------------------------------------------------
-- Server version	5.6.25-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id_admin` int(10) unsigned NOT NULL,
  `owned_rso` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE KEY `owned_rso_UNIQUE` (`owned_rso`),
  KEY `admin_user_fkey_idx` (`id_admin`),
  CONSTRAINT `admin_user_fkey` FOREIGN KEY (`id_admin`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `owned_rso_fkey` FOREIGN KEY (`owned_rso`) REFERENCES `rso` (`id_rso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `approved_events`
--

DROP TABLE IF EXISTS `approved_events`;
/*!50001 DROP VIEW IF EXISTS `approved_events`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `approved_events` AS SELECT 
 1 AS `id_event`,
 1 AS `date`,
 1 AS `time`,
 1 AS `location`,
 1 AS `description`,
 1 AS `admin_approved`,
 1 AS `sadmin_approved`,
 1 AS `type`,
 1 AS `host_rso`,
 1 AS `name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `average_event_ratings`
--

DROP TABLE IF EXISTS `average_event_ratings`;
/*!50001 DROP VIEW IF EXISTS `average_event_ratings`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `average_event_ratings` AS SELECT 
 1 AS `id_event`,
 1 AS `name`,
 1 AS `average_rating`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id_comment` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `commenter` int(10) unsigned NOT NULL DEFAULT '0',
  `event` int(10) unsigned NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content` text NOT NULL,
  PRIMARY KEY (`commenter`,`event`,`timestamp`),
  UNIQUE KEY `id_comment_UNIQUE` (`id_comment`),
  KEY `event_commented_fkey_idx` (`event`),
  CONSTRAINT `commenter_fkey` FOREIGN KEY (`commenter`) REFERENCES `student` (`id_student`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `event_commented_fkey` FOREIGN KEY (`event`) REFERENCES `event` (`id_event`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Holds comment information includeing the commenter id, content, timestamp and event commented upon';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entity_type`
--

DROP TABLE IF EXISTS `entity_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entity_type` (
  `entity_name` varchar(45) NOT NULL,
  `id_entity_name` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_entity_name`),
  UNIQUE KEY `entity_name_id_UNIQUE` (`id_entity_name`),
  UNIQUE KEY `entity_name_UNIQUE` (`entity_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Whether something is an rso, event, user, or university';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id_event` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `time` time(4) NOT NULL,
  `location` int(11) NOT NULL,
  `description` text,
  `admin_approved` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `sadmin_approved` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL,
  `host_rso` int(11) DEFAULT '0',
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`date`,`time`,`location`),
  UNIQUE KEY `id_event_UNIQUE` (`id_event`),
  KEY `event_type_fkey_idx` (`type`),
  KEY `event_loc_fkey_idx` (`location`),
  KEY `rso_host_fkey_idx` (`host_rso`),
  CONSTRAINT `event_loc_fkey` FOREIGN KEY (`location`) REFERENCES `location` (`id_location`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `event_type_fkey` FOREIGN KEY (`type`) REFERENCES `event_type` (`id_event_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rso_host_fkey` FOREIGN KEY (`host_rso`) REFERENCES `rso` (`type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Holds information about events. The primary keys are date time and location so that no two events can conflict. The location and type columns are also foreign keys.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event_rating`
--

DROP TABLE IF EXISTS `event_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_rating` (
  `reviewer` int(10) unsigned NOT NULL,
  `event` int(10) unsigned NOT NULL,
  `rating` int(10) unsigned NOT NULL,
  PRIMARY KEY (`reviewer`,`event`,`rating`),
  KEY `event_rated_fkey_idx` (`event`),
  CONSTRAINT `event_rated_fkey` FOREIGN KEY (`event`) REFERENCES `event` (`id_event`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reviewer_fkey` FOREIGN KEY (`reviewer`) REFERENCES `student` (`id_student`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event_type`
--

DROP TABLE IF EXISTS `event_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_type` (
  `id_event_type` int(11) NOT NULL,
  `name` varchar(16) NOT NULL,
  PRIMARY KEY (`id_event_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Holds the event types, RSO, Private, Public, etc...';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `id_location` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  PRIMARY KEY (`latitude`,`longitude`),
  UNIQUE KEY `idlocation_UNIQUE` (`id_location`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Holds location data for events, universities, etc';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `non_rso_events`
--

DROP TABLE IF EXISTS `non_rso_events`;
/*!50001 DROP VIEW IF EXISTS `non_rso_events`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `non_rso_events` AS SELECT 
 1 AS `id_event`,
 1 AS `date`,
 1 AS `time`,
 1 AS `location`,
 1 AS `description`,
 1 AS `admin_approved`,
 1 AS `sadmin_approved`,
 1 AS `type`,
 1 AS `host_rso`,
 1 AS `name`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture` (
  `id_picture` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `path` varchar(2083) NOT NULL,
  PRIMARY KEY (`id_picture`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Holds pictures for a university, an event, an rso, etc.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `picture_mapping`
--

DROP TABLE IF EXISTS `picture_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture_mapping` (
  `id_picture_mapping` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mapped_to_id` int(10) unsigned NOT NULL,
  `entity_type` int(11) unsigned NOT NULL,
  `picture` int(19) unsigned NOT NULL,
  PRIMARY KEY (`id_picture_mapping`),
  UNIQUE KEY `id_picture_mapping_UNIQUE` (`id_picture_mapping`),
  KEY `entity_type_fkey_idx` (`entity_type`),
  KEY `picture_fkey_idx` (`picture`),
  CONSTRAINT `entity_type_fkey` FOREIGN KEY (`entity_type`) REFERENCES `entity_type` (`id_entity_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `picture_fkey` FOREIGN KEY (`picture`) REFERENCES `picture` (`id_picture`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Maps a certain picture to a university, rso, or event';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rso`
--

DROP TABLE IF EXISTS `rso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rso` (
  `id_rso` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` int(11) NOT NULL,
  `num_members` int(10) unsigned DEFAULT '0',
  `university` int(11) unsigned NOT NULL,
  PRIMARY KEY (`name`,`university`),
  UNIQUE KEY `id_rso_UNIQUE` (`id_rso`),
  KEY `rso_type_fkey_idx` (`type`),
  KEY `affiliated_uni_fkey_idx` (`university`),
  CONSTRAINT `affiliated_uni_fkey` FOREIGN KEY (`university`) REFERENCES `university` (`id_university`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rso_type_fkey` FOREIGN KEY (`type`) REFERENCES `rso_type` (`id_rso_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Contains information on RSOs/ Foreign keys for the rso type, and the affiliated university.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `rso_events`
--

DROP TABLE IF EXISTS `rso_events`;
/*!50001 DROP VIEW IF EXISTS `rso_events`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `rso_events` AS SELECT 
 1 AS `id_event`,
 1 AS `date`,
 1 AS `time`,
 1 AS `location`,
 1 AS `description`,
 1 AS `admin_approved`,
 1 AS `sadmin_approved`,
 1 AS `type`,
 1 AS `host_rso`,
 1 AS `name`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `rso_type`
--

DROP TABLE IF EXISTS `rso_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rso_type` (
  `id_rso_type` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_rso_type`),
  UNIQUE KEY `idrsotype_UNIQUE` (`id_rso_type`),
  UNIQUE KEY `rsotypecol_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id_student` int(10) unsigned NOT NULL,
  `university_attending` int(10) unsigned NOT NULL,
  `university_email` varchar(45) NOT NULL,
  PRIMARY KEY (`id_student`),
  UNIQUE KEY `university_email_UNIQUE` (`university_email`),
  KEY `uni_attending_fkey_idx` (`university_attending`),
  CONSTRAINT `student_user_fkey` FOREIGN KEY (`id_student`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `uni_attending_fkey` FOREIGN KEY (`university_attending`) REFERENCES `university` (`id_university`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `super_admin`
--

DROP TABLE IF EXISTS `super_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `super_admin` (
  `id_super_admin` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_super_admin`),
  CONSTRAINT `sadmin_user_fkey` FOREIGN KEY (`id_super_admin`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `unapproved_events`
--

DROP TABLE IF EXISTS `unapproved_events`;
/*!50001 DROP VIEW IF EXISTS `unapproved_events`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `unapproved_events` AS SELECT 
 1 AS `id_event`,
 1 AS `date`,
 1 AS `time`,
 1 AS `location`,
 1 AS `description`,
 1 AS `admin_approved`,
 1 AS `sadmin_approved`,
 1 AS `type`,
 1 AS `host_rso`,
 1 AS `name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `unapproved_non_rso_events`
--

DROP TABLE IF EXISTS `unapproved_non_rso_events`;
/*!50001 DROP VIEW IF EXISTS `unapproved_non_rso_events`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `unapproved_non_rso_events` AS SELECT 
 1 AS `id_event`,
 1 AS `date`,
 1 AS `time`,
 1 AS `location`,
 1 AS `description`,
 1 AS `admin_approved`,
 1 AS `sadmin_approved`,
 1 AS `type`,
 1 AS `host_rso`,
 1 AS `name`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `university`
--

DROP TABLE IF EXISTS `university`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `university` (
  `id_university` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` text,
  `uni_location` int(11) NOT NULL,
  `num_students` int(10) unsigned DEFAULT '0',
  `short_name` varchar(16) DEFAULT NULL,
  `sadmin_creator` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_university`),
  UNIQUE KEY `uni_location_UNIQUE` (`uni_location`),
  KEY `sadmin_creator_fkey_idx` (`sadmin_creator`),
  CONSTRAINT `sadmin_creator_fkey` FOREIGN KEY (`sadmin_creator`) REFERENCES `super_admin` (`id_super_admin`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `uni_loc_fkey` FOREIGN KEY (`uni_location`) REFERENCES `location` (`id_location`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Holds information on all the universities hosting events and affilliating with RSOs';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='This holds all of the user data';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user` int(11) unsigned NOT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`user`),
  KEY `user_role_fkey_idx` (`role`),
  CONSTRAINT `user_fkey` FOREIGN KEY (`user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_role_fkey` FOREIGN KEY (`role`) REFERENCES `user_type` (`name_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `id_user_type` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `name_id` int(11) NOT NULL,
  PRIMARY KEY (`name_id`),
  UNIQUE KEY `idUserTypes_UNIQUE` (`id_user_type`),
  UNIQUE KEY `userTypeName_UNIQUE` (`name`),
  UNIQUE KEY `type_id_UNIQUE` (`name_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Final view structure for view `approved_events`
--

/*!50001 DROP VIEW IF EXISTS `approved_events`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `approved_events` AS select `event`.`id_event` AS `id_event`,`event`.`date` AS `date`,`event`.`time` AS `time`,`event`.`location` AS `location`,`event`.`description` AS `description`,`event`.`admin_approved` AS `admin_approved`,`event`.`sadmin_approved` AS `sadmin_approved`,`event`.`type` AS `type`,`event`.`host_rso` AS `host_rso`,`event`.`name` AS `name` from `event` where ((`event`.`admin_approved` = 1) or (`event`.`sadmin_approved` = 1)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `average_event_ratings`
--

/*!50001 DROP VIEW IF EXISTS `average_event_ratings`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `average_event_ratings` AS select `event`.`id_event` AS `id_event`,`event`.`name` AS `name`,avg(`event_rating`.`rating`) AS `average_rating` from (`event` left join `event_rating` on((`event`.`id_event` = `event_rating`.`event`))) group by `event`.`id_event` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `non_rso_events`
--

/*!50001 DROP VIEW IF EXISTS `non_rso_events`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `non_rso_events` AS select `event`.`id_event` AS `id_event`,`event`.`date` AS `date`,`event`.`time` AS `time`,`event`.`location` AS `location`,`event`.`description` AS `description`,`event`.`admin_approved` AS `admin_approved`,`event`.`sadmin_approved` AS `sadmin_approved`,`event`.`type` AS `type`,`event`.`host_rso` AS `host_rso`,`event`.`name` AS `name` from `event` where (`event`.`host_rso` = 0) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `rso_events`
--

/*!50001 DROP VIEW IF EXISTS `rso_events`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `rso_events` AS select `event`.`id_event` AS `id_event`,`event`.`date` AS `date`,`event`.`time` AS `time`,`event`.`location` AS `location`,`event`.`description` AS `description`,`event`.`admin_approved` AS `admin_approved`,`event`.`sadmin_approved` AS `sadmin_approved`,`event`.`type` AS `type`,`event`.`host_rso` AS `host_rso`,`event`.`name` AS `name` from `event` where (`get_event_type`(`event`.`id_event`) = 'RSO') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `unapproved_events`
--

/*!50001 DROP VIEW IF EXISTS `unapproved_events`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `unapproved_events` AS select `event`.`id_event` AS `id_event`,`event`.`date` AS `date`,`event`.`time` AS `time`,`event`.`location` AS `location`,`event`.`description` AS `description`,`event`.`admin_approved` AS `admin_approved`,`event`.`sadmin_approved` AS `sadmin_approved`,`event`.`type` AS `type`,`event`.`host_rso` AS `host_rso`,`event`.`name` AS `name` from `event` where ((`event`.`admin_approved` = 0) and (`event`.`sadmin_approved` = 0)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `unapproved_non_rso_events`
--

/*!50001 DROP VIEW IF EXISTS `unapproved_non_rso_events`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `unapproved_non_rso_events` AS select `unapproved_events`.`id_event` AS `id_event`,`unapproved_events`.`date` AS `date`,`unapproved_events`.`time` AS `time`,`unapproved_events`.`location` AS `location`,`unapproved_events`.`description` AS `description`,`unapproved_events`.`admin_approved` AS `admin_approved`,`unapproved_events`.`sadmin_approved` AS `sadmin_approved`,`unapproved_events`.`type` AS `type`,`unapproved_events`.`host_rso` AS `host_rso`,`unapproved_events`.`name` AS `name` from `unapproved_events` union select `non_rso_events`.`id_event` AS `id_event`,`non_rso_events`.`date` AS `date`,`non_rso_events`.`time` AS `time`,`non_rso_events`.`location` AS `location`,`non_rso_events`.`description` AS `description`,`non_rso_events`.`admin_approved` AS `admin_approved`,`non_rso_events`.`sadmin_approved` AS `sadmin_approved`,`non_rso_events`.`type` AS `type`,`non_rso_events`.`host_rso` AS `host_rso`,`non_rso_events`.`name` AS `name` from `non_rso_events` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-03 20:36:18
