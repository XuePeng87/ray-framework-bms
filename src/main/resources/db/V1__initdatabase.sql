-- 初始化系统按钮表
DROP TABLE IF EXISTS `sys_button`;
CREATE TABLE `sys_button` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单主键',
  `button_title` varchar(25) NOT NULL DEFAULT '' COMMENT '名称',
  `button_icon` varchar(200) NOT NULL DEFAULT '' COMMENT '图标',
  `button_remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_user` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `modify_user` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统按钮表';
LOCK TABLES `sys_button` WRITE;
INSERT INTO `sys_button` (`id`, `menu_id`, `button_title`, `button_icon`, `button_remark`, `deleted`, `create_user`, `modify_user`, `create_time`, `modify_time`)
VALUES
	(1,2,'add','','创建',0,'root','root','2020-01-07 10:03:25','2020-01-08 10:32:32'),
	(10,3,'authorizationMenu','','授权菜单',0,'root','root','2020-01-07 10:06:35','2020-01-08 10:32:41'),
	(11,3,'batchDelete','','批量删除',0,'root','root','2020-01-07 10:05:04','2020-01-08 10:32:42'),
	(12,5,'check','','查询',0,'root','root','2020-01-07 10:05:04','2020-01-08 10:32:42'),
	(13,6,'authorizationApi','','授权API',0,'root','root','2020-05-20 11:42:47','2020-05-20 11:42:47'),
	(14,7,'add','','创建',0,'root','root','2020-05-20 11:42:47','2020-05-20 11:42:47'),
	(15,7,'edit','','修改',0,'root','root','2020-05-20 11:42:47','2020-05-20 11:42:47'),
	(16,7,'delete','','删除',0,'root','root','2020-05-20 11:42:47','2020-05-20 11:42:47'),
	(17,7,'setUser','','设置人员',0,'root','root','2020-05-20 11:42:47','2020-05-20 11:42:47'),
	(18,7,'addChildren','','创建子部门',0,'root','root','2020-05-20 11:42:47','2020-05-20 11:42:47'),
	(19,8,'typeAdd','','创建分类',0,'root','root','2020-05-20 11:42:47','2020-05-20 11:42:47'),
	(20,8,'typeEdit','','编辑分类',0,'root','root','2020-05-20 11:42:47','2020-05-20 11:42:47'),
	(21,8,'typeDel','','删除分类',0,'root','root','2020-05-20 11:42:47','2020-05-20 11:42:47'),
	(22,8,'optAdd','','创建字典',0,'root','root','2020-05-20 11:42:47','2020-05-20 11:42:47'),
	(23,8,'optEdit','','编辑字典',0,'root','root','2020-05-20 11:42:47','2020-05-20 11:42:47'),
	(24,8,'optDel','','删除字典',0,'root','root','2020-05-20 11:42:47','2020-05-20 11:42:47'),
	(2,2,'delete','','删除',0,'root','root','2020-01-07 10:03:45','2020-01-08 10:32:32'),
	(3,2,'edit','','修改',0,'root','root','2020-01-07 10:03:37','2020-01-08 10:32:33'),
	(4,2,'reset','','重置密码',0,'root','root','2020-01-07 10:04:16','2020-01-08 10:32:34'),
	(5,2,'batchDelete','','批量删除',0,'root','root','2020-01-07 10:04:00','2020-01-08 10:32:35'),
	(6,3,'authorizationUser','','授权用户',0,'root','root','2020-01-07 10:06:01','2020-01-08 10:32:35'),
	(7,3,'add','','创建',0,'root','root','2020-01-07 10:05:14','2020-01-08 10:32:36'),
	(8,3,'delete','','删除',0,'root','root','2020-01-07 10:05:29','2020-01-08 10:32:38'),
	(9,3,'edit','','修改',0,'root','root','2020-01-07 10:05:21','2020-01-08 10:32:38');
UNLOCK TABLES;

-- 初始化系统菜单表
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级主键',
  `menu_title` varchar(50) NOT NULL COMMENT '标题',
  `menu_title_en` varchar(50) NOT NULL COMMENT '英文标题',
  `menu_icon` varchar(200) NOT NULL DEFAULT '' COMMENT '图标',
  `menu_cache` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否缓存',
  `menu_path` varchar(200) NOT NULL DEFAULT '' COMMENT '路径',
  `menu_level` smallint(6) NOT NULL DEFAULT '1' COMMENT '层级',
  `menu_sort` smallint(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `menu_remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `fixed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否内置',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_user` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `modify_user` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单表';
INSERT INTO `sys_menu` (`id`, `menu_pid`, `menu_title`, `menu_title_en`, `menu_icon`, `menu_path`, `menu_level`, `menu_sort`, `menu_remark`, `fixed`, `deleted`, `create_user`, `modify_user`, `create_time`, `modify_time`)
VALUES
	(1,0,'系统管理','System Management','xitongguanli','/system',1,1,'系统管理',0,0,'root','root','2019-12-23 14:59:48','2019-12-27 14:39:47'),
	(2,1,'用户管理','User Management','yonghuguanli','/system/user',2,1,'用户管理',0,0,'root','root','2019-12-23 15:00:05','2020-01-16 13:58:11'),
	(3,1,'角色管理','Role Management','jiaoseguanli','/system/role',2,2,'角色管理',0,0,'root','root','2019-12-23 15:00:22','2020-01-10 15:27:40'),
	(4,1,'功能管理','Function Management','gongnengguanli','/system/function',2,4,'功能管理',1,0,'root','root','2019-12-23 15:00:46','2020-01-10 15:27:41'),
	(5,1,'操作日志','Operation Log','caozuorizhi','/system/log',2,6,'操作日志',0,0,'root','root','2020-01-16 13:58:08','2020-01-16 14:00:20'),
	(6,1,'接口授权','API Authorization','jiekoushouquan','/system/api',2,5,'接口授权',1,0,'root','root','2020-05-20 10:53:39','2020-05-20 10:53:39'),
	(7,1,'部门管理','Department Management','bumenguanli','/system/dept',2,3,'部门管理',0,0,'root','root','2020-05-20 10:53:39','2020-05-20 10:53:39'),
	(8,1,'数据字典','Dictionary Management','shujuzidian','/system/dict',2,7,'数据字典',0,0,'root','root','2020-05-20 10:53:39','2020-05-20 10:53:39');

-- 初始化系统操作记录表
DROP TABLE IF EXISTS `sys_op_log`;
CREATE TABLE `sys_op_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(50) NOT NULL DEFAULT '' COMMENT '操作账号',
  `module` varchar(20) NOT NULL DEFAULT '' COMMENT '模块',
  `description` varchar(50) NOT NULL DEFAULT '' COMMENT '描述',
  `src_ip` varchar(15) NOT NULL DEFAULT '' COMMENT '访问IP',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统操作表';
DROP TABLE IF EXISTS `sys_op_log_content`;
CREATE TABLE `sys_op_log_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_id` bigint(20) NOT NULL COMMENT '日志主键',
  `content` json COMMENT '操作内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统操作详情表';

-- 初始化系统角色表
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(25) NOT NULL DEFAULT '' COMMENT '编号',
  `role_name` varchar(25) NOT NULL DEFAULT '' COMMENT '名称',
  `role_remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `fixed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否内置',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_user` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `modify_user` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';
INSERT INTO `sys_role` (`id`, `role_code`, `role_name`, `role_remark`, `fixed`, `deleted`, `create_user`, `modify_user`, `create_time`, `modify_time`)
VALUES
	(1,'ROLE_ADMIN','超级管理员','超级管理员',1,0,'root','root','2019-12-23 14:59:03','2020-01-16 14:03:32');

-- 初始化系统角色与API关系表
DROP TABLE IF EXISTS `sys_role_api_relation`;
CREATE TABLE `sys_role_api_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色主键',
  `api_url` varchar(200) NOT NULL DEFAULT '' COMMENT 'API后台接口地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色与API关系表';
INSERT INTO `sys_role_api_relation` (`id`, `role_id`, `api_url`)
VALUES
	(1,1,'/v1/operation-logs/**'),
	(2,1,'/v1/roles/**'),
	(3,1,'/v1/buttons/**'),
	(4,1,'/v1/users/**'),
	(5,1,'/v1/menus/**'),
	(6,1,'/v1/depts/**'),
	(7,1,'/v1/dict-types/**'),
	(8,1,'/v1/dicts/**');

-- 初始化系统角色与功能关系表
DROP TABLE IF EXISTS `sys_role_function_relation`;
CREATE TABLE `sys_role_function_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色主键',
  `function_id` bigint(20) NOT NULL COMMENT '功能主键',
  `function_type` smallint(6) NOT NULL COMMENT '功能类型：0=菜单，1=按钮',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色与功能关系表';
INSERT INTO `sys_role_function_relation` (`id`, `role_id`, `function_id`, `function_type`)
VALUES
	(1,1,1,0),
	(10,1,5,1),
	(11,1,6,1),
	(12,1,7,1),
	(13,1,8,1),
	(14,1,9,1),
	(15,1,10,1),
	(16,1,11,1),
	(17,1,12,1),
	(18,1,6,0),
	(19,1,13,1),
	(20,1,7,0),
	(21,1,14,1),
	(22,1,15,1),
	(23,1,16,1),
	(24,1,17,1),
	(25,1,18,1),
	(26,1,8,0),
	(27,1,19,1),
	(28,1,20,1),
	(29,1,21,1),
	(2,1,2,0),
	(3,1,3,0),
	(4,1,4,0),
	(5,1,5,0),
	(6,1,1,1),
	(7,1,2,1),
	(8,1,3,1),
	(9,1,4,1);

-- 初始化系统角色与用户关系表
DROP TABLE IF EXISTS `sys_role_user_relation`;
CREATE TABLE `sys_role_user_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色与用户关系表';
INSERT INTO `sys_role_user_relation` (`id`, `role_id`, `user_id`)
VALUES
	(1,1,1);

-- 初始化系统用户表
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_account` varchar(50) NOT NULL DEFAULT '' COMMENT '账号',
  `user_secret` varchar(36) NOT NULL DEFAULT '' COMMENT '密码',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '姓名',
  `user_phone` varchar(25) NOT NULL DEFAULT '' COMMENT '电话',
  `user_email` varchar(200) NOT NULL DEFAULT '' COMMENT '邮箱',
  `user_avator` varchar(200) NOT NULL DEFAULT '' COMMENT '头像',
  `user_availabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0=停用；1=启用；',
  `user_remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `fixed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否内置',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_user` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `modify_user` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';
INSERT INTO `sys_user` (`id`, `user_account`, `user_secret`, `user_name`, `user_phone`, `user_email`, `user_avator`, `user_availabled`, `user_remark`, `fixed`, `deleted`, `create_user`, `modify_user`, `create_time`, `modify_time`)
VALUES
	(1,'root','670b14728ad9902aecba32e22fa4f6bd','超级管理员','13888888888','root@qq.com','',1,'',1,0,'root','root','2019-12-20 14:22:09','2020-01-14 17:31:37');

-- 初始化系统部门表
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `dept_pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '父部门主键',
  `dept_phone_number` varchar(20) NOT NULL DEFAULT '' COMMENT '电话',
  `dept_level` smallint(6) NOT NULL DEFAULT '0' COMMENT '级别',
  `dept_sort` smallint(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `dept_remark` varchar(50) NOT NULL DEFAULT '' COMMENT '备注',
  `dept_availabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0=停用；1=启用；',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_user` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `modify_user` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统部门表';

-- 初始化系统部门与用户关系表
DROP TABLE IF EXISTS `sys_dept_user_relation`;
CREATE TABLE `sys_dept_user_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_id` bigint(20) NOT NULL COMMENT '部门主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键',
  `leader` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是负责人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统部门与用户关系表';

-- 初始化数据字典表
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_type_name` varchar(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '字典类型名称',
  `dict_type_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `dict_remark` varchar(500) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_user` varchar(50) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` varchar(50) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '修改人',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统字典类型表';

DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_type` bigint(20) NOT NULL COMMENT '字典类型',
  `dict_code` varchar(50) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(50) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '字典键值',
  `dict_sort` smallint(6) NOT NULL DEFAULT '0' COMMENT '字典排序',
  `dict_css_class` varchar(128) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '样式属性（其他样式扩展）',
  `dict_list_class` varchar(128) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '表格回显样式',
  `dict_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否默认',
  `dict_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `dict_remark` varchar(500) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_user` varchar(50) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` varchar(50) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '更新者',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统字典表';