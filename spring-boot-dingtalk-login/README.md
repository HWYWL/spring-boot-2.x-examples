# spring-boot-dingtalk-login
我们使用钉钉授权登录我们的网站，咿呀咿呀哟

首先我们到这个地址去新建一个我们的应用
```
https://open-dev.dingtalk.com/#/loginAndShareApp
```
![](https://i.imgur.com/UVXVGRH.png)

创建完成之后可以得到**appId**和**appSecret**这两个东西

接下来我们写我们的代码，下面这个是第三方登录的一个sdk
```
<dependency>
    <groupId>me.zhyd.oauth</groupId>
    <artifactId>JustAuth</artifactId>
    <version>1.0.1</version>
</dependency>
```

一顿骚操作之后写完我们的代码后端代码
```java
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
```

再一顿骚操作之后写完我们的前端页面
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录授权</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/script.css">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <img src="/image/logo.gif" class="img-responsive center-block" alt="Cinque Terre">
            <h3 class="text-center">登录授权</h3>
        </div>

        <hr class="layui-bg-red">

        <div class="text-center">
            <a class="layui-btn layui-btn-normal" href="/oauth/render/dingtalk">钉钉登录授权</a>
        </div>
    </div>
</div>

<script src="/layui/layui.js" charset="utf-8"></script>
</body>
</html>
```

随后访问我们的 http://localhost:8080
![](https://i.imgur.com/fQ1aVCz.png)
我们点击授权，弹出授权窗口
![](https://i.imgur.com/pDuNMkE.png)
我们用钉钉扫码授权之后会回调我们之前创建应用填写的回调地址
![](https://i.imgur.com/FCFso2K.png)

我们这个地址是获取用户信息，更多详情请参考代码
### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL