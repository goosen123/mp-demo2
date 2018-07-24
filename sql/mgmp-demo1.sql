/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : mgmp-demo1

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2018-07-24 17:03:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `userId` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `code` varchar(255) DEFAULT NULL COMMENT '订单号',
  `orderTitle` varchar(255) DEFAULT NULL COMMENT '订单标题',
  `totalCost` double DEFAULT NULL COMMENT '订单总金额',
  `totalVolume` int(11) DEFAULT NULL COMMENT '订单总量',
  `orderStatus` int(11) DEFAULT NULL COMMENT '商品订单状态：0待付款 1待发货 2待收货 3待评价 4退款中 5已完成 6已取消 7已退款 8拒绝退款',
  `isPay` int(11) DEFAULT NULL COMMENT '是否支付了：0未支付 1已支付',
  `payWay` int(11) DEFAULT NULL COMMENT '支付方式 1支付宝 2微信 3银联 4余额',
  `payWayDetail` int(11) DEFAULT NULL COMMENT '支付方式详细 0余额支付  1微信app支付  2微信公众号支付 3微信小程序支付 4微信扫码支付 5微信刷卡支付 6H5支付',
  `orderRemark` varchar(255) DEFAULT NULL COMMENT '订单备注',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('00adb475cf5d459eab09b9074bd2ff59', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018071000009', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-10 18:42:31', '2018-07-10 18:42:31');
INSERT INTO `orders` VALUES ('018203692cbd466aa3f1179ab8fed3b4', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400007', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:54:10', '2018-07-24 11:54:10');
INSERT INTO `orders` VALUES ('1452459e84914700a2c487a7daa196ca', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400005', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:54:08', '2018-07-24 11:54:08');
INSERT INTO `orders` VALUES ('154c5bb373304bd58c52f2276f136d31', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018071000005', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-10 14:48:22', '2018-07-10 14:48:22');
INSERT INTO `orders` VALUES ('172db51781764a538f06be03182e73a6', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018071000001', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-10 14:44:21', '2018-07-10 14:44:21');
INSERT INTO `orders` VALUES ('3297013118cc47649fe49fcc7c6f30c7', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018071000007', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-10 18:21:39', '2018-07-10 18:21:39');
INSERT INTO `orders` VALUES ('33f592c7a900450cb4a73bce50dba772', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400013', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:54:14', '2018-07-24 11:54:14');
INSERT INTO `orders` VALUES ('5bce9c46cb154becbdfc6413b18cf4a2', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400009', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:54:11', '2018-07-24 11:54:11');
INSERT INTO `orders` VALUES ('5e3bfd5c421d4c5eafc59dac32c28918', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018071000002', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-10 14:44:57', '2018-07-10 14:44:57');
INSERT INTO `orders` VALUES ('713d7baa94434b75b0ed5f151a059fbb', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400001', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:53:41', '2018-07-24 11:53:41');
INSERT INTO `orders` VALUES ('78df278bf7e749f6afc1e00df9147a50', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400006', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:54:09', '2018-07-24 11:54:09');
INSERT INTO `orders` VALUES ('8160ada3b38745258f49ba7b7ae61f83', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400011', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:54:12', '2018-07-24 11:54:12');
INSERT INTO `orders` VALUES ('82a0a41e7ea74df08f539cb0f13d0d59', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400008', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:54:10', '2018-07-24 11:54:10');
INSERT INTO `orders` VALUES ('8b71d22c3cd444c5a13c2d33feac75a6', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400014', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:54:17', '2018-07-24 11:54:17');
INSERT INTO `orders` VALUES ('a389d897c997454ca226d129e0901ce0', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400003', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:54:06', '2018-07-24 11:54:06');
INSERT INTO `orders` VALUES ('a907fdc465c44318ba7a0b6f55ec6b33', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018071000003', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-10 14:45:18', '2018-07-10 14:45:18');
INSERT INTO `orders` VALUES ('adfb180b7bfe44ad9371d293a740c0a0', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018071100001', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-11 13:38:47', '2018-07-11 13:38:47');
INSERT INTO `orders` VALUES ('bf415b8674e54d60be83d1b9b5041cf5', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018071000004', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-10 14:48:09', '2018-07-10 14:48:09');
INSERT INTO `orders` VALUES ('c0049147b74c4c5cb91288f59c6fe190', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018071000008', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-10 18:22:44', '2018-07-10 18:22:44');
INSERT INTO `orders` VALUES ('c3841309b5be4855902fcc9bbf1aa0fb', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400004', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:54:07', '2018-07-24 11:54:07');
INSERT INTO `orders` VALUES ('d2740f1600964ac889cf60e8d05842cb', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400012', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:54:13', '2018-07-24 11:54:13');
INSERT INTO `orders` VALUES ('d2bba733461140cd9d2c07c97ce54581', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400002', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:54:05', '2018-07-24 11:54:05');
INSERT INTO `orders` VALUES ('e8936a3363424d82acfe2f71659b7e77', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018072400010', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-24 11:54:11', '2018-07-24 11:54:11');
INSERT INTO `orders` VALUES ('f24731a04a504a46b108d0bcc6e6de8c', '861aa2703f1a4f04bb448e3ac07d2ad8', '普通郭靖', 'O2018071000006', null, '75', '3', '0', '0', null, null, '小心轻放', '2018-07-10 14:50:45', '2018-07-10 14:50:45');

-- ----------------------------
-- Table structure for `orders_product`
-- ----------------------------
DROP TABLE IF EXISTS `orders_product`;
CREATE TABLE `orders_product` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `orderId` varchar(255) DEFAULT NULL COMMENT '订单ID',
  `productId` varchar(255) DEFAULT NULL COMMENT '商品ID',
  `code` varchar(255) DEFAULT NULL COMMENT '产品编码',
  `coverPic` varchar(255) DEFAULT NULL COMMENT '封面',
  `cyclePic` longtext COMMENT '轮播图',
  `productTitle` varchar(255) DEFAULT NULL COMMENT '标题',
  `productBrief` varchar(255) DEFAULT NULL COMMENT '产品简介',
  `productDesc` longtext COMMENT '产品详情介绍',
  `productAttrId` varchar(255) DEFAULT NULL COMMENT '商品属性ID',
  `attrName` varchar(255) DEFAULT NULL COMMENT '属性名称',
  `attrPic` varchar(255) DEFAULT NULL COMMENT '属性封面',
  `attrBrief` varchar(255) DEFAULT NULL COMMENT '属性简介',
  `originalPrice` double DEFAULT NULL COMMENT '原价',
  `salePrice` double DEFAULT NULL COMMENT '售价',
  `itemVolume` int(11) DEFAULT NULL COMMENT '条目数量',
  `itemCost` double DEFAULT NULL COMMENT '条目金额',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表';

-- ----------------------------
-- Records of orders_product
-- ----------------------------
INSERT INTO `orders_product` VALUES ('0f9f4441a1ee4ac2aa7a431b6ccaa27d', '82a0a41e7ea74df08f539cb0f13d0d59', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:54:10', '2018-07-24 11:54:10');
INSERT INTO `orders_product` VALUES ('19050b232b4a43139411721077451160', '8b71d22c3cd444c5a13c2d33feac75a6', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:54:17', '2018-07-24 11:54:17');
INSERT INTO `orders_product` VALUES ('1a224d1966c543e79b483cbfc819ac68', '5bce9c46cb154becbdfc6413b18cf4a2', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:54:11', '2018-07-24 11:54:11');
INSERT INTO `orders_product` VALUES ('219d27a46ceb437e993671b9eef50b7d', '154c5bb373304bd58c52f2276f136d31', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-10 14:48:22', '2018-07-10 14:48:22');
INSERT INTO `orders_product` VALUES ('2c8af9194f244a3dafc5b38a5b422600', 'f24731a04a504a46b108d0bcc6e6de8c', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-10 14:50:45', '2018-07-10 14:50:45');
INSERT INTO `orders_product` VALUES ('2e90376dae304a9caff1a9036d28d682', 'c3841309b5be4855902fcc9bbf1aa0fb', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:54:07', '2018-07-24 11:54:07');
INSERT INTO `orders_product` VALUES ('30ccf18aabbf45eb86300cb155a52094', 'c0049147b74c4c5cb91288f59c6fe190', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-10 18:22:44', '2018-07-10 18:22:44');
INSERT INTO `orders_product` VALUES ('3151ae3b81654fcc8092808abfffb31e', 'adfb180b7bfe44ad9371d293a740c0a0', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-11 13:38:47', '2018-07-11 13:38:47');
INSERT INTO `orders_product` VALUES ('318ee18a37914f8dbf526170fd4ad065', 'd2740f1600964ac889cf60e8d05842cb', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:54:13', '2018-07-24 11:54:13');
INSERT INTO `orders_product` VALUES ('3b10cce381224886bdf344cccab377b0', '33f592c7a900450cb4a73bce50dba772', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:54:14', '2018-07-24 11:54:14');
INSERT INTO `orders_product` VALUES ('3e02979ce9004f5f93cbd01b0301b36c', '8160ada3b38745258f49ba7b7ae61f83', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:54:12', '2018-07-24 11:54:12');
INSERT INTO `orders_product` VALUES ('4a9d346a15484ea881f8adb8a6cf580f', '00adb475cf5d459eab09b9074bd2ff59', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-10 18:42:31', '2018-07-10 18:42:31');
INSERT INTO `orders_product` VALUES ('4c9d706c505a477cb8efdcaba5622e01', 'c3841309b5be4855902fcc9bbf1aa0fb', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:54:07', '2018-07-24 11:54:07');
INSERT INTO `orders_product` VALUES ('4db1f327084e4eedaef4e560dda647eb', 'a907fdc465c44318ba7a0b6f55ec6b33', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-10 14:45:18', '2018-07-10 14:45:18');
INSERT INTO `orders_product` VALUES ('5433ce68232f4a8fb5808bbbbba463ce', '154c5bb373304bd58c52f2276f136d31', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-10 14:48:22', '2018-07-10 14:48:22');
INSERT INTO `orders_product` VALUES ('5586fb4c92614c6c8ee2c0c3782a2c4c', '172db51781764a538f06be03182e73a6', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-10 14:44:21', '2018-07-10 14:44:21');
INSERT INTO `orders_product` VALUES ('5b7906a159eb459899e5cbb9da4631fc', 'd2bba733461140cd9d2c07c97ce54581', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:54:05', '2018-07-24 11:54:05');
INSERT INTO `orders_product` VALUES ('5c30a50a9f1a4f76adbfedaf4f6f11fb', '018203692cbd466aa3f1179ab8fed3b4', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:54:10', '2018-07-24 11:54:10');
INSERT INTO `orders_product` VALUES ('5ff64aebdbdb4ca5aedffdf146dc5644', '78df278bf7e749f6afc1e00df9147a50', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:54:09', '2018-07-24 11:54:09');
INSERT INTO `orders_product` VALUES ('68ef4b9fbf5b4ced83c0247ecbde562f', '33f592c7a900450cb4a73bce50dba772', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:54:14', '2018-07-24 11:54:14');
INSERT INTO `orders_product` VALUES ('6ae4e9b199df44d08794bdd30608102e', '1452459e84914700a2c487a7daa196ca', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:54:08', '2018-07-24 11:54:08');
INSERT INTO `orders_product` VALUES ('7413ad3ecaec4e458ae0694b8a0d2d26', '3297013118cc47649fe49fcc7c6f30c7', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-10 18:21:39', '2018-07-10 18:21:39');
INSERT INTO `orders_product` VALUES ('74d0327e3bd9417ea51c7edb1fa418a2', 'a389d897c997454ca226d129e0901ce0', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:54:06', '2018-07-24 11:54:06');
INSERT INTO `orders_product` VALUES ('7fb7fbceccf547c582eca1fc11db3883', 'e8936a3363424d82acfe2f71659b7e77', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:54:11', '2018-07-24 11:54:11');
INSERT INTO `orders_product` VALUES ('808e77ce790a48c6869f49426e74595e', '172db51781764a538f06be03182e73a6', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-10 14:44:21', '2018-07-10 14:44:21');
INSERT INTO `orders_product` VALUES ('8099461faba14701818c61726a90f0cd', '00adb475cf5d459eab09b9074bd2ff59', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-10 18:42:31', '2018-07-10 18:42:31');
INSERT INTO `orders_product` VALUES ('85cb6a3fd58c4cad91df09ec948b99d4', '1452459e84914700a2c487a7daa196ca', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:54:08', '2018-07-24 11:54:08');
INSERT INTO `orders_product` VALUES ('8917739217834982af31d34cf92a424e', '5e3bfd5c421d4c5eafc59dac32c28918', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-10 14:44:57', '2018-07-10 14:44:57');
INSERT INTO `orders_product` VALUES ('898509c27de9477cb10a79ca6bbf5a74', 'f24731a04a504a46b108d0bcc6e6de8c', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-10 14:50:45', '2018-07-10 14:50:45');
INSERT INTO `orders_product` VALUES ('8ae25abf85ff441ab49fcbc3b7e98bce', '78df278bf7e749f6afc1e00df9147a50', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:54:09', '2018-07-24 11:54:09');
INSERT INTO `orders_product` VALUES ('8affdb4b21af49f5862be3627c9a763e', '3297013118cc47649fe49fcc7c6f30c7', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-10 18:21:39', '2018-07-10 18:21:39');
INSERT INTO `orders_product` VALUES ('8d6e34ff7e3b4c298269851dd31bb3fc', '713d7baa94434b75b0ed5f151a059fbb', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:53:41', '2018-07-24 11:53:41');
INSERT INTO `orders_product` VALUES ('9804e92367454890afc5df10b2c112e0', '5e3bfd5c421d4c5eafc59dac32c28918', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-10 14:44:57', '2018-07-10 14:44:57');
INSERT INTO `orders_product` VALUES ('9e76842400944616850080d911b86aee', 'a907fdc465c44318ba7a0b6f55ec6b33', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-10 14:45:18', '2018-07-10 14:45:18');
INSERT INTO `orders_product` VALUES ('a106b3f6b25e4fab96879a400448d152', 'd2740f1600964ac889cf60e8d05842cb', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:54:13', '2018-07-24 11:54:13');
INSERT INTO `orders_product` VALUES ('a14099f663134e7688b537a80098d2df', '713d7baa94434b75b0ed5f151a059fbb', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:53:41', '2018-07-24 11:53:41');
INSERT INTO `orders_product` VALUES ('a3b2c95cf2b04cbe99ce9ed902cdac3f', '018203692cbd466aa3f1179ab8fed3b4', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:54:10', '2018-07-24 11:54:10');
INSERT INTO `orders_product` VALUES ('b19d7b5c2db1453db060bf3388198f00', 'adfb180b7bfe44ad9371d293a740c0a0', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-11 13:38:47', '2018-07-11 13:38:47');
INSERT INTO `orders_product` VALUES ('b3490eb1db3b4a29a199fa4813b13c5d', 'a389d897c997454ca226d129e0901ce0', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:54:06', '2018-07-24 11:54:06');
INSERT INTO `orders_product` VALUES ('baebfbfdf5a44a6d87d4299156d0df03', 'bf415b8674e54d60be83d1b9b5041cf5', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-10 14:48:09', '2018-07-10 14:48:09');
INSERT INTO `orders_product` VALUES ('bbb7d3e11862488296d64d41f52e1ee9', 'd2bba733461140cd9d2c07c97ce54581', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:54:05', '2018-07-24 11:54:05');
INSERT INTO `orders_product` VALUES ('c242407589aa42a1b5b27fe9d99963d3', '8b71d22c3cd444c5a13c2d33feac75a6', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:54:17', '2018-07-24 11:54:17');
INSERT INTO `orders_product` VALUES ('d6a5662e95d24ce68421a484c8c08667', 'c0049147b74c4c5cb91288f59c6fe190', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-10 18:22:44', '2018-07-10 18:22:44');
INSERT INTO `orders_product` VALUES ('dc44028db0274c52a98684e145212ce6', '5bce9c46cb154becbdfc6413b18cf4a2', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:54:11', '2018-07-24 11:54:11');
INSERT INTO `orders_product` VALUES ('e00a6c51cb004803a58c5b2563cb86fa', '82a0a41e7ea74df08f539cb0f13d0d59', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:54:10', '2018-07-24 11:54:10');
INSERT INTO `orders_product` VALUES ('e223b46ce2db42a0a1b9c5d29fb2981e', 'e8936a3363424d82acfe2f71659b7e77', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '8c7f274a0ede4ccd82fe4e232b28f58c', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '1', '25', '2018-07-24 11:54:11', '2018-07-24 11:54:11');
INSERT INTO `orders_product` VALUES ('f86e6f315865419097d6a3b4cf3f87bb', 'bf415b8674e54d60be83d1b9b5041cf5', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-10 14:48:09', '2018-07-10 14:48:09');
INSERT INTO `orders_product` VALUES ('fbad71049da14c0b84b6a51d83c0aaec', '8160ada3b38745258f49ba7b7ae61f83', '5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '9c33fd814e5e4d589440295aaa9116ef', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '2', '50', '2018-07-24 11:54:12', '2018-07-24 11:54:12');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `code` varchar(255) DEFAULT NULL COMMENT '产品编码',
  `coverPic` varchar(255) DEFAULT NULL COMMENT '封面',
  `cyclePic` longtext COMMENT '轮播图',
  `productTitle` varchar(255) DEFAULT NULL COMMENT '标题',
  `productBrief` varchar(255) DEFAULT NULL COMMENT '产品简介',
  `productDesc` longtext COMMENT '产品详情介绍',
  `originalPrice` double DEFAULT NULL COMMENT '原价',
  `salePrice` double DEFAULT NULL COMMENT '售价',
  `salesVolume` int(11) DEFAULT NULL COMMENT '商品销量',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('2623d025237847fb9d3224dc9e00fb0d', 'p001', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚的西裤', '很好很好', '<p>商品详情</p>', '50', '25', '0', '2018-06-29 18:08:02', '2018-06-29 18:08:02');
INSERT INTO `product` VALUES ('46c9e0baf3704b4e969b4a75b7023be4', 'P005', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装5', '商品简介5', null, '50', '30', '0', '2018-06-27 18:01:17', '2018-06-27 18:01:17');
INSERT INTO `product` VALUES ('5c8432d7b7674301a961d1803538e364', 'P002', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装2', '商品简介2', null, '50', '30', '0', '2018-06-27 18:00:35', '2018-06-27 18:00:35');
INSERT INTO `product` VALUES ('ce8f9abc9ba94fa18a62066158885cef', 'P004', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '时尚男装4', '商品简介4', null, '50', '30', '0', '2018-06-27 18:01:04', '2018-06-27 18:01:04');

-- ----------------------------
-- Table structure for `product_attr`
-- ----------------------------
DROP TABLE IF EXISTS `product_attr`;
CREATE TABLE `product_attr` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `productId` varchar(64) DEFAULT NULL COMMENT '商品ID',
  `attrName` varchar(255) DEFAULT NULL COMMENT '属性名称',
  `attrPic` varchar(255) DEFAULT NULL COMMENT '属性封面',
  `attrBrief` varchar(255) DEFAULT NULL COMMENT '属性简介',
  `originalPrice` double DEFAULT NULL COMMENT '原价',
  `salePrice` double DEFAULT NULL COMMENT '售价',
  `stockVolume` int(11) DEFAULT NULL COMMENT '商品库存',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性表';

-- ----------------------------
-- Records of product_attr
-- ----------------------------
INSERT INTO `product_attr` VALUES ('27b0e2828bcd4fa8921e97ddb3a46aeb', '5c8432d7b7674301a961d1803538e364', '黄色4', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '51', '2018-07-02 16:49:40', '2018-07-02 16:49:40');
INSERT INTO `product_attr` VALUES ('8c7f274a0ede4ccd82fe4e232b28f58c', '5c8432d7b7674301a961d1803538e364', '黄色2', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '51', '2018-07-02 16:49:30', '2018-07-02 16:49:30');
INSERT INTO `product_attr` VALUES ('9c33fd814e5e4d589440295aaa9116ef', '5c8432d7b7674301a961d1803538e364', '黄色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '51', '2018-07-02 16:48:51', '2018-07-02 16:48:51');
INSERT INTO `product_attr` VALUES ('de25c0b1807845faa296a10e37130485', '5c8432d7b7674301a961d1803538e364', '黄色5', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '51', '2018-07-02 16:49:44', '2018-07-02 16:49:44');
INSERT INTO `product_attr` VALUES ('fd99d3461aad4878a47604641f1370ef', '5c8432d7b7674301a961d1803538e364', '黄色3', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '很好很好的黄色', '50', '25', '51', '2018-07-02 16:49:36', '2018-07-02 16:49:36');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(600) DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `num` int(65) DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(65) DEFAULT NULL COMMENT '菜单层级',
  `ismenu` int(11) DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `tips` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(65) DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `isopen` int(11) DEFAULT NULL COMMENT '是否打开:  1:打开   0:不打开',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('00361400cf954a62a3b2d942240e88db', 'orders_view', 'orders', '[0],[shop],[orders],', '查看订单', '', '/orders/view', '1', '3', '0', null, '1', '1', '2018-07-24 13:43:11', '2018-07-24 13:43:11');
INSERT INTO `sys_menu` VALUES ('0f3315c885944ff8b31d61cb05eafe5e', 'shop', '0', '[0],', '商城管理', '', '#', '1', '1', '1', null, '1', '1', '2018-07-23 18:21:16', '2018-07-23 18:21:16');
INSERT INTO `sys_menu` VALUES ('0f537a7c434e4f3a979e434224696bf3', 'menu', 'system', '[0],[system],', '菜单管理', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/menu', '1', '2', '1', '菜单', '1', '1', '2018-07-13 14:37:00', '2018-07-13 14:37:00');
INSERT INTO `sys_menu` VALUES ('21c3e062b78a4145a33c128e4229ec7a', 'product_add', 'product', '[0],[shop],[product],', '添加商品', '', '/product/add', '1', '3', '0', null, '1', '1', '2018-07-24 15:53:45', '2018-07-24 15:53:45');
INSERT INTO `sys_menu` VALUES ('302069d545d047298198be565d77f28c', 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/menu/add', '1', '3', '0', '按钮', '1', '1', '2018-07-13 14:37:13', '2018-07-13 14:37:13');
INSERT INTO `sys_menu` VALUES ('30d1c5639e294d338d43277bbacfc9cf', 'mgr', 'system', '[0],[system],', '用户管理', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/mgr', '1', '2', '1', '菜单', '1', '1', '2018-07-05 17:34:07', '2018-07-05 17:34:07');
INSERT INTO `sys_menu` VALUES ('365d1eaed5c94adfbd187eba9dbc8f2f', 'orders', 'shop', '[0],[shop],', '订单管理', '', '/orders', '2', '2', '1', null, '1', '1', '2018-07-23 18:23:39', '2018-07-23 18:23:39');
INSERT INTO `sys_menu` VALUES ('3ca03d5f76164609be142b83a7e69b90', 'role_add', 'role', '[0],[system],[role],', '添加角色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/role/add', '1', '3', '0', '按钮', '1', '1', '2018-07-13 14:38:31', '2018-07-13 14:38:31');
INSERT INTO `sys_menu` VALUES ('4be47b5997d4491599f521de14ef2a82', 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/mgr/setRole', '1', '3', '0', '按钮', '1', '1', '2018-07-20 10:05:49', '2018-07-20 10:05:49');
INSERT INTO `sys_menu` VALUES ('52e1607b1fd14da8b9b3cde758e479b1', 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/menu/remove', '3', '3', '0', '按钮', '1', '1', '2018-07-20 10:31:39', '2018-07-20 10:31:39');
INSERT INTO `sys_menu` VALUES ('530010688db04eacac2aaa0b34857aba', 'role_edit', 'role', '[0],[system],[role],', '修改角色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/role/edit', '2', '3', '0', '按钮', '1', '1', '2018-07-13 14:38:42', '2018-07-13 14:38:42');
INSERT INTO `sys_menu` VALUES ('5e732386d265422897c7630a707bdb94', 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/mgr/delete', '1', '3', '0', '按钮', '1', '1', '2018-07-20 10:05:30', '2018-07-20 10:05:30');
INSERT INTO `sys_menu` VALUES ('6adc3c22a6dd401b98e8de6ff826d7fc', 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/role/setAuthority', '4', '3', '0', '按钮', '1', '1', '2018-07-20 10:32:11', '2018-07-20 10:32:11');
INSERT INTO `sys_menu` VALUES ('8c0d51a75a8548a08c86f3954615a5c9', 'role', 'system', '[0],[system],', '角色管理', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/role', '1', '2', '1', '菜单', '1', '1', '2018-07-13 14:38:20', '2018-07-13 14:38:20');
INSERT INTO `sys_menu` VALUES ('92c22123175040b98ed83e094d908027', 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/mgr/edit', '2', '3', '0', '按钮', '1', '1', '2018-07-05 17:34:36', '2018-07-05 17:35:21');
INSERT INTO `sys_menu` VALUES ('92ed8e9c0478458ba22eb2cb638cc35d', 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', '', '/mgr/reset', '4', '3', '0', null, '1', '1', '2018-07-24 10:44:41', '2018-07-24 10:44:41');
INSERT INTO `sys_menu` VALUES ('c356fca3a30141ef9d1d720c1f096a93', 'product_edit', 'product', '[0],[shop],[product],', '修改商品', '', '/product/edit', '2', '3', '0', null, '1', '1', '2018-07-24 15:55:14', '2018-07-24 15:55:14');
INSERT INTO `sys_menu` VALUES ('c8ae71bf395b40d6bae1076374072c35', 'system', '0', '[0],', '系统管理', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '#', '1', '1', '1', '菜单', '1', '1', '2018-07-04 15:27:36', '2018-07-04 15:27:36');
INSERT INTO `sys_menu` VALUES ('ca32f561a7494e42a29dc9d2756ca85c', 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/mgr/add', '1', '3', '0', '按钮', '1', '1', '2018-07-05 17:35:37', '2018-07-05 17:35:37');
INSERT INTO `sys_menu` VALUES ('cec15b9e00a84d79945670afd04cfc89', 'product_delete', 'product', '[0],[shop],[product],', '删除商品', '', '/product/delete', '3', '3', '0', null, '1', '1', '2018-07-24 15:56:37', '2018-07-24 15:56:37');
INSERT INTO `sys_menu` VALUES ('d61c076c1bc844c186653b57049c8b29', 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/menu/edit', '2', '3', '0', '按钮', '1', '1', '2018-07-13 14:37:26', '2018-07-13 14:37:26');
INSERT INTO `sys_menu` VALUES ('d8c340d7edf94424b7944da45272d26a', 'role_remove', 'role', '[0],[system],[role],', '删除角色', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '/role/remove', '3', '3', '0', '按钮', '1', '1', '2018-07-20 10:31:55', '2018-07-20 10:31:55');
INSERT INTO `sys_menu` VALUES ('e33c60b925fc47a4a7cfada317bebe14', 'product', 'shop', '[0],[shop],', '商品管理', '', '/product', '1', '2', '1', null, '1', '1', '2018-07-23 18:22:53', '2018-07-23 18:22:53');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `pid` varchar(255) DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('20a00e3f72a54f91a24cb903ac84083f', '1', '0', '超级管理员', 'administrator', '2018-07-05 18:21:08', '2018-07-05 18:21:08');
INSERT INTO `sys_role` VALUES ('37bb7512a139454a94fde8dff37d8188', '2', null, '管理员3', 'admin3', '2018-07-05 18:32:40', '2018-07-05 18:32:40');
INSERT INTO `sys_role` VALUES ('89a8af80fc7c418eabf6fddb1793e6b3', '3', null, '管理员2', 'admin2', '2018-07-05 18:32:34', '2018-07-05 18:32:34');
INSERT INTO `sys_role` VALUES ('cb181c07d0564a5aacda05dc0a801d39', '4', null, '管理员', 'admin', '2018-07-05 18:32:16', '2018-07-05 18:32:16');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `roleId` varchar(255) DEFAULT NULL COMMENT '角色id',
  `menuId` varchar(255) DEFAULT NULL COMMENT '菜单id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('0147710a7d864f2abc95d047ead8bc5f', '20a00e3f72a54f91a24cb903ac84083f', '0f537a7c434e4f3a979e434224696bf3', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('061b63cfb5c3492e93e47bfccd6131f1', '20a00e3f72a54f91a24cb903ac84083f', '00361400cf954a62a3b2d942240e88db', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('0b1d23b7a963460f925d1b55086b5cfe', '20a00e3f72a54f91a24cb903ac84083f', '6adc3c22a6dd401b98e8de6ff826d7fc', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('12f128f80f194406b198427ab430eac0', '20a00e3f72a54f91a24cb903ac84083f', 'e33c60b925fc47a4a7cfada317bebe14', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('2be42eb8eaa4493f8f7c8fa6eb6f3f70', '20a00e3f72a54f91a24cb903ac84083f', '30d1c5639e294d338d43277bbacfc9cf', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('3268132a2add4bb7ab34197203aa5328', '20a00e3f72a54f91a24cb903ac84083f', 'cec15b9e00a84d79945670afd04cfc89', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('3a80c5d8c67e4630ab2a57aca16fd0f3', '20a00e3f72a54f91a24cb903ac84083f', '3ca03d5f76164609be142b83a7e69b90', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('49043d372e2e419ea8b8dc4d35b21fb0', '20a00e3f72a54f91a24cb903ac84083f', '21c3e062b78a4145a33c128e4229ec7a', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('5f257bd885244b29a20a94218abee32b', '20a00e3f72a54f91a24cb903ac84083f', '52e1607b1fd14da8b9b3cde758e479b1', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('70971e3532b94991bf804e26a00926c1', '20a00e3f72a54f91a24cb903ac84083f', '5e732386d265422897c7630a707bdb94', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('7f84e4d07aa14ce99cdaccc496629142', '20a00e3f72a54f91a24cb903ac84083f', '530010688db04eacac2aaa0b34857aba', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('87155101d44d44bd82bb20b53da601b0', '20a00e3f72a54f91a24cb903ac84083f', 'c8ae71bf395b40d6bae1076374072c35', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('898003806776469581b9848e02533140', '20a00e3f72a54f91a24cb903ac84083f', '302069d545d047298198be565d77f28c', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('92a619b675e5478bbae5d0cea3d33ac0', '20a00e3f72a54f91a24cb903ac84083f', '4be47b5997d4491599f521de14ef2a82', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('9fcb3d1a751a482998acdd6a29d6dd13', '20a00e3f72a54f91a24cb903ac84083f', 'ca32f561a7494e42a29dc9d2756ca85c', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('a28f6dd3995c4da5922868213701de20', '20a00e3f72a54f91a24cb903ac84083f', '8c0d51a75a8548a08c86f3954615a5c9', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('b8f4e30cb59c43bf804746a65ea50af6', '20a00e3f72a54f91a24cb903ac84083f', '365d1eaed5c94adfbd187eba9dbc8f2f', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('ddae0d35fd5e48878a4f4d4d55fdb918', '20a00e3f72a54f91a24cb903ac84083f', '92ed8e9c0478458ba22eb2cb638cc35d', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('df3cdbd7e9994b52905b8776c165ca6f', '20a00e3f72a54f91a24cb903ac84083f', 'c356fca3a30141ef9d1d720c1f096a93', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('e7c7a7637d8a4d60a71aa518601e02cb', '20a00e3f72a54f91a24cb903ac84083f', 'd8c340d7edf94424b7944da45272d26a', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('f4b5e2677ff147a0915d6e90bb65f8c6', '20a00e3f72a54f91a24cb903ac84083f', '92c22123175040b98ed83e094d908027', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('fca53f8b7ad447babb823a2afa159756', '20a00e3f72a54f91a24cb903ac84083f', 'd61c076c1bc844c186653b57049c8b29', '2018-07-24 15:57:11', '2018-07-24 15:57:11');
INSERT INTO `sys_role_menu` VALUES ('feef9fdfabab4f16b115bc8a29386330', '20a00e3f72a54f91a24cb903ac84083f', '0f3315c885944ff8b31d61cb05eafe5e', '2018-07-24 15:57:11', '2018-07-24 15:57:11');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `userName` varchar(45) DEFAULT NULL COMMENT '名字',
  `userSex` int(11) DEFAULT NULL COMMENT '性别（1：男 2：女 3：未知）',
  `userEmail` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `userPhone` varchar(45) DEFAULT NULL COMMENT '电话',
  `userType` int(11) DEFAULT NULL COMMENT '用户类型(1：管理员  2：普通用户）',
  `status` int(11) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `userMoney` double DEFAULT NULL COMMENT '用户余额',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('066e80f9c0c64089bab57e999f1d7514', null, '15088132101', '123456', '黄蓉1', '2', '2630344884@qq.com', '15088132101', '1', '1', '0', '2018-07-17 16:48:20', '2018-07-17 16:48:20');
INSERT INTO `sys_user` VALUES ('07018fb623cb4ba8ba32d9746c865d95', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '15088132166', '123456', '郭靖10', '1', '2630344884@qq.com', '15088132165', '1', '1', '0', '2018-07-16 10:30:27', '2018-07-16 10:30:27');
INSERT INTO `sys_user` VALUES ('158ff2afa34f408ba38612d6b57666c0', null, '15088132102', '123456', '黄蓉2', '2', '2630344884@qq.com', '15088132102', '1', '1', '0', '2018-07-18 18:26:53', '2018-07-19 15:25:36');
INSERT INTO `sys_user` VALUES ('178129a539fc496a872c2e2902781da7', null, '15088132100', 'e10adc3949ba59abbe56e057f20f883e', '黄蓉', '2', '2630344884@qq.com', '15088132100', '1', '1', '0', '2018-07-17 15:33:12', '2018-07-18 18:25:38');
INSERT INTO `sys_user` VALUES ('304e3ccebba14fdea179269196c14f4f', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '15088132165', '123456', '郭靖9', '1', '2630344884@qq.com', '15088132165', '1', '1', '0', '2018-07-16 10:30:12', '2018-07-16 10:30:12');
INSERT INTO `sys_user` VALUES ('56fe60c239d94977a5e05114a8bcd1a7', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '15088132169', '123456', '郭靖2', '1', '2630344884@qq.com', '15088132169', '1', '1', '0', '2018-07-03 16:49:20', '2018-07-03 16:49:20');
INSERT INTO `sys_user` VALUES ('618eb09683d946ddb747a5b8ebc300e4', '', '15088132168', 'e10adc3949ba59abbe56e057f20f883e', '郭靖', '1', '2630344884@qq.com', '15088132168', '1', '1', '0', '2018-07-03 16:03:42', '2018-07-24 10:58:49');
INSERT INTO `sys_user` VALUES ('7a702d316089427b872d8100010b014e', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '15088132161', '123456', '郭靖5', '1', '2630344884@qq.com', '15088132161', '1', '1', '0', '2018-07-16 10:26:46', '2018-07-16 10:26:46');
INSERT INTO `sys_user` VALUES ('861aa2703f1a4f04bb448e3ac07d2ad8', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '15088132168', '123456', '普通郭靖', '1', '2630344884@qq.com', '15088132168', '2', '1', '0', '2018-07-09 14:50:08', '2018-07-09 14:50:08');
INSERT INTO `sys_user` VALUES ('b9773ed2ee454a29bb94e2079b42aca7', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '15088132162', '123456', '郭靖6', '1', '2630344884@qq.com', '15088132162', '1', '1', '0', '2018-07-16 10:27:01', '2018-07-16 10:27:01');
INSERT INTO `sys_user` VALUES ('f50a8686e4f4409096d960d3cb9cf2cc', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '15088132160', '123456', '郭靖4', '1', '2630344884@qq.com', '15088132160', '1', '1', '0', '2018-07-16 10:26:05', '2018-07-16 10:26:05');
INSERT INTO `sys_user` VALUES ('fe3b138fa0a54bf49ecc36c362ddc68d', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '15088132164', '123456', '郭靖8', '1', '2630344884@qq.com', '15088132164', '1', '1', '0', '2018-07-16 10:29:55', '2018-07-16 10:29:55');
INSERT INTO `sys_user` VALUES ('ff4403ea1ed04233a2eb2984cf5c8aec', 'http://onm6xj8b2.bkt.clouddn.com/08422fb4-7d88-4794-bede-2b0cb789a805.jpg', '15088132163', '123456', '郭靖7', '1', '2630344884@qq.com', '15088132163', '1', '1', '0', '2018-07-16 10:27:18', '2018-07-16 10:27:18');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `userId` varchar(255) DEFAULT NULL COMMENT '用户id',
  `roleId` varchar(255) DEFAULT NULL COMMENT '角色id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('0c1f1c0c3181490992256d4a9742be9c', '618eb09683d946ddb747a5b8ebc300e4', '20a00e3f72a54f91a24cb903ac84083f', '2018-07-23 10:52:51', '2018-07-23 10:52:51');
INSERT INTO `sys_user_role` VALUES ('255a4867e33f4f51b3863fc4fb1e0ccc', '618eb09683d946ddb747a5b8ebc300e4', '37bb7512a139454a94fde8dff37d8188', '2018-07-23 10:52:51', '2018-07-23 10:52:51');
INSERT INTO `sys_user_role` VALUES ('3cb6ce071b7c4038be5eeadfd079ce16', '618eb09683d946ddb747a5b8ebc300e4', '89a8af80fc7c418eabf6fddb1793e6b3', '2018-07-23 10:52:51', '2018-07-23 10:52:51');
