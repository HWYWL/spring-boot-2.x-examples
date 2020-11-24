package com.yi.current.mapper.dao;

import com.yi.current.mapper.model.Baike;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 操作数据接口
 * @author YI
 * @date 2018-11-27 15:51:01
 */
public interface BaikeMapper extends Mapper<Baike> {
    /**
     * 通过注解sql查询
     * @return
     */
    @Select("SELECT * FROM `baike` WHERE bad > #{bad}")
    List<Baike> selectListBySQL(int bad);

    /**
     * 通过xml访问数据库（不推荐方式）
     * @param book
     * @return
     */
    List<Baike> selectByBookName(String book);
}