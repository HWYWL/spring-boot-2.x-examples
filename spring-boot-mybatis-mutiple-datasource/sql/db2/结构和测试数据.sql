/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : script

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-06 10:12:37
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

-- ----------------------------
-- Records of script_info
-- ----------------------------
INSERT INTO `script_info` VALUES ('1', 'H:/Program Files/nginx-1.13.10/nginx.bat', 'H:/Program Files/nginx-1.13.10/nginx.bat', '3', 'nainx启动脚本', '0', null, null, null, '0', '2018-04-27 17:23:24');
INSERT INTO `script_info` VALUES ('2', 'H:/Program Files/MongoDBServer3.4/inmongod.exe', 'H:/Program Files/MongoDBServer3.4/inmongod.exe', '3', 'MongoDB启动脚本', '0', 'null', 'null', 'null', '-1', '2018-04-27 17:24:09');
INSERT INTO `script_info` VALUES ('3', 'I:/Program Files/elasticsearch-5.5.2-noed1/bin/elasticsearch.bat', 'I:/Program Files/elasticsearch-5.5.2-noed1/bin/elasticsearch.bat', '3', 'elasticsearch-5.5.2-noed1启动脚本', '0', null, null, null, '0', '2018-04-27 17:24:57');
INSERT INTO `script_info` VALUES ('4', 'H:/Program Files/elasticsearch-5.5.2-noed2/inelasticsearch.bat', 'H:/Program Files/elasticsearch-5.5.2-noed2/inelasticsearch.bat', '3', 'elasticsearch-5.5.2-noed2启动脚本', '0', null, null, null, '0', '2018-04-27 17:25:07');
INSERT INTO `script_info` VALUES ('5', 'H:/Program Files/elasticsearch-5.5.2-noed3/inelasticsearch.bat', 'H:/Program Files/elasticsearch-5.5.2-noed3/inelasticsearch.bat', '3', 'elasticsearch-5.5.2-noed3启动脚本', '0', null, null, null, '0', '2018-04-27 17:25:18');
INSERT INTO `script_info` VALUES ('6', null, 'ipconfig', '2', '查看IP地址', '-1', null, null, null, '0', '2018-04-30 23:29:58');
INSERT INTO `script_info` VALUES ('7', null, 'ifconfig', '0', '查看IP地址', '-1', '111.230.10.220', 'root', '1111111', '0', '2018-05-01 00:19:10');
INSERT INTO `script_info` VALUES ('8', '/home/yi/software/tale/tale-cli', 'cd /home/yi/software/tale && ./tale-cli status', '1', '查看Tale博客状态', '0', '111.230.10.220', 'root', '1111111', '0', '2018-05-01 00:42:46');
INSERT INTO `script_info` VALUES ('9', 'H:/Program Files/zookeeper-3.4.10/bin/zkServer.cmd', 'H:/Program Files/zookeeper-3.4.10/bin/zkServer.cmd', '3', 'zookeeper启动命令', '0', '', '', '', '0', '2018-05-02 13:35:03');
INSERT INTO `script_info` VALUES ('10', '', 'ipconfig', '2', '测试', '0', '', '', '', '-1', '2018-06-19 14:37:33');
INSERT INTO `script_info` VALUES ('11', '', 'ipconfig', '2', '测试', '0', '', '', '', '-1', '2018-06-19 14:37:47');
INSERT INTO `script_info` VALUES ('12', '', 'ipconfig', '0', '测试', '0', '', '', '', '0', '2018-06-19 14:37:58');
INSERT INTO `script_info` VALUES ('13', '', 'ipconfig', '2', 'ipconfig', '0', '', '', '', '0', '2018-06-19 14:39:00');
