package com.yi.psql.mapper;

import com.yi.psql.model.Baike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BaikeDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Baike record);

    int insertSelective(Baike record);

    Baike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Baike record);

    int updateByPrimaryKey(Baike record);
}