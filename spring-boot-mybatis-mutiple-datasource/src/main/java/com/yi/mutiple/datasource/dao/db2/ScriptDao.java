package com.yi.mutiple.datasource.dao.db2;

import com.yi.mutiple.datasource.model.ScriptInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * 操作Script对象
 * @author YI
 * @date 2018-12-6 09:40:19
 */
@Qualifier("db2SqlSessionTemplate")
public interface ScriptDao {
    /**
     * 根据脚本类型查找
     * @param type 脚本类型
     * @return
     */
    @Select("SELECT * FROM script_info WHERE type = #{type}")
    List<ScriptInfo> findScriptByType(Byte type);
}
