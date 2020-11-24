# spring-boot-https

### 说明
我们使用spring boot配置https证书，打到反向代理访问https的效果。由于现在手里没有可用的证书，所以我们使用jdk帮我们生成一个：
```
keytool -genkey -alias tomcat -keypass 123456 -keyalg RSA -keysize 1024 -validity 365 -keystore D:/tomcat.keystore -storepass 123456
```
上面代码我们在D盘生成一个**密码为：123456**，**名称为tomcat.keystore**的证书
![](https://i.imgur.com/Zb4NU4L.png)

接下来我们新建一个spring boot 工程把证书拷贝到resources目录下，配置application.properties文件：
```
# https端口号
server.port=443
http.port=8888
# 证书的路径
server.ssl.key-store=classpath:tomcat.keystore
# 证书密码，请修改为您自己证书的密码
server.ssl.key-password=123456
# 秘钥库类型
server.ssl.key-store-type=JKS
# 证书别名
server.ssl.key-alias=tomcat
```
![](https://i.imgur.com/H9wB6cm.png)

然后我们自定义一个Tomcat配置类如下：
```java
package com.yi.https.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
/**
 * http转https配置
 * @author YI
 * @date 2019-3-11 22:31:28
 */
@Configuration
public class HttpsConfig {
    /**
     * 监听的http请求的端口,需要在application配置中添加http.port=端口号  如80
     */
    @Value("${http.port}")
    Integer httpPort;

    /**
     * 正常启用的https端口 如443
     */
    @Value("${server.port}")
    Integer httpsPort;

    /**
     * 配置内置的servlet容器工厂为tomcat.
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

    /**
     * 配置一个http连接信息.
     * @return
     */
    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(httpPort);
        connector.setSecure(false);
        connector.setRedirectPort(httpsPort);
        return connector;
    }
}
```

好了核心代码和配置大功告成，接下来我们访问一下吧：http://localhost:8888 随后他会自动跳到我们配置的443端口

![](https://i.imgur.com/XhriQWg.png)
因为我们不是安全的证书，所以点击高级那里，然后点击继续前往

![](https://i.imgur.com/6IQ0rXp.png)

比较简单，具体看代码吧
https://github.com/HWYWL/spring-boot-2.x-examples/tree/master/spring-boot-https

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL