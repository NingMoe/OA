/*
Navicat MySQL Data Transfer

Source Server         : school
Source Server Version : 50535
Source Host           : 192.168.11.144:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-05-21 17:20:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accessoryfile
-- ----------------------------
DROP TABLE IF EXISTS `accessoryfile`;
CREATE TABLE `accessoryfile` (
  `AccessoryId` int(11) NOT NULL AUTO_INCREMENT,
  `FileId` int(11) NOT NULL,
  `AccessoryType` int(11) NOT NULL,
  `AccessoryName` varchar(50) NOT NULL,
  `AccessorySize` int(11) NOT NULL,
  `CreateDate` datetime NOT NULL,
  `AccessoryPath` varchar(200) NOT NULL,
  PRIMARY KEY (`AccessoryId`),
  KEY `FK_gv06hifpq1tfc23a2uwwkqlu4` (`FileId`),
  KEY `FK_gvhrnbjl275d6oakl28u2jwqj` (`AccessoryType`),
  CONSTRAINT `FK_gv06hifpq1tfc23a2uwwkqlu4` FOREIGN KEY (`FileId`) REFERENCES `fileinfo` (`FileId`),
  CONSTRAINT `FK_gvhrnbjl275d6oakl28u2jwqj` FOREIGN KEY (`AccessoryType`) REFERENCES `filetypeinfo` (`FileTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accessoryfile
-- ----------------------------

-- ----------------------------
-- Table structure for branchinfo
-- ----------------------------
DROP TABLE IF EXISTS `branchinfo`;
CREATE TABLE `branchinfo` (
  `BranchId` int(11) NOT NULL AUTO_INCREMENT,
  `BranchName` varchar(50) NOT NULL,
  `BranchShortName` varchar(50) NOT NULL,
  PRIMARY KEY (`BranchId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of branchinfo
-- ----------------------------
INSERT INTO `branchinfo` VALUES ('1', '北大青鸟集团', '青鸟');
INSERT INTO `branchinfo` VALUES ('2', '中国科学院', '中科院');

-- ----------------------------
-- Table structure for departinfo
-- ----------------------------
DROP TABLE IF EXISTS `departinfo`;
CREATE TABLE `departinfo` (
  `DepartId` int(11) NOT NULL AUTO_INCREMENT,
  `PrincipalUser` varchar(50),
  `BranchId` int(11) NOT NULL,
  `DepartName` varchar(50) NOT NULL,
  `ConnectTelNo` varchar(20) DEFAULT NULL,
  `ConnectMobileTelNo` bigint(20) DEFAULT NULL,
  `Faxes` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`DepartId`),
  KEY `FK_qg76ch6pdacl3u3mqtwkrlsk6` (`PrincipalUser`),
  KEY `FK_qqh3iu426hi35ev7bf7mcl17i` (`BranchId`),
  CONSTRAINT `FK_qg76ch6pdacl3u3mqtwkrlsk6` FOREIGN KEY (`PrincipalUser`) REFERENCES `userinfo` (`UserId`),
  CONSTRAINT `FK_qqh3iu426hi35ev7bf7mcl17i` FOREIGN KEY (`BranchId`) REFERENCES `branchinfo` (`BranchId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of departinfo
-- ----------------------------
INSERT INTO `test`.`departinfo` (`DepartId`, `PrincipalUser`, `BranchId`, `DepartName`, `ConnectTelNo`, `ConnectMobileTelNo`, `Faxes`) VALUES ('1', 'zhu', '1', '日程管理部', '020-66666666', '18656460515', '0592-88887777');
INSERT INTO `test`.`departinfo` (`DepartId`, `PrincipalUser`, `BranchId`, `DepartName`, `ConnectTelNo`, `ConnectMobileTelNo`, `Faxes`) VALUES ('2', 'cai', '1', '人事管理部', '020-66666666', '18656460515', '0592-88887777');
INSERT INTO `test`.`departinfo` (`DepartId`, `PrincipalUser`, `BranchId`, `DepartName`, `ConnectTelNo`, `ConnectMobileTelNo`, `Faxes`) VALUES ('3', 'gao', '1', '考勤管理部', '020-66666666', '18656460515', '0592-88887777');
INSERT INTO `test`.`departinfo` (`DepartId`, `PrincipalUser`, `BranchId`, `DepartName`, `ConnectTelNo`, `ConnectMobileTelNo`, `Faxes`) VALUES ('4', 'liu', '1', '消息管理部', '020-66666666', '18656460515', '0592-88887777');
INSERT INTO `test`.`departinfo` (`DepartId`, `PrincipalUser`, `BranchId`, `DepartName`, `ConnectTelNo`, `ConnectMobileTelNo`, `Faxes`) VALUES ('5', 'zhou', '1', '文档管理部', '020-66666666', '18656460515', '0592-88887777');

-- ----------------------------
-- Table structure for fileinfo
-- ----------------------------
DROP TABLE IF EXISTS `fileinfo`;
CREATE TABLE `fileinfo` (
  `FileId` int(11) NOT NULL AUTO_INCREMENT,
  `FileOwner` varchar(50) NOT NULL,
  `FileType` int(11) NOT NULL,
  `FileName` varchar(50) NOT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  `CreateDate` datetime NOT NULL,
  `ParentId` int(11) NOT NULL,
  `FilePath` varchar(200) NOT NULL,
  `IfDelete` int(11) NOT NULL,
  `pids` varchar(50),
  PRIMARY KEY (`FileId`),
  KEY `FK_l32xhm344wbnhinqsuw529kng` (`FileOwner`),
  KEY `FK_1n62molt8two90ngj5r9mhuwm` (`FileType`),
  KEY `FK_cutkhk6dlwnldcvriqttw050e` (`ParentId`),
  CONSTRAINT `FK_1n62molt8two90ngj5r9mhuwm` FOREIGN KEY (`FileType`) REFERENCES `filetypeinfo` (`FileTypeId`),
  CONSTRAINT `FK_cutkhk6dlwnldcvriqttw050e` FOREIGN KEY (`ParentId`) REFERENCES `fileinfo` (`FileId`),
  CONSTRAINT `FK_l32xhm344wbnhinqsuw529kng` FOREIGN KEY (`FileOwner`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fileinfo
-- ----------------------------
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('1', 'cai', '1', '文档管理', '这一定不是文档根目录', '2014-05-15 11:58:11', '1', '文档管理/', '0', ',1,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('2', 'cai', '1', '公共文档', '对整个系统的整体', '2014-05-14 16:35:50', '1', '文档管理/公共文档', '0', ',1,2,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('3', 'cai', '1', '头像', '所有员工的头像文件夹', '2014-05-14 16:43:33', '3', 'upload/', '0', ',3,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('4', 'chen', '1', '管理制度', '公司的管理规范', '2014-05-16 11:57:22', '1', '文档管理/管理制度', '0', ',1,4,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('5', 'gao', '1', '部门文档', '各部门的文件分类', '2014-05-16 11:59:05', '1', '文档管理/部门文档', '0', ',1,5,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('6', 'liu', '1', '研发部', '研发部相关资料', '2014-05-16 11:59:05', '5', '文档管理/部门文档/研发部', '0', ',1,5,6,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('7', 'wang', '1', '部门员工介绍', '介绍研发部所有成员', '2014-05-16 11:59:05', '6', '文档管理/部门文档/研发部/部门员工介绍', '0', ',1,5,6,7,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('8', 'zhou', '1', '所有项目', '所有可用的项目汇总', '2014-05-16 11:59:05', '6', '文档管理/部门文档/研发部/所有项目', '0', ',1,5,6,8,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('9', 'zhu', '1', '技术部', '技术部相关资料', '2014-05-13 10:33:56', '5', '文档管理\\部门管理\\技术部', '0', ',1,5,6,9,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('14', 'cai', '14', 'cai.JPG', '我的头像', '2014-05-26 10:58:01', '3', 'upload/cai.JPG', '0', ',3,14,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('15', 'gao', '14', 'gao.jpeg', '高雅的头像', '2014-05-14 14:17:14', '3', 'upload/gao.jpg', '0', ',3,15,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('16', 'wang', '14', 'wang.jpeg', '王鹏的头像', '2014-05-10 10:57:47', '3', 'upload/wang.jpeg', '0', ',3,16,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('17', 'zhu', '12', 'zhu.png', '朱义传的头像', '2014-05-10 11:14:28', '3', 'upload/zhu.png', '0', ',3,17,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('18', 'chen', '14', 'chen.jpg', '', '2014-05-12 13:31:39', '3', 'upload/chen.jpg', '0', ',3,18,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('19', 'liu', '14', 'liu.jpeg', '', '2014-05-12 13:44:51', '3', 'upload/liu.jpeg', '0', ',3,19,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('20', 'cai', '13', 'log', '', '2014-05-26 09:01:25', '7', '文档管理/部门文档/研发部/部门员工介绍/log.bmp', '0', ',1,5,6,7,20,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('21', 'cai', '14', '原始青鸟桌面', '', '2014-05-26 09:01:26', '7', '文档管理/部门文档/研发部/部门员工介绍/原始青鸟桌面.jpg', '0', ',1,5,6,7,21,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('22', 'cai', '15', '虚拟机密码说明', '', '2014-05-26 09:52:03', '7', '文档管理/部门文档/研发部/部门员工介绍/虚拟机密码说明.txt', '0', ',1,5,6,7,22,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('23', 'cai', '15', '虚拟机密码说明', '', '2014-05-26 09:52:12', '7', '文档管理/部门文档/研发部/部门员工介绍/虚拟机密码说明.txt', '0', ',1,5,6,7,23,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('24', 'cai', '16', '新建 Microsoft Office PowerPoint 97-2003 演示文稿', '', '2014-05-26 09:55:13', '7', '文档管理/部门文档/研发部/部门员工介绍/新建 Microsoft Office PowerPoint 97-2003 演示文稿.ppt', '0', ',1,5,6,7,24,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('25', 'cai', '17', '6', '', '2014-05-26 10:04:44', '4', '文档管理/管理制度/6.xls', '0', ',1,4,25,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('26', 'cai', '18', '2', '', '2014-05-26 10:04:45', '4', '文档管理/管理制度/2.xlsx', '0', ',1,4,26,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('28', 'cai', '19', '4', '', '2014-05-26 10:04:46', '4', '文档管理/管理制度/4.pptx', '0', ',1,4,28,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('29', 'cai', '20', '1', '', '2014-05-26 10:25:58', '4', '文档管理/管理制度/1.pdf', '0', ',1,4,29,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('30', 'cai', '21', '5', '', '2014-05-26 10:26:49', '4', '文档管理/管理制度/5.rar', '0', ',1,4,30,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('31', '娃哈哈', '14', '娃哈哈.bmp', '', '2014-05-26 10:58:53', '3', 'upload/娃哈哈.bmp', '0', ',3,31,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('32', 'cai', '14', '原始青鸟桌面', '阿拉伯', '2014-06-16 10:02:14', '4', '文档管理\\管理制度\\原始青鸟桌面.jpg', '0', ',1,4,32,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('33', 'cai', '1', '你好啊', '', '2014-06-16 10:14:10', '1', '文档管理\\你好啊', '1', ',1,33,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('34', 'cai', '13', 'log', '', '2014-06-16 10:20:46', '33', '文档管理\\你好啊\\log.bmp', '1', ',1,33,34,');
INSERT INTO `test`.`fileinfo` (`FileId`, `FileOwner`, `FileType`, `FileName`, `Remark`, `CreateDate`, `ParentId`, `FilePath`, `IfDelete`, `pids`) VALUES ('35', 'cai', '14', '原始青鸟桌面', '', '2014-06-16 10:20:46', '33', '文档管理\\你好啊\\原始青鸟桌面.jpg', '1', ',1,33,35,');

-- ----------------------------
-- Table structure for filetypeinfo
-- ----------------------------
DROP TABLE IF EXISTS `filetypeinfo`;
CREATE TABLE `filetypeinfo` (
  `FileTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `FileTypeName` varchar(50) NOT NULL,
  `FileTypeImage` varchar(50) NOT NULL DEFAULT 'unknow',
  `FileTypeSuffix` varchar(50) DEFAULT '',
  PRIMARY KEY (`FileTypeId`),
  UNIQUE KEY `FileTypeSuffix` (`FileTypeSuffix`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of filetypeinfo
-- ----------------------------
INSERT INTO `test`.`filetypeinfo` (`FileTypeId`, `FileTypeName`, `FileTypeImage`, `FileTypeSuffix`) VALUES ('1', '文件夹', 'folder', '');
INSERT INTO `test`.`filetypeinfo` (`FileTypeId`, `FileTypeName`, `FileTypeImage`, `FileTypeSuffix`) VALUES ('2', 'Microsoft Office Word 97-2003 文档', 'doc', '.doc');
INSERT INTO `test`.`filetypeinfo` (`FileTypeId`, `FileTypeName`, `FileTypeImage`, `FileTypeSuffix`) VALUES ('12', 'PNG 图像', 'png', '.png');
INSERT INTO `test`.`filetypeinfo` (`FileTypeId`, `FileTypeName`, `FileTypeImage`, `FileTypeSuffix`) VALUES ('13', 'BMP 图像', 'bmp', '.bmp');
INSERT INTO `test`.`filetypeinfo` (`FileTypeId`, `FileTypeName`, `FileTypeImage`, `FileTypeSuffix`) VALUES ('14', 'JPEG 图像', 'jpg', '.jpg');
INSERT INTO `test`.`filetypeinfo` (`FileTypeId`, `FileTypeName`, `FileTypeImage`, `FileTypeSuffix`) VALUES ('15', '文本文档', 'txt', '.txt');
INSERT INTO `test`.`filetypeinfo` (`FileTypeId`, `FileTypeName`, `FileTypeImage`, `FileTypeSuffix`) VALUES ('16', 'Microsoft Office PowerPoint 97-2003 演示文稿', 'ppt', '.ppt');
INSERT INTO `test`.`filetypeinfo` (`FileTypeId`, `FileTypeName`, `FileTypeImage`, `FileTypeSuffix`) VALUES ('17', 'Microsoft Office Excel 97-2003 工作表', 'xls', '.xls');
INSERT INTO `test`.`filetypeinfo` (`FileTypeId`, `FileTypeName`, `FileTypeImage`, `FileTypeSuffix`) VALUES ('18', 'Microsoft Office Excel 工作表', 'xlsx', '.xlsx');
INSERT INTO `test`.`filetypeinfo` (`FileTypeId`, `FileTypeName`, `FileTypeImage`, `FileTypeSuffix`) VALUES ('19', 'Microsoft Office PowerPoint 演示文稿', 'pptx', '.pptx');
INSERT INTO `test`.`filetypeinfo` (`FileTypeId`, `FileTypeName`, `FileTypeImage`, `FileTypeSuffix`) VALUES ('20', 'Adobe Acrobat Document', 'pdf', '.pdf');
INSERT INTO `test`.`filetypeinfo` (`FileTypeId`, `FileTypeName`, `FileTypeImage`, `FileTypeSuffix`) VALUES ('21', 'WinRAR 压缩文件', 'rar', '.rar');
INSERT INTO `test`.`filetypeinfo` (`FileTypeId`, `FileTypeName`, `FileTypeImage`, `FileTypeSuffix`) VALUES ('23', 'Microsoft Office Word 文档', 'docx', '.docx');


-- ----------------------------
-- Table structure for loginlog
-- ----------------------------
DROP TABLE IF EXISTS `loginlog`;
CREATE TABLE `loginlog` (
  `LoginId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` varchar(50) NOT NULL,
  `LoginTime` datetime NOT NULL,
  `IfSuccess` int(11) NOT NULL COMMENT '??????¡¤???????1????????0?¡ì¡ã?',
  `LoginUserIp` varchar(100) NOT NULL,
  `LoginDesc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`LoginId`),
  KEY `FK_47oiqvjkqm3t08g6indcm30yn` (`UserId`),
  CONSTRAINT `FK_47oiqvjkqm3t08g6indcm30yn` FOREIGN KEY (`UserId`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=355 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loginlog
-- ----------------------------
INSERT INTO `loginlog` VALUES ('1', 'gao', '2014-04-25 16:29:21', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('2', 'cai', '2014-04-24 16:29:49', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('3', 'liu', '2014-04-22 16:30:21', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('4', 'gao', '2014-04-28 16:29:21', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('5', 'cai', '2014-04-29 16:29:49', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('6', 'liu', '2014-04-29 16:30:21', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('7', 'gao', '2014-04-28 16:29:21', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('8', 'cai', '2014-04-29 16:29:49', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('9', 'liu', '2014-04-29 16:30:21', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('10', 'gao', '2014-04-28 16:29:21', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('11', 'cai', '2014-04-29 16:29:49', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('12', 'liu', '2014-04-29 16:30:21', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('13', 'cai', '2014-05-08 15:09:13', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('14', 'cai', '2014-05-08 15:11:59', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('15', 'cai', '2014-05-08 15:17:39', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('16', 'cai', '2014-05-08 15:21:30', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('17', 'cai', '2014-05-08 15:24:28', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('18', 'cai', '2014-05-08 15:25:34', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('19', 'cai', '2014-05-08 15:28:28', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('20', 'cai', '2014-05-08 15:32:52', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('21', 'cai', '2014-05-08 15:38:24', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('22', 'zhu', '2014-05-08 15:44:35', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('23', 'zhu', '2014-05-08 15:51:23', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('24', 'zhu', '2014-05-08 16:11:15', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('25', 'zhu', '2014-05-08 16:14:22', '1', '127.0.0.1', '管理员登录');
INSERT INTO `loginlog` VALUES ('26', 'cai', '2014-05-08 16:32:20', '1', '127.0.0.1', '管理员登录');

-- ----------------------------
-- Table structure for manualsign
-- ----------------------------
DROP TABLE IF EXISTS `manualsign`;
CREATE TABLE `manualsign` (
  `SignId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` varchar(50) NOT NULL,
  `SignTime` datetime NOT NULL,
  `SignDesc` varchar(200) NOT NULL,
  `SignTag` int(11) NOT NULL,
  PRIMARY KEY (`SignId`),
  KEY `FK_jcxvf473aljjteccg3hxlb4se` (`UserId`),
  CONSTRAINT `FK_jcxvf473aljjteccg3hxlb4se` FOREIGN KEY (`UserId`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manualsign
-- ----------------------------
INSERT INTO `manualsign` VALUES ('1', 'zhu', '2014-05-14 00:00:00', '签到', '1');
INSERT INTO `manualsign` VALUES ('2', 'zhu', '2014-05-14 00:00:00', '签退', '1');
INSERT INTO `manualsign` VALUES ('3', 'cai', '2014-05-21 00:00:00', '', '1');
INSERT INTO `manualsign` VALUES ('4', 'cai', '2014-05-21 00:00:00', 'DSAFSDSDFSD', '1');
INSERT INTO `manualsign` VALUES ('5', 'cai', '2014-05-22 00:00:00', 'DSAFSDSDAGDFGDFS', '1');
INSERT INTO `manualsign` VALUES ('6', 'cai', '2014-05-22 00:00:00', '', '1');
INSERT INTO `manualsign` VALUES ('7', 'cai', '2014-05-22 00:00:00', 'kjjgikg', '1');

-- ----------------------------
-- Table structure for meetinginfo
-- ----------------------------
DROP TABLE IF EXISTS `meetinginfo`;
CREATE TABLE `meetinginfo` (
  `MeetingId` int(11) NOT NULL AUTO_INCREMENT,
  `MeetingName` varchar(50) NOT NULL,
  PRIMARY KEY (`MeetingId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meetinginfo
-- ----------------------------
INSERT INTO `meetinginfo` VALUES ('1', '部门小组会议');
INSERT INTO `meetinginfo` VALUES ('2', '部门全体会议');
INSERT INTO `meetinginfo` VALUES ('3', '董事会');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `MessageId` int(11) NOT NULL AUTO_INCREMENT,
  `Type` int(11) NOT NULL,
  `Title` varchar(100) NOT NULL,
  `Content` varchar(255) NOT NULL,
  `BeginTime` datetime NOT NULL,
  `EndTime` datetime NOT NULL,
  `FromUserId` varchar(50) NOT NULL,
  `IfPublish` int(11) NOT NULL,
  `RecordTime` datetime NOT NULL,
  PRIMARY KEY (`MessageId`),
  KEY `FK_67951fo1og2qjm35k5dwg2ev` (`Type`),
  CONSTRAINT `FK_67951fo1og2qjm35k5dwg2ev` FOREIGN KEY (`Type`) REFERENCES `messagetype` (`MessageTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '1', '项目收工', 'OA项目 客户要求这个月上交，请各位务必加紧编码！！', '2014-04-14 16:27:43', '2014-04-15 16:27:51', '1', '1', '2014-04-15 16:29:24');

-- ----------------------------
-- Table structure for messagetouser
-- ----------------------------
DROP TABLE IF EXISTS `messagetouser`;
CREATE TABLE `messagetouser` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ToUserId` varchar(50) NOT NULL,
  `MessageId` int(11) NOT NULL,
  `IfRead` int(11) NOT NULL COMMENT '??¡¤???????1????????0??????',
  PRIMARY KEY (`Id`),
  KEY `FK_q6vp1lt6w270sowfem8rgbdl4` (`ToUserId`),
  KEY `FK_t34oboprfb9oh9cwto9btr4j9` (`MessageId`),
  CONSTRAINT `FK_q6vp1lt6w270sowfem8rgbdl4` FOREIGN KEY (`ToUserId`) REFERENCES `userinfo` (`UserId`),
  CONSTRAINT `FK_t34oboprfb9oh9cwto9btr4j9` FOREIGN KEY (`MessageId`) REFERENCES `message` (`MessageId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messagetouser
-- ----------------------------
INSERT INTO `messagetouser` VALUES ('1', 'zhu', '1', '1');

-- ----------------------------
-- Table structure for messagetype
-- ----------------------------
DROP TABLE IF EXISTS `messagetype`;
CREATE TABLE `messagetype` (
  `MessageTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `MessageTypeName` varchar(50) NOT NULL,
  `MessageDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MessageTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messagetype
-- ----------------------------
INSERT INTO `messagetype` VALUES ('1', '普通', null);
INSERT INTO `messagetype` VALUES ('2', '一般', null);
INSERT INTO `messagetype` VALUES ('3', '加急', null);
INSERT INTO `messagetype` VALUES ('4', '特批', null);

-- ----------------------------
-- Table structure for mynote
-- ----------------------------
DROP TABLE IF EXISTS `mynote`;
CREATE TABLE `mynote` (
  `NoteId` int(11) NOT NULL AUTO_INCREMENT,
  `CreateUser` varchar(50) NOT NULL,
  `NoteTitle` varchar(50) NOT NULL,
  `NoteContent` varchar(255) DEFAULT NULL,
  `CreateTime` datetime NOT NULL,
  PRIMARY KEY (`NoteId`),
  KEY `FK_a8tcr7ydivfnrpvxp8m0nha1t` (`CreateUser`),
  CONSTRAINT `FK_a8tcr7ydivfnrpvxp8m0nha1t` FOREIGN KEY (`CreateUser`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mynote
-- ----------------------------

-- ----------------------------
-- Table structure for operatelog
-- ----------------------------
DROP TABLE IF EXISTS `operatelog`;
CREATE TABLE `operatelog` (
  `OperateId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` varchar(50) NOT NULL,
  `OperateName` varchar(50) NOT NULL,
  `ObjectId` varchar(50) NOT NULL,
  `OperateDesc` varchar(200) NOT NULL,
  `OperateTime` datetime NOT NULL,
  PRIMARY KEY (`OperateId`),
  KEY `FK_ixmxoedr1mg731cd7v7lsg2xb` (`UserId`),
  CONSTRAINT `FK_ixmxoedr1mg731cd7v7lsg2xb` FOREIGN KEY (`UserId`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operatelog
-- ----------------------------
INSERT INTO `operatelog` VALUES ('1', 'gao', '登录', '系统管理员', '管理员登录', '2014-05-02 15:22:03');
INSERT INTO `operatelog` VALUES ('2', 'cai', '删除', '系统管理员', '管理员删除', '2014-05-01 15:22:40');
INSERT INTO `operatelog` VALUES ('3', 'zhu', '发布', '系统管理员', '管理员发布', '2014-04-29 15:23:11');
INSERT INTO `operatelog` VALUES ('4', 'gao', '删除', '系统管理员', '放到回收站', '2014-05-03 16:28:41');

-- ----------------------------
-- Table structure for precontract
-- ----------------------------
DROP TABLE IF EXISTS `precontract`;
CREATE TABLE `precontract` (
  `PreContractId` int(11) NOT NULL AUTO_INCREMENT,
  `ScheduleId` int(11) NOT NULL,
  `UserId` varchar(50) NOT NULL,
  PRIMARY KEY (`PreContractId`),
  KEY `FK_oab1c0n6w02ooo5xi0eb34fif` (`ScheduleId`),
  KEY `FK_91syy0g8cl62tkun6ag9x0cvp` (`UserId`),
  CONSTRAINT `FK_91syy0g8cl62tkun6ag9x0cvp` FOREIGN KEY (`UserId`) REFERENCES `userinfo` (`UserId`),
  CONSTRAINT `FK_oab1c0n6w02ooo5xi0eb34fif` FOREIGN KEY (`ScheduleId`) REFERENCES `schedule` (`ScheduleId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of precontract
-- ----------------------------
INSERT INTO `precontract` VALUES ('1', '1', 'zhu');
INSERT INTO `precontract` VALUES ('2', '4', 'zhu');
INSERT INTO `precontract` VALUES ('3', '2', 'zhu');
INSERT INTO `precontract` VALUES ('4', '3', 'zhu');
INSERT INTO `precontract` VALUES ('5', '4', 'zhu');
INSERT INTO `precontract` VALUES ('6', '2', 'zhu');
INSERT INTO `precontract` VALUES ('7', '3', 'zhu');
INSERT INTO `precontract` VALUES ('8', '4', 'zhu');
INSERT INTO `precontract` VALUES ('9', '2', 'zhu');
INSERT INTO `precontract` VALUES ('10', '3', 'zhu');
INSERT INTO `precontract` VALUES ('11', '4', 'zhu');
INSERT INTO `precontract` VALUES ('12', '2', 'zhu');
INSERT INTO `precontract` VALUES ('13', '3', 'zhu');
INSERT INTO `precontract` VALUES ('14', '4', 'zhu');
INSERT INTO `precontract` VALUES ('15', '2', 'zhu');
INSERT INTO `precontract` VALUES ('16', '3', 'zhu');

-- ----------------------------
-- Table structure for readcommonmessage
-- ----------------------------
DROP TABLE IF EXISTS `readcommonmessage`;
CREATE TABLE `readcommonmessage` (
  `ReadId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` varchar(50) NOT NULL,
  `MessageId` int(11) NOT NULL,
  PRIMARY KEY (`ReadId`),
  KEY `FK_jhr5yamq3ah6pbabxkm1rly1o` (`UserId`),
  KEY `FK_fe202eqt9sy4pmphx2cjk6nnq` (`MessageId`),
  CONSTRAINT `FK_fe202eqt9sy4pmphx2cjk6nnq` FOREIGN KEY (`MessageId`) REFERENCES `message` (`MessageId`),
  CONSTRAINT `FK_jhr5yamq3ah6pbabxkm1rly1o` FOREIGN KEY (`UserId`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readcommonmessage
-- ----------------------------

-- ----------------------------
-- Table structure for roleinfo
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo`;
CREATE TABLE `roleinfo` (
  `RoleId` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(50) NOT NULL,
  `RoleDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleinfo
-- ----------------------------
INSERT INTO `roleinfo` VALUES ('1', '普通用户', '有一定权限');
INSERT INTO `roleinfo` VALUES ('2', '管理员', '很NX的权限');
INSERT INTO `roleinfo` VALUES ('3', 'xx', 'xx');
INSERT INTO `roleinfo` VALUES ('4', 'xxx', 'xxx');
INSERT INTO `roleinfo` VALUES ('5', 'xxxx', 'xxxx');
INSERT INTO `roleinfo` VALUES ('6', 'xxxxxx', 'xxxxxx');
INSERT INTO `roleinfo` VALUES ('7', 'xxxxxxxx', 'xxxxxxxx');
INSERT INTO `roleinfo` VALUES ('8', 'xxxxxxxxxxxxxxxxxxx', 'xxxxxxxxxxxxxxxxx');
INSERT INTO `roleinfo` VALUES ('9', 'xxxxxxxxxxxxxxxxxxxxxxxxxxx', 'xxxxxxxxxxxxxxxxxxxxxxx');
INSERT INTO `roleinfo` VALUES ('10', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx');
INSERT INTO `roleinfo` VALUES ('11', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx');

-- ----------------------------
-- Table structure for roleinfo_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo_userinfo`;
CREATE TABLE `roleinfo_userinfo` (
  `role_id` int(11) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_4ixa4r97q6b66bdeo7v36ellx` (`role_id`),
  CONSTRAINT `FK_4ixa4r97q6b66bdeo7v36ellx` FOREIGN KEY (`role_id`) REFERENCES `roleinfo` (`RoleId`),
  CONSTRAINT `FK_a3iq6pta237iipbbaf1r800kf` FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleinfo_userinfo
-- ----------------------------
INSERT INTO `roleinfo_userinfo` VALUES ('1', 'chen');
INSERT INTO `roleinfo_userinfo` VALUES ('1', 'zhu');
INSERT INTO `roleinfo_userinfo` VALUES ('2', 'cai');
INSERT INTO `roleinfo_userinfo` VALUES ('2', 'chen');
INSERT INTO `roleinfo_userinfo` VALUES ('2', 'gao');
INSERT INTO `roleinfo_userinfo` VALUES ('2', 'liu');
INSERT INTO `roleinfo_userinfo` VALUES ('2', 'wang');
INSERT INTO `roleinfo_userinfo` VALUES ('2', 'zhu');
INSERT INTO `roleinfo_userinfo` VALUES ('3', '132');
INSERT INTO `roleinfo_userinfo` VALUES ('3', 'chen');
INSERT INTO `roleinfo_userinfo` VALUES ('3', 'zhu');
INSERT INTO `roleinfo_userinfo` VALUES ('4', 'ss');
INSERT INTO `roleinfo_userinfo` VALUES ('5', 'ss');
INSERT INTO `roleinfo_userinfo` VALUES ('6', '132');
INSERT INTO `roleinfo_userinfo` VALUES ('6', 'ss');

-- ----------------------------
-- Table structure for roleright
-- ----------------------------
DROP TABLE IF EXISTS `roleright`;
CREATE TABLE `roleright` (
  `RoleRightId` int(11) NOT NULL AUTO_INCREMENT,
  `NodeId` int(11) NOT NULL,
  `RoleId` int(11) NOT NULL,
  PRIMARY KEY (`RoleRightId`),
  KEY `FK_qs0jey5o0gmd73ocfqmdqxpr6` (`NodeId`),
  KEY `FK_tcm09xf4c1ryrsvl3fek8ukim` (`RoleId`),
  CONSTRAINT `FK_qs0jey5o0gmd73ocfqmdqxpr6` FOREIGN KEY (`NodeId`) REFERENCES `sysfun` (`NodeId`),
  CONSTRAINT `FK_tcm09xf4c1ryrsvl3fek8ukim` FOREIGN KEY (`RoleId`) REFERENCES `roleinfo` (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleright
-- ----------------------------
INSERT INTO `roleright` VALUES ('58', '106003', '2');
INSERT INTO `roleright` VALUES ('59', '101', '2');
INSERT INTO `roleright` VALUES ('60', '103002', '2');
INSERT INTO `roleright` VALUES ('61', '102', '2');
INSERT INTO `roleright` VALUES ('62', '105001', '2');
INSERT INTO `roleright` VALUES ('63', '106', '2');
INSERT INTO `roleright` VALUES ('64', '105004', '2');
INSERT INTO `roleright` VALUES ('65', '104001', '2');
INSERT INTO `roleright` VALUES ('66', '104', '2');
INSERT INTO `roleright` VALUES ('67', '106', '2');
INSERT INTO `roleright` VALUES ('68', '104', '2');
INSERT INTO `roleright` VALUES ('69', '105', '2');
INSERT INTO `roleright` VALUES ('70', '105002', '2');
INSERT INTO `roleright` VALUES ('71', '102', '2');
INSERT INTO `roleright` VALUES ('72', '106002', '2');
INSERT INTO `roleright` VALUES ('73', '103', '2');
INSERT INTO `roleright` VALUES ('74', '102003', '2');
INSERT INTO `roleright` VALUES ('75', '103001', '2');
INSERT INTO `roleright` VALUES ('76', '101003', '2');
INSERT INTO `roleright` VALUES ('77', '101001', '2');
INSERT INTO `roleright` VALUES ('78', '105003', '2');
INSERT INTO `roleright` VALUES ('79', '104002', '2');
INSERT INTO `roleright` VALUES ('80', '101002', '2');
INSERT INTO `roleright` VALUES ('81', '103', '2');
INSERT INTO `roleright` VALUES ('82', '102001', '2');
INSERT INTO `roleright` VALUES ('83', '102002', '2');
INSERT INTO `roleright` VALUES ('84', '101', '2');
INSERT INTO `roleright` VALUES ('85', '106001', '2');
INSERT INTO `roleright` VALUES ('86', '105', '2');
INSERT INTO `roleright` VALUES ('100', '101', '3');
INSERT INTO `roleright` VALUES ('101', '101002', '3');
INSERT INTO `roleright` VALUES ('102', '105', '3');
INSERT INTO `roleright` VALUES ('103', '106', '3');
INSERT INTO `roleright` VALUES ('104', '105001', '3');
INSERT INTO `roleright` VALUES ('105', '101003', '3');
INSERT INTO `roleright` VALUES ('106', '106003', '3');
INSERT INTO `roleright` VALUES ('107', '101001', '3');
INSERT INTO `roleright` VALUES ('108', '101', '3');
INSERT INTO `roleright` VALUES ('109', '106', '1');
INSERT INTO `roleright` VALUES ('110', '101', '1');
INSERT INTO `roleright` VALUES ('111', '106003', '1');
INSERT INTO `roleright` VALUES ('112', '105', '1');
INSERT INTO `roleright` VALUES ('113', '105004', '1');
INSERT INTO `roleright` VALUES ('114', '101002', '1');

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `ScheduleId` int(11) NOT NULL AUTO_INCREMENT,
  `MeetingId` int(11) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `BeginTime` datetime NOT NULL,
  `EndTime` datetime NOT NULL,
  `SchContent` varchar(255) NOT NULL,
  `CreateUser` varchar(50) NOT NULL,
  `CreateTime` datetime NOT NULL,
  `IfPrivate` varchar(255) NOT NULL,
  PRIMARY KEY (`ScheduleId`),
  KEY `FK_lfx3007u00wsh4h0qkt00mus` (`MeetingId`),
  CONSTRAINT `FK_lfx3007u00wsh4h0qkt00mus` FOREIGN KEY (`MeetingId`) REFERENCES `meetinginfo` (`MeetingId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES ('1', '1', '会议1', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');
INSERT INTO `schedule` VALUES ('2', '1', '会议1', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');
INSERT INTO `schedule` VALUES ('3', '1', '会议2', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');
INSERT INTO `schedule` VALUES ('4', '2', '会议3', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');
INSERT INTO `schedule` VALUES ('5', '3', '会议4', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');
INSERT INTO `schedule` VALUES ('6', '2', '会议5', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');
INSERT INTO `schedule` VALUES ('7', '1', '会议6', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');
INSERT INTO `schedule` VALUES ('8', '3', '会议7', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');
INSERT INTO `schedule` VALUES ('9', '1', '会议8', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');
INSERT INTO `schedule` VALUES ('10', '2', '会议9', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');
INSERT INTO `schedule` VALUES ('11', '3', '会议10', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');
INSERT INTO `schedule` VALUES ('12', '3', '会议11', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');
INSERT INTO `schedule` VALUES ('13', '2', '会议12', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');
INSERT INTO `schedule` VALUES ('14', '1', '会议13', '浙江杭州', '2014-04-15 15:03:44', '2014-04-16 15:03:46', '没有事，聊一聊', '', '2014-04-15 15:05:15', '');

-- ----------------------------
-- Table structure for sysfun
-- ----------------------------
DROP TABLE IF EXISTS `sysfun`;
CREATE TABLE `sysfun` (
  `NodeId` int(11) NOT NULL AUTO_INCREMENT,
  `ParentNodeId` int(11) DEFAULT NULL,
  `DisplayName` varchar(50) NOT NULL,
  `NodeURL` varchar(50) DEFAULT NULL,
  `DisplayOrder` int(11) NOT NULL,
  `IconCls` varchar(100) NOT NULL,
  `Attributes` varchar(255) NOT NULL,
  PRIMARY KEY (`NodeId`),
  KEY `FK_qjcey6wj3t21rptkbadr3chus` (`ParentNodeId`),
  CONSTRAINT `FK_qjcey6wj3t21rptkbadr3chus` FOREIGN KEY (`ParentNodeId`) REFERENCES `sysfun` (`NodeId`)
) ENGINE=InnoDB AUTO_INCREMENT=106004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysfun
-- ----------------------------
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('101', NULL, '人事管理', '', '101', 'hr-manage', '');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('102', NULL, '日程管理', '', '102', 'schedule-manage', '');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('103', NULL, '文档管理', '', '103', 'document-manage', '');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('104', NULL, '消息管理', '', '104', 'email-manage', '');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('105', NULL, '系统管理', '', '105', 'sys-manage', '');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('106', NULL, '考勤管理', '', '106', 'manul-manage', '');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('101001', '101', '机构管理', 'branch', '101001', 'hr-derpart-manage', 'hr/branchMgt.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('101002', '101', '部门管理', 'depart', '101002', 'hr-branch-manage', 'hr/departMgt.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('101003', '101', '员工管理', 'user', '101003', 'hr-user-manage', 'hr/employeeMgt.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('102001', '102', '我的日程', 'mySchedule', '102001', 'schedule-depart-manage', 'schedule/mySchedule.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('102002', '102', '部门日程', 'departSchedule', '102002', 'schedule-my-manage', 'schedule/departSchedule.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('102003', '102', '我的便签', '', '102003', 'schedule-note-manage', 'schedule/myNote.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('103001', '103', '文档管理', 'doc', '103001', 'document-manage', 'doc/documentMgt.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('103002', '103', '回收站', 'recycle', '103002', 'recycle-bin', 'doc/recycle.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('104001', '104', '消息传递', '', '104001', 'email-manage', 'mm/messageShow.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('104002', '104', '信箱', '', '104002', 'email-email-manage', '');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('105001', '105', '角色管理', 'role', '105001', 'role-manage', 'sm/roleManage.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('105002', '105', '登录日志', 'loginlog', '105002', 'login-log', 'sm/loginLog.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('105003', '105', '操作日志', 'operatelog', '105003', 'oper-log', 'sm/OperateLog.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('105004', '105', '菜单排序', '', '105004', 'menu-sort', '');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('106001', '106', '考勤历史记录查询', 'history', '106001', 'manul-hostory-manage', 'tc/search.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('106002', '106', '考勤统计', 'workTime', '106002', 'manul-census-manage', 'tc/stat.jsp');
INSERT INTO `test`.`sysfun` (`NodeId`, `ParentNodeId`, `DisplayName`, `NodeURL`, `DisplayOrder`, `IconCls`, `Attributes`) VALUES ('106003', '106', '签到、签退', 'manual', '106003', 'manul-out-input-manage', 'tc/manu.jsp');



-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `UserId` varchar(50) NOT NULL,
  `UserState` int(11) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `PassWord` varchar(50) NOT NULL,
  `DepartId` int(11) NOT NULL,
  `Gender` int(11) NOT NULL,
  PRIMARY KEY (`UserId`),
  KEY `FK_bl6v32865m3301xpic3bo9ehw` (`UserState`),
  CONSTRAINT `FK_bl6v32865m3301xpic3bo9ehw` FOREIGN KEY (`UserState`) REFERENCES `userstate` (`UserStateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('132', '1', '我是123', '123456a', '4', '1');
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('cai', '1', '蔡彬文', '123456a', '2', '1');
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('chen', '1', '陈习', '123456a', '4', '1');
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('gao', '1', '高雅', '123456a', '3', '2');
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('liu', '1', '柳家强', '123456a', '4', '1');
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('ss', '1', '谁是123', '123456a', '2', '1');
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('user', '2', '小三efsdf', '123456a', '1', '2');
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('wang', '1', '王鹏', '123456a', '1', '1');
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('xm', '2', '王小明', '123456a', '2', '2');
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('zhou', '1', '周定康', '123456a', '5', '1');
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('zhu', '1', '朱义传', '123456a', '1', '1');
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('娃哈哈', '1', '娃哈哈', '123456a', '4', '2');
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('leyoyo', '1', '乐悠悠', '123456a', '3', '2');
INSERT INTO `test`.`userinfo` (`UserId`, `UserState`, `UserName`, `PassWord`, `DepartId`, `Gender`) VALUES ('xyy', '1', '喜洋洋', '123456a', '5', '1');


-- ----------------------------
-- Table structure for userstate
-- ----------------------------
DROP TABLE IF EXISTS `userstate`;
CREATE TABLE `userstate` (
  `UserStateId` int(11) NOT NULL AUTO_INCREMENT,
  `UserStateName` varchar(50) NOT NULL,
  PRIMARY KEY (`UserStateId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userstate
-- ----------------------------
INSERT INTO `userstate` VALUES ('1', '正常状态');
INSERT INTO `userstate` VALUES ('2', '被屏蔽');

-- ----------------------------
-- Table structure for worktime
-- ----------------------------
DROP TABLE IF EXISTS `worktime`;
CREATE TABLE `worktime` (
  `WorkTimeId` int(11) NOT NULL AUTO_INCREMENT,
  `OnDutyTime` varchar(50) NOT NULL,
  `OffDutyTime` varchar(50) NOT NULL,
  PRIMARY KEY (`WorkTimeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of worktime
-- ----------------------------
