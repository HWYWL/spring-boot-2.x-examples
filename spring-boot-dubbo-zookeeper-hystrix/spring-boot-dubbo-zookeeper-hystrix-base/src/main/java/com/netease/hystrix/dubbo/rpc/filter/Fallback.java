package com.netease.hystrix.dubbo.rpc.filter;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * 业务失败返回处理函数
 * @author YI
 * @date 2018-9-7 15:07:51
 */
@SPI
public interface Fallback {
    /**
     * 业务失败返回处理函数
     * @return
     */
    Object invoke();
}
