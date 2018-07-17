DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `pk_log_id` varchar(50) NOT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `oper` varchar(200) DEFAULT NULL COMMENT '干了啥',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `is_delete` int(2) DEFAULT '1',
  `fk_user_id` varchar(50) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_log_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `pk_menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限表编号',
  `menu_id` int(11) DEFAULT NULL,
  `menu_url` varchar(50) DEFAULT '#' COMMENT '栏目的url',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '栏目名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间(自动更新)',
  `is_delete` int(2) DEFAULT '1' COMMENT '是否删除',
  PRIMARY KEY (`pk_menu_id`)
) ENGINE=MyISAM AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `pk_note_id` varchar(100) NOT NULL,
  `fk_user_id` varchar(50) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `note_title` varchar(200) DEFAULT NULL,
  `note_desc_short` text,
  `note_desc` text,
  `is_stick` int(2) DEFAULT '0',
  `is_delete` int(2) DEFAULT '1',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pk_note_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `pk_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `is_delete` int(11) DEFAULT '1' COMMENT '是否已经删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`pk_role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `pk_role_perm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限表编号',
  `fk_role_id` int(11) DEFAULT NULL COMMENT '角色表id（关联角色表pk_role_id字段）',
  `menu_rights` varchar(200) DEFAULT NULL COMMENT '菜单权限',
  `add_rights` varchar(200) DEFAULT NULL COMMENT '增加的权限',
  `del_righs` varchar(200) DEFAULT NULL COMMENT '删除的权限',
  `select_rights` varchar(200) DEFAULT NULL COMMENT '查看的权限',
  `update_rights` varchar(200) DEFAULT NULL COMMENT '修改的权限',
  `is_delete` int(11) DEFAULT '1' COMMENT '是否已经删除(0表示删除，1表示没删除)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pk_role_perm_id`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `pk_user_id` varchar(50) NOT NULL COMMENT '用户编号',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录姓名',
  `login_pwd` varchar(200) DEFAULT NULL COMMENT '用户密码',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `user_img` varchar(500) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL COMMENT '邮件',
  `user_phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `is_delete` int(2) DEFAULT '1' COMMENT '是否已经删除(0表示删除，1表示没删除)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间(自动更新)',
  PRIMARY KEY (`pk_user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `pk_user_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色表编号',
  `fk_role_id` int(11) DEFAULT NULL COMMENT '角色id（关联角色表pk_role_id字段）',
  `fk_user_id` int(11) DEFAULT NULL COMMENT '用户id（关联用户信息表pk_user_id字段）',
  `is_delete` int(11) DEFAULT '1' COMMENT '是否已经删除(0表示删除，1表示没删除)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间(自动更新)',
  PRIMARY KEY (`pk_user_role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
