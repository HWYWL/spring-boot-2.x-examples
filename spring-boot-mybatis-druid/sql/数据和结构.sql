/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : baike

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-07-03 13:33:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for baike
-- ----------------------------
DROP TABLE IF EXISTS `baike`;
CREATE TABLE `baike` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `book` varchar(255) DEFAULT NULL COMMENT '书',
  `tag` varchar(255) DEFAULT NULL COMMENT '标签',
  `good` int(11) DEFAULT '0' COMMENT '点赞',
  `bad` int(11) DEFAULT '0' COMMENT '鄙视',
  `name` varchar(255) DEFAULT NULL COMMENT '作者名称',
  `gender` varchar(100) DEFAULT NULL COMMENT '作者性别',
  `goldCoin` int(11) DEFAULT '0' COMMENT '获得的金币打赏',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：上架、-1：下架',
  `crateDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='百科表';

-- ----------------------------
-- Records of baike
-- ----------------------------
INSERT INTO `baike` VALUES ('1', '老人与海', '[\"文学\",\"小说\"]', '10000', '10', '海明威', '男', '100', '0', '2019-03-05 14:32:47', '2019-03-05 14:32:47');
INSERT INTO `baike` VALUES ('2', '全职法师', '[\"魔幻\",\"小说\"]', '1000000', '5', '乱', '男', '1000', '0', '2019-03-05 14:32:47', '2019-03-05 14:32:47');
INSERT INTO `baike` VALUES ('3', '老人与河', '[\"文学\",\"小说\"]', '500', '10', '海明威', '男', '100', '0', '2019-03-05 14:32:47', '2019-03-05 14:32:47');
INSERT INTO `baike` VALUES ('4', '老人与江', '[\"文学\",\"小说\"]', '5000', '10', '海明威', '男', '100', '0', '2019-03-05 14:32:47', '2019-03-05 14:32:47');
INSERT INTO `baike` VALUES ('5', '遮天', '[\"魔幻\",\"小说\"]', '661000', '11', '辰东', '男', '100', '1', '2019-03-19 22:49:57', '2019-03-19 22:49:57');
INSERT INTO `baike` VALUES ('6', '红楼梦', '[\"文学\",\"小说\"]', '900880', '22', '曹雪芹', '女', '1000', '1', '2019-03-19 22:49:57', '2019-03-19 22:49:57');
