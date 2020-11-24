package com.yi.nacos.config.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.yi.nacos.config.config.NacosConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YI
 * @date 2020/3/21 11:18
 */
@Component
public class NaCosUtil {
    @Autowired
    NacosConfig nacosConfig;

    /**
     * 获取配置
     *
     * @param dataId Data Id
     * @param group  Group
     * @return
     */
    public String getNacosConfig(String dataId, String group) {
        if (StrUtil.isEmpty(dataId) || StrUtil.isEmpty(group)) {
            return "参数为空!";
        }

        Map<String, Object> map = new HashMap<>();
        map.put("dataId", dataId);
        map.put("group", group);

        return HttpUtil.get(nacosConfig.getConfigs(), map);
    }
}