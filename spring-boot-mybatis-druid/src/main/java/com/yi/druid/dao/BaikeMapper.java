package com.yi.druid.dao;

import com.yi.druid.model.Baike;
import com.yi.druid.model.BaikeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作数据接口
 * @author YI
 * @date 2018-8-22 17:48:27
 */
public interface BaikeMapper {
    /**
     * 根据条件统计
     * @param example
     * @return
     */
    long countByExample(BaikeExample example);

    /**
     * 根据条件删除
     * @param example
     * @return
     */
    int deleteByExample(BaikeExample example);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据，包括null
     * @param record
     * @return
     */
    int insert(Baike record);

    /**
     * 插入数据，不包括null
     * @param record
     * @return
     */
    int insertSelective(Baike record);

    /**
     * 条件查询
     * @param example
     * @return
     */
    List<Baike> selectByExample(BaikeExample example);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    Baike selectByPrimaryKey(Long id);

    /**
     * 根据条件更新数据，不包括null
     * @param record    数据
     * @param example   条件
     * @return
     */
    int updateByExampleSelective(@Param("record") Baike record, @Param("example") BaikeExample example);

    /**
     * 根据条件更新数据，包括null
     * @param record    数据
     * @param example   条件
     * @return
     */
    int updateByExample(@Param("record") Baike record, @Param("example") BaikeExample example);

    /**
     * 根据bean更新数据，不包括null，bean里面必须有主键
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Baike record);

    /**
     * 根据bean更新数据，包括null，bean里面必须有主键
     * @param record
     * @return
     */
    int updateByPrimaryKey(Baike record);
}