/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : summer

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 09/25/2018 22:50:56 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(64) NOT NULL,
  `parent_id` varchar(64) NOT NULL COMMENT '父级id',
  `parent_ids` varchar(500) NOT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `type` char(1) DEFAULT NULL COMMENT '权限类型 0-页面 1-按钮',
  `path` varchar(100) DEFAULT NULL COMMENT '路径',
  `title` varchar(20) DEFAULT NULL COMMENT '中文名称',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `button_type` char(2) DEFAULT NULL COMMENT '按钮类型',
  `status` char(1) DEFAULT NULL COMMENT '状态 0-启用 1-启用',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `component` varchar(255) DEFAULT NULL COMMENT '前端组件',
  `level` char(1) DEFAULT NULL COMMENT '权限等级',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者id',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者id',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) NOT NULL COMMENT '删除标识 0-正常 1-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- ----------------------------
--  Records of `sys_permission`
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('571152ea4b0546c78552b62fc2f984f7', '0', '0', 'sysMain', '0', '/sysManage', '系统管理', '10', null, '0', 'ios-book', 'sysManage', '1', '1', '2018-07-28 21:17:37', '1', '2018-07-28 21:17:37', null, '0'), ('c60e645bb8514cd3a0bcbdfde60e26e8', '571152ea4b0546c78552b62fc2f984f7', '0,571152ea4b0546c78552b62fc2f984f7', 'user-manage', '0', 'user-manage', '用户管理', '10', null, '0', 'ios-book', 'sys/user-manage/userManageList', '2', '1', '2018-07-29 09:56:29', '1', '2018-07-29 09:56:29', null, '0'), ('dcadebf0d1f24cd1b8bdd98b1e9b0572', '571152ea4b0546c78552b62fc2f984f7', '0,571152ea4b0546c78552b62fc2f984f7', 'role-manage', '0', 'role-manage', '角色管理', '10', null, '0', 'ios-book', 'sys/role-manage/roleManageList', '2', '1', '2018-07-29 09:56:32', '1', '2018-07-29 09:56:32', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_permission_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_role`;
CREATE TABLE `sys_permission_role` (
  `sys_role_id` varchar(64) DEFAULT NULL COMMENT '角色id',
  `sys_permission_id` varchar(64) DEFAULT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_permission_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission_role` VALUES ('1', '571152ea4b0546c78552b62fc2f984f7'), ('1', 'c60e645bb8514cd3a0bcbdfde60e26e8'), ('1', 'dcadebf0d1f24cd1b8bdd98b1e9b0572');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '角色名称英文',
  `cn_name` varchar(10) NOT NULL COMMENT '中文名称',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者id',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者id',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT NULL COMMENT '删除标识 0-正常 1-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', '超级管理员', '1', '2018-07-21 16:30:03', '1', '2018-07-21 16:30:03', null, '0'), ('5746e24e182544fa8f2483bb8c8843cc', 'ROLE_COMMEN_USER', '普通用户', '1', '2018-07-21 16:28:37', '1', '2018-07-21 16:28:37', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL,
  `username` varchar(255) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `name` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `status` char(1) NOT NULL COMMENT '是否有用 0-启用 1-禁用',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者id',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者id',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT NULL COMMENT '删除标识 0-正常 1-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$sOJy7gPeYfcLwDwjBYlNFOPqwKYLL6Bhoi3kwo.gBZre4avzEbhli', null, null, 'admin', '0', '1', '2018-07-21 16:35:28', '1', '2018-07-21 16:35:28', null, '0'), ('721dd8a4d2bb4cb9847e40210dc9adf8', 'test', '$2a$10$0.JznVVOZeulhlqiTfxNfeYluQzxiBv06LQUtOQcBnz8YMj06cs8i', null, null, 'test', '0', '1', '2018-07-21 17:00:38', '1', '2018-07-21 17:00:38', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `sys_user_id` varchar(64) DEFAULT NULL COMMENT '系统用户id',
  `sys_role_id` varchar(64) DEFAULT NULL COMMENT '系统角色id',
  KEY `sys_user_id` (`sys_user_id`),
  KEY `sys_role_id` (`sys_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户与角色关系中间表';

-- ----------------------------
--  Records of `sys_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '1'), ('721dd8a4d2bb4cb9847e40210dc9adf8', '5746e24e182544fa8f2483bb8c8843cc');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
