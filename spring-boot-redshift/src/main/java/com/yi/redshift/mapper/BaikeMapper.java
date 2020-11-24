package com.yi.redshift.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.redshift.model.Baike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 实时数据数据库操作
 *
 * @author huangwenyi
 */
@Repository
public interface BaikeMapper extends BaseMapper<Baike> {
    /**
     * 通过注解sql查询
     *
     * @return
     */
    @Select("SELECT * FROM baike WHERE book = #{book, jdbcType=VARCHAR}")
    List<Baike> selectListBySQL(String book);

    /**
     * 通过id查询
     *
     * @return
     */
    @Select("SELECT * FROM baike WHERE id = #{id, jdbcType=INTEGER}")
    Baike selectById(Integer id);

    /**
     * 数据插入 如果字段和Amazon Redshift 保留关键字相同，需要用双引号括起来
     * 关键字列表 https://docs.amazonaws.cn/redshift/latest/dg/r_pg_keywords.html
     *
     * @param list 数据集合
     */
    @Insert("<script> INSERT INTO baike(id, book, label, good, bad, name, gender, gold_coin, status, crate_date, update_date) " +
            "VALUES " +
            "<foreach collection='list' item='item' index='index' separator=','>" +
            "(#{item.id}, #{item.book}, #{item.label}, #{item.good}, #{item.bad}, #{item.name}, #{item.gender}, #{item.goldCoin}, #{item.status}, #{item.crateDate}, #{item.updateDate}) " +
            "</foreach> </script>"
    )
    int insertBaike(List<Baike> list);

    /**
     * 通过id删除
     *
     * @return
     */
    @Delete("delete from baike where id=#{id}")
    int deleteById(Integer id);

    /**
     * 数据插入 如果字段和Amazon Redshift 保留关键字相同，需要用双引号括起来
     * 关键字列表 https://docs.amazonaws.cn/redshift/latest/dg/r_pg_keywords.html
     *
     * @param baike
     */
    @Update({
            "<script>" +
                    "UPDATE baike\n" +
                    "        <set>\n" +
                    "                    <if test ='null != book'>book = #{book},</if>\n" +
                    "                    <if test ='null != label'>label = #{label},</if>\n" +
                    "                    <if test ='null != good'>good = #{good},</if>\n" +
                    "                    <if test ='null != bad'>bad = #{bad},</if>\n" +
                    "                    <if test ='null != name'>name = #{name},</if>\n" +
                    "                    <if test ='null != gender'>gender = #{gender},</if>\n" +
                    "                    <if test ='null != goldCoin'>gold_coin = #{goldCoin},</if>\n" +
                    "                    <if test ='null != status'>status = #{status},</if>\n" +
                    "                    <if test ='null != crateDate'>crate_date = #{crateDate},</if>\n" +
                    "                    <if test ='null != updateDate'>update_date = #{updateDate}</if>\n" +
                    "        </set>\n" +
                    "        WHERE id = #{id}" +
                    "</script>"
    })
    void updateBaike(Baike baike);
}
