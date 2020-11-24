package com.yi.mybatis.plus.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.mybatis.plus.model.Baike;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作数据接口
 * @author YI
 * @date 2018-8-22 17:48:27
 */
public interface BaikeMapper extends BaseMapper<Baike> {
    /**
     * 通过注解sql查询
     * @return
     */
    @Select("SELECT * FROM `baike` WHERE bad > 5")
    List<Baike> selectListBySQL();

    /**
     * 根据条件查询
     * @param wrapper 组合条件
     * @return
     */
    List<Baike> selectListByWrapper(@Param("ew") Wrapper wrapper);
}