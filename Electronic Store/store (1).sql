/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : store

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2016-11-30 15:37:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `cellphones`
-- ----------------------------
DROP TABLE IF EXISTS `cellphones`;
CREATE TABLE `cellphones` (
  `itemid` int(11) NOT NULL,
  `imei` bigint(20) NOT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  UNIQUE KEY `CELPHONES_IMEI_UNIQUE` (`imei`),
  CONSTRAINT `cellphone_itemid` FOREIGN KEY (`itemid`) REFERENCES `items` (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cellphones
-- ----------------------------
INSERT INTO `cellphones` VALUES ('1', '12345', 'Samsung', 'Galaxy s8', 'black');
INSERT INTO `cellphones` VALUES ('2', '123456', 'Samsung', 'Galaxy s8', 'black ');
INSERT INTO `cellphones` VALUES ('3', '369852', 'Apple', 'Iphone 7', 'SILVER');
INSERT INTO `cellphones` VALUES ('4', '12344321', 'SAMSUNG', 'GALAXY S8', 'BLACK');
INSERT INTO `cellphones` VALUES ('8', '0', 'APPLE', 'IPHONE7PLUS', 'WHITE');
INSERT INTO `cellphones` VALUES ('23', '12', 'SAMSUNG', 'S6', 'BLACK');
INSERT INTO `cellphones` VALUES ('24', '13', 'SAMSUNG', 'NOTE 5 ', 'WHITE');
INSERT INTO `cellphones` VALUES ('25', '14', 'SAMSUNG', 'NOTE 5 ', 'BLACK');
INSERT INTO `cellphones` VALUES ('26', '15', 'SAMSUNG', 'TAB 2 ', 'WHITE ');
INSERT INTO `cellphones` VALUES ('27', '18', 'APPLE', 'IPHONE 7', 'BLACK');
INSERT INTO `cellphones` VALUES ('28', '20', 'APPLE ', 'IPHONE 7 PLUS ', 'WHITE ');

-- ----------------------------
-- Table structure for `headphones`
-- ----------------------------
DROP TABLE IF EXISTS `headphones`;
CREATE TABLE `headphones` (
  `itemid` int(11) NOT NULL,
  `serial` varchar(20) NOT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `hasmicrophone` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  UNIQUE KEY `SERIALNO_UNIQUE` (`serial`),
  CONSTRAINT `headphones_itemid` FOREIGN KEY (`itemid`) REFERENCES `items` (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of headphones
-- ----------------------------
INSERT INTO `headphones` VALUES ('6', '100', 'DR DRE', 'BEATS PRO', 'BLACK', '1');

-- ----------------------------
-- Table structure for `items`
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `itemid` int(11) NOT NULL AUTO_INCREMENT,
  `itemtypeid` tinyint(1) DEFAULT NULL,
  `purchaseprice` double(12,0) DEFAULT NULL,
  `sellingprice` double(12,0) DEFAULT NULL,
  `sold` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`itemid`),
  KEY `ITEMS_ITEMTYPEID` (`itemtypeid`),
  CONSTRAINT `ITEMS_ITEMTYPEID` FOREIGN KEY (`itemtypeid`) REFERENCES `itemtypes` (`itemtypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO `items` VALUES ('1', '1', '488', '599', '1');
INSERT INTO `items` VALUES ('2', '1', '499', '599', '1');
INSERT INTO `items` VALUES ('3', '1', '599', '649', '1');
INSERT INTO `items` VALUES ('4', '1', '499', '649', '1');
INSERT INTO `items` VALUES ('5', '1', '499', '799', '1');
INSERT INTO `items` VALUES ('6', '2', '399', '449', '1');
INSERT INTO `items` VALUES ('7', '3', '2', '5', '0');
INSERT INTO `items` VALUES ('8', '1', '649', '999', '1');
INSERT INTO `items` VALUES ('9', '1', '350', '499', '1');
INSERT INTO `items` VALUES ('10', '1', '450', '649', '1');
INSERT INTO `items` VALUES ('11', '1', '450', '599', '1');
INSERT INTO `items` VALUES ('12', '1', '499', '749', '1');
INSERT INTO `items` VALUES ('13', '1', '350', '399', '1');
INSERT INTO `items` VALUES ('14', '1', '499', '749', '1');
INSERT INTO `items` VALUES ('15', '1', '450', '599', '0');
INSERT INTO `items` VALUES ('16', '1', '499', '749', '0');
INSERT INTO `items` VALUES ('17', '1', '350', '399', '0');
INSERT INTO `items` VALUES ('18', '1', '499', '749', '0');
INSERT INTO `items` VALUES ('19', '1', '450', '599', '0');
INSERT INTO `items` VALUES ('20', '1', '499', '749', '0');
INSERT INTO `items` VALUES ('21', '1', '350', '399', '0');
INSERT INTO `items` VALUES ('22', '1', '499', '749', '0');
INSERT INTO `items` VALUES ('23', '1', '499', '599', '0');
INSERT INTO `items` VALUES ('24', '1', '499', '749', '0');
INSERT INTO `items` VALUES ('25', '1', '499', '749', '0');
INSERT INTO `items` VALUES ('26', '1', '599', '849', '0');
INSERT INTO `items` VALUES ('27', '1', '499', '599', '0');
INSERT INTO `items` VALUES ('28', '1', '649', '749', '0');

-- ----------------------------
-- Table structure for `itemtypes`
-- ----------------------------
DROP TABLE IF EXISTS `itemtypes`;
CREATE TABLE `itemtypes` (
  `itemtypeid` tinyint(1) NOT NULL DEFAULT '0',
  `itemtype` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`itemtypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of itemtypes
-- ----------------------------
INSERT INTO `itemtypes` VALUES ('1', 'Cellphone');
INSERT INTO `itemtypes` VALUES ('2', 'Headphones');
INSERT INTO `itemtypes` VALUES ('3', 'Sim Card');

-- ----------------------------
-- Table structure for `simcards`
-- ----------------------------
DROP TABLE IF EXISTS `simcards`;
CREATE TABLE `simcards` (
  `itemid` int(11) NOT NULL,
  `iccid` varchar(50) NOT NULL,
  `network` varchar(50) DEFAULT NULL,
  `size` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  UNIQUE KEY `ICICD_UNIQUE` (`iccid`),
  CONSTRAINT `simcards` FOREIGN KEY (`itemid`) REFERENCES `items` (`itemid`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of simcards
-- ----------------------------
INSERT INTO `simcards` VALUES ('7', '12344980', 'T-MOBILE', 'Nano');

-- ----------------------------
-- Table structure for `transactiondetails`
-- ----------------------------
DROP TABLE IF EXISTS `transactiondetails`;
CREATE TABLE `transactiondetails` (
  `transactionid` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  PRIMARY KEY (`transactionid`,`itemid`),
  KEY `transactiondetails_itemid` (`itemid`),
  CONSTRAINT `transactiondetails_itemid` FOREIGN KEY (`itemid`) REFERENCES `items` (`itemid`),
  CONSTRAINT `transactiondetails_transactionid` FOREIGN KEY (`transactionid`) REFERENCES `transactions` (`transactionid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of transactiondetails
-- ----------------------------
INSERT INTO `transactiondetails` VALUES ('1', '1');
INSERT INTO `transactiondetails` VALUES ('2', '1');
INSERT INTO `transactiondetails` VALUES ('3', '2');
INSERT INTO `transactiondetails` VALUES ('4', '2');
INSERT INTO `transactiondetails` VALUES ('3', '3');
INSERT INTO `transactiondetails` VALUES ('6', '3');
INSERT INTO `transactiondetails` VALUES ('5', '4');
INSERT INTO `transactiondetails` VALUES ('12', '4');
INSERT INTO `transactiondetails` VALUES ('5', '5');
INSERT INTO `transactiondetails` VALUES ('13', '5');
INSERT INTO `transactiondetails` VALUES ('5', '6');
INSERT INTO `transactiondetails` VALUES ('9', '6');
INSERT INTO `transactiondetails` VALUES ('5', '7');
INSERT INTO `transactiondetails` VALUES ('7', '8');
INSERT INTO `transactiondetails` VALUES ('8', '8');
INSERT INTO `transactiondetails` VALUES ('10', '9');
INSERT INTO `transactiondetails` VALUES ('11', '9');
INSERT INTO `transactiondetails` VALUES ('10', '10');
INSERT INTO `transactiondetails` VALUES ('11', '10');
INSERT INTO `transactiondetails` VALUES ('14', '11');
INSERT INTO `transactiondetails` VALUES ('18', '11');
INSERT INTO `transactiondetails` VALUES ('14', '12');
INSERT INTO `transactiondetails` VALUES ('18', '12');
INSERT INTO `transactiondetails` VALUES ('14', '13');
INSERT INTO `transactiondetails` VALUES ('18', '13');
INSERT INTO `transactiondetails` VALUES ('14', '14');
INSERT INTO `transactiondetails` VALUES ('19', '14');
INSERT INTO `transactiondetails` VALUES ('15', '15');
INSERT INTO `transactiondetails` VALUES ('15', '16');
INSERT INTO `transactiondetails` VALUES ('15', '17');
INSERT INTO `transactiondetails` VALUES ('15', '18');
INSERT INTO `transactiondetails` VALUES ('16', '19');
INSERT INTO `transactiondetails` VALUES ('16', '20');
INSERT INTO `transactiondetails` VALUES ('16', '21');
INSERT INTO `transactiondetails` VALUES ('16', '22');
INSERT INTO `transactiondetails` VALUES ('20', '23');
INSERT INTO `transactiondetails` VALUES ('20', '24');
INSERT INTO `transactiondetails` VALUES ('20', '25');
INSERT INTO `transactiondetails` VALUES ('20', '26');
INSERT INTO `transactiondetails` VALUES ('21', '27');
INSERT INTO `transactiondetails` VALUES ('21', '28');

-- ----------------------------
-- Table structure for `transactions`
-- ----------------------------
DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
  `transactionid` int(11) NOT NULL AUTO_INCREMENT,
  `transactioncode` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `transactiontime` date DEFAULT NULL,
  `transactiontype` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`transactionid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of transactions
-- ----------------------------
INSERT INTO `transactions` VALUES ('1', '12345', 'samsung', 'info@samsung.com', '2016-11-15', 'Purchase');
INSERT INTO `transactions` VALUES ('2', '12345', 'Vikram', 'dr.vlucky@gmail.com', '2016-11-15', 'Sale');
INSERT INTO `transactions` VALUES ('3', '123456', 'Samsung', 'info@samsung.com', '2016-11-15', 'Purchase');
INSERT INTO `transactions` VALUES ('4', '00000', 'Kevin', 'kevin.nakkar@gmail.com', '2016-11-15', 'Sale');
INSERT INTO `transactions` VALUES ('5', '32415', 'Samsung', 'info@samsung.com', '2016-11-22', 'Purchase');
INSERT INTO `transactions` VALUES ('6', '369852', 'SAM', 'SAM@GMAIL.COM', '2016-11-22', 'Sale');
INSERT INTO `transactions` VALUES ('7', '11111', 'Apple', 'info@apple.com', '2016-11-22', 'Purchase');
INSERT INTO `transactions` VALUES ('8', '0', 'RYANH', 'RYANH@GMAIL.COM', '2016-11-22', 'Sale');
INSERT INTO `transactions` VALUES ('9', '100', 'AMANDA', 'AMANDA@GMAIL.COM', '2016-11-22', 'Sale');
INSERT INTO `transactions` VALUES ('10', '990', 'LG', 'info@lg.com', '2016-11-22', 'Purchase');
INSERT INTO `transactions` VALUES ('11', '55', 'LIANG', 'LIANG@YAHOO.COM', '2016-11-22', 'Sale');
INSERT INTO `transactions` VALUES ('12', '66', 'kevin', 'kevij@yahoo.com', '2016-11-22', 'Sale');
INSERT INTO `transactions` VALUES ('13', 'Kevin ', 'S', 'Dk@mg.com', '2016-11-29', 'Sale');
INSERT INTO `transactions` VALUES ('14', 'null', 'Samsung', 'samsung@info.com', '2016-11-29', 'Purchase');
INSERT INTO `transactions` VALUES ('15', 'null', 'Samsung', 'samsung@info.com', '2016-11-29', 'Purchase');
INSERT INTO `transactions` VALUES ('16', 'null', 'Samsung', 'samsung@info.com', '2016-11-29', 'Purchase');
INSERT INTO `transactions` VALUES ('18', '1', 'TOM', 'TOM@HAROLD.COM', '2016-11-29', 'Sale');
INSERT INTO `transactions` VALUES ('19', 'r', 'rita', 'rita@google.com', '2016-11-29', 'Sale');
INSERT INTO `transactions` VALUES ('20', '2', 'Samsung', 'info@samsung.com', '2016-11-29', 'Purchase');
INSERT INTO `transactions` VALUES ('21', '1', 'Apple', 'info@appl.com', '2016-11-29', 'Purchase');
