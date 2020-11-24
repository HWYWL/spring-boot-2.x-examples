package com.yi.base;

/**
 * 对外提供公共的接口服务
 * @author YI
 * @date 2018-8-20 16:32:55
 */
public interface HelloService {
    /**
     * 公共接口 sayHello
     * @param name 称呼
     * @return
     */
    String sayHello(String name);

    /**
     * 公共接口 sayGoodbye
     * @param name 称呼
     * @return
     */
    String sayGoodbye(String name);
}
