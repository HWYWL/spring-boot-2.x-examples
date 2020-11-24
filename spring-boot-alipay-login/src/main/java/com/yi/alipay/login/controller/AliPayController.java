package com.yi.alipay.login.controller;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthAlipayRequest;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支付宝授权登录
 *
 * @author YI
 * @date 2019-4-12 10:02:15
 */
@RestController
@RequestMapping("/oauth")
public class AliPayController {

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
     * @param auth_code 用户授权后回调回本地系统时携带的参数
     * @return
     */
    @RequestMapping("/callback")
    public Object login(String auth_code){
        AuthRequest authRequest = getAuthRequest();

        return authRequest.login(auth_code);
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
        return new AuthAlipayRequest(AuthConfig.builder()
                .clientId("2019041163867325")
                .clientSecret("2019041163867325")
                .alipayPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzd1BcGhdvP9IQjDc/KDDyeWfEyMm8pyBx+O+6y4chkKZbTLZlDSigCSdzxUcvt1n8li8lg8t3QGLVXCiN8/jmE8B6Y/g68FZ342HswRUFNdZvDPZYDtekIAilJyrIUuJ6lM+Kv1atjuEfU5whzYxNvc4zfsWWU0t+LmxRXKWK6Cln7gABKqq4u9TRe7N+xexKS0J4njVl4izAnjeMfM1hgCHYZuZHxBdTMtnyXsP/Ix0Lhv9AbIlA9UInY9drmKctFgmppFsbYQKNcn5xXgUOS53xLPPhsccaZtJzp2Smu5FIo7hW08er6enubWjq3Bdw341ew209qYEyXrpauA7JQIDAQAB")
                .redirectUri("http://hwy.ac.cn/oauth/callback")
        .build());
    }
}
