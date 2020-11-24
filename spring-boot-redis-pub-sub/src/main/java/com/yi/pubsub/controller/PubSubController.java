package com.yi.pubsub.controller;

import com.yi.pubsub.model.PubSub;
import com.yi.pubsub.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订阅发布接口
 * @author YI
 * @date 2018-9-6 16:02:52
 */
@RestController
public class PubSubController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 发送消息 支持基于Redis的消息订阅/发布消息
     * @return
     */
    @RequestMapping("/pub")
    @ResponseBody
    public MessageResult pub(String message) {
        //推送遵从news.*的格式 接受处理
        stringRedisTemplate.convertAndSend(PubSub.NEWSTEST.getChannel(), message);

        return MessageResult.ok();
    }
}
