
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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `cod_categoria` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cod_immagine` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'matrimonio',NULL),(2,'battesimo/babysh',NULL),(3,'corse',NULL);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composizione`
--

DROP TABLE IF EXISTS `composizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composizione` (
  `codi_prodotto` int NOT NULL,
  `num_ordine` int NOT NULL,
  `quantita` double NOT NULL,
  `iva` double NOT NULL DEFAULT '0',
  `prezzo` double(6,2) NOT NULL DEFAULT '0.00',
  `dataprenotazione` date DEFAULT NULL,
  PRIMARY KEY (`codi_prodotto`,`num_ordine`),
  KEY `codi_prodotto_idx` (`codi_prodotto`),
  KEY `num_ordine_idx` (`num_ordine`),
  CONSTRAINT `codi_prodotto` FOREIGN KEY (`codi_prodotto`) REFERENCES `prodotto` (`cod_prodotto`),
  CONSTRAINT `num_ordine` FOREIGN KEY (`num_ordine`) REFERENCES `ordine` (`id_ordine`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composizione`
--

LOCK TABLES `composizione` WRITE;
/*!40000 ALTER TABLE `composizione` DISABLE KEYS */;
INSERT INTO `composizione` VALUES (2,0,1,22,799.99,NULL),(3,1,1,22,1999.99,NULL);
/*!40000 ALTER TABLE `composizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consegna`
--

DROP TABLE IF EXISTS `consegna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consegna` (
  `id_consegna` int NOT NULL,
  `via` varchar(45) NOT NULL,
  `cap` int NOT NULL,
  `numero` int NOT NULL,
  `citta` varchar(45) NOT NULL,
  `utente` varchar(50) NOT NULL,
  PRIMARY KEY (`id_consegna`),
  KEY `email_utente_idx` (`utente`),
  CONSTRAINT `email` FOREIGN KEY (`utente`) REFERENCES `utente` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consegna`
--

LOCK TABLES `consegna` WRITE;
/*!40000 ALTER TABLE `consegna` DISABLE KEYS */;
INSERT INTO `consegna` VALUES (0,'Tua',31231,123123,'TOP','a@a');
/*!40000 ALTER TABLE `consegna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `immagine`
--

DROP TABLE IF EXISTS `immagine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `immagine` (
  `nome_immagine` varchar(45) NOT NULL,
  `path` varchar(60) NOT NULL,
  PRIMARY KEY (`nome_immagine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immagine`
--

LOCK TABLES `immagine` WRITE;
/*!40000 ALTER TABLE `immagine` DISABLE KEYS */;
INSERT INTO `immagine` VALUES ('1','./images/1.jpg'),('10','./images/10.jpg'),('2','./images/2.jpg'),('3','./images/3.jpg'),('4','./images/4.jpg'),('5','./images/5.jpg'),('6','./images/6.jpg'),('7','./images/7.jpg'),('8','./images/8.jpg'),('9','./images/9.jpg');
/*!40000 ALTER TABLE `immagine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodo_pagamento`
--

DROP TABLE IF EXISTS `metodo_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodo_pagamento` (
  `id_pagamento` int NOT NULL,
  `nominativo` varchar(80) NOT NULL,
  `CVV` int NOT NULL,
  `meseScadenza` int NOT NULL,
  `codice_carta` varchar(16) NOT NULL,
  `annoScadenza` int NOT NULL,
  `e_utente` varchar(50) NOT NULL,
  PRIMARY KEY (`id_pagamento`),
  KEY `e_utente_idx` (`e_utente`),
  CONSTRAINT `e_utente` FOREIGN KEY (`e_utente`) REFERENCES `utente` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodo_pagamento`
--

LOCK TABLES `metodo_pagamento` WRITE;
/*!40000 ALTER TABLE `metodo_pagamento` DISABLE KEYS */;
INSERT INTO `metodo_pagamento` VALUES (0,'Tony',123,12,'123123123123',2023,'a@a');
/*!40000 ALTER TABLE `metodo_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `id_ordine` int NOT NULL,
  `data_ordine` date NOT NULL,
  `stato_ordine` varchar(45) NOT NULL,
  `cod_consegna` int NOT NULL,
  `cod_pagamento` int NOT NULL,
  `cod_utente` varchar(50) NOT NULL,
  `prezzo_totale` double(6,2) NOT NULL DEFAULT '0.00',
  `pacchetto` double DEFAULT NULL,
  `dataprenotazione` date DEFAULT NULL,
  PRIMARY KEY (`id_ordine`),
  KEY `consegna_idx` (`cod_consegna`),
  KEY `cod_pagamento_idx` (`cod_pagamento`),
  KEY `cod_utente_idx` (`cod_utente`),
  CONSTRAINT `cod_consegna` FOREIGN KEY (`cod_consegna`) REFERENCES `consegna` (`id_consegna`),
  CONSTRAINT `cod_pagamento` FOREIGN KEY (`cod_pagamento`) REFERENCES `metodo_pagamento` (`id_pagamento`),
  CONSTRAINT `cod_utente` FOREIGN KEY (`cod_utente`) REFERENCES `utente` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (0,'2023-07-16','In lavorazione',0,0,'a@a',799.99,NULL,NULL),(1,'2023-07-16','In lavorazione',0,0,'a@a',1999.99,NULL,NULL);
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotto`
--

DROP TABLE IF EXISTS `prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotto` (
  `cod_prodotto` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  `prezzo` double(6,2) NOT NULL,
  `descrizione` varchar(800) NOT NULL,
  `quantita` int NOT NULL,
  `cod_immagine` varchar(45) DEFAULT NULL,
  `codi_categoria` int NOT NULL,
  `rimosso` int NOT NULL DEFAULT '0',
  `iva` double NOT NULL DEFAULT '22',
  PRIMARY KEY (`cod_prodotto`),
  KEY `cod_immagine_idx` (`cod_immagine`),
  KEY `codi_categoria_idx` (`codi_categoria`),
  CONSTRAINT `cod_immagine` FOREIGN KEY (`cod_immagine`) REFERENCES `immagine` (`nome_immagine`),
  CONSTRAINT `codi_categoria` FOREIGN KEY (`codi_categoria`) REFERENCES `categoria` (`cod_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES (1,'Corse',999.99,'Adrenalina allo stato puro, scatta il momento delle tue curve...',1,'10',1,0,22),(2,'Battesimo',799.99,'La tua nuova nascita, decidi il meglio per i tuoi nascituri',1,'5',1,0,22),(3,'Matrimonio',1999.99,'Acquista il ricordo unico.',1,'3',1,0,22);
/*!40000 ALTER TABLE `prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `tipo_account` tinyint DEFAULT '0',
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(35) NOT NULL,
  `codice_fiscale` varchar(16) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES ('a','a',0,'a','a','a'),('a@a','a',0,'a','a','a'),('aaaaa@aaaa.a','a',0,'a','a','a'),('adas@asda.a','aaaa',0,'aaaa','aaaa','asdasd2e21c3r1c2'),('adas@asda.ada','aaaa',0,'aaaa','aaaa','dwedcrcxd35r324d'),('admin@admin.com','admin',1,'Antonio','Botta','xxxx'),('admin@admin.it','admin',0,'admin','admin','admin'),('adsas@asdas.asd','asdf',0,'asdf','asdf','d21e23234123rs12'),('alla@alla','a',0,'Simone','Botta','asdasd23ee23'),('anna@anna.a','anna',0,'anna','anna','1234567890123456'),('anna@anna.com','anna',0,'Anna','Luce','1234567890123456'),('anna@anna.it','a',0,'Anna','Pio','1234567890123456'),('antonio-9708@outlook.it','a',0,'Simone','Botta','asdasd23ee23'),('asd@asd.com','aaaa',0,'asdf','asdf','1234567890123456'),('asd@asd.it','asdf',0,'asdf','asdf','1234567890123456'),('asdkl@kajsd.ka','aaaa',0,'aaaa','aaaa','aaaesfqwui324ct2'),('c@c.c','c',0,'c','c','c'),('cas@casa','casa',0,'casa','casa','234124124'),('casa@hotmail.it','a',0,'Simone','Botta','asdasd23ee23'),('ccc@ccc.c','a',0,'a','a','a'),('ciao@ciao','ciao',0,'ciao','ciao','c90213'),('eeee@eee.a','aaaa',0,'eeee','eeee','r3qwiroèc34qfr23'),('eeee@eee.b','eeee',0,'eeee','eeee','r3qwiroèc34qfr23'),('eeee@eee.e','eeee',0,'eeee','eeee','r3qwiroèc34qfr23'),('genny.schisano@gmail.com','Genny1990',0,'Gennaro','Schisano','SCHGNR80A01B963Z'),('gerardo.napolitano@gmail.com','Gerardo2000',1,'Gerardo','Napolitano','NPLGRD00R04A509E'),('gg.winno@gmail.com','aaaaaaaa',0,'Antonio','Pratico','ANTPRT098123ERG3'),('io@io.it','anna',0,'anna','anna','1234567890123456'),('jjj@jj.j','jjjj',0,'jjjj','jjjj','jj21jue1o2je12r1'),('k@k.k','k',0,'k','k','k'),('kakka@kakka.kakka','kakka',0,'kakka','kakka','1234567890123456'),('kiki@kiki.kiki','kiki',0,'kiki','kiki','i23jrà34jtò2346à'),('kiki@kiki.kikia','aaaa',0,'kiki','kiki','i23jrà34jtò2346à'),('kkkk@kkk.kk','kkkk',0,'kkkk','kkkk','k'),('kpkp@kpkp.k','kkkk',0,'kkkk','kkkk','kkkdokqwedok934i'),('lalla@lalla.lall','lalla',0,'lalla','lalla','2412341251253127'),('lalla@lalla.lalla','lalla',0,'lalla','lalla','2412341251253125'),('lalla@lalla.lalls','lalla',0,'lalla','lalla','2412341251253127'),('ldldas@lasd.kfas','uuuu',0,'uuuu','uuuu','89rue230r9u3jpof'),('lkdas@xn--ldks-oqa.l','kkkk',0,'kkkk','kkkk','keo32i234f5235f2'),('lll@ll.l','pppp',0,'pppp','pppp','ppp1234213521351'),('loca@loca','loca',0,'loca','loca','234124124124141'),('lolp@lpo.l','llll',0,'llll','llll','dijweldkj3qòweor'),('marisa.lasorda@gmail.com','Marisa1994',1,'Marisa','La Sorda','LSRMRS94T61B963S'),('mary.santillo@gmail.com','Mary1975',0,'Maria','Santillo','SNTMRA75L43F839O'),('mmmm@mmmm.m','mmmm',0,'mmmm','mmmm','3k12nji12jj12ri1'),('nomis97@gmail.it','a',0,'Simone','Botta','asdasd23ee23'),('ooo@ooo.o','oooo',0,'oooo','oooo','o341234213423434'),('peppe.alifano@gmail.com','Peppe2002',0,'Giuseppe','Alifano','LFNGPP02T14G482E'),('peppe@peppe.peppe','peppe',0,'peppe','peppe','1234567890123456'),('popo@popo.popo','popo',0,'popo','popo','203iko31j4cftpò3'),('prova@prova.it','123456',0,'prova','prova','1111111111111111'),('prova@prova.prova','prova',0,'prova','prova','1234567890123');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilizzo`
--

DROP TABLE IF EXISTS `utilizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilizzo` (
  `codi_pagamento` int NOT NULL,
  `codi_utente` varchar(50) NOT NULL,
  KEY `cod_pagamento_idx` (`codi_pagamento`),
  KEY `codi_utente_idx` (`codi_utente`),
  CONSTRAINT `codi_pagamento` FOREIGN KEY (`codi_pagamento`) REFERENCES `metodo_pagamento` (`id_pagamento`),
  CONSTRAINT `codi_utente` FOREIGN KEY (`codi_utente`) REFERENCES `utente` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilizzo`
--

LOCK TABLES `utilizzo` WRITE;
/*!40000 ALTER TABLE `utilizzo` DISABLE KEYS */;
/*!40000 ALTER TABLE `utilizzo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-18 22:02:24
