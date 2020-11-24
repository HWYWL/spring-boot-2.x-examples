# spring-boot-email

### 说明
此邮件库来源于：[王爵大佬](https://github.com/biezhi/oh-my-email "王爵大佬")

### 特性
- 简洁的邮件发送API
- 支持自定义发件人昵称
- 支持扩展邮件Message
- 支持抄送／HTML／附件
- 支持异步发送
- 支持邮件模板

### 例子
```java
package com.yi.email;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import io.github.biezhi.ome.OhMyEmail;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootEmailApplicationTests {

    @Test
    public void testSendText() throws MessagingException {
        OhMyEmail.subject("这是一封测试TEXT邮件")
                .from("XX的QQ邮箱")
                .to("xxxxxxx@qq.com")
                .text("信件内容")
                .send();
    }

    @Test
    public void testSendHtml() throws MessagingException {
        OhMyEmail.subject("这是一封测试HTML邮件")
                .from("XX的QQ邮箱")
                .to("xxxxxxx@qq.com")
                .html("<h1 font=red>信件内容</h1>")
                .send();
    }

    @Test
    public void testSendAttach() throws MessagingException {
        OhMyEmail.subject("这是一封测试附件邮件")
                .from("XX的QQ邮箱")
                .to("xxxxxxx@qq.com")
                .html("<h1 font=red>信件内容</h1>")
                .attach(new File("/xxx/xxx/hello.jpeg"), "测试图片.jpeg")
                .send();
    }

    @Test
    public void testPebble() throws IOException, PebbleException, MessagingException {
        PebbleEngine engine = new PebbleEngine.Builder().build();
        PebbleTemplate compiledTemplate = engine.getTemplate("register.html");

        Map<String, Object> context = new HashMap<>(16);
        context.put("username", "YI");
        context.put("email", "xxx@163.com");

        Writer writer = new StringWriter();
        compiledTemplate.evaluate(writer, context);

        String output = writer.toString();
        System.out.println(output);

        OhMyEmail.subject("这是一封测试Pebble模板邮件")
                .from("XX的QQ邮箱")
                .to("xxxxxxx@qq.com")
                .html(output)
                .send();
    }

    @Test
    public void testJetx() throws MessagingException {
        JetEngine engine = JetEngine.create();
        JetTemplate template = engine.getTemplate("/register.jetx");

        Map<String, Object> context = new HashMap<String, Object>();
        context.put("username", "YI");
        context.put("email", "xxx@163.com");
        context.put("url", "<a href='hwy.ac.cn'>https://hwy.ac.cn</a>");

        StringWriter writer = new StringWriter();
        template.render(context, writer);
        String output = writer.toString();
        System.out.println(output);

        OhMyEmail.subject("这是一封测试Jetx模板邮件")
                .from("XX的QQ邮箱")
                .to("xxxxxxx@qq.com")
                .html(output)
                .send();
    }

}


```


### 邮件模版
```
<div>
    <p>亲爱的<b>${username}</b>, 欢迎加入JavaChina!</p>
    <p>当您收到这封信的时候，您已经可以正常登录了。</p>
    <p>请点击链接登录首页: ${url}</p>
    <p>如果您的email程序不支持链接点击，请将上面的地址拷贝至您的浏览器(如IE)的地址栏进入。</p>
    <p>如果您还想申请管理员权限，可以联系管理员 ${email}</p>
    <p>我们对您产生的不便，深表歉意。</p>
    <p>希望您在JavaChina度过快乐的时光!</p>
    <p></p>
    <p>-----------------------</p>
    <p></p>
    <p>(这是一封自动产生的email，请勿回复。)</p>
</div>
```

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL