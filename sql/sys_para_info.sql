/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/3/5 18:05:49                            */
/*==============================================================*/


drop table if exists sys_para_info;

/*==============================================================*/
/* Table: sys_para_info                                         */
/*==============================================================*/
create table sys_para_info
(
   PARA_ID              bigint(20) unsigned not null auto_increment comment '系统参数Id',
   PARA_CODE            national varchar(30) not null comment '系统参数类别编码',
   PARA_NAME            national varchar(30) comment '系统参数名称',
   PARA_VALUE           national varchar(50) comment '系统参数值',
   PARA_DESC            national varchar(50) comment '系统参数描述',
   IS_USED              tinyint(1) comment '启用标识',
   CREATE_TIME          datetime not null comment '创建时间',
   UPDATE_TIME          datetime comment '更新时间',
   primary key (PARA_ID),
   unique key uk_sys_para_info (PARA_CODE)
);

SELECT * from sys_para_info where PARA_CODE = 'zjf' AND IS_USED = 1