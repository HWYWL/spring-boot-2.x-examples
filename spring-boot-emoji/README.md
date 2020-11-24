# spring-boot-emoji

### 说明
通过java获取emoji的表情，官方文档：https://github.com/vdurmont/emoji-java

我们通过几个方法将其展现在我们的接口上：
```java
/**
 * Emoji表情
 * @author YI
 * @date 2019-2-27 21:06:44
 */
@RestController
@RequestMapping("/emoji")
public class EmojiController {

    /**
     * 通过tag方式获取对应的所有Emoji表情
     * @param tag tag标签，例如“happy”
     * @return Emoji表情集合，如果找不到返回null
     */
    @RequestMapping("/getForTag")
    public MessageResult getForTag(String tag){
        Set<Emoji> emojis = EmojiManager.getForTag(tag);

        return MessageResult.ok(emojis);
    }

    /**
     * 通过别名获取Emoji
     * @param alias 别名，例如“smile”
     * @return Emoji对象，如果找不到返回null
     */
    @RequestMapping("/getForAlias")
    public MessageResult getForAlias (String alias){
        Emoji emoji = EmojiManager.getForAlias(alias);

        return MessageResult.ok(emoji);
    }

    /**
     * 获取所有的表情符号
     * @return
     */
    @RequestMapping("/getAll")
    public MessageResult getAll (){
        Collection<Emoji> emojis = EmojiManager.getAll();

        return MessageResult.ok(emojis);
    }

    /**
     * 判断字符串是否为Emoji表情
     * @param str 字符串
     * @return
     */
    @RequestMapping("/isEmoji")
    public MessageResult isEmoji (String str){
        boolean bool = EmojiManager.isEmoji(str);

        return MessageResult.ok(bool);
    }
}
```

我们请求其中一个接口看看效果：
![](https://i.imgur.com/lnQs4LB.png)


### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL