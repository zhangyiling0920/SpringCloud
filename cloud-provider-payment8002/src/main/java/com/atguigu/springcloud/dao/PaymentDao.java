package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {
    Payment queryById(Long id);
    Long insert(String serial);



}
//-- ----------------------------
//-- Table structure for payment
//-- ----------------------------
//DROP TABLE IF EXISTS `payment`;
//CREATE TABLE `payment`
//(
//    `id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
//    `serial` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付流水号',
//    PRIMARY KEY (`id`) USING BTREE
//) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付表' ROW_FORMAT = Dynamic;
//
//-- ----------------------------
//-- Records of payment
//-- ----------------------------
//INSERT INTO `payment`
//VALUES (31, '尚硅谷111');
//INSERT INTO `payment`
//VALUES (32, 'atguigu002');
//INSERT INTO `payment`
//VALUES (34, 'atguigu002');
//INSERT INTO `payment`
//VALUES (35, 'atguigu002');
//
//SET
//FOREIGN_KEY_CHECKS = 1;