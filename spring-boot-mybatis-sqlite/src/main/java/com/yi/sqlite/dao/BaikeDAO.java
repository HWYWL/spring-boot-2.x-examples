package com.yi.sqlite.dao;

import com.yi.sqlite.model.Baike;
import com.yi.sqlite.model.BaikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaikeDAO {
    long countByExample(BaikeExample example);

    int deleteByExample(BaikeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Baike record);

    int insertSelective(Baike record);

    List<Baike> selectByExample(BaikeExample example);

    Baike selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Baike record, @Param("example") BaikeExample example);

    int updateByExample(@Param("record") Baike record, @Param("example") BaikeExample example);

    int updateByPrimaryKeySelective(Baike record);

    int updateByPrimaryKey(Baike record);
}