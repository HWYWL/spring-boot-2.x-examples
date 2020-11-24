package com.yi.consul.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yi.consul.config.ConsulConfig;
import com.yi.consul.model.Consul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * consul配置
 * @author YI
 * @date 2020/3/21 16:33
 */
@Component
public class ConsulUtil {
    @Autowired
    ConsulConfig consulConfig;

    /**
     * 获取单个
     * @param key 配置的路径，到具体的key
     * @return
     */
    public String getValue(String key){
        String template = consulConfig.getVal();
        String result = StrUtil.format(template, Dict.create().set("key", key));
        return HttpUtil.get(result);
    }

    /**
     * 获取目录下的配置列表
     * @param key 配置的路径，不到具体的key
     * @return
     */
    public List<Consul> getValAll(String key){
        String template = consulConfig.getValAll();
        String result = StrUtil.format(template, Dict.create().set("key", key));
        String resopn = HttpUtil.get(result);
        JSONArray array = JSONUtil.parseArray(resopn);
        List<Consul> consuls = JSONUtil.toList(array, Consul.class);
        consuls.forEach(e -> e.setValue(StrUtil.removeAllLineBreaks(Base64.decodeStr(e.getValue()))));

        return consuls;
    }
}