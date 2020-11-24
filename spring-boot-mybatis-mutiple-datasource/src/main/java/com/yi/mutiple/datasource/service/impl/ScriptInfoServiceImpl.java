package com.yi.mutiple.datasource.service.impl;

import com.yi.mutiple.datasource.dao.db2.ScriptDao;
import com.yi.mutiple.datasource.model.ScriptInfo;
import com.yi.mutiple.datasource.service.ScriptInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Script操作逻辑
 * @author YI
 * @date 2018-12-6 09:48:51
 */
@Service
public class ScriptInfoServiceImpl implements ScriptInfoService {
    @Autowired
    ScriptDao scriptDao;

    @Override
    public List<ScriptInfo> findScriptByType(Byte type) {
        return scriptDao.findScriptByType(type);
    }
}
