/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : auto-code

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-03-18 11:13:01
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='百科表';

-- ----------------------------
-- Records of baike
-- ----------------------------
INSERT INTO `baike` VALUES ('1', '老人与海', '[\"文学\",\"小说\"]', '1001', '10', '海明威', '男', '100', '0', '2018-08-23 18:08:22', '2018-08-23 18:08:22');
INSERT INTO `baike` VALUES ('2', '全职法师', '[\"魔幻\",\"小说\"]', '1000000', '5', '乱', '男', '10000009', '0', '2018-08-22 18:31:30', '2018-08-22 18:31:30');

-- ----------------------------
-- Table structure for test_class
-- ----------------------------
DROP TABLE IF EXISTS `test_class`;
CREATE TABLE `test_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `class_name` varchar(50) DEFAULT NULL COMMENT '班级名称',
  `quantity` int(11) DEFAULT NULL COMMENT '班级人数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='班级';

-- ----------------------------
-- Records of test_class
-- ----------------------------

-- ----------------------------
-- Table structure for test_role
-- ----------------------------
DROP TABLE IF EXISTS `test_role`;
CREATE TABLE `test_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '测试角色',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `status` int(2) DEFAULT '0' COMMENT '{"name":"状态","0":"启用","1":"禁用"}',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `dels` int(2) DEFAULT '0' COMMENT '{"name":"是否删除","0":"正常","1":"删除"}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='测试角色';

-- ----------------------------
-- Records of test_role
-- ----------------------------

-- ----------------------------
-- Table structure for test_user
-- ----------------------------
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE `test_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` int(2) DEFAULT NULL COMMENT '{"name":"状态","1":"启用","0":"禁用"}',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `remarks` text COMMENT '备注',
  `mun` decimal(20,2) DEFAULT NULL COMMENT '数字',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='测试用户';

-- ----------------------------
-- Records of test_user
-- ----------------------------

-- ----------------------------
-- Table structure for test_user_role
-- ----------------------------
DROP TABLE IF EXISTS `test_user_role`;
CREATE TABLE `test_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色关系表',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='测试用户角色';

-- ----------------------------
-- Records of test_user_role
-- ----------------------------
