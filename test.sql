DROP TABLE IF EXISTS `monitoring`;
CREATE TABLE `monitoring` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户',
  `anomalous_region` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '异区域',
  `customer_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '客户id',
  `customer_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '客户名称',
  `abnormal_condition` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '异常信息',
  `machine_type` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '机器类型:1-新风 2-饮水机',
  `create_time` bigint(13) DEFAULT NULL COMMENT '添加时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
  `abnormal_time` bigint(13) DEFAULT NULL COMMENT '异常时间',
  `monitor_code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '机器编号',
  `monitor_mode_code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '机器型号',
  `belong_region` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '所属区域',
  `belong_floor` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '所属楼层',
  `cumulative_flow` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '累计流量',
  `length` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '时长',
  `param_json` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '参数',
  `status` varchar(3) COLLATE utf8_bin DEFAULT NULL COMMENT '状态：1-开机  2-关机',
  `Stall` varchar(3) COLLATE utf8_bin DEFAULT NULL COMMENT '档位:1 2 3',
  `prop1` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段1',
  `prop2` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段2',
  `prop3` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段3',
  `prop4` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段4',
  `prop5` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段5',
  `prop6` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段6',
  `prop7` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段7',
  `prop8` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段8',
  `prop9` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段9',
  `prop10` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段10',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3012 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='机器表';

-- ----------------------------
-- Records of monitoring
-- ----------------------------
BEGIN;
INSERT INTO `monitoring` VALUES (1, 'user1', '用户名字', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac1', 'A型', '区域1', '二楼', '', '4', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (2, 'user1', '用户名字', NULL, NULL, NULL, '1', '1', NULL, NULL, NULL, 'mac2', 'A型', '区域4', '3楼', '', '3', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (3, 'user1', '用户名字', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac3', 'A型', '区域2', '1楼', '', '4', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (4, 'user1', '用户名字', NULL, NULL, NULL, '1', '1', NULL, NULL, NULL, 'mac4', 'A型', '区域1', '4楼', '', '13', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (5, 'user1', '用户名字', NULL, NULL, NULL, '1', '2', NULL, NULL, NULL, 'w1', 'B型', '区域2', '二楼', '32', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (6, 'user1', '用户名字', NULL, NULL, NULL, '1', '2', NULL, NULL, NULL, 'w2', 'B型', '区域2', '3楼', '63', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (7, 'user1', '用户名字', NULL, NULL, NULL, '1', '2', NULL, NULL, NULL, 'w3', 'B型', '区域1', '1楼', '36', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (8, 'user1', '用户名字', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w4', 'B型', '区域4', '4楼', '33', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (9, 'user2', '用户名字2', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac21', 'A型', '区域2', '3楼', '', '3', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (10, 'user2', '用户名字2', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac31', 'A型', '区域2', '1楼', '', '4', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (11, 'user2', '用户名字2', NULL, NULL, NULL, '1', '1', NULL, NULL, NULL, 'mac41', 'A型', '区域1', '4楼', '', '13', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (12, 'user2', '用户名字2', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w11', 'B型', '区域2', '二楼', '34', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (13, 'user2', '用户名字2', NULL, NULL, NULL, '1', '2', NULL, NULL, NULL, 'w21', 'B型', '区域2', '3楼', '3', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (14, 'user2', '用户名字2', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w31', 'B型', '区域1', '1楼', '4', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (15, 'user2', '用户名字2', NULL, NULL, NULL, '1', '2', NULL, NULL, NULL, 'w41', 'B型', '区域2', '4楼', '1', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (16, 'user3', '用户名字3', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac12', 'A型', '区域1', '二楼', '', '4', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (17, 'user3', '用户名字3', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac22', 'A型', '区域2', '3楼', '', '3', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (18, 'user3', '用户名字3', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac32', 'A型', '区域2', '1楼', '', '4', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (19, 'user3', '用户名字3', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac42', 'A型', '区域1', '4楼', '', '13', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (20, 'user3', '用户名字3', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w12', 'B型', '5', '二楼', '2', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (21, 'user3', '用户名字3', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w22', 'B型', '区域2', '3楼', '3', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (22, 'user3', '用户名字3', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w32', 'B型', '区域1', '1楼', '2', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (23, 'user3', '用户名字3', NULL, NULL, NULL, '1', '2', NULL, NULL, NULL, 'w42', 'B型', '区域2', '4楼', '7', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (24, 'user4', '用户名字4', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac212', 'A型', '区域2', '3楼', '', '3', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (25, 'user4', '用户名字4', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac312', 'A型', '区域2', '1楼', '', '4', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (26, 'user4', '用户名字4', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac412', 'A型', '区域1', '4楼', '', '13', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (27, 'user4', '用户名字4', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w112', 'B型', '区域2', '二楼', '5', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (28, 'user4', '用户名字4', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w212', 'B型', '区域3', '3楼', '4', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (29, 'user4', '用户名字4', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w312', 'B型', '区域1', '1楼', '3', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (30, 'user4', '用户名字4', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w412', 'B型', '区域2', '4楼', '2', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (111, 'user111', '用户名字11', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac111', 'A型', '区域1', '二楼', '', '4', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (211, 'user111', '用户名字11', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac211', 'A型', '区域2', '3楼', '', '3', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (311, 'user111', '用户名字11', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac311', 'A型', '区域2', '1楼', '', '4', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (411, 'user111', '用户名字11', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac411', 'A型', '区域1', '4楼', '', '13', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (511, 'user111', '用户名字11', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w111', 'B型', '区域2', '二楼', '123', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (611, 'user111', '用户名字11', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w211', 'B型', '区域2', '3楼', '45', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (711, 'user111', '用户名字11', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w311', 'B型', '区域1', '1楼', '3', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (811, 'user111', '用户名字11', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w411', 'B型', '区域3', '4楼', '234', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (911, 'user211', '用户名字211', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac2111', 'A型', '区域2', '3楼', '', '3', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (1011, 'user211', '用户名字211', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac3111', 'A型', '区域2', '1楼', '', '4', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (1111, 'user211', '用户名字211', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac4111', 'A型', '区域1', '4楼', '', '13', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (1211, 'user211', '用户名字211', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w1111', 'B型', '区域2', '二楼', '4', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (1311, 'user211', '用户名字211', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w2111', 'B型', '区域2', '3楼', '25', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (1411, 'user211', '用户名字211', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w3111', 'B型', '区域1', '1楼', '5', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (1511, 'user211', '用户名字211', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w4111', 'B型', '区域2', '4楼', '432', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (1611, 'user311', '用户名字311', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac1211', 'A型', '区域1', '二楼', '', '4', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (1711, 'user311', '用户名字311', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac2211', 'A型', '区域2', '3楼', '', '3', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (1811, 'user311', '用户名字311', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac3211', 'A型', '区域2', '1楼', '', '4', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (1911, 'user311', '用户名字311', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac4211', 'A型', '区域1', '4楼', '', '13', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (2011, 'user311', '用户名字311', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w1211', 'B型', '区域6', '二楼', '12', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (2111, 'user311', '用户名字311', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w2211', 'B型', '区域2', '3楼', '235', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (2211, 'user311', '用户名字311', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w3211', 'B型', '区域1', '1楼', '5', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (2311, 'user311', '用户名字311', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w4211', 'B型', '区域3', '4楼', '35', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (2411, 'user411', '用户名字411', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac21211', 'A型', '区域2', '3楼', '', '3', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (2511, 'user411', '用户名字411', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac31211', 'A型', '区域2', '1楼', '', '4', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (2611, 'user411', '用户名字411', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'mac41211', 'A型', '区域1', '4楼', '', '13', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (2711, 'user411', '用户名字411', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w11211', 'B型', '区域3', '二楼', '66', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (2811, 'user411', '用户名字411', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w21211', 'B型', '区域7', '3楼', '123', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (2911, 'user411', '用户名字411', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w31211', 'B型', '区域1', '1楼', '745', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring` VALUES (3011, 'user411', '用户名字411', NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, 'w41211', 'B型', '区域2', '4楼', '1', '', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `monitoring_history`;
CREATE TABLE `monitoring_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `monitor_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '机器id',
  `monitor_code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '机器编号',
  `monitor_mode_code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '机器型号',
  `monitor_detail_json` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '机器数据详情',
  `create_time` bigint(13) DEFAULT NULL COMMENT '添加时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
  `prop1` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '时长',
  `prop2` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段2',
  `prop3` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段3',
  `prop4` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段4',
  `prop5` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段5',
  `prop6` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段6',
  `prop7` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段7',
  `prop8` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段8',
  `prop9` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段9',
  `prop10` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段10',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='机器表历史记录表';

-- ----------------------------
-- Records of monitoring_history
-- ----------------------------
BEGIN;
INSERT INTO `monitoring_history` VALUES (11, '15', 'w41', 'B型', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', 1518361369000, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring_history` VALUES (12, '15', 'w41', 'B型', '{\"raw\":\"44\",\"purification\":\"22\",\"flow\":\"112\"}', 1518361469000, NULL, '1.5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring_history` VALUES (13, '15', 'w41', 'B型', '{\"raw\":\"41\",\"purification\":\"12\",\"flow\":\"212\"}', 1518362469000, NULL, '5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring_history` VALUES (14, '15', 'w41', 'B型', '{\"raw\":\"43\",\"purification\":\"21\",\"flow\":\"12\"}', 1518361369000, NULL, '6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring_history` VALUES (15, '15', 'w41', 'B型', '{\"raw\":\"44\",\"purification\":\"22\",\"flow\":\"112\"}', 1518361469000, NULL, '7', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring_history` VALUES (16, '15', 'w41', 'B型', '{\"raw\":\"41\",\"purification\":\"12\",\"flow\":\"212\"}', 1518362469000, NULL, '8', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring_history` VALUES (211, '11', 'mac41', 'A型', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', 1518361369000, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring_history` VALUES (212, '11', 'mac41', 'A型', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', 1518361469000, NULL, '1.5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring_history` VALUES (213, '11', 'mac41', 'A型', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', 1518362469000, NULL, '5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring_history` VALUES (214, '11', 'mac41', 'A型', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', 1518361369000, NULL, '6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring_history` VALUES (215, '11', 'mac41', 'A型', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', 1518361469000, NULL, '7', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `monitoring_history` VALUES (216, '11', 'mac41', 'A型', '{\"pm\":\"100000\",\"co2\":\"21\",\"voc\":\"12\",\"temperature\":\"43度\",\"humidity\":\"23度\"}', 1518362469000, NULL, '8', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
