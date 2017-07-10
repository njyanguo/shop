create database shop;
use shop;
-- 用户表
DROP TABLE IF EXISTS `shop_user`;
create table `shop_user`(
	`_id` VARCHAR(36) primary key,
	`user_id` varchar(32) not null,
	`user_name` varchar(128) not null,
	`PASS_word` varchar(64) not null,
	`phone` VARCHAR(11) ,
	`address` varchar(256),
	`createTime` TIMESTAMP,
	`updatetime` TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 一级商品类别表
DROP TABLE IF EXISTS `shop_category`;
CREATE TABLE `shop_category` (
  `_id` int(32) AUTO_INCREMENT,
  `category_id` varchar(16) NOT NULL,
  `category_name` varchar(128) NOT NULL,
  `category_parent_id` varchar(16) not null, -- 父类id 0 表示第一级，没有父类
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  `createby` varchar(20) NOT NULL default 'admin',
  `updatedate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  `updateby` varchar(20) DEFAULT NULL default 'admin',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 广告公告表

DROP TABLE IF EXISTS `advert`;

create table `advert`(
	`advid` int(5)  AUTO_INCREMENT,
	`advType` varchar(2) not null, -- 广告公告类型 A 广告   N 公告
	`advseq`  int not null,  -- 广告公告序号
	`advTitle` varchar(128),
	`advImg` varchar(128),
	`advstatus` varchar(2) not null,-- 广告公告状态   0 表示有效， 1表示无效
	`advContent` blob,              --广告公告内容
	`createby` varchar(20) not null,
	`createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
	`updateby` varchar(20) DEFAULT NULL,
	`updatedate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key (`advid`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `shop_goods`;

--商品表
--商品分类----------有几种衣服, A 衣服   B衣服   C衣服   D衣服
--商品类型----------可能有1种衣服有几个颜色,例如A衣服有 red blue etc.
CREATE TABLE `shop_goods` (
  `goods_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_code` varchar(200) DEFAULT NULL COMMENT '商品编号',
  `goods_name` varchar(200) NOT NULL DEFAULT '' COMMENT '商品名称',
  `goods_EngName` varchar(200) NOT NULL DEFAULT '' COMMENT '商品英文名',
  `goods_price` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '原价',
  `goods_type_id` mediumint(8) unsigned DEFAULT NULL COMMENT '商品类型',
  `goods_cat_id` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '商品分类',
  `goods_brand_id` mediumint(8) unsigned DEFAULT NULL COMMENT '商品品牌',
  `goods_series_id` mediumint(8) unsigned DEFAULT NULL COMMENT '商品系列',
  `marketable` enum('true','false') NOT NULL DEFAULT 'true' COMMENT '上架',
  `market_time_enable` enum('true','false') NOT NULL DEFAULT 'true' COMMENT '启用上下架时间',
  `goods_store` mediumint(8) unsigned DEFAULT '0' COMMENT '库存',
  `goods_notify_num` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '缺货登记',
  `goods_uptime` int(10) unsigned DEFAULT NULL COMMENT '上架时间',
  `goods_downtime` int(10) unsigned DEFAULT NULL COMMENT '下架时间',
  `goods_last_modify` int(10) unsigned DEFAULT NULL COMMENT '更新时间',
  --`p_order` mediumint(8) unsigned NOT NULL DEFAULT '30' COMMENT '排序',
  --`d_order` mediumint(8) unsigned NOT NULL DEFAULT '30' COMMENT '动态排序',
  `goods_score` mediumint(8) unsigned DEFAULT NULL COMMENT '积分',
  `goods_cost` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '成本价',
  `goods_mktprice` decimal(20,3) DEFAULT NULL COMMENT '建议价',
  `goods_weight` decimal(20,3) DEFAULT NULL COMMENT '重量',
  `goods_unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `goods_brief` varchar(255) DEFAULT NULL COMMENT '商品简介',
  --`goods_type` enum('normal','bind','gift') NOT NULL DEFAULT 'normal' COMMENT '销售类型',
  `goods_image_id` varchar(32) DEFAULT NULL COMMENT '默认图片',
  `goods_udfimg` enum('true','false') DEFAULT 'false' COMMENT '是否用户自定义图',
  `goods_udfimg_id` varchar(32) DEFAULT NULL COMMENT '用户自定义图',
  --`small_pic` varchar(255) DEFAULT NULL COMMENT '小图',
  --`big_pic` varchar(255) DEFAULT NULL COMMENT '大图',
  `goods_intro` longtext COMMENT '详细介绍',
  `goods_store_place` varchar(255) DEFAULT NULL COMMENT '库位',
  `goods_min_buy` mediumint(8) unsigned DEFAULT NULL COMMENT '起定量',
  --`package_scale` decimal(20,2) DEFAULT NULL COMMENT '打包比例',
  --`package_unit` varchar(20) DEFAULT NULL COMMENT '打包单位',
  --`package_use` enum('0','1') DEFAULT NULL COMMENT '是否开启打包',
  --`score_setting` enum('percent','number') DEFAULT 'number',
  --`store_prompt` mediumint(8) unsigned DEFAULT NULL COMMENT '库存提示规则',
  --`nostore_sell` enum('0','1') DEFAULT '0' COMMENT '是否开启无库存销售',
  --`goods_setting` longtext COMMENT '商品设置',
  --`spec_desc` longtext COMMENT '货品规格序列化',
  --`params` longtext COMMENT '商品规格序列化',
  --`disabled` enum('true','false') NOT NULL DEFAULT 'false',
  --`rank_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'google page rank count',
  --`comments_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论次数',
  --`view_w_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '周浏览次数',
  --`view_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '浏览次数',
  --`count_stat` longtext COMMENT '统计数据序列化',
  `buy_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '购买次数',
  --`buy_w_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '购买次数',
  `buy_m_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '月销量',
  --`ex_bn` varchar(255) DEFAULT NULL COMMENT '旧货号(ME系统数据)',
  --`mch` varchar(255) DEFAULT NULL COMMENT 'Mch(ME系统数据)',
  --`is_notify` enum('0','1') DEFAULT '0' COMMENT '是否到货通知',
  --`dec_pics` longtext COMMENT '商品实拍',
  --`wap_dec_pics` longtext COMMENT '手机端商品实拍',
  --`constitution` varchar(255) DEFAULT NULL COMMENT '成份',
  --`countryOfOrigin` varchar(255) DEFAULT NULL COMMENT '产地',
  --`countryOfOriginEng` varchar(255) DEFAULT NULL COMMENT '产地英文名',
  `shelf_life` varchar(255) DEFAULT NULL COMMENT '保质期',
  `reminder` varchar(255) DEFAULT NULL COMMENT '温馨提示',
  --`gtin` varchar(255) DEFAULT NULL COMMENT 'GTIN(ME系统数据)',
  `starttime` int(10) unsigned DEFAULT NULL COMMENT '生效时间',
  --`endtime` int(10) unsigned DEFAULT NULL COMMENT '到期时间',
  --`isNewShowHomePage` enum('0','1') DEFAULT '0' COMMENT '显示到首页新品位置',
  --`isNew` enum('0','1') DEFAULT '0' COMMENT '首页新品位置显示新品标签',
  `isIntake` enum('0','1') DEFAULT '0' COMMENT '是否为食品',
  `isClearanceProduct` enum('0','1') DEFAULT '0' COMMENT '是否为清货产品',
  --`colorCode` varchar(255) DEFAULT NULL COMMENT 'colorCode',
  --`colorName` varchar(255) DEFAULT NULL COMMENT 'colorName',
  --`sizeCode` varchar(255) DEFAULT NULL COMMENT 'sizeCode',
  --`productSize` varchar(255) DEFAULT NULL COMMENT 'productSize',
  --`color` varchar(255) DEFAULT NULL COMMENT '颜色(图片ID或纯色值)',
  --`stock_out_time` int(10) unsigned DEFAULT NULL COMMENT '缺货时间',
  `p_1` mediumint(8) unsigned DEFAULT NULL,
  `p_2` mediumint(8) unsigned DEFAULT NULL,
  `p_3` mediumint(8) unsigned DEFAULT NULL,
  `p_4` mediumint(8) unsigned DEFAULT NULL,
  `p_5` mediumint(8) unsigned DEFAULT NULL,
  `p_6` mediumint(8) unsigned DEFAULT NULL,
  `p_7` mediumint(8) unsigned DEFAULT NULL,
  `p_8` mediumint(8) unsigned DEFAULT NULL,
  `p_9` mediumint(8) unsigned DEFAULT NULL,
  `p_10` mediumint(8) unsigned DEFAULT NULL,
  `p_11` mediumint(8) unsigned DEFAULT NULL,
  `p_12` mediumint(8) unsigned DEFAULT NULL,
  `p_13` mediumint(8) unsigned DEFAULT NULL,
  `p_14` mediumint(8) unsigned DEFAULT NULL,
  `p_15` mediumint(8) unsigned DEFAULT NULL,
  `p_16` mediumint(8) unsigned DEFAULT NULL,
  `p_17` mediumint(8) unsigned DEFAULT NULL,
  `p_18` mediumint(8) unsigned DEFAULT NULL,
  `p_19` mediumint(8) unsigned DEFAULT NULL,
  `p_20` mediumint(8) unsigned DEFAULT NULL,
  `p_21` varchar(255) DEFAULT NULL,
  `p_22` varchar(255) DEFAULT NULL,
  `p_23` varchar(255) DEFAULT NULL,
  `p_24` varchar(255) DEFAULT NULL,
  `p_25` varchar(255) DEFAULT NULL,
  `p_26` varchar(255) DEFAULT NULL,
  `p_27` varchar(255) DEFAULT NULL,
  `p_28` varchar(255) DEFAULT NULL,
  `p_29` varchar(255) DEFAULT NULL,
  `p_30` varchar(255) DEFAULT NULL,
  `p_31` varchar(255) DEFAULT NULL,
  `p_32` varchar(255) DEFAULT NULL,
  `p_33` varchar(255) DEFAULT NULL,
  `p_34` varchar(255) DEFAULT NULL,
  `p_35` varchar(255) DEFAULT NULL,
  `p_36` varchar(255) DEFAULT NULL,
  `p_37` varchar(255) DEFAULT NULL,
  `p_38` varchar(255) DEFAULT NULL,
  `p_39` varchar(255) DEFAULT NULL,
  `p_40` varchar(255) DEFAULT NULL,
  `p_41` varchar(255) DEFAULT NULL,
  `p_42` varchar(255) DEFAULT NULL,
  `p_43` varchar(255) DEFAULT NULL,
  `p_44` varchar(255) DEFAULT NULL,
  `p_45` varchar(255) DEFAULT NULL,
  `p_46` varchar(255) DEFAULT NULL,
  `p_47` varchar(255) DEFAULT NULL,
  `p_48` varchar(255) DEFAULT NULL,
  `p_49` varchar(255) DEFAULT NULL,
  `p_50` varchar(255) DEFAULT NULL,
  primary key (`goods_id`)
  --`act_type` varchar(20) DEFAULT 'normal' COMMENT '活动类型',
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



--货品表

CREATE TABLE `sdb_b2c_products` (
  `product_id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '货品ID',
  `goods_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '商品ID',
  `barcode` varchar(128) DEFAULT NULL COMMENT '条码',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `bn` varchar(30) DEFAULT NULL COMMENT '货号',
  `price` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '原价格',
  `cost` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '成本价',
  `mktprice` decimal(20,3) DEFAULT NULL COMMENT '建议价',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '货品名称',
  `weight` decimal(20,3) DEFAULT NULL COMMENT '单位重量',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `store` mediumint(8) unsigned DEFAULT '0' COMMENT '库存',
  `store_place` varchar(255) DEFAULT NULL COMMENT '库位',
  `freez` mediumint(8) unsigned DEFAULT NULL COMMENT '冻结库存',
  `goods_type` enum('normal','bind','gift') NOT NULL DEFAULT 'normal' COMMENT '销售类型',
  `spec_info` longtext COMMENT '物品描述',
  `spec_desc` longtext COMMENT '规格值,序列化',
  `is_default` enum('true','false') NOT NULL DEFAULT 'false',
  `qrcode_image_id` varchar(32) DEFAULT NULL COMMENT '二维码图片ID',
  `uptime` int(10) unsigned DEFAULT NULL COMMENT '录入时间',
  `last_modify` int(10) unsigned DEFAULT NULL COMMENT '最后修改时间',
  `disabled` enum('true','false') DEFAULT 'false',
  `marketable` enum('true','false') NOT NULL DEFAULT 'true' COMMENT '上架',
  `ex_bn` varchar(255) DEFAULT NULL COMMENT '旧货号(ME系统数据)',
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `ind_bn` (`bn`) USING BTREE,
  KEY `ind_goods_id` (`goods_id`) USING BTREE,
  KEY `ind_disabled` (`disabled`) USING BTREE,
  KEY `ind_barcode` (`barcode`) USING BTREE,
  KEY `idx_goods_type` (`goods_type`) USING BTREE,
  KEY `idx_store` (`store`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;