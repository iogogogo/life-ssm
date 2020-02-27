/*
 Navicat MySQL Data Transfer

 Source Server         : 127.0.0.1-mac
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : life-ssm

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 27/02/2020 09:49:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`          int(20)      NOT NULL AUTO_INCREMENT,
    `username`    varchar(255) NOT NULL,
    `address`     varchar(255) NOT NULL,
    `birthday`    datetime DEFAULT NULL,
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
    `weight`      float    DEFAULT '0',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
