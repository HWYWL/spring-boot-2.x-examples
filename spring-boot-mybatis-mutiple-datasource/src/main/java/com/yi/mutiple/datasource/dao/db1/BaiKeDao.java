package com.yi.mutiple.datasource.dao.db1;

import com.yi.mutiple.datasource.model.Baike;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 操作baike对象
 * @author YI
 * @date 2018-12-6 09:40:19
 */
@Qualifier("db1SqlSessionTemplate")
public interface BaiKeDao {
    /**
     * 根据id查找
     * @param id 主键id
     * @return
     */
    @Select("SELECT * FROM baike WHERE id = #{id}")
    Baike findBaikeById(Integer id);
}
