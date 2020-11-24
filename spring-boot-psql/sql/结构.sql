/*
 Navicat Premium Data Transfer

 Source Server         : 本地Postgres数据库
 Source Server Type    : PostgreSQL
 Source Server Version : 110004
 Source Host           : localhost:5432
 Source Catalog        : gamedb_test
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 110004
 File Encoding         : 65001

 Date: 26/11/2019 16:51:34
*/


-- ----------------------------
-- Table structure for baike
-- ----------------------------
DROP TABLE IF EXISTS "public"."baike";
CREATE TABLE "public"."baike" (
  "id" int4 NOT NULL DEFAULT nextval('baike_id_seq'::regclass),
  "book" varchar(255) COLLATE "pg_catalog"."default",
  "tag" varchar(255) COLLATE "pg_catalog"."default",
  "good" int4,
  "bad" int4,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "gender" varchar(100) COLLATE "pg_catalog"."default",
  "gold_coin" int4,
  "status" int2 NOT NULL,
  "crate_date" timestamp(6) NOT NULL,
  "update_date" timestamp(6) NOT NULL
)
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
-- Indexes structure for table baike
-- ----------------------------
CREATE UNIQUE INDEX "baike_id" ON "public"."baike" USING btree (
  "id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table baike
-- ----------------------------
ALTER TABLE "public"."baike" ADD CONSTRAINT "baike_pkey" PRIMARY KEY ("id");
