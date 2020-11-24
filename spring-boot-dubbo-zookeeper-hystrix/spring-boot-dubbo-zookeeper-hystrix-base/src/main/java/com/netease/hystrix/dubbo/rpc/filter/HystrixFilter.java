package com.netease.hystrix.dubbo.rpc.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.netease.hystrix.dubbo.rpc.filter.config.*;
import com.netflix.hystrix.HystrixCommand;

/**
 * 熔断执行，通过反射获取需要执行的方法
 * @author YI
 * @date 2018-9-7 16:19:13
 */
@Activate(group = Constants.CONSUMER, before = "future")
public class HystrixFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        URL url = invoker.getUrl();
        String methodName = invocation.getMethodName();
        String interfaceName = invoker.getInterface().getName();
        //获取相关熔断配置
        HystrixCommand.Setter setter = SetterFactory.create(interfaceName, methodName, url);
        //获取降级方法
        String fallback = url.getMethodParameter(methodName, methodName);

        System.out.println("熔断..................");

        DubboCommand command = new DubboCommand(setter, invoker, invocation, fallback);
        Result result = command.execute();

        return result;
    }

}
