CREATE TABLE `zz_demo` (
`id`  bigint NULL AUTO_INCREMENT ,
`name`  varchar(255) NULL ,
`addtime`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '录入时间' ,
`disabled`  tinyint(1) NULL DEFAULT 0 COMMENT '0有效；1作废' ,
PRIMARY KEY (`id`)
)
;
