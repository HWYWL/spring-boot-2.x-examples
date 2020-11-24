package com.yi.mutiple.datasource.controller;

import com.yi.mutiple.datasource.config.ScriptEnum;
import com.yi.mutiple.datasource.model.ScriptInfo;
import com.yi.mutiple.datasource.service.ScriptInfoService;
import com.yi.mutiple.datasource.utils.MessageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据源访问
 * @author YI
 * @date 2018-4-27 16:11:21
 */
@RestController
@RequestMapping("/script")
public class ScriptController {
    private static final Logger log = LoggerFactory.getLogger(ScriptController.class);

    @Autowired
    ScriptInfoService scriptInfoService;

    @RequestMapping("/findScriptByType")
    public MessageResult findScriptByType(){
        List<ScriptInfo> scriptInfos = scriptInfoService.findScriptByType(ScriptEnum.BLANK.getValue());

        log.info(scriptInfos.toString());

        return MessageResult.ok(scriptInfos);
    }
}
