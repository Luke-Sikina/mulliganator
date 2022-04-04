DROP TABLE IF EXISTS `deck_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

DROP TABLE IF EXISTS `deck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

CREATE TABLE `deck` (
  `name` varchar(255) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `deck` WRITE;
/*!40000 ALTER TABLE `deck` DISABLE KEYS */;
INSERT INTO `deck` VALUES ('GUR Wavetron',1), ('U Faeries',2);
/*!40000 ALTER TABLE `deck` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `deck_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deck_card` (
  `name` varchar(255) DEFAULT NULL,
  `count` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `deck_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_name_deck` (`deck_id`,`name`),
  CONSTRAINT `deck_card_ibfk_1` FOREIGN KEY (`deck_id`) REFERENCES `deck` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `deck_card` WRITE;
/*!40000 ALTER TABLE `deck_card` DISABLE KEYS */;
INSERT INTO `deck_card` VALUES ('Wavesifter',4,1,1),('Ancient Stirrings',4,2,1),('Crop Rotation',2,4,1),('Expedition Map',4,5,1),('Urza\'s Tower',4,6,1),('Urza\'s Mine',4,7,1),('Urza\'s Power Plant',4,8,1);
/*!40000 ALTER TABLE `deck_card` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-04 18:45:42
