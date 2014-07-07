/*
Navicat MySQL Data Transfer

Source Server         : conn1
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-04-10 18:40:37
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
  CONSTRAINT `FK_gvhrnbjl275d6oakl28u2jwqj` FOREIGN KEY (`AccessoryType`) REFERENCES `filetypeinfo` (`FileTypeId`),
  CONSTRAINT `FK_gv06hifpq1tfc23a2uwwkqlu4` FOREIGN KEY (`FileId`) REFERENCES `fileinfo` (`FileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for branchinfo
-- ----------------------------
DROP TABLE IF EXISTS `branchinfo`;
CREATE TABLE `branchinfo` (
  `BranchId` int(11) NOT NULL AUTO_INCREMENT,
  `BranchName` varchar(50) NOT NULL,
  `BranchShortName` varchar(50) NOT NULL,
  PRIMARY KEY (`BranchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for departinfo
-- ----------------------------
DROP TABLE IF EXISTS `departinfo`;
CREATE TABLE `departinfo` (
  `DepartId` int(11) NOT NULL AUTO_INCREMENT,
  `PrincipalUser` int(11) NOT NULL,
  `BranchId` int(11) NOT NULL,
  `DepartName` varchar(50) NOT NULL,
  `ConnectTelNo` bigint(20) DEFAULT NULL,
  `ConnectMobileTelNo` bigint(20) DEFAULT NULL,
  `Faxes` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DepartId`),
  KEY `FK_qg76ch6pdacl3u3mqtwkrlsk6` (`PrincipalUser`),
  KEY `FK_qqh3iu426hi35ev7bf7mcl17i` (`BranchId`),
  CONSTRAINT `FK_qqh3iu426hi35ev7bf7mcl17i` FOREIGN KEY (`BranchId`) REFERENCES `branchinfo` (`BranchId`),
  CONSTRAINT `FK_qg76ch6pdacl3u3mqtwkrlsk6` FOREIGN KEY (`PrincipalUser`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fileinfo
-- ----------------------------
DROP TABLE IF EXISTS `fileinfo`;
CREATE TABLE `fileinfo` (
  `FileId` int(11) NOT NULL AUTO_INCREMENT,
  `FileOwner` int(11) NOT NULL,
  `FileType` int(11) NOT NULL,
  `FileName` varchar(50) NOT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  `CreateDate` datetime NOT NULL,
  `ParentId` int(11) NOT NULL,
  `FilePath` varchar(200) NOT NULL,
  `IfDelete` int(11) NOT NULL,
  PRIMARY KEY (`FileId`),
  KEY `FK_l32xhm344wbnhinqsuw529kng` (`FileOwner`),
  KEY `FK_1n62molt8two90ngj5r9mhuwm` (`FileType`),
  CONSTRAINT `FK_1n62molt8two90ngj5r9mhuwm` FOREIGN KEY (`FileType`) REFERENCES `filetypeinfo` (`FileTypeId`),
  CONSTRAINT `FK_l32xhm344wbnhinqsuw529kng` FOREIGN KEY (`FileOwner`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filetypeinfo
-- ----------------------------
DROP TABLE IF EXISTS `filetypeinfo`;
CREATE TABLE `filetypeinfo` (
  `FileTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `FileTypeName` varchar(50) NOT NULL,
  `FileTypeImage` varchar(50) NOT NULL,
  `FileTypeSuffix` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`FileTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for loginlog
-- ----------------------------
DROP TABLE IF EXISTS `loginlog`;
CREATE TABLE `loginlog` (
  `LoginId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `LoginTime` datetime NOT NULL,
  `IfSuccess` int(11) NOT NULL COMMENT '??????¡¤???????1????????0?¡ì¡ã?',
  `LoginUserIp` varchar(100) NOT NULL,
  `LoginDesc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`LoginId`),
  KEY `FK_47oiqvjkqm3t08g6indcm30yn` (`UserId`),
  CONSTRAINT `FK_47oiqvjkqm3t08g6indcm30yn` FOREIGN KEY (`UserId`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for manualsign
-- ----------------------------
DROP TABLE IF EXISTS `manualsign`;
CREATE TABLE `manualsign` (
  `SignId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `SignTime` datetime NOT NULL,
  `SignDesc` varchar(200) NOT NULL,
  `SignTag` int(11) NOT NULL,
  PRIMARY KEY (`SignId`),
  KEY `FK_jcxvf473aljjteccg3hxlb4se` (`UserId`),
  CONSTRAINT `FK_jcxvf473aljjteccg3hxlb4se` FOREIGN KEY (`UserId`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for meetinginfo
-- ----------------------------
DROP TABLE IF EXISTS `meetinginfo`;
CREATE TABLE `meetinginfo` (
  `MeetingId` int(11) NOT NULL AUTO_INCREMENT,
  `MeetingName` varchar(50) NOT NULL,
  PRIMARY KEY (`MeetingId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for messagetouser
-- ----------------------------
DROP TABLE IF EXISTS `messagetouser`;
CREATE TABLE `messagetouser` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ToUserId` int(11) NOT NULL,
  `MessageId` int(11) NOT NULL,
  `IfRead` int(11) NOT NULL COMMENT '??¡¤???????1????????0??????',
  PRIMARY KEY (`Id`),
  KEY `FK_q6vp1lt6w270sowfem8rgbdl4` (`ToUserId`),
  KEY `FK_t34oboprfb9oh9cwto9btr4j9` (`MessageId`),
  CONSTRAINT `FK_t34oboprfb9oh9cwto9btr4j9` FOREIGN KEY (`MessageId`) REFERENCES `message` (`MessageId`),
  CONSTRAINT `FK_q6vp1lt6w270sowfem8rgbdl4` FOREIGN KEY (`ToUserId`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for messagetype
-- ----------------------------
DROP TABLE IF EXISTS `messagetype`;
CREATE TABLE `messagetype` (
  `MessageTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `MessageTypeName` varchar(50) NOT NULL,
  `MessageDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MessageTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mynote
-- ----------------------------
DROP TABLE IF EXISTS `mynote`;
CREATE TABLE `mynote` (
  `NoteId` int(11) NOT NULL AUTO_INCREMENT,
  `CreateUser` int(11) NOT NULL,
  `NoteTitle` varchar(50) NOT NULL,
  `NoteContent` varchar(255) DEFAULT NULL,
  `CreateTime` datetime NOT NULL,
  PRIMARY KEY (`NoteId`),
  KEY `FK_a8tcr7ydivfnrpvxp8m0nha1t` (`CreateUser`),
  CONSTRAINT `FK_a8tcr7ydivfnrpvxp8m0nha1t` FOREIGN KEY (`CreateUser`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for operatelog
-- ----------------------------
DROP TABLE IF EXISTS `operatelog`;
CREATE TABLE `operatelog` (
  `OperateId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `OperateName` varchar(50) NOT NULL,
  `ObjectId` varchar(50) NOT NULL,
  `OperateDesc` varchar(200) NOT NULL,
  `OperateTime` datetime NOT NULL,
  PRIMARY KEY (`OperateId`),
  KEY `FK_ixmxoedr1mg731cd7v7lsg2xb` (`UserId`),
  CONSTRAINT `FK_ixmxoedr1mg731cd7v7lsg2xb` FOREIGN KEY (`UserId`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for precontract
-- ----------------------------
DROP TABLE IF EXISTS `precontract`;
CREATE TABLE `precontract` (
  `PreContractId` int(11) NOT NULL AUTO_INCREMENT,
  `ScheduleId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  PRIMARY KEY (`PreContractId`),
  KEY `FK_oab1c0n6w02ooo5xi0eb34fif` (`ScheduleId`),
  KEY `FK_91syy0g8cl62tkun6ag9x0cvp` (`UserId`),
  CONSTRAINT `FK_91syy0g8cl62tkun6ag9x0cvp` FOREIGN KEY (`UserId`) REFERENCES `userinfo` (`UserId`),
  CONSTRAINT `FK_oab1c0n6w02ooo5xi0eb34fif` FOREIGN KEY (`ScheduleId`) REFERENCES `schedule` (`ScheduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for readcommonmessage
-- ----------------------------
DROP TABLE IF EXISTS `readcommonmessage`;
CREATE TABLE `readcommonmessage` (
  `ReadId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `MessageId` int(11) NOT NULL,
  PRIMARY KEY (`ReadId`),
  KEY `FK_jhr5yamq3ah6pbabxkm1rly1o` (`UserId`),
  KEY `FK_fe202eqt9sy4pmphx2cjk6nnq` (`MessageId`),
  CONSTRAINT `FK_fe202eqt9sy4pmphx2cjk6nnq` FOREIGN KEY (`MessageId`) REFERENCES `message` (`MessageId`),
  CONSTRAINT `FK_jhr5yamq3ah6pbabxkm1rly1o` FOREIGN KEY (`UserId`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for roleinfo
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo`;
CREATE TABLE `roleinfo` (
  `RoleId` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(50) NOT NULL,
  `RoleDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`RoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for roleinfo_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo_userinfo`;
CREATE TABLE `roleinfo_userinfo` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_4ixa4r97q6b66bdeo7v36ellx` (`role_id`),
  CONSTRAINT `FK_4ixa4r97q6b66bdeo7v36ellx` FOREIGN KEY (`role_id`) REFERENCES `roleinfo` (`RoleId`),
  CONSTRAINT `FK_a3iq6pta237iipbbaf1r800kf` FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  CONSTRAINT `FK_tcm09xf4c1ryrsvl3fek8ukim` FOREIGN KEY (`RoleId`) REFERENCES `roleinfo` (`RoleId`),
  CONSTRAINT `FK_qs0jey5o0gmd73ocfqmdqxpr6` FOREIGN KEY (`NodeId`) REFERENCES `sysfun` (`NodeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sysfun
-- ----------------------------
DROP TABLE IF EXISTS `sysfun`;
CREATE TABLE `sysfun` (
  `NodeId` int(11) NOT NULL AUTO_INCREMENT,
  `ParentNodeId` int(11) NOT NULL,
  `DisplayName` varchar(50) NOT NULL,
  `NodeURL` varchar(50) DEFAULT NULL,
  `DisplayOrder` int(11) NOT NULL,
  PRIMARY KEY (`NodeId`),
  KEY `FK_qjcey6wj3t21rptkbadr3chus` (`ParentNodeId`),
  CONSTRAINT `FK_qjcey6wj3t21rptkbadr3chus` FOREIGN KEY (`ParentNodeId`) REFERENCES `sysfun` (`NodeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
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
-- Table structure for userstate
-- ----------------------------
DROP TABLE IF EXISTS `userstate`;
CREATE TABLE `userstate` (
  `UserStateId` int(11) NOT NULL AUTO_INCREMENT,
  `UserStateName` varchar(50) NOT NULL,
  PRIMARY KEY (`UserStateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
