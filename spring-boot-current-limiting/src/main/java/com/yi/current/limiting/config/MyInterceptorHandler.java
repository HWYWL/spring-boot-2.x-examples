//package com.yi.current.limiting.config;
//
//import cn.yueshutong.springbootstartercurrentlimiting.handler.CurrentInterceptorHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 针对系统级别的拒绝策略
// *
// * @author YI
// * @date 2019-4-27 12:30:53
// */
//@Component
//public class MyInterceptorHandler implements CurrentInterceptorHandler {
//
//    @Override
//    public void preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        response.getWriter().print("当前系统不可用");
//    }
//}
