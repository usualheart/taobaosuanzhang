# Host: 127.0.0.1  (Version: 5.5.15)
# Date: 2020-08-11 11:38:55
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "anstable"
#

CREATE TABLE `anstable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `totalSale` float(10,2) DEFAULT '0.00' COMMENT '总销售额',
  `totalProductCost` float(10,2) DEFAULT '0.00' COMMENT '总货物成本',
  `expressFee` float(10,1) DEFAULT '0.0' COMMENT '总快递费',
  `zhitongcheFee` float(10,2) DEFAULT '0.00' COMMENT '直通车费',
  `softwareFee` float(10,2) DEFAULT '0.00' COMMENT '其它软件费',
  `staffWage` float(10,2) DEFAULT NULL COMMENT '员工费用',
  `profit` float(10,2) DEFAULT '0.00' COMMENT '其它软件费',
  `orderDate` date NOT NULL DEFAULT '0000-00-00' COMMENT '日期',
  PRIMARY KEY (`id`,`orderDate`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Structure for table "productitem"
#

CREATE TABLE `productitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderID` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `productID` int(11) DEFAULT NULL COMMENT '商品编号',
  `price` float DEFAULT NULL COMMENT '价格',
  `number` int(11) DEFAULT NULL COMMENT '购买数量',
  `OrderState` varchar(30) DEFAULT NULL COMMENT '订单状态',
  `productAttr` varchar(30) DEFAULT NULL COMMENT '商品属性',
  `title` varchar(30) DEFAULT NULL COMMENT '标题',
  `orderDate` date DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1847 DEFAULT CHARSET=utf8;

#
# Structure for table "sumtable"
#

CREATE TABLE `sumtable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productID` int(11) DEFAULT NULL COMMENT '商品编号',
  `cost` float DEFAULT NULL COMMENT '商品成本',
  `number` int(11) DEFAULT NULL COMMENT '月销售量',
  `numberPresent` int(11) DEFAULT NULL COMMENT '赠送数量',
  `numberSum` int(11) DEFAULT NULL COMMENT '总数量',
  `sumCost` float DEFAULT '0' COMMENT '货物总成本',
  `title` varchar(40) DEFAULT NULL COMMENT '标题',
  `orderDate` date DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;
