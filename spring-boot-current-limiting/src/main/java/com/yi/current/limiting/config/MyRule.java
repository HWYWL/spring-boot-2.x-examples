//package com.yi.current.limiting.config;
//
//import cn.yueshutong.springbootstartercurrentlimiting.handler.CurrentRuleHandler;
//import cn.yueshutong.springbootstartercurrentlimiting.property.CurrentProperty;
//import cn.yueshutong.springbootstartercurrentlimiting.property.CurrentPropertyFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 自定义限流规则，使用此规则系统默认配置的限流规则会失效
// *
// * @author YI
// * @date 2019-4-27 12:35:52
// */
//@Component
//public class MyRule implements CurrentRuleHandler {
//    private Logger log = LoggerFactory.getLogger(getClass());
//
//    /**
//     * CurrentProperty 构造方法参数说明：
//     * id	标识名。若为IP地址则为IP地址限流，若为用户名则为用户限流，若为访问的URL则为接口限流。
//     * qps	每秒并发量。支持小数、分数，计算规则：次数/时间(秒)。为0禁止访问。
//     * initialDelay	首次放入令牌（即允许访问）延迟时间，可作为系统启动保护时间，单位/毫秒。
//     * failFast	是否需开启快速失败。false即切换为阻塞。
//     * overflow	是否严格控制请求速率和次数，true即切换为漏桶算法。
//     * time	该规则限流器对象的存活时间（选填）
//     * unit	该规则限流器对象的存活时间单位（选填）
//     * @param request 请求
//     * @return
//     */
//    @Override
//    public CurrentProperty rule(HttpServletRequest request) {
//        request.getServletPath(); // /hello
//        request.getMethod(); // GET
//        request.getRemoteHost(); // 127.0.0.1
//        request.getSession(); // session
//
//        //返回NULL是无规则的意思，即放行。
//        return CurrentPropertyFactory.of("Default", 3, 0, true, true);
//    }
//}
