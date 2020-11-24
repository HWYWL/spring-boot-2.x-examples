package com.yi.tencent.cloud.controller;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthTencentCloudRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 腾讯云授权登录
 *
 * @author YI
 * @date 2019-4-4 12:00:40
 */
@RestController
@RequestMapping("/oauth")
public class TencentCloudController {

    /**
     * 生成授权地址
     * @param response
     * @throws IOException
     */
    @RequestMapping("/render/tencentcloud")
    public void rendderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();

        response.sendRedirect(authRequest.authorize());
    }

    /**
     * 回调
     * @param code 用户授权后回调回本地系统时携带的参数
     * @return
     */
    @RequestMapping("/callback/tencentcloud")
    public Object login(String code){
        AuthRequest authRequest = getAuthRequest();

        return authRequest.login(code);
    }

    /**
     * 取消授权
     * @param token 授权的token
     * @return
     */
    @RequestMapping("/revoke/tencentcloud/{token}")
    public Object revokeAuth(@PathVariable("token") String token){
        AuthRequest authRequest = getAuthRequest();

        return authRequest.revoke(token);
    }

    /**
     * 创建授权
     * @return
     */
    private AuthRequest getAuthRequest(){
        return new AuthTencentCloudRequest((AuthConfig.builder()
                .clientId("66415fe5deb40178eeb835dfa3a827cc")
                .clientSecret("a328be4e420689010023a2936881c8cfeaee0f05")
                .redirectUri("http://hwy.ac.cn/oauth/callback/tencentcloud")
        .build()
        ));
    }
}
