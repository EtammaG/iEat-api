-- MySQL dump 10.13  Distrib 8.0.34, for Linux (x86_64)
--
-- Host: localhost    Database: ieat
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `ieat`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ieat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ieat`;

--
-- Table structure for table `address_book`
--

DROP TABLE IF EXISTS `address_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address_book` (
  `id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `consignee` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '收货人',
  `sex` tinyint NOT NULL COMMENT '性别 0 女 1 男',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号',
  `province_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '省级区划编号',
  `province_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '省级名称',
  `city_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '市级区划编号',
  `city_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '市级名称',
  `district_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '区级区划编号',
  `district_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '区级名称',
  `detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '详细地址',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标签',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认 0 否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='地址管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_book`
--

LOCK TABLES `address_book` WRITE;
/*!40000 ALTER TABLE `address_book` DISABLE KEYS */;
INSERT INTO `address_book` VALUES (1417414526093082626,1417012167126876162,'小明',1,'13812345678',NULL,NULL,NULL,NULL,NULL,NULL,'昌平区金燕龙办公楼','公司',1,'2021-07-20 17:22:12','2021-07-20 17:26:33',1417012167126876162,1417012167126876162,0),(1417414926166769666,1417012167126876162,'小李',1,'13512345678',NULL,NULL,NULL,NULL,NULL,NULL,'测试','家',0,'2021-07-20 17:23:47','2021-07-20 17:23:47',1417012167126876162,1417012167126876162,0);
/*!40000 ALTER TABLE `address_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` bigint NOT NULL COMMENT '主键',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '密码',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','$2a$10$H2e/OrrSm8HedJJpXvP4pe91ENwwnjZQG/ShxPXQjxtlI77z5tQbO',1,'2023-09-22 13:13:29','2023-09-22 13:13:30',1,1);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL COMMENT '主键',
  `type` int DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '分类名称',
  `sort` int NOT NULL DEFAULT '0' COMMENT '顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_category_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='菜品及套餐分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (100434468732403714,0,'凤菜',0,'2023-09-28 15:37:03','2023-09-28 15:37:03',98615515722809345,98615515722809345),(1397844263642378242,1,'湘菜',1,'2021-05-27 09:16:58','2021-07-15 20:25:23',1,1),(1397844303408574465,1,'川菜',2,'2021-05-27 09:17:07','2021-06-02 14:27:22',1,1),(1397844391040167938,1,'粤菜',3,'2021-05-27 09:17:28','2021-07-09 14:37:13',1,1),(1413341197421846529,1,'饮品',11,'2021-07-09 11:36:15','2021-07-09 14:39:15',1,1),(1413342269393674242,2,'商务套餐',5,'2021-07-09 11:40:30','2021-07-09 14:43:45',1,1),(1413384954989060097,1,'主食',12,'2021-07-09 14:30:07','2021-07-09 14:39:19',1,1),(1413386191767674881,2,'儿童套餐',6,'2021-07-09 14:35:02','2021-07-09 14:39:05',1,1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish`
--

DROP TABLE IF EXISTS `dish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '菜品名称',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `price` decimal(10,2) DEFAULT NULL COMMENT '菜品价格',
  `code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '商品码',
  `image` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '图片',
  `description` varchar(400) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '描述信息',
  `status` int NOT NULL DEFAULT '1' COMMENT '0 停售 1 起售',
  `sort` int NOT NULL DEFAULT '0' COMMENT '顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_dish_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='菜品管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish`
--

LOCK TABLES `dish` WRITE;
/*!40000 ALTER TABLE `dish` DISABLE KEYS */;
INSERT INTO `dish` VALUES (1397849739276890114,'辣子鸡',1397844263642378242,7800.00,'222222222','f966a38e-0780-40be-bb52-5699d13cb3d9.jpg','来自鲜嫩美味的小鸡，值得一尝',1,0,'2021-05-27 09:38:43','2021-05-27 09:38:43',1,1,0),(1397850140982161409,'毛氏红烧肉',1397844263642378242,6800.00,'123412341234','0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg','毛氏红烧肉毛氏红烧肉，确定不来一份？',1,0,'2021-05-27 09:40:19','2021-05-27 09:40:19',1,1,0),(1397850392090947585,'组庵鱼翅',1397844263642378242,4800.00,'123412341234','740c79ce-af29-41b8-b78d-5f49c96e38c4.jpg','组庵鱼翅，看图足以表明好吃程度',1,0,'2021-05-27 09:41:19','2021-05-27 09:41:19',1,1,0),(1397850851245600769,'霸王别姬',1397844263642378242,12800.00,'123412341234','057dd338-e487-4bbc-a74c-0384c44a9ca3.jpg','还有什么比霸王别姬更美味的呢？',1,0,'2021-05-27 09:43:08','2021-05-27 09:43:08',1,1,0),(1397851099502260226,'全家福',1397844263642378242,11800.00,'23412341234','a53a4e6a-3b83-4044-87f9-9d49b30a8fdc.jpg','别光吃肉啦，来份全家福吧，让你长寿又美味',1,0,'2021-05-27 09:44:08','2021-05-27 09:44:08',1,1,0),(1397851370462687234,'邵阳猪血丸子',1397844263642378242,13800.00,'1246812345678','2a50628e-7758-4c51-9fbb-d37c61cdacad.jpg','看，美味不？来嘛来嘛，这才是最爱吖',1,0,'2021-05-27 09:45:12','2021-05-27 09:45:12',1,1,0),(1397851668262465537,'口味蛇',1397844263642378242,16800.00,'1234567812345678','0f4bd884-dc9c-4cf9-b59e-7d5958fec3dd.jpg','爬行界的扛把子，东兴-口味蛇，让你欲罢不能',1,0,'2021-05-27 09:46:23','2021-05-27 09:46:23',1,1,0),(1397852391150759938,'辣子鸡丁',1397844303408574465,8800.00,'2346812468','ef2b73f2-75d1-4d3a-beea-22da0e1421bd.jpg','辣子鸡丁，辣子鸡丁，永远的魂',1,0,'2021-05-27 09:49:16','2021-05-27 09:49:16',1,1,0),(1397853183287013378,'麻辣兔头',1397844303408574465,19800.00,'123456787654321','2a2e9d66-b41d-4645-87bd-95f2cfeed218.jpg','麻辣兔头的详细制作，麻辣鲜香，色泽红润，回味悠长',1,0,'2021-05-27 09:52:24','2021-05-27 09:52:24',1,1,0),(1397853709101740034,'蒜泥白肉',1397844303408574465,9800.00,'1234321234321','d2f61d70-ac85-4529-9b74-6d9a2255c6d7.jpg','多么的有食欲啊',1,0,'2021-05-27 09:54:30','2021-05-27 09:54:30',1,1,0),(1397853890262118402,'鱼香肉丝',1397844303408574465,3800.00,'1234212321234','8dcfda14-5712-4d28-82f7-ae905b3c2308.jpg','鱼香肉丝简直就是我们童年回忆的一道经典菜，上学的时候点个鱼香肉丝盖饭坐在宿舍床上看着肥皂剧，绝了！现在完美复刻一下上学的时候感觉',1,0,'2021-05-27 09:55:13','2021-05-27 09:55:13',1,1,0),(1397854652581064706,'麻辣水煮鱼',1397844303408574465,14800.00,'2345312·345321','1fdbfbf3-1d86-4b29-a3fc-46345852f2f8.jpg','鱼片是买的切好的鱼片，放几个虾，增加味道',1,0,'2021-05-27 09:58:15','2021-05-27 09:58:15',1,1,0),(1397854865672679425,'鱼香炒鸡蛋',1397844303408574465,2000.00,'23456431·23456','0f252364-a561-4e8d-8065-9a6797a6b1d3.jpg','鱼香菜也是川味的特色。里面没有鱼却鱼香味',1,0,'2021-05-27 09:59:06','2021-05-27 09:59:06',1,1,0),(1397860242057375745,'脆皮烧鹅',1397844391040167938,12800.00,'123456786543213456','e476f679-5c15-436b-87fa-8c4e9644bf33.jpeg','“广东烤鸭美而香，却胜烧鹅说古冈（今新会），燕瘦环肥各佳妙，君休偏重便宜坊”，可见烧鹅与烧鸭在粤菜之中已早负盛名。作为广州最普遍和最受欢迎的烧烤肉食，以它的“色泽金红，皮脆肉嫩，味香可口”的特色，在省城各大街小巷的烧卤店随处可见。',1,0,'2021-05-27 10:20:27','2021-05-27 10:20:27',1,1,0),(1397860578738352129,'白切鸡',1397844391040167938,6600.00,'12345678654','9ec6fc2d-50d2-422e-b954-de87dcd04198.jpeg','白切鸡是一道色香味俱全的特色传统名肴，又叫白斩鸡，是粤菜系鸡肴中的一种，始于清代的民间。白切鸡通常选用细骨农家鸡与沙姜、蒜茸等食材，慢火煮浸白切鸡皮爽肉滑，清淡鲜美。著名的泮溪酒家白切鸡，曾获商业部优质产品金鼎奖。湛江白切鸡更是驰名粤港澳。粤菜厨坛中，鸡的菜式有200余款之多，而最为人常食不厌的正是白切鸡，深受食家青睐。',1,0,'2021-05-27 10:21:48','2021-05-27 10:21:48',1,1,0),(1397860792492666881,'烤乳猪',1397844391040167938,38800.00,'213456432123456','2e96a7e3-affb-438e-b7c3-e1430df425c9.jpeg','广式烧乳猪主料是小乳猪，辅料是蒜，调料是五香粉、芝麻酱、八角粉等，本菜品主要通过将食材放入炭火中烧烤而成。烤乳猪是广州最著名的特色菜，并且是“满汉全席”中的主打菜肴之一。烤乳猪也是许多年来广东人祭祖的祭品之一，是家家都少不了的应节之物，用乳猪祭完先人后，亲戚们再聚餐食用。',1,0,'2021-05-27 10:22:39','2021-05-27 10:22:39',1,1,0),(1397860963880316929,'脆皮乳鸽',1397844391040167938,10800.00,'1234563212345','3fabb83a-1c09-4fd9-892b-4ef7457daafa.jpeg','“脆皮乳鸽”是广东菜中的一道传统名菜，属于粤菜系，具有皮脆肉嫩、色泽红亮、鲜香味美的特点，常吃可使身体强健，清肺顺气。随着菜品制作工艺的不断发展，逐渐形成了熟炸法、生炸法和烤制法三种制作方法。无论那种制作方法，都是在鸽子经过一系列的加工，挂脆皮水后再加工而成，正宗的“脆皮乳鸽皮脆肉嫩、色泽红亮、鲜香味美、香气馥郁。这三种方法的制作过程都不算复杂，但想达到理想的效果并不容易。',1,0,'2021-05-27 10:23:19','2021-05-27 10:23:19',1,1,0),(1397861683434139649,'清蒸河鲜海鲜',1397844391040167938,38800.00,'1234567876543213456','1405081e-f545-42e1-86a2-f7559ae2e276.jpeg','新鲜的海鲜，清蒸是最好的处理方式。鲜，体会为什么叫海鲜。清蒸是广州最经典的烹饪手法，过去岭南地区由于峻山大岭阻隔，交通不便，经济发展起步慢，自家打的鱼放在锅里煮了就吃，没有太多的讲究，但却发现这清淡的煮法能使鱼的鲜甜跃然舌尖。',1,0,'2021-05-27 10:26:11','2021-05-27 10:26:11',1,1,0),(1397862198033297410,'老火靓汤',1397844391040167938,49800.00,'123456786532455','583df4b7-a159-4cfc-9543-4f666120b25f.jpeg','老火靓汤又称广府汤，是广府人传承数千年的食补养生秘方，慢火煲煮的中华老火靓汤，火候足，时间长，既取药补之效，又取入口之甘甜。 广府老火汤种类繁多，可以用各种汤料和烹调方法，烹制出各种不同口味、不同功效的汤来。',1,0,'2021-05-27 10:28:14','2021-05-27 10:28:14',1,1,0),(1397862477831122945,'上汤焗龙虾',1397844391040167938,108800.00,'1234567865432','5b8d2da3-3744-4bb3-acdc-329056b8259d.jpeg','上汤焗龙虾是一道色香味俱全的传统名菜，属于粤菜系。此菜以龙虾为主料，配以高汤制成的一道海鲜美食。本品肉质洁白细嫩，味道鲜美，蛋白质含量高，脂肪含量低，营养丰富。是色香味俱全的传统名菜。',1,0,'2021-05-27 10:29:20','2021-05-27 10:29:20',1,1,0),(1413342036832100354,'北冰洋',1413341197421846529,500.00,'','c99e0aab-3cb7-4eaa-80fd-f47d4ffea694.png','',1,0,'2021-07-09 11:39:35','2021-07-09 15:12:18',1,1,0),(1413384757047271425,'王老吉',1413341197421846529,500.00,'','00874a5e-0df2-446b-8f69-a30eb7d88ee8.png','',1,0,'2021-07-09 14:29:20','2021-07-12 09:09:16',1,1,0),(1413385247889891330,'米饭',1413384954989060097,200.00,'','ee04a05a-1230-46b6-8ad5-1a95b140fff3.png','',1,0,'2021-07-09 14:31:17','2021-07-11 16:35:26',1,1,0),(1707333554049933313,'清远鸡',100434468732403714,99.90,'2333','1.jpg','哈哈哈啊',1,0,'2023-09-28 17:56:55','2023-09-28 17:57:50',98615515722809345,98615515722809345,0);
/*!40000 ALTER TABLE `dish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish_flavor`
--

DROP TABLE IF EXISTS `dish_flavor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish_flavor` (
  `dish_id` bigint NOT NULL COMMENT '菜品',
  `flavor_id` bigint NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  KEY `dish_flavor_dish_id_index` (`dish_id`),
  KEY `dish_flavor_flavor_id_index` (`flavor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='菜品口味关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish_flavor`
--

LOCK TABLES `dish_flavor` WRITE;
/*!40000 ALTER TABLE `dish_flavor` DISABLE KEYS */;
INSERT INTO `dish_flavor` VALUES (1397849417854791681,1397850141040881665,'2021-05-27 09:37:27',1,0),(1397849739276890114,1397851370483658756,'2021-05-27 09:38:43',1,0),(1397849739276890114,1397850141040881665,'2021-05-27 09:38:43',1,0),(1397849936404983809,1397851370483658756,'2021-05-27 09:39:30',1,0),(1397849936404983809,1397850141040881665,'2021-05-27 09:39:30',1,0),(1397850140982161409,1397851370483658756,'2021-05-27 09:40:19',1,0),(1397850140982161409,1397850141040881665,'2021-05-27 09:40:19',1,0),(1397850392090947585,1397850141040881665,'2021-05-27 09:41:19',1,0),(1397850392090947585,1397850141040881665,'2021-05-27 09:41:19',1,0),(1397850630700707841,1397851370483658756,'2021-05-27 09:42:16',1,0),(1397850630700707841,1397850141040881665,'2021-05-27 09:42:16',1,0),(1397850851245600769,1397851370483658756,'2021-05-27 09:43:08',1,0),(1397850851245600769,1397850141040881665,'2021-05-27 09:43:08',1,0),(1397851099502260226,1397851370483658756,'2021-05-27 09:44:08',1,0),(1397851099502260226,1397850141040881665,'2021-05-27 09:44:08',1,0),(1397851370462687234,1397854133632413697,'2021-05-27 09:45:12',1,0),(1397851370462687234,1397851370483658756,'2021-05-27 09:45:12',1,0),(1397851370462687234,1397850141040881665,'2021-05-27 09:45:12',1,0),(1397851668262465537,1397854133632413697,'2021-05-27 09:46:23',1,0),(1397852391150759938,1397851370483658756,'2021-05-27 09:49:16',1,0),(1397852391150759938,1397850141040881665,'2021-05-27 09:49:16',1,0),(1397853183287013378,1397850141040881665,'2021-05-27 09:52:24',1,0),(1397853423461249026,1397850141040881665,'2021-05-27 09:53:22',1,0),(1397853709101740034,1397851370483658756,'2021-05-27 09:54:30',1,0),(1397853890262118402,1397850141040881665,'2021-05-27 09:55:13',1,0),(1397854133603053569,1397854133632413697,'2021-05-27 09:56:11',1,0),(1397854652581064706,1397851370483658756,'2021-05-27 09:58:15',1,0),(1397854652581064706,1397850141040881665,'2021-05-27 09:58:15',1,0),(1397854865672679425,1397850141040881665,'2021-05-27 09:59:06',1,0),(1397855742273826817,1397850141040881665,'2021-05-27 10:02:35',1,0),(1397855906468245506,1397851370483658756,'2021-05-27 10:03:14',1,0),(1397856190540066818,1397850141040881665,'2021-05-27 10:04:21',1,0),(1397859056684150785,1397850141040881665,'2021-05-27 10:15:45',1,0),(1397859277812051969,1397850141040881665,'2021-05-27 10:16:37',1,0),(1397859487476920321,1397850141040881665,'2021-05-27 10:17:27',1,0),(1397859757036449794,1397853423486414850,'2021-05-27 10:18:32',1,0),(1397860242057375745,1397850141040881665,'2021-05-27 10:20:27',1,0),(1397860963880316929,1397850141040881665,'2021-05-27 10:23:19',1,0),(1397861135733534722,1397853423486414850,'2021-05-27 10:24:00',1,0),(1397861370010578945,1397850141040881665,'2021-05-27 10:24:56',1,0),(1397861683434139649,1397851370483658756,'2021-05-27 10:26:11',1,0),(1397861898438356993,1397851370483658756,'2021-05-27 10:27:02',1,0),(1397862198033297410,1397851370483658756,'2021-05-27 10:28:14',1,0),(1397862477831122945,1397850141040881665,'2021-05-27 10:29:20',1,0),(1398089545676271617,1397854133632413697,'2021-05-28 01:31:38',1,0),(1398089782285348866,1397850141040881665,'2021-05-28 01:32:34',1,0),(1398090003228700673,1397851370483658756,'2021-05-28 01:33:27',1,0),(1398090264517062657,1397851370483658756,'2021-05-28 01:34:29',1,0),(1398090455324340225,1397850141040881665,'2021-05-28 01:35:14',1,0),(1398090685419663362,1397854133632413697,'2021-05-28 01:36:09',1,0),(1398090825329061889,1397851370483658756,'2021-05-28 01:36:43',1,0),(1398091007017922561,1397850141040881665,'2021-05-28 01:37:26',1,0),(1398091296131297281,1397850141040881665,'2021-05-28 01:38:35',1,0),(1398091546480914433,1397851370483658756,'2021-05-28 01:39:35',1,0),(1398091729788776450,1397850141040881665,'2021-05-28 01:40:18',1,0),(1398091889449152513,1397850141040881665,'2021-05-28 01:40:56',1,0),(1398092095142014978,1397850141040881665,'2021-05-28 01:41:45',1,0),(1398092283847946241,1397850141040881665,'2021-05-28 01:42:30',1,0),(1398094018893099009,1397850141040881665,'2021-05-28 01:49:24',1,0),(1398094391456346113,1397850141040881665,'2021-05-28 01:50:53',1,0),(1399305325713600514,1397850141040881665,'2021-06-01 03:50:25',1399309715396669441,0),(1413384757047271425,1397854133632413697,'2021-07-12 09:09:16',1,0),(1413342036832100354,1397854133632413697,'2021-07-09 15:12:18',1,0),(1707333554049933313,100470533572788225,'2023-09-28 17:56:59',98615515722809345,0),(1707333554049933313,100470752616120322,'2023-09-28 17:57:50',98615515722809345,0);
/*!40000 ALTER TABLE `dish_flavor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '身份证号',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `employee_pk` (`phone`),
  UNIQUE KEY `employee_pk2` (`id_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='员工信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (98615515722809345,'张三','zhangsan','$2a$10$sp6HHpdzGM3YA9tdO0JrEu7YKVBUXdxuRqiAqd9r3HusM00WbsnJu','12345678900','1','123456789009876543',1,'2023-09-18 22:57:28','2023-09-18 22:57:34',1,1),(98615782010781698,'李四','lisi','$2a$10$sp6HHpdzGM3YA9tdO0JrEu7YKVBUXdxuRqiAqd9r3HusM00WbsnJu','13812312312','1','110101199001010047',1,'2021-05-06 17:20:07','2021-05-10 02:24:09',1,1),(98616297406857219,'王五','wangwu','$2a$10$VwbP5zDp5.dAQaTdF3a8/.TqhQT50BQajjeS3nvTpEtEGjmoKpBlC','18372637283','0','666676788765456543',1,'2023-09-23 18:01:37','2023-09-23 18:01:37',2,2),(98688908123963393,'赵六','zhaoliu','$2a$10$i.3E6AwxC6vI43TJ/oo9mO4cVZdlmxgMOZpR9/13ko9DvduyPWDSe','17265378762','0','999999999999999999',1,'2023-09-23 22:43:22','2023-09-23 22:43:22',98615515722809345,98615515722809345);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flavor`
--

DROP TABLE IF EXISTS `flavor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flavor` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '口味名称',
  `value` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '口味数据list',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='菜品口味表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flavor`
--

LOCK TABLES `flavor` WRITE;
/*!40000 ALTER TABLE `flavor` DISABLE KEYS */;
INSERT INTO `flavor` VALUES (100470533572788225,'spicy','[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]','2023-09-28 17:57:00','2023-09-28 17:57:00',98615515722809345,98615515722809345,0),(100470752616120322,'spicy','[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]','2023-09-28 17:57:50','2023-09-28 17:57:50',98615515722809345,98615515722809345,0),(1397850141040881665,'辣度','[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]','2021-05-27 09:37:27','2021-05-27 09:37:27',1,1,0),(1397851370483658756,'忌口','[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]','2021-05-27 09:38:43','2021-05-27 09:38:43',1,1,0),(1397853423486414850,'甜味','[\"无糖\",\"少糖\",\"半躺\",\"多糖\",\"全糖\"]','2021-05-27 10:24:00','2021-05-27 10:24:00',1,1,0),(1397854133632413697,'温度','[\"常温\",\"冷藏\"]','2021-07-12 09:09:16','2021-07-12 09:09:16',1,1,0);
/*!40000 ALTER TABLE `flavor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL COMMENT '主键',
  `orders_id` bigint DEFAULT NULL COMMENT '订单id',
  `shopping_cart_id` bigint DEFAULT NULL COMMENT '购物车项id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='订单明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL COMMENT '主键',
  `status` int NOT NULL DEFAULT '1' COMMENT '订单状态 1待付款，2待派送，3已派送，4已完成，5已取消',
  `user_id` bigint NOT NULL COMMENT '下单用户',
  `address_book_id` bigint NOT NULL COMMENT '地址id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `checkout_time` datetime NOT NULL COMMENT '结账时间',
  `pay_method` int NOT NULL DEFAULT '1' COMMENT '支付方式 1微信,2支付宝',
  `amount` decimal(10,2) NOT NULL COMMENT '实收金额',
  `remark` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setmeal`
--

DROP TABLE IF EXISTS `setmeal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setmeal` (
  `id` bigint NOT NULL COMMENT '主键',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '套餐名称',
  `price` decimal(10,2) NOT NULL COMMENT '套餐价格',
  `status` int DEFAULT NULL COMMENT '状态 0:停用 1:启用',
  `code` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '编码',
  `description` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '描述信息',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '图片',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_setmeal_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='套餐';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setmeal`
--

LOCK TABLES `setmeal` WRITE;
/*!40000 ALTER TABLE `setmeal` DISABLE KEYS */;
INSERT INTO `setmeal` VALUES (101887189880668161,1413342269393674242,'name',0.00,0,'code','description','image','2023-10-02 13:34:21','2023-10-02 13:34:21',98615515722809345,98615515722809345,0),(1415580119015145474,1413386191767674881,'儿童套餐A计划',4000.00,1,'','','61d20592-b37f-4d72-a864-07ad5bb8f3bb.jpg','2021-07-15 15:52:55','2021-07-15 15:52:55',1415576781934608386,1415576781934608386,0);
/*!40000 ALTER TABLE `setmeal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setmeal_dish`
--

DROP TABLE IF EXISTS `setmeal_dish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setmeal_dish` (
  `id` bigint NOT NULL COMMENT '主键',
  `setmeal_id` bigint NOT NULL COMMENT '套餐id ',
  `dish_id` bigint NOT NULL COMMENT '菜品id',
  `copies` int NOT NULL COMMENT '份数',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='套餐菜品关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setmeal_dish`
--

LOCK TABLES `setmeal_dish` WRITE;
/*!40000 ALTER TABLE `setmeal_dish` DISABLE KEYS */;
INSERT INTO `setmeal_dish` VALUES (101887202765570050,101887189880668161,1397850392090947585,1,0,'2023-10-02 13:34:24','2023-10-02 13:34:24',98615515722809345,98615515722809345,0),(101887202765570051,101887189880668161,1397850140982161409,1,0,'2023-10-02 13:34:24','2023-10-02 13:34:24',98615515722809345,98615515722809345,0),(1415580119052894209,1415580119015145474,1397862198033297410,1,0,'2021-07-15 15:52:55','2021-07-15 15:52:55',1415576781934608386,1415576781934608386,0),(1415580119061282817,1415580119015145474,1413342036832100354,1,0,'2021-07-15 15:52:55','2021-07-15 15:52:55',1415576781934608386,1415576781934608386,0),(1415580119069671426,1415580119015145474,1413385247889891330,1,0,'2021-07-15 15:52:55','2021-07-15 15:52:55',1415576781934608386,1415576781934608386,0);
/*!40000 ALTER TABLE `setmeal_dish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_cart`
--

DROP TABLE IF EXISTS `shopping_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_cart` (
  `id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '主键',
  `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '选定口味',
  `number` int NOT NULL DEFAULT '1' COMMENT '数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='购物车';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart`
--

LOCK TABLES `shopping_cart` WRITE;
/*!40000 ALTER TABLE `shopping_cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `shopping_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '姓名',
  `phone` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '头像',
  `status` int DEFAULT '0' COMMENT '状态 0:禁用，1:正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='用户信息';
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

-- Dump completed on 2023-10-29 20:56:17
