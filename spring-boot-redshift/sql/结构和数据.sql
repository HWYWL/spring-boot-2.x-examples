/*
 Navicat Premium Data Transfer

 Source Server         : 实时数据测试集群
 Source Server Type    : PostgreSQL
 Source Server Version : 80002
 Source Host           : realtime-data.cbsqzow79keo.cn-northwest-1.redshift.amazonaws.com.cn:5439
 Source Catalog        : realtimedb
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 80002
 File Encoding         : 65001

 Date: 20/12/2019 16:02:23
*/


-- ----------------------------
-- Table structure for baike
-- ----------------------------
DROP TABLE "public"."baike";
CREATE TABLE "public"."baike" (
  "id" int4 NOT NULL,
  "book" varchar(255),
  "tag" varchar(255),
  "good" int4,
  "bad" int4,
  "name" varchar(255),
  "gender" varchar(100),
  "gold_coin" int4,
  "status" int2 NOT NULL,
  "crate_date" date NOT NULL,
  "update_date" date NOT NULL
)
WITH OIDS
;
COMMENT ON COLUMN "public"."baike"."id" IS 'ID';
COMMENT ON COLUMN "public"."baike"."book" IS '书';
COMMENT ON COLUMN "public"."baike"."tag" IS '标签';
COMMENT ON COLUMN "public"."baike"."good" IS '点赞';
COMMENT ON COLUMN "public"."baike"."bad" IS '鄙视';
COMMENT ON COLUMN "public"."baike"."name" IS '作者名称';
COMMENT ON COLUMN "public"."baike"."gender" IS '作者性别';
COMMENT ON COLUMN "public"."baike"."gold_coin" IS '获得的金币打赏';
COMMENT ON COLUMN "public"."baike"."status" IS '0：上架、-1：下架';
COMMENT ON COLUMN "public"."baike"."crate_date" IS '创建时间';
COMMENT ON COLUMN "public"."baike"."update_date" IS '更新时间';
COMMENT ON TABLE "public"."baike" IS '百科表';

-- ----------------------------
-- Records of baike
-- ----------------------------
INSERT INTO "public"."baike" VALUES (2, '全职法师', '["魔幻","小说"]', 1000000, 5, '乱', '男', 1000, 0, '2019-12-20', '2019-12-20');
INSERT INTO "public"."baike" VALUES (1, '老人与海', '["文学","小说"]', 600, 10, '海明威', '男', 100, 0, '2019-12-20', '2019-12-20');

-- ----------------------------
-- Primary Key structure for table baike
-- ----------------------------
ALTER TABLE "public"."baike" ADD CONSTRAINT "baike_pkey" PRIMARY KEY ("id");
