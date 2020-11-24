# spring-boot-github-login
我们使用GitHub授权登录我们的网站，咿呀咿呀哟

首先我们到这个地址去新建一个我们的应用
```
https://github.com/settings/developers
```
![](https://i.imgur.com/JiPzjcu.png)

创建完成之后可以得到**Client ID**和**Client Secret**这两个东西
![](https://i.imgur.com/vhaeEoB.png)

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
 * GitHub授权登录
 *
 * @author YI
 * @date 2019-4-2 10:13:43
 */
@RestController
@RequestMapping("/oauth")
public class GitHubController {

    /**
     * 生成授权地址
     * @param response
     * @throws IOException
     */
    @RequestMapping("/render/github")
    public void rendderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();

        response.sendRedirect(authRequest.authorize());
    }

    /**
     * 回调
     * @param code 用户授权后回调回本地系统时携带的参数
     * @return
     */
    @RequestMapping("/callback/github")
    public Object login(String code){
        AuthRequest authRequest = getAuthRequest();

        return authRequest.login(code);
    }

    /**
     * 取消授权
     * @param token 授权的token
     * @return
     */
    @RequestMapping("/revoke/github/{token}")
    public Object revokeAuth(@PathVariable("token") String token){
        AuthRequest authRequest = getAuthRequest();

        return authRequest.revoke(token);
    }

    /**
     * 创建授权
     * @return
     */
    private AuthRequest getAuthRequest(){
        return new AuthGithubRequest(AuthConfig.builder()
                .clientId("d4e48f1e8b60bf90efb1")
                .clientSecret("67d31e882631a50a0b6c7c1e11a38f39076b0e9a")
                .redirectUri("http://localhost:8080/oauth/callback/github")
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
            <a class="layui-btn layui-btn-normal" href="/oauth/render/github">GitHub登录授权</a>
        </div>
    </div>
</div>

<script src="/layui/layui.js" charset="utf-8"></script>
</body>
</html>
```

随后访问我们的 http://localhost:8080
![](https://i.imgur.com/UQuLmeE.png)
我们点击授权，弹出授权窗口
![](https://i.imgur.com/xgEVNPi.png)
我们点击授权之后会回调我们之前创建应用填写的回调地址
![](https://i.imgur.com/31ncgmq.png)

我们这个地址是获取用户信息，更多详情请参考代码
### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL