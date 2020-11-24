# spring-boot-tencentcloud
我们使用腾讯云授权登录我们的网站，咿呀咿呀哟

首先我们到这个地址去新建一个我们的应用
```
https://dev.tencent.com/user/account/setting/applications
```
![](https://i.imgur.com/NLoqW92.png)
创建完成之后可以得到**appId**和**appSecret**这两个东西

![](https://i.imgur.com/paooUez.png)

然后填写我们的回调地址，因为腾讯云不能用localhost之类的域名，所以我这里填写了我博客的域名，然后在host文件中重新映射
```
127.0.0.1 hwy.ac.cn
```
然后把application.properties文件的服务器端口配置为80
```
server.port=80
```

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
            <a class="layui-btn layui-btn-normal" href="/oauth/render/tencentcloud">腾讯云登录授权</a>
        </div>
    </div>
</div>

<script src="/layui/layui.js" charset="utf-8"></script>
</body>
</html>
```

随后访问我们的 http://localhost

![](https://i.imgur.com/cFDULgy.png)
我们点击授权，弹出授权窗口

![](https://i.imgur.com/S1tSrqB.png)
我们用授权之后会回调我们之前创建应用填写的回调地址

![](https://i.imgur.com/3Ag6cRo.png)

我们这个地址是获取用户信息，更多详情请参考代码
### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL