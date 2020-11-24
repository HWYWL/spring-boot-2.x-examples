package com.yi.dingtalk.login.controller;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthDingTalkRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeiboRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 钉钉授权登录
 *
 * @author YI
 * @date 2019-4-2 10:13:43
 */
@RestController
@RequestMapping("/oauth")
public class DingTalkController {

    /**
     * 生成授权地址
     * @param response
     * @throws IOException
     */
    @RequestMapping("/render/dingtalk")
    public void rendderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();

        response.sendRedirect(authRequest.authorize());
    }

    /**
     * 回调
     * @param code 用户授权后回调回本地系统时携带的参数
     * @return
     */
    @RequestMapping("/callback/dingtalk")
    public Object login(String code, String state){
        AuthRequest authRequest = getAuthRequest();

        return authRequest.login(code);
    }

    /**
     * 取消授权
     * @param token 授权的token
     * @return
     */
    @RequestMapping("/revoke/dingtalk/{token}")
    public Object revokeAuth(@PathVariable("token") String token){
        AuthRequest authRequest = getAuthRequest();

        return authRequest.revoke(token);
    }

    /**
     * 创建授权
     * @return
     */
    private AuthRequest getAuthRequest(){
        return new AuthDingTalkRequest((AuthConfig.builder()
                .clientId("dingoapshcdmbriyh6r0it")
                .clientSecret("6e8Gwjznt90oKcUkaSqDSVMqgDdR89DjJJzxgRPvCezKg0adbKd08-rWgGt4iLfJ")
                .redirectUri("http://localhost:8080/oauth/callback/dingtalk")
        .build()
        ));
    }
}
