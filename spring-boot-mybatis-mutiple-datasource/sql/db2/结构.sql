/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : script

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-06 10:12:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for script_info
-- ----------------------------
DROP TABLE IF EXISTS `script_info`;
CREATE TABLE `script_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `path` varchar(255) DEFAULT NULL COMMENT '脚本路径',
  `command` varchar(255) DEFAULT NULL COMMENT '命令',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：linux命令、1：linux shll脚本、2：windows命令、3：windows bat脚本',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `enable` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：启用、-1：失效',
  `host` varchar(255) DEFAULT NULL COMMENT '远程脚本IP',
  `user_name` varchar(255) DEFAULT NULL COMMENT '服务器账号',
  `pass_word` varchar(255) DEFAULT NULL COMMENT '服务器密码',
  `del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：正常使用、-1：已被删除',
  `crtTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用戶表';
