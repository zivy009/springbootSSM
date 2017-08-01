/*
Navicat MySQL Data Transfer

Source Server         : campus112.126.90.223
Source Server Version : 50633
Source Host           : 112.126.90.223:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2017-07-31 11:17:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `schema_version`
-- ----------------------------
 
 

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
`id`  int(11) NOT NULL COMMENT '权限表' ,
`name`  varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`code`  varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源代码' ,
`description`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci

;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('1', 'demo列表', 'demo:list', 'demo列表权限'), ('2', 'demo更新', 'demo:update', 'demo更新功能'), ('3', 'demo添加', 'demo:add', 'demo添加功能');
COMMIT;

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
`id`  int(11) NOT NULL COMMENT '角色表' ,
`name`  varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`code`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`description`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`type`  int(11) NULL DEFAULT 1 COMMENT '1系统角色；2其他的下发的角色' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci

;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', 'admin', 'admin', '超级管理员', '1'), ('2', 'user', 'user', '成员用户', '0');
COMMIT;

-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
`role_id`  int(11) NOT NULL COMMENT '角色ID' ,
`permission_id`  int(11) NOT NULL COMMENT '权限' ,
PRIMARY KEY (`role_id`, `permission_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci
COMMENT='角色授权'

;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES ('1', '1'), ('1', '2'), ('1', '3');
COMMIT;

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
`id`  int(20) NOT NULL COMMENT '用户表' ,
`login_name`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`name`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名' ,
`password`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`salt`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ' 加密参数' ,
`addtime`  timestamp NULL DEFAULT NULL ,
`disabled`  tinyint(1) NULL DEFAULT 0 COMMENT '1禁用；0否' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci

;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin1', '4779e4a9903dfb08f9f877791c516a73', 'admin', '2017-07-27 13:40:52', '0'), ('2', 'zivy', 'zivyiv', '90539cfde7843b59006e0db3bda19698', 'zivy', '2017-07-27 16:51:09', '0');
COMMIT;

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
`role_id`  int(11) NOT NULL COMMENT '角色ID' ,
`user_id`  int(11) NOT NULL COMMENT '用户' ,
PRIMARY KEY (`role_id`, `user_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci
COMMENT='用户角色'

;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '1'), ('2', '2');
COMMIT;

-- ----------------------------
-- Table structure for `zz_demo`
-- ----------------------------
DROP TABLE IF EXISTS `zz_demo`;
CREATE TABLE `zz_demo` (
`id`  bigint(20) NOT NULL ,
`name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`addtime`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '录入时间' ,
`disabled`  tinyint(1) NULL DEFAULT 0 COMMENT '0有效；1作废' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci

;

-- ----------------------------
-- Records of zz_demo
-- ----------------------------
BEGIN;
INSERT INTO `zz_demo` VALUES ('1', 'a', '2017-07-10 11:48:28', '0'), ('2', 'b', '2017-07-10 11:48:30', '0'), ('3', 'c收到', '2017-07-13 09:52:31', '0'), ('4', 'aa', '2017-07-12 16:52:42', '0'), ('5', 'test', '2017-07-17 12:29:02', '0'), ('6', 'atest', '2017-07-17 12:30:31', '0'), ('7', 'tet', '2017-07-17 12:33:10', '0'), ('8', 'adfa', '2017-07-17 12:33:35', '0'), ('9', 'gfsdf', '2017-07-17 12:37:13', '0'), ('10', '我是谁。', '2017-07-18 10:52:01', '0'), ('11', '阿道夫', '2017-07-18 10:52:43', '0'), ('12', 'test', '2017-07-18 11:14:46', '0'), ('13', 't1', '2017-07-18 11:23:38', '0'), ('14', 't2', '2017-07-18 11:26:33', '0'), ('15', 't3', '2017-07-18 11:28:32', '0'), ('16', 't6', '2017-07-18 11:30:07', '0'), ('17', 't7', '2017-07-18 11:31:53', '0'), ('18', 'test8', '2017-07-18 11:39:52', '0'), ('19', 't9', '2017-07-18 11:39:59', '0'), ('22', 't91', '2017-07-19 11:09:01', '0'), ('23', 't9112z', '2017-07-19 11:16:08', '0');
COMMIT;

-- ----------------------------
-- Indexes structure for table schema_version
-- ----------------------------
CREATE INDEX `schema_version_vr_idx` ON `schema_version`(`version_rank`) USING BTREE ;
CREATE INDEX `schema_version_ir_idx` ON `schema_version`(`installed_rank`) USING BTREE ;
CREATE INDEX `schema_version_s_idx` ON `schema_version`(`success`) USING BTREE ;

-- ----------------------------
-- Indexes structure for table sys_user
-- ----------------------------
CREATE UNIQUE INDEX `login_name` ON `sys_user`(`login_name`) USING BTREE ;
