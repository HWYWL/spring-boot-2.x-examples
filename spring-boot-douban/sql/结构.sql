/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : baike

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 27/12/2019 18:09:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for nezha
-- ----------------------------
DROP TABLE IF EXISTS `nezha`;
CREATE TABLE `nezha`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `film_review` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '影评',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12331 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '哪吒之魔童降世影评表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
