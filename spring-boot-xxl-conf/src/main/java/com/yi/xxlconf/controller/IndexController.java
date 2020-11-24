package com.yi.xxlconf.controller;

import com.xxl.conf.core.XxlConfClient;
import com.xxl.conf.core.annotation.XxlConf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xuxueli 2018-02-04 01:27:30
 */
@Slf4j
@Controller
public class IndexController {
    @XxlConf("default.key02")
    public String paramByAnno;

    /**
     * 配置变更监听示例：可开发Listener逻辑，监听配置变更事件；可据此实现动态刷新JDBC连接池等高级功能；
     */
    static {
        XxlConfClient.addListener("default.key01", (key, value) -> log.info("配置变更事件通知：{}={}", key, value));
    }

    @RequestMapping("/index")
    @ResponseBody
    public List<String> index(){

        List<String> list = new LinkedList<>();

        //方式1: API方式
        String paramByApi = XxlConfClient.get("default.key01", null);
        list.add("1、API方式: default.key01=" + paramByApi);

        //@XxlConf 注解方式
        list.add("2、@XxlConf 注解方式: default.key02=" + paramByAnno);

        return list;
    }

}
