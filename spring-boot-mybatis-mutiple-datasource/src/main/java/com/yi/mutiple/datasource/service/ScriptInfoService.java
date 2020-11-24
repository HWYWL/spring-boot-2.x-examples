package com.yi.mutiple.datasource.service;

import com.yi.mutiple.datasource.model.ScriptInfo;

import java.util.List;

/**
 * Script操作逻辑
 * @author YI
 * @date 2018-12-6 09:48:51
 */
public interface ScriptInfoService {
    /**
     * 根据脚本类型查找
     * @param type 脚本类型
     * @return
     */
    List<ScriptInfo> findScriptByType(Byte type);
}
