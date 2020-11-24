# spring-boot-gitee-login
我们使用gitee（码云）授权登录我们的网站，咿呀咿呀哟

首先我们到这个地址去取光剑一个我们的应用
```
https://gitee.com/oauth/applications
```
![](https://i.imgur.com/bprB4G4.png)

创建完成之后可以得到**Client ID**和**Client Secret**这两个东西
![](https://i.imgur.com/NE1u2xs.png)

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
 * gitee授权登录
 *
 * @author YI
 * @date 2019-3-28 11:04:43
 */
@RestController
@RequestMapping("/oauth")
public class GiteeController {

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

        AuthResponse revoke = authRequest.revoke(token);
        return revoke;
    }

    /**
     * 创建授权
     * @return
     */
    private AuthRequest getAuthRequest(){
        return new AuthGiteeRequest(AuthConfig.builder()
                .clientId("1049e8dbca6b16b99f289ace91f51b69d4197cf1c8d633a09f49cfa2ba65a99d")
                .clientSecret("053e24571d26b62ce4927353d84f3cc465fe6ba6af1e45759f0d782e0a186d12")
                .redirectUri("http://localhost:8080/oauth/callback")
        .build()
        );
    }
}
```

再一顿骚操作之后写完我们的前端页面
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>授权登录</title>
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
    </div>
</div>

<hr class="layui-bg-red">

<div class="center-button">
    <a class="layui-btn layui-btn-normal" href="/oauth/render">Gitee登录授权</a>
</div>

<script src="/layui/layui.js" charset="utf-8"></script>
</body>
</html>
```

随后访问我们的 http://localhost:8080

![](https://i.imgur.com/ChJYSQn.png)
我们点击授权，弹出授权窗口，我们点击授权之后会回调我们之前创建应用填写的地址
![](https://i.imgur.com/n3lIJzI.png)

我们这个地址借的是获取用户信息，更多详情请参考代码
### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL