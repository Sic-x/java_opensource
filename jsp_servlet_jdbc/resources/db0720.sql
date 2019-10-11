/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50525
Source Host           : 127.0.0.1:3306
Source Database       : db0720

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2019-08-22 18:00:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '浚浚', '123', 'tjj');
INSERT INTO `user` VALUES ('16', '勇勇', '123', 'ZY');
