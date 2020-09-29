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

--
-- Table structure for table `pantry`
--

DROP TABLE IF EXISTS `pantry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pantry` (
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `product_type_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `expiration` date NOT NULL,
  `pantry_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`pantry_id`),
  KEY `user_id_idx` (`user_id`),
  KEY `product_type_id_idx` (`product_type_id`),
  CONSTRAINT `product_type_id` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`product_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pantry`
--

LOCK TABLES `pantry` WRITE;
/*!40000 ALTER TABLE `pantry` DISABLE KEYS */;
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
  `product_type` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`product_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type`
--

LOCK TABLES `product_type` WRITE;
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
INSERT INTO `product_type` VALUES (1,'Almonds, dry roasted','Snack'),(2,'Bacon','Meat'),(3,'Bananas, dehydrated','Snack'),(4,'Bananas, fresh','Produce'),(5,'Beans, Black ','Canned'),(6,'Beans, Pinto','Canned'),(7,'Beef, Boneless Top Loin Steak','Meat'),(8,'Beef, Breakfast Sausage','Meat'),(9,'Beef, Hot Dogs','Meat'),(10,'Beef, Porterhouse Steak','Meat'),(11,'Beef, T-Bone Steak','Meat'),(12,'Beef, Tenderloin Roast','Meat'),(13,'Beef, Top Round Roast','Meat'),(14,'Beef, Top Round Steak','Meat'),(15,'Bread, Wheat','Baked Goods'),(16,'Bread, White','Baked Goods'),(17,'Broccoli, raw ','Produce'),(18,'Butter, Stick, salted','Dairy'),(19,'Butter, Stick, unsalted','Dairy'),(20,'Cantaloupe, raw','Produce'),(21,'Carrots, sliced or crinkle cut, frozen','Frozen'),(22,'Carrots, sliced or crinkle cut, raw','Produce'),(23,'Carrots, whole, raw','Produce'),(24,'Cheese, American, sliced','Dairy'),(25,'Cheese, Cheddar, block','Dairy'),(26,'Cheese, Cheddar, shredded','Dairy'),(27,'Cheese, Cheddar, sliced','Dairy'),(28,'Cheese, Cottage 2% milkfat','Dairy'),(29,'Cheese, Mozzarella, low-moisture','Dairy'),(30,'Cheese, Parmesan, grated','Dairy'),(31,'Cheese, Ricotta','Dairy'),(32,'Cheese, Swiss, sliced','Dairy'),(33,'Chicken, Breast','Meat'),(34,'Chicken, Drumstick','Meat'),(35,'Chicken, Thigh','Meat'),(36,'Chorizo Pork Sausage','Meat'),(37,'Cookies, Chocolate Chip','Snack'),(38,'Cookies, Oatmeal','Snack'),(39,'Egg, Grade A, Large','Dairy'),(40,'Flour, all purpose','Baking'),(41,'Flour, Corn, Yellow (Fine Meal)','Baking'),(42,'Flour, Rice (White)','Baking'),(43,'Flour, White (Enriched) ','Baking'),(44,'Flour, Whole Wheat','Baking'),(45,'Ground Turkey, 93% lean','Meat'),(46,'Haddock, raw','Fish'),(47,'Ham, sliced, raw','Meat'),(48,'Ham, whole, raw','Meat'),(49,'Hummus, Original','Refrigerated'),(50,'Hummus, Other','Refrigerated'),(51,'Kale, fresh','Produce'),(52,'Kale, frozen','Produce'),(53,'Ketchup','Condiments'),(54,'Kiwi, fresh','Produce'),(55,'Lettuce, fresh ','Produce'),(56,'Lettuce, Romaine, fresh ','Produce'),(57,'Milk, 1%','Dairy'),(58,'Milk, 2%','Dairy'),(59,'Milk, Skim','Dairy'),(60,'Milk, Whole','Dairy'),(61,'Mustard, Deli','Condiments'),(62,'Mustard, Yellow','Condiments'),(63,'Nectarines, fresh','Produce'),(64,'Oil, Canola','Baking'),(65,'Oil, Coconut','Baking'),(66,'Oil, Corn','Baking'),(67,'Oil, Olive, extra virgin','Baking'),(68,'Oil, Vegetable','Baking'),(69,'Olives, Black','Canned'),(70,'Olives, Green','Canned'),(71,'Onions, Red','Produce'),(72,'Onions, Yellow','Produce'),(73,'Oranges, Navel, fresh','Produce'),(74,'Peaches, fresh','Produce'),(75,'Peanut Butter, creamy','Condiments'),(76,'Peanut Butter, crunchy','Condiments'),(77,'Pears, Bosc, fresh','Produce'),(78,'Pears, Green Anjou, fresh','Produce'),(79,'Pickles, Kosher Dill, chips','Canned'),(80,'Pickles, Kosher Dill, spears','Canned'),(81,'Pollock, raw ','Fish'),(82,'Pork, Italian Sausage, raw','Meat'),(83,'Salsa, Chunky, hot','Canned'),(84,'Salsa, Chunky, medium','Canned'),(85,'Salt, table, iodized','Baking'),(86,'Sauce, Pasta, red','Canned'),(87,'Spinach, fresh ','Produce'),(88,'Strawberries, fresh','Produce'),(89,'Strawberries, fresh','Produce'),(90,'Sugar','Baking'),(91,'Sunflower Seeds, dry roasted, salted','Snack'),(92,'Tomatoes, diced, canned','Canned'),(93,'Tomatoes, Grape','Produce'),(94,'Tomatoes, whole, canned','Canned'),(95,'Tuna, canned','Canned'),(96,'Yogurt, Greek, plain','Dairy'),(97,'Yogurt, Greek, strawberry','Dairy');
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `firstname` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `lastname` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `expiry_length` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2020-09-29  9:48:37
