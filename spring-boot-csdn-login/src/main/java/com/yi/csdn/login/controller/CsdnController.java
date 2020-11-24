package com.yi.csdn.login.controller;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthCsdnRequest;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CSDN授权登录
 *
 * @author YI
 * @date 2019-3-28 11:04:43
 */
@RestController
@RequestMapping("/oauth")
public class CsdnController {

    /**
     * 生成授权地址
     * @param response
     * @throws IOException
     */
    @RequestMapping("/render")
    public void rendderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();

        response.sendRedirect(authRequest.authorize());
    }

    /**
     * 回调
     * @param code 用户授权后回调回本地系统时携带的参数
     * @return
     */
    @RequestMapping("/callback")
    public Object login(String code){
        AuthRequest authRequest = getAuthRequest();

        return authRequest.login(code);
    }

    /**
     * 取消授权
     * @param token 授权的token
     * @return
     */
    @RequestMapping("/revoke/{token}")
    public Object revokeAuth(@PathVariable("token") String token){
        AuthRequest authRequest = getAuthRequest();

        return authRequest.revoke(token);
    }

    /**
     * 创建授权
     * @return
     */
    private AuthRequest getAuthRequest(){
        return new AuthCsdnRequest(AuthConfig.builder()
                .clientId("mS1SqdpQpXMsWaCvmO6w")
                .clientSecret("ID2P279ZVkn8qyI3y03yIbF608uv95qS")
                .redirectUri("http://localhost:8080/oauth/callback")
        .build()
        );
    }
}
