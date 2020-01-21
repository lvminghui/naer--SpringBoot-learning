
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `css` varchar(30) DEFAULT NULL,
  `href` varchar(1000) DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `permission` varchar(50) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '0', '用户管理', 'fa-users', '', '1', '', '1');
INSERT INTO `sys_permission` VALUES ('2', '1', '用户查询', 'fa-user', '/api/getPage?pageName=user/user-list', '1', '', '2');
INSERT INTO `sys_permission` VALUES ('3', '2', '查询', '', '', '2', 'sys:user:query', '100');
INSERT INTO `sys_permission` VALUES ('4', '2', '新增', '', '', '2', 'sys:user:add', '100');
INSERT INTO `sys_permission` VALUES ('5', '2', '删除', null, null, '2', 'sys:user:del', '100');
INSERT INTO `sys_permission` VALUES ('6', '1', '修改密码', 'fa-pencil-square-o', '/api/getPage?pageName=user/user-change-password', '1', 'sys:user:password', '4');
INSERT INTO `sys_permission` VALUES ('7', '0', '系统设置', 'fa-gears', '', '1', '', '5');
INSERT INTO `sys_permission` VALUES ('8', '7', '菜单', 'fa-cog', '/api/getPage?pageName=permission/permission-list', '1', '', '6');
INSERT INTO `sys_permission` VALUES ('9', '8', '查询', '', '', '2', 'sys:menu:query', '100');
INSERT INTO `sys_permission` VALUES ('10', '8', '新增', '', '', '2', 'sys:menu:add', '100');
INSERT INTO `sys_permission` VALUES ('11', '8', '删除', '', '', '2', 'sys:menu:del', '100');
INSERT INTO `sys_permission` VALUES ('12', '7', '角色', 'fa-user-secret', '/api/getPage?pageName=role/role-list', '1', '', '7');
INSERT INTO `sys_permission` VALUES ('13', '12', '查询', '', '', '2', 'sys:role:query', '100');
INSERT INTO `sys_permission` VALUES ('14', '12', '新增', '', '', '2', 'sys:role:add', '100');
INSERT INTO `sys_permission` VALUES ('15', '12', '删除', '', '', '2', 'sys:role:del', '100');
INSERT INTO `sys_permission` VALUES ('16', '0', '文件管理', 'fa-folder-open', '/api/getPage?pageName=file/file-list', '1', '', '8');
INSERT INTO `sys_permission` VALUES ('17', '16', '查询', '', '', '2', 'sys:file:query', '100');
INSERT INTO `sys_permission` VALUES ('18', '16', '删除', '', '', '2', 'sys:file:del', '100');
INSERT INTO `sys_permission` VALUES ('19', '0', '数据源监控', 'fa-eye', 'druid/index.html', '1', '', '9');
INSERT INTO `sys_permission` VALUES ('20', '0', '接口swagger', 'fa-file-pdf-o', 'swagger-ui.html', '1', '', '10');
INSERT INTO `sys_permission` VALUES ('21', '0', '代码生成', 'fa-wrench', '/api/getPage?pageName=generate/edit', '1', 'generate:edit', '11');
INSERT INTO `sys_permission` VALUES ('22', '0', '日志查询', 'fa-reorder', '/api/getPage?pageName=log/log-list', '1', 'sys:log:query', '13');
INSERT INTO `sys_permission` VALUES ('23', '8', '修改', null, null, '2', 'sys:menu:edit', '100');
INSERT INTO `sys_permission` VALUES ('24', '12', '修改', null, null, '2', 'sys:role:edit', '100');
INSERT INTO `sys_permission` VALUES ('25', '2', '修改', null, null, '2', 'sys:user:edit', '100');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '管理员', '2017-05-01 13:25:39', '2019-06-04 02:25:13');
INSERT INTO `sys_role` VALUES ('2', 'USER', '普通用户', '2017-08-01 21:47:31', '2019-05-30 09:08:24');
INSERT INTO `sys_role` VALUES ('3', 'TEACHER', '', '2019-03-27 02:10:23', '2019-05-23 07:48:01');
INSERT INTO `sys_role` VALUES ('4', 'test', 'test', '2019-04-29 02:16:48', '2019-05-22 09:51:26');

-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`),
  KEY `fk_sysrolepermission_permissionId` (`permissionId`),
  CONSTRAINT `fk_permission_roleId` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_sysrolepermission_permissionId` FOREIGN KEY (`permissionId`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '1');
INSERT INTO `sys_role_permission` VALUES ('3', '1');
INSERT INTO `sys_role_permission` VALUES ('4', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('2', '2');
INSERT INTO `sys_role_permission` VALUES ('3', '2');
INSERT INTO `sys_role_permission` VALUES ('4', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('2', '3');
INSERT INTO `sys_role_permission` VALUES ('3', '3');
INSERT INTO `sys_role_permission` VALUES ('4', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('3', '4');
INSERT INTO `sys_role_permission` VALUES ('4', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '5');
INSERT INTO `sys_role_permission` VALUES ('2', '5');
INSERT INTO `sys_role_permission` VALUES ('3', '5');
INSERT INTO `sys_role_permission` VALUES ('4', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('2', '6');
INSERT INTO `sys_role_permission` VALUES ('3', '6');
INSERT INTO `sys_role_permission` VALUES ('4', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('3', '7');
INSERT INTO `sys_role_permission` VALUES ('4', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('3', '8');
INSERT INTO `sys_role_permission` VALUES ('4', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('3', '9');
INSERT INTO `sys_role_permission` VALUES ('4', '9');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('3', '10');
INSERT INTO `sys_role_permission` VALUES ('4', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('3', '11');
INSERT INTO `sys_role_permission` VALUES ('4', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('3', '12');
INSERT INTO `sys_role_permission` VALUES ('4', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('3', '13');
INSERT INTO `sys_role_permission` VALUES ('4', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('3', '14');
INSERT INTO `sys_role_permission` VALUES ('4', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
INSERT INTO `sys_role_permission` VALUES ('3', '15');
INSERT INTO `sys_role_permission` VALUES ('4', '15');
INSERT INTO `sys_role_permission` VALUES ('3', '16');
INSERT INTO `sys_role_permission` VALUES ('4', '16');
INSERT INTO `sys_role_permission` VALUES ('3', '17');
INSERT INTO `sys_role_permission` VALUES ('4', '17');
INSERT INTO `sys_role_permission` VALUES ('3', '18');
INSERT INTO `sys_role_permission` VALUES ('4', '18');
INSERT INTO `sys_role_permission` VALUES ('1', '19');
INSERT INTO `sys_role_permission` VALUES ('3', '19');
INSERT INTO `sys_role_permission` VALUES ('4', '19');
INSERT INTO `sys_role_permission` VALUES ('1', '20');
INSERT INTO `sys_role_permission` VALUES ('2', '20');
INSERT INTO `sys_role_permission` VALUES ('3', '20');
INSERT INTO `sys_role_permission` VALUES ('4', '20');
INSERT INTO `sys_role_permission` VALUES ('1', '21');
INSERT INTO `sys_role_permission` VALUES ('2', '21');
INSERT INTO `sys_role_permission` VALUES ('3', '21');
INSERT INTO `sys_role_permission` VALUES ('4', '21');
INSERT INTO `sys_role_permission` VALUES ('3', '22');
INSERT INTO `sys_role_permission` VALUES ('4', '22');
INSERT INTO `sys_role_permission` VALUES ('1', '23');
INSERT INTO `sys_role_permission` VALUES ('3', '23');
INSERT INTO `sys_role_permission` VALUES ('4', '23');
INSERT INTO `sys_role_permission` VALUES ('1', '24');
INSERT INTO `sys_role_permission` VALUES ('3', '24');
INSERT INTO `sys_role_permission` VALUES ('4', '24');
INSERT INTO `sys_role_permission` VALUES ('1', '25');
INSERT INTO `sys_role_permission` VALUES ('2', '25');
INSERT INTO `sys_role_permission` VALUES ('3', '25');
INSERT INTO `sys_role_permission` VALUES ('4', '25');

-- ----------------------------
-- Table structure for `sys_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `fk_roleId` (`roleId`),
  CONSTRAINT `fk_roleId` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `fk_userId` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1');
INSERT INTO `sys_role_user` VALUES ('2', '2');
INSERT INTO `sys_role_user` VALUES ('18', '2');
INSERT INTO `sys_role_user` VALUES ('27', '2');
INSERT INTO `sys_role_user` VALUES ('28', '2');
INSERT INTO `sys_role_user` VALUES ('29', '2');
INSERT INTO `sys_role_user` VALUES ('30', '2');
INSERT INTO `sys_role_user` VALUES ('41', '2');
INSERT INTO `sys_role_user` VALUES ('3', '3');
INSERT INTO `sys_role_user` VALUES ('26', '3');
INSERT INTO `sys_role_user` VALUES ('32', '3');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `headImgUrl` varchar(255) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$DFIwAy//Ol3X6Q1e5CEue.FfUnJ5Fj709z9oY1pwCWzpca.SpYs72', '管理员', null, null, '158784879852', '12@qq.com', null, null, '1', '2019-04-08 00:20:51', '2019-04-08 00:20:51');
INSERT INTO `sys_user` VALUES ('2', 'user', '$2a$10$ILWAB4ZOoRr2pXqarliI6uAuL7Q/7dAMTpWO9p7dyVSHHO7zQMTeW', '用户', null, null, '1111111111', '11@qq.com', '2019-03-31', null, '1', '2019-04-09 06:44:50', '2019-04-09 06:44:50');
INSERT INTO `sys_user` VALUES ('3', 'alex', '534b44a19bf18d20b71ecc4eb77c572f', '讲师', null, '', '13245698712', 'alex@qq.com', '2019-03-31', '1', '1', '2019-03-27 02:27:35', '2019-04-09 07:57:17');
INSERT INTO `sys_user` VALUES ('18', 'user1', '96e79218965eb72c92a549dd5a330112', '111', null, null, '123455432123', '134@qq.com', '2019-05-12', null, '1', '2019-05-14 04:44:22', '2019-05-14 04:44:22');
INSERT INTO `sys_user` VALUES ('26', 'user2', '96e79218965eb72c92a549dd5a330112', 'user2', null, null, '09876567890', 'aa@QQ.com', '2019-05-12', null, '1', '2019-05-15 02:22:21', '2019-05-21 00:57:14');
INSERT INTO `sys_user` VALUES ('27', 'user3', '96e79218965eb72c92a549dd5a330112', 'user3', null, null, '44366758876586578', 'bb@qq.com', '2019-05-14', null, '1', '2019-05-15 02:23:51', '2019-05-15 02:23:51');
INSERT INTO `sys_user` VALUES ('28', 'user4', '96e79218965eb72c92a549dd5a330112', 'user4', null, null, '2143323543456876', 'cc@qq.com', '2019-04-30', null, '1', '2019-05-15 02:24:22', '2019-05-15 02:24:22');
INSERT INTO `sys_user` VALUES ('29', 'user5', '96e79218965eb72c92a549dd5a330112', 'user5', null, null, '1221344234565', 'dd@qq.com', '2018-12-03', null, '1', '2019-05-15 02:24:49', '2019-05-15 02:24:49');
INSERT INTO `sys_user` VALUES ('30', 'user6', '96e79218965eb72c92a549dd5a330112', 'user6', null, null, '123213215135453', 'ee@qq.coom', '2019-05-15', null, '1', '2019-05-15 02:25:16', '2019-05-21 03:08:26');
INSERT INTO `sys_user` VALUES ('32', 'user7', '96e79218965eb72c92a549dd5a330112', 'user7', null, null, '21345457980765', 'tt@qq.com', '2019-05-20', null, '1', '2019-05-15 06:16:32', '2019-05-21 03:08:37');
INSERT INTO `sys_user` VALUES ('41', 'user67', '96e79218965eb72c92a549dd5a330112', 'user67', null, null, '123456324568', 'asdsa@qq.com', '2019-05-14', null, '1', '2019-05-16 08:39:11', '2019-05-16 08:39:11');
