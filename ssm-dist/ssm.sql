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

 Date: 28/02/2020 11:56:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`
(
    `id`    int(11)      NOT NULL AUTO_INCREMENT,
    `name`  varchar(255) NOT NULL,
    `money` float DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account`
VALUES (1, 'jack.zhang', 1000);
INSERT INTO `account`
VALUES (2, 'kevin.yu', 1000);
COMMIT;

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
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users`
VALUES (1, 'iogogogo', '紫兰轩001', '2020-02-27 17:12:57', '2020-02-27 17:12:57', 80);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
