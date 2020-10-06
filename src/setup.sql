-- MySQL dump 10.13  Distrib 5.7.18, for osx10.12 (x86_64)
--
-- Host: localhost    Database: topshelf
-- ------------------------------------------------------
-- Server version	5.7.18

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

CREATE DATABASE IF NOT EXISTS topshelf;

-- Grants for 'user'@'%'
CREATE USER IF NOT EXISTS 'user'@'localhost';
ALTER USER 'user'@'localhost' IDENTIFIED BY '*C06327039E918D3247E4438D3785C723719DC8B5' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 100 MAX_UPDATES_PER_HOUR 100 MAX_CONNECTIONS_PER_HOUR 100 PASSWORD EXPIRE DEFAULT ACCOUNT UNLOCK;
GRANT DELETE, EXECUTE, INSERT, SELECT, SHOW VIEW, UPDATE ON `topshelf`.* TO 'user'@'%';
GRANT USAGE ON *.* TO 'user'@'localhost';

--
-- Table structure for table `pantry`
--
USE topshelf;

DROP TABLE IF EXISTS `pantry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pantry` (
  `pantry_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `expiration` date NOT NULL,
  `product_type_id` int(11) NOT NULL,
  PRIMARY KEY (`pantry_id`),
  KEY `product_type_id_idx` (`product_type_id`),
  CONSTRAINT `product_type_id` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`product_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pantry`
--

LOCK TABLES `pantry` WRITE;
/*!40000 ALTER TABLE `pantry` DISABLE KEYS */;
INSERT INTO `pantry` VALUES (1,'Almonds, dry roasted',1,'2020-10-31',8),(2,'Bacon',1,'2020-10-31',1),(3,'Bananas, dehydrated',1,'2020-10-31',8),(4,'Bananas, fresh',1,'2020-10-31',6),(5,'Beans, Black ',1,'2020-10-31',9),(6,'Beans, Pinto',1,'2020-10-31',9),(7,'Beef, Boneless Top Loin Steak',1,'2020-10-31',1),(8,'Beef, Breakfast Sausage',1,'2020-10-31',1),(9,'Beef, Hot Dogs',1,'2020-10-31',1),(10,'Beef, Porterhouse Steak',1,'2020-10-31',1),(11,'Beef, T-Bone Steak',1,'2020-10-31',1),(12,'Beef, Tenderloin Roast',1,'2020-10-31',1),(13,'Beef, Top Round Roast',1,'2020-10-31',1),(14,'Beef, Top Round Steak',1,'2020-10-31',1),(15,'Bread, Wheat',1,'2020-10-31',4),(16,'Bread, White',1,'2020-10-31',4),(17,'Broccoli, raw ',1,'2020-10-31',6),(18,'Butter, Stick, salted',1,'2020-10-31',7),(19,'Butter, Stick, unsalted',1,'2020-10-31',7),(20,'Cantaloupe, raw',1,'2020-10-31',6),(21,'Carrots, sliced or crinkle cut, frozen',1,'2020-10-31',11),(22,'Carrots, sliced or crinkle cut, raw',1,'2020-10-31',6),(23,'Carrots, whole, raw',1,'2020-10-31',6),(24,'Cheese, American, sliced',1,'2020-10-31',7),(25,'Cheese, Cheddar, block',1,'2020-10-31',7),(26,'Cheese, Cheddar, shredded',1,'2020-10-31',7),(27,'Cheese, Cheddar, sliced',1,'2020-10-31',7),(28,'Cheese, Cottage 2% milkfat',1,'2020-10-31',7),(29,'Cheese, Mozzarella, low-moisture',1,'2020-10-31',7),(30,'Cheese, Parmesan, grated',1,'2020-10-31',7),(31,'Cheese, Ricotta',1,'2020-10-31',7),(32,'Cheese, Swiss, sliced',1,'2020-10-31',7),(33,'Chicken, Breast',1,'2020-10-31',1),(34,'Chicken, Drumstick',1,'2020-10-31',1),(35,'Chicken, Thigh',1,'2020-10-31',1),(36,'Chorizo Pork Sausage',1,'2020-10-31',1),(37,'Cookies, Chocolate Chip',1,'2020-10-31',8),(38,'Cookies, Oatmeal',1,'2020-10-31',8),(39,'Egg, Grade A, Large',1,'2020-10-31',7),(40,'Flour, all purpose',1,'2020-10-31',3),(41,'Flour, Corn, Yellow (Fine Meal)',1,'2020-10-31',3),(42,'Flour, Rice (White)',1,'2020-10-31',3),(43,'Flour, White (Enriched) ',1,'2020-10-31',3),(44,'Flour, Whole Wheat',1,'2020-10-31',3),(45,'Ground Turkey, 93% lean',1,'2020-10-31',1),(46,'Haddock, raw',1,'2020-10-31',2),(47,'Ham, sliced, raw',1,'2020-10-31',1),(48,'Ham, whole, raw',1,'2020-10-31',1),(49,'Hummus, Original',1,'2020-10-31',10),(50,'Hummus, Other',1,'2020-10-31',10),(51,'Kale, fresh',1,'2020-10-31',6),(52,'Kale, frozen',1,'2020-10-31',6),(53,'Ketchup',1,'2020-10-31',5),(54,'Kiwi, fresh',1,'2020-10-31',6),(55,'Lettuce, fresh ',1,'2020-10-31',6),(56,'Lettuce, Romaine, fresh ',1,'2020-10-31',6),(57,'Milk, 1%',1,'2020-10-31',7),(58,'Milk, 2%',1,'2020-10-31',7),(59,'Milk, Skim',1,'2020-10-31',7),(60,'Milk, Whole',1,'2020-10-31',7),(61,'Mustard, Deli',1,'2020-10-31',5),(62,'Mustard, Yellow',1,'2020-10-31',5),(63,'Nectarines, fresh',1,'2020-10-31',6),(64,'Oil, Canola',1,'2020-10-31',3),(65,'Oil, Coconut',1,'2020-10-31',3),(66,'Oil, Corn',1,'2020-10-31',3),(67,'Oil, Olive, extra virgin',1,'2020-10-31',3),(68,'Oil, Vegetable',1,'2020-10-31',3),(69,'Olives, Black',1,'2020-10-31',9),(70,'Olives, Green',1,'2020-10-31',9),(71,'Onions, Red',1,'2020-10-31',6),(72,'Onions, Yellow',1,'2020-10-31',6),(73,'Oranges, Navel, fresh',1,'2020-10-31',6),(74,'Peaches, fresh',1,'2020-10-31',6),(75,'Peanut Butter, creamy',1,'2020-10-31',5),(76,'Peanut Butter, crunchy',1,'2020-10-31',5),(77,'Pears, Bosc, fresh',1,'2020-10-31',6),(78,'Pears, Green Anjou, fresh',1,'2020-10-31',6),(79,'Pickles, Kosher Dill, chips',1,'2020-10-31',9),(80,'Pickles, Kosher Dill, spears',1,'2020-10-31',9),(81,'Pollock, raw ',1,'2020-10-31',2),(82,'Pork, Italian Sausage, raw',1,'2020-10-31',1),(83,'Salsa, Chunky, hot',1,'2020-10-31',9),(84,'Salsa, Chunky, medium',1,'2020-10-31',9),(85,'Salt, table, iodized',1,'2020-10-31',3),(86,'Sauce, Pasta, red',1,'2020-10-31',9),(87,'Spinach, fresh ',1,'2020-10-31',6),(88,'Strawberries, fresh',1,'2020-10-31',6),(89,'Strawberries, fresh',1,'2020-10-31',6),(90,'Sugar',1,'2020-10-31',3),(91,'Sunflower Seeds, dry roasted, salted',1,'2020-10-31',8),(92,'Tomatoes, diced, canned',1,'2020-10-31',9),(93,'Tomatoes, Grape',1,'2020-10-31',6),(94,'Tomatoes, whole, canned',1,'2020-10-31',9),(95,'Tuna, canned',1,'2020-10-31',9),(96,'Yogurt, Greek, plain',1,'2020-10-31',7),(97,'Yogurt, Greek, strawberry',1,'2020-10-31',7);
/*!40000 ALTER TABLE `pantry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type`
--

DROP TABLE IF EXISTS `product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_type` (
  `product_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`product_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type`
--

LOCK TABLES `product_type` WRITE;
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
INSERT INTO `product_type` VALUES (1,'Meat'),(2,'Seafood'),(3,'Baking'),(4,'Baked Goods'),(5,'Condiments'),(6,'Produce'),(7,'Dairy'),(8,'Snack'),(9,'Canned'),(10,'Refrigerated'),(11,'Frozen');
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `password` blob NOT NULL,
  `firstname` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `lastname` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `expiry_length` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user','Password1!','John ','Test',365);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-04 21:45:33
