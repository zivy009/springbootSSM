CREATE TABLE `sys_permission` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '权限表' ,
`name`  varchar(128)  ,
`code`  varchar(128)   COMMENT '资源代码' ,
`description`  varchar(255)   ,
PRIMARY KEY (`id`)
)
;

CREATE TABLE `sys_role` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '角色表' ,
`name`  varchar(128)  ,
`code`  varchar(128) ,
`description`  varchar(255) ,
`type`  int(11) NULL DEFAULT 1 COMMENT '1系统角色；2其他的下发的角色' ,
PRIMARY KEY (`id`)
);

CREATE TABLE `sys_user` (
`id`  int(20) NOT NULL AUTO_INCREMENT COMMENT '用户表' ,
`login_name`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`name`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名' ,
`password`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`salt`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ' 加密参数' ,
`addtime`  timestamp NULL DEFAULT NULL ,
`disabled`  tinyint(1) NULL DEFAULT 0 COMMENT '1禁用；0否' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `login_name` (`login_name`) USING BTREE 
);

CREATE TABLE `sys_role_permission` (
`role_id`  int(11) NOT NULL COMMENT '角色ID' ,
`permission_id` int(11)   NOT NULL COMMENT '权限' ,
PRIMARY KEY (`role_id`, `permission_id`)
)
COMMENT='角色授权';

CREATE TABLE `sys_user_role` (
`role_id`  int(11) NOT NULL COMMENT '角色ID' ,
`user_id` int(11)   NOT NULL COMMENT '用户' ,
PRIMARY KEY (`role_id`, `user_id`)
)
COMMENT='用户角色'
;



