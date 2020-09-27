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
  `product_type` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `expiration` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pantry`
--

LOCK TABLES `pantry` WRITE;
/*!40000 ALTER TABLE `pantry` DISABLE KEYS */;
INSERT INTO `pantry` VALUES ('Almonds, dry roasted','Snack',NULL,NULL),('Bacon','Meat',NULL,NULL),('Bananas, dehydrated','Snack',NULL,NULL),('Bananas, fresh','Produce',NULL,NULL),('Beans, Black ','Canned',NULL,NULL),('Beans, Pinto','Canned',NULL,NULL),('Beef, Boneless Top Loin Steak','Meat',NULL,NULL),('Beef, Breakfast Sausage','Meat',NULL,NULL),('Beef, Hot Dogs','Meat',NULL,NULL),('Beef, Porterhouse Steak','Meat',NULL,NULL),('Beef, T-Bone Steak','Meat',NULL,NULL),('Beef, Tenderloin Roast','Meat',NULL,NULL),('Beef, Top Round Roast','Meat',NULL,NULL),('Beef, Top Round Steak','Meat',NULL,NULL),('Bread, Wheat','Baked Goods',NULL,NULL),('Bread, White','Baked Goods',NULL,NULL),('Broccoli, raw ','Produce',NULL,NULL),('Butter, Stick, salted','Dairy',NULL,NULL),('Butter, Stick, unsalted','Dairy',NULL,NULL),('Cantaloupe, raw','Produce',NULL,NULL),('Carrots, sliced or crinkle cut, frozen','Frozen',NULL,NULL),('Carrots, sliced or crinkle cut, raw','Produce',NULL,NULL),('Carrots, whole, raw','Produce',NULL,NULL),('Cheese, American, sliced','Dairy',NULL,NULL),('Cheese, Cheddar, block','Dairy',NULL,NULL),('Cheese, Cheddar, shredded','Dairy',NULL,NULL),('Cheese, Cheddar, sliced','Dairy',NULL,NULL),('Cheese, Cottage 2% milkfat','Dairy',NULL,NULL),('Cheese, Mozzarella, low-moisture','Dairy',NULL,NULL),('Cheese, Parmesan, grated','Dairy',NULL,NULL),('Cheese, Ricotta','Dairy',NULL,NULL),('Cheese, Swiss, sliced','Dairy',NULL,NULL),('Chicken, Breast','Meat',NULL,NULL),('Chicken, Drumstick','Meat',NULL,NULL),('Chicken, Thigh','Meat',NULL,NULL),('Chorizo Pork Sausage','Meat',NULL,NULL),('Cookies, Chocolate Chip','Snack',NULL,NULL),('Cookies, Oatmeal','Snack',NULL,NULL),('Egg, Grade A, Large','Dairy',NULL,NULL),('Flour, all purpose','Baking',NULL,NULL),('Flour, Corn, Yellow (Fine Meal)','Baking',NULL,NULL),('Flour, Rice (White)','Baking',NULL,NULL),('Flour, White (Enriched) ','Baking',NULL,NULL),('Flour, Whole Wheat','Baking',NULL,NULL),('Ground Turkey, 93% lean','Meat',NULL,NULL),('Haddock, raw','Fish',NULL,NULL),('Ham, sliced, raw','Meat',NULL,NULL),('Ham, whole, raw','Meat',NULL,NULL),('Hummus, Original','Refrigerated',NULL,NULL),('Hummus, Other','Refrigerated',NULL,NULL),('Kale, fresh','Produce',NULL,NULL),('Kale, frozen','Produce',NULL,NULL),('Ketchup','Condiments',NULL,NULL),('Kiwi, fresh','Produce',NULL,NULL),('Lettuce, fresh ','Produce',NULL,NULL),('Lettuce, Romaine, fresh ','Produce',NULL,NULL),('Milk, 1%','Dairy',NULL,NULL),('Milk, 2%','Dairy',NULL,NULL),('Milk, Skim','Dairy',NULL,NULL),('Milk, Whole','Dairy',NULL,NULL),('Mustard, Deli','Condiments',NULL,NULL),('Mustard, Yellow','Condiments',NULL,NULL),('Nectarines, fresh','Produce',NULL,NULL),('Oil, Canola','Baking',NULL,NULL),('Oil, Coconut','Baking',NULL,NULL),('Oil, Corn','Baking',NULL,NULL),('Oil, Olive, extra virgin','Baking',NULL,NULL),('Oil, Vegetable','Baking',NULL,NULL),('Olives, Black','Canned',NULL,NULL),('Olives, Green','Canned',NULL,NULL),('Onions, Red','Produce',NULL,NULL),('Onions, Yellow','Produce',NULL,NULL),('Oranges, Navel, fresh','Produce',NULL,NULL),('Peaches, fresh','Produce',NULL,NULL),('Peanut Butter, creamy','Condiments',NULL,NULL),('Peanut Butter, crunchy','Condiments',NULL,NULL),('Pears, Bosc, fresh','Produce',NULL,NULL),('Pears, Green Anjou, fresh','Produce',NULL,NULL),('Pickles, Kosher Dill, chips','Canned',NULL,NULL),('Pickles, Kosher Dill, spears','Canned',NULL,NULL),('Pollock, raw ','Fish',NULL,NULL),('Pork, Italian Sausage, raw','Meat',NULL,NULL),('Salsa, Chunky, hot','Canned',NULL,NULL),('Salsa, Chunky, medium','Canned',NULL,NULL),('Salt, table, iodized','Baking',NULL,NULL),('Sauce, Pasta, red','Canned',NULL,NULL),('Spinach, fresh ','Produce',NULL,NULL),('Strawberries, fresh','Produce',NULL,NULL),('Strawberries, fresh','Produce',NULL,NULL),('Sugar','Baking',NULL,NULL),('Sunflower Seeds, dry roasted, salted','Snack',NULL,NULL),('Tomatoes, diced, canned','Canned',NULL,NULL),('Tomatoes, Grape','Produce',NULL,NULL),('Tomatoes, whole, canned','Canned',NULL,NULL),('Tuna, canned','Canned',NULL,NULL),('Yogurt, Greek, plain','Dairy',NULL,NULL),('Yogurt, Greek, strawberry','Dairy',NULL,NULL);
/*!40000 ALTER TABLE `pantry` ENABLE KEYS */;
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
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('batman01','Bruce','Wayne');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_data`
--

DROP TABLE IF EXISTS `user_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_data` (
  `username` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_data`
--

LOCK TABLES `user_data` WRITE;
/*!40000 ALTER TABLE `user_data` DISABLE KEYS */;
INSERT INTO `user_data` VALUES ('batman01','password1');
/*!40000 ALTER TABLE `user_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-26 23:27:34
