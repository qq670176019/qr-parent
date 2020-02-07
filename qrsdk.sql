/*
SQLyog Ultimate v8.32 
MySQL - 5.7.17-log : Database - qrsdk
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`qrsdk` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `qrsdk`;

/*Table structure for table `admin_user` */

DROP TABLE IF EXISTS `admin_user`;

CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `userId` int(11) DEFAULT NULL,
  `canDelete` varchar(45) DEFAULT '0' COMMENT '是否能被删除，0：能，1：不能',
  PRIMARY KEY (`id`),
  KEY `fk_admin_user_id_idx` (`userId`),
  CONSTRAINT `fk_admin_user_id` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `admin_user` */

insert  into `admin_user`(`id`,`userId`,`canDelete`) values (1,1,'1'),(2,2,'0'),(3,3,'0'),(4,4,'0'),(6,6,'0'),(7,7,'0'),(9,9,'0'),(10,10,'0'),(11,11,'0'),(12,12,'0'),(13,13,'0'),(14,14,'0'),(15,15,'0'),(16,16,'0'),(18,1006,'0'),(23,1012,'0'),(27,1014,'0');

/*Table structure for table `per_role` */

DROP TABLE IF EXISTS `per_role`;

CREATE TABLE `per_role` (
  `perId` int(11) DEFAULT NULL COMMENT '权限ID',
  `roleId` int(11) DEFAULT NULL COMMENT '角色ID',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_per_role_perId_idx` (`perId`),
  KEY `fk_per_role_roleId_idx` (`roleId`),
  CONSTRAINT `fk_per_role_perId` FOREIGN KEY (`perId`) REFERENCES `permissions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_per_role_roleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

/*Data for the table `per_role` */

insert  into `per_role`(`perId`,`roleId`,`id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,1,21),(22,1,22),(23,1,23),(24,1,24),(25,1,25),(26,1,26),(27,1,27),(28,1,28),(29,1,29),(30,1,30),(31,1,31),(32,1,32),(33,1,33),(34,1,34),(35,1,35),(36,1,36),(37,1,37),(38,1,38),(39,1,39),(40,1,40),(41,1,41),(42,1,42),(43,1,43),(44,1,44),(45,1,45),(46,1,46),(47,1,47),(48,1,48),(49,1,49),(50,1,50),(51,1,51),(52,1,52),(53,1,53),(54,1,54),(55,1,55);

/*Table structure for table `permissions` */

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `parentId` int(11) DEFAULT NULL COMMENT '父级ID,0代表顶级',
  `perKey` varchar(100) DEFAULT NULL COMMENT '权限资源key',
  `type` int(2) DEFAULT NULL COMMENT '资源类型,1:菜单，2：按钮',
  `perUrl` varchar(200) DEFAULT NULL COMMENT '请求url',
  `order` int(4) DEFAULT NULL COMMENT '顺序',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限',
  `description` varchar(100) DEFAULT NULL COMMENT '菜单描述',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录修改时间',
  `icon` varchar(45) DEFAULT NULL COMMENT '菜单图标',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `perKey_UNIQUE` (`perKey`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;

/*Data for the table `permissions` */

insert  into `permissions`(`id`,`name`,`parentId`,`perKey`,`type`,`perUrl`,`order`,`permission`,`description`,`createTime`,`modifyTime`,`icon`) values (1,'基础系统',0,'basicSystem',1,'/basicSystem',1,'sys','初始化菜单','2017-05-15 00:59:37','2020-02-03 21:12:32',NULL),(2,'系统配置',1,'sysSetting',1,'/basicSystem/sysSetting',1,'sys:basicSystem_sysSetting','初始化菜单','2017-05-15 00:59:37','2020-02-03 21:12:32',NULL),(3,'角色管理',1,'role',1,'/basicSystem/role',2,'sys:basicSystem_role','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:26:47',NULL),(4,'管理员管理',1,'admin',1,'/basicSystem/admin',3,'sys:basicSystem_admin','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:26:47',NULL),(5,'运营系统',0,'bizSystem',1,'/bizSystem',2,'biz','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:35:20',NULL),(6,'通知公告管理',5,'noticeManage',1,'/bizSystem/noticeManage',1,'biz:bizSystem','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:26:47',NULL),(7,'渠道管理',5,'channelManage',1,'/bizSystem/channelManage',2,'biz:channelManage','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:26:47',NULL),(8,'发行商管理',5,'cpManage',1,'/bizSystem/cpManage',3,'biz:cpManage','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:26:47',NULL),(9,'游戏管理',5,'gameManage',1,'/bizSystem/gameManage',4,'biz:gameManage','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:35:20',NULL),(10,'玩家管理',5,'gameUserManage',1,'/bizSystem/gameUserManage',5,'biz:gameUserManage','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:26:47',NULL),(11,'渠道封禁',5,'channelBanManage',1,'/bizSystem/channelBanManage',6,'biz:channelBanManage','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:26:47',NULL),(12,'结算系统',0,'fundSystem',1,'/fundSystem',3,'fund','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:35:20',NULL),(13,'游戏基础分成比例设置',12,'gameBasicRate',1,'/fundSystem/gameBasicRate',1,'fund:gameBasicRate','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:35:20',NULL),(14,'渠道特殊分成比例设置',12,'channelSpecialRate',1,'/fundSystem/channelSpecialRate',2,'fund:channelSpecialRate','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:35:20',NULL),(15,'发行商结算信息',12,'cpFundInfo',1,'/fundSystem/cpFundInfo',3,'fund:cpFundInfo','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:35:20',NULL),(16,'渠道结算信息',12,'channelFundInfo',1,'/fundSystem/channelFundInfo',4,'fund:channelFundInfo','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:35:20',NULL),(17,'渠道结算申请处理',12,'channelFundApplyDeal',1,'/fundSystem/channelFundApplyDeal',5,'fund:channelFundApplyDeal','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:35:20',NULL),(18,'渠道结算记录',12,'channelFundRecord',1,'/fundSystem/channelFundRecord',6,'fund:channelFundRecord','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:35:20',NULL),(19,'发行商结算记录',12,'cpFundRecord',1,'/fundSystem/cpFundRecord',7,'fund:cpFundRecord','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:35:20',NULL),(20,'渠道宣传网站',0,'website',1,'/website',4,'website','初始化菜单','2017-05-15 00:59:37','2020-02-03 21:00:46',NULL),(21,'代金券管理',20,'ticketManage',1,'/website/ticketManage',1,'website:ticketManage','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:50:30',NULL),(22,'游戏素材',20,'gameMaterial',1,'/website/gameMaterial',2,'website:gameMaterial','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:50:30',NULL),(23,'游戏礼包管理',20,'gameGiftManage',1,'/website/gameGiftManage',3,'website:gameGiftManage','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:50:30',NULL),(24,'开服表管理',20,'gameOpenManage',1,'/website/gameOpenManage',4,'website:gameOpenManage','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:50:30',NULL),(25,'游戏资讯管理',20,'gameNoticeManage',1,'/website/gameNoticeManage',5,'website:gameNoticeManage','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:50:30',NULL),(26,'广告管理',20,'adsManage',1,'/website/adsManage',6,'website:adsManage','初始化菜单','2017-05-15 00:59:37','2020-02-03 21:12:32',NULL),(27,'猜你喜欢',20,'guessLike',1,'/website/guessLike',7,'website:guessLike','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:50:30',NULL),(28,'消息推送',20,'msgPush',1,'/website/msgPush',8,'website:msgPush','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:50:30',NULL),(29,'友情链接',20,'friendLink',1,'/website/friendLink',9,'website:friendLink','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:50:30',NULL),(30,'app更新',20,'appUpdate',1,'/website/appUpdate',10,'website:appUpdate','初始化菜单','2017-05-15 00:59:37','2020-02-03 20:50:30',NULL),(31,'数据查询系统',0,'dataQuerySystem',1,'/dataQuerySystem',5,'dataQuerySystem','初始化菜单','2017-05-15 00:59:37','2020-02-03 21:03:53',NULL),(32,'用户数据',31,'userData',1,'/dataQuerySystem/userData',1,'dataQuerySystem:userData','初始化菜单','2017-05-15 00:59:37','2020-02-03 21:03:53',NULL),(33,'消费记录',31,'consumeData',1,'/dataQuerySystem/consumeData',2,'dataQuerySystem:consumeData','初始化菜单','2017-05-15 00:59:37','2020-02-03 21:12:32',NULL),(34,'渠道数据',31,'channelData',1,'/dataQuerySystem/channelData',3,'dataQuerySystem:channelData','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(35,'游戏数据',31,'gameData',1,'/dataQuerySystem/gameData',4,'dataQuerySystem:gameData','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(36,'充值记录',31,'rechargeData',1,'/dataQuerySystem/rechargeData',5,'dataQuerySystem:rechargeData','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(37,'挂靠记录',31,'changeData',1,'/dataQuerySystem/changeData',6,'dataQuerySystem:changeData','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(38,'平台币操作记录',31,'platCoinOperRecord',1,'/dataQuerySystem/platCoinOperRecord',7,'dataQuerySystem:platCoinOperRecord','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(39,'返利申请记录',31,'rebateApplyRecord',1,'/dataQuerySystem/rebateApplyRecord',8,'dataQuerySystem:rebateApplyRecord','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(40,'积分操作记录',31,'pointsOperRecord',1,'/dataQuerySystem/pointsOperRecord',9,'dataQuerySystem:pointsOperRecord','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(41,'代金券使用记录',31,'ticketUseRecord',1,'/dataQuerySystem/ticketUseRecord',10,'dataQuerySystem:ticketUseRecord','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(42,'渠道提现记录',31,'channelCashRecord',1,'/dataQuerySystem/channelCashRecord',11,'dataQuerySystem:channelCashRecord','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(43,'账号交易记录',31,'accountDealRecord',1,'/dataQuerySystem/accountDealRecord',12,'dataQuerySystem:accountDealRecord','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(44,'礼包申请记录',31,'giftApplyRecord',1,'/dataQuerySystem/giftApplyRecord',13,'dataQuerySystem:giftApplyRecord','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(45,'活动参与记录',31,'actAttendRecord',1,'/dataQuerySystem/actAttendRecord',14,'dataQuerySystem:actAttendRecord','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(46,'客服系统',0,'serviceSystem',1,'/serviceSystem',6,'serviceSystem','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(47,'账号交易帖子审核',48,'dealPostVerify',1,'/serviceSystem/dealPostVerify',1,'serviceSystem:dealPostVerify','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(48,'账号交易审核',48,'dealVerify',1,'/serviceSystem/dealVerify',2,'serviceSystem:dealVerify','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(49,'返利申请处理',48,'rebateApplyVerify',1,'/serviceSystem/rebateApplyVerify',3,'serviceSystem:rebateApplyVerify','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(50,'投诉处理',48,'complainDeal',1,'/serviceSystem/complainDeal',4,'serviceSystem:complainDeal','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(51,'监控系统',0,'monitorSystem',1,'/monitorSystem',7,'monitorSystem','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(52,'性能监控',53,'funcMonitor',1,'/monitorSystem/funcMonitor',1,'monitorSystem:funcMonitor','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(53,'登录日志',53,'loginLog',1,'/monitorSystem/loginLog',2,'monitorSystem:loginLog','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(54,'计划任务',53,'crontab',1,'/monitorSystem/crontab',3,'monitorSystem:crontab','初始化菜单','2017-05-15 00:59:37','2020-02-05 15:18:41',NULL),(55,'控制台',0,'console',1,'/console',7,'console','初始化菜单','2020-02-07 12:48:40','2020-02-07 12:48:43',NULL);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `roleKey` varchar(50) DEFAULT NULL COMMENT '角色标识符',
  `description` varchar(50) DEFAULT NULL COMMENT '角色描述',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除，1：禁用 2：删除',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录修改时间',
  `canDelete` int(11) DEFAULT '0' COMMENT '是否可以被删除，0：可以，1：不可以',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `roleKey_UNIQUE` (`roleKey`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`roleKey`,`description`,`status`,`createTime`,`modifyTime`,`canDelete`) values (1,'管理员','sys_manager','管理员-不可编辑和删除','0','2017-02-14 15:16:57','2020-02-04 23:00:15',1),(2,'一级渠道','sys_first_class_channel','一级渠道-不可编辑和删除','0','2017-03-12 01:23:32','2020-02-04 23:00:16',1),(3,'二级渠道','sys_second_class_channel','二级渠道-不可编辑和删除','0','2017-03-12 01:24:08','2020-02-04 23:00:18',1),(4,'发行商','sys_cp','发行商-不可编辑和删除','0','2017-04-04 22:19:04','2020-02-04 23:00:20',1),(5,'游戏玩家','game_user','游戏玩家-不可编辑和删除','0','2017-11-07 15:14:20','2020-02-04 23:00:24',1),(6,'默认无组用户','no_team','默认无组用户','0','2017-11-07 15:15:25','2020-02-05 15:22:53',0),(9,'客服组','service','客服组','0','2020-02-05 19:07:45','2020-02-05 19:07:45',0),(14,'测试角色组','ceshijuese','测试角色组','1','2020-02-06 15:45:17','2020-02-06 16:05:27',0);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(32) DEFAULT NULL COMMENT '用户姓名',
  `mobile` varchar(11) DEFAULT NULL COMMENT '用户手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '用户邮箱地址',
  `username` varchar(32) DEFAULT NULL COMMENT '用户登录ID',
  `password` varchar(100) DEFAULT NULL COMMENT '用户密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '加密私盐',
  `locked` char(1) NOT NULL DEFAULT '0' COMMENT '账户锁定状态0:未锁定，1：锁定',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除状态0:存在1:禁用 2:''删除''',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `accountName_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1016 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`mobile`,`email`,`username`,`password`,`salt`,`locked`,`status`,`createTime`,`modifyTime`) values (1,'超级管理员','18615266456','729466718@qq.com','admin','630989b25c7fc6dc5b491a5edcca9bc5bb02f2c943anc95e','38276595c95024n5C9OaBj16r7801ed3','0','0','2017-02-18 10:01:36','2020-02-05 14:06:40'),(2,'渠道管理员','18810788870','lihua_zhuan@126.com','sysRoot','8ofdI607a7N6akd2i9cn5cm33G92kc907a0ad06212f68829','oI7NkinmGk000162192i864L01763e09','0','1','2017-02-19 18:44:30','2020-02-06 12:44:40'),(3,'普通管理员','18816766654','sys@126.com','sys','a-a-a-a','4w+UuWxWTurL9ATUDkhDQFhkCvPvrq5Z','1','0','2017-05-20 19:15:20','2020-02-03 22:08:38'),(4,'鹿丸','15554167116','85563766@qq.com','ceshi666','8169wabb1a35cF73Y11zaeC6872b2bbybeA87p7a6a4M7enb','1wb3FYzC72yAp6Mn4VS7X1ZK0Yk51F11','0','0','2017-10-16 19:19:21','2020-02-03 22:08:38'),(5,'测试用户','18988765543','sha@126.com','sha','fe61dc883e1856d8b49ccb118c3536d6','cDvL6YECyktmYxGD8BxU20VvSYF3Tpxa','1','0','2017-11-13 22:42:11','2020-02-03 22:08:38'),(6,'546254786','15105323625','123456@qq.com','546254786','6C60783A86p42M7fTdf3d8obdY1278bVe983b5b47e01a0yc','C7ApMT3oY7V8571y72yEu0jj369R9D2Z','0','0','2018-02-10 01:12:54','2020-02-03 22:08:38'),(7,'aliyun','18615266456','123@qq.com','aliyun','8206a8e0a719d0f1d961a01e9ce71d7f','w3GcmcO7NPgmVbwirbq5f7NhHn4FC/QP','1','0','2018-04-05 00:01:19','2020-02-03 22:08:38'),(9,'纲手','15266222439','448313222@qq.com','gangshou','d6ed3413b331d0bc0d53ac21421c17cc','4HPl8MU8Q+7lPt+vIG/xC4g4aLMQ5bh6','0','0','2018-05-10 07:18:08','2020-02-03 22:08:38'),(10,'雏田','15105323625','3248507694@qq.com','chutian','c1cf88czeai181e1U23b4b99dm96y4d38422aWa81f9Of159','18zi1Ub9my32W1O5f1U1Irg0eo1221As','1','0','2018-05-10 07:26:14','2020-02-03 22:08:38'),(11,'小南','13345678900','3056938408@qq.com','xiaonan','15f80ecbbc355e1decebc2c78a760dfc','rSrieKO2OxmV26eDDQh2CPwRO/N2jPxU','0','0','2018-05-10 07:28:02','2020-02-03 22:08:38'),(12,'止水','13345678900','956610008@qq.com','zhishui','63b70aaf0211880c4899f5a88235144f','1TR5S0sSmUH/yjwnDLAiOLwISYZ/Ffzs','1','0','2018-05-10 07:29:15','2020-02-03 22:08:38'),(13,'小樱','13345678900','869051836@qq.com','xiaoying','9829bf702209823c7fc512338664f2e0','yxos3vbkgK64if192kZinlLYYIeeqQze','0','0','2018-05-10 07:30:20','2020-02-03 22:08:38'),(14,'wifi运营商','18611174451','3393530452@qq.com','7r11111','95d39da27ce001c7194e5c84bebd9b3d','BKlITUD9TWaFnAJ8WXPFnlE2XkffKhqb','0','1','2018-06-26 08:02:08','2020-02-03 22:08:38'),(15,'test客服','18615266456','729466718@qq.com','kefu','42361bd2bd3bfb03965635fa1d6ed7f6','1wVQafeHWpxQerW3der+tRPZgbxXxkug','1','0','2018-07-12 08:00:42','2020-02-03 22:08:38'),(16,'功能验证账号','18615266456','123@qq.com','verify','9496ac01c7f1c63b7d028bf9628beff0','GmFhSHtUdZSIUOX49kYKeCY2vBCzniDT','0','0','2018-09-05 12:03:06','2020-02-03 22:08:38'),(115,'name','13220557785','12@qq.com','name','6P4cEaf744X10f0be1dOf8b66pb3jcfy446adSe6F5cTd006','PE7XfeObpjy6SFT0jC06hlR76M2LGT1D','0','0','2020-01-30 15:10:28','2020-02-03 22:08:38'),(116,'name2','13220557785','12@qq.com','name2','4V1b4669f0O9bp4e5a71edYd1242297v919cd77b708V656e','V49Op51Y22v977V67308GF14l8Q8XZe3','1','1','2020-01-30 15:11:14','2020-02-03 22:08:38'),(117,'1','13220557785','12@qq.com','eersx','5pcclb1eb3Q7163f4d476cC458f1214096230ye653fs0172','pleQ647C8202y5s7s7889Tg06v96ks3r','0','0','2020-01-30 15:32:02','2020-02-03 22:08:38'),(1000,'2',NULL,NULL,'2','2','2','0','0','2020-02-03 21:56:38','2020-02-03 22:08:38'),(1002,'name4','13220557785','12@qq.com','name22','fNb75694fe1a5709y774bd0e9z21yaf06f82c5cclaaqc520','N5417y40zy085lq2fPNi97g950Qi19J3','0','0','2020-02-04 18:12:00','2020-02-04 18:12:00'),(1003,'naannanan','13220557785','12@qq.com','naannanan','9da982dR41hd7Nd0Pe186c0f4T29jbayb8ub0hd5764re38a','d8RhNP80Tjyuh7r8YzA4Tg6ArokHwE37','0','0','2020-02-04 20:45:56','2020-02-04 20:45:56'),(1005,'uuuuuuu',NULL,NULL,NULL,NULL,NULL,'0','0','2020-02-05 15:26:24','2020-02-05 15:26:24'),(1006,'guanguan','13220557785','123@qq.com','guanguan','8ne12f917765d6410edB4c882u7cb95b85h8350720bieep1','n21660B8ubbh52ip78C1b4dEjZKl2707','0','0','2020-02-05 17:20:00','2020-02-05 17:20:00'),(1012,'sdfasdfsad','13220557785','12@qq.com','sdfasdfsad','752dUe7I1bg46Vb0zdb7e1877R46Z3f5eel785d5C595f9Xb','5UIgVz78RZ5l5C5X12O5vn801l3Q2Dx9','0','0','2020-02-06 14:01:03','2020-02-06 14:01:03'),(1014,'qweqwe','13220557785','123@qq.com','qweqweq','cga1T60472i24W791b32a127cve9t9a7c15805866dca0362','gT4iW122vt7556a600c50yzu7b89Bh8r','0','0','2020-02-06 15:18:56','2020-02-06 15:18:56');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `userId` int(11) DEFAULT NULL COMMENT '用户id',
  `roleId` int(11) DEFAULT '6' COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `fk_use_role_userid_idx` (`userId`),
  KEY `fk_user_role_roleId_idx` (`roleId`),
  CONSTRAINT `fk_use_role_userid` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_roleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='用户-角色表,暂时用不到，一共就4种用户\n1：管理员  2：渠道  3：发行商 4：玩家';

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`userId`,`roleId`) values (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,1),(8,9,1),(9,10,1),(10,11,1),(11,12,1),(12,13,1),(13,14,1),(14,15,1),(15,16,1),(22,1012,9);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
