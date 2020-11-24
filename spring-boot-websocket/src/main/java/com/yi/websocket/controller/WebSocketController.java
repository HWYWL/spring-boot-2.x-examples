package com.yi.websocket.controller;

import cn.hutool.core.util.StrUtil;
import com.yi.websocket.service.SocketServer;
import com.yi.websocket.utils.MessageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * WebSocket 消息推送(个人和广播)
 * @author YI
 * @date 2018-9-27 14:57:20
 */
@RestController
public class WebSocketController {

    /**
     * 个人信息推送
     * @param msg   发送的信息内容
     * @param username  用户长连接传的用户人数
     * @return
     */
    @RequestMapping("sendMsg")
    public MessageResult sendMsg(String msg, String username){
        String [] persons = username.split(StrUtil.COMMA);
        SocketServer.SendMany(msg, persons);

        return MessageResult.ok();
    }

    /**
     * 推送给所有在线用户
     * @param msg   发送的信息内容
     * @return
     */
    @RequestMapping("sendAll")
    public MessageResult sendAll(String msg){
        SocketServer.sendAll(msg);

        return MessageResult.ok();
    }

    /**
     * 获取当前在线用户
     * @return
     */
    @RequestMapping("webStatus")
    public MessageResult webStatus(){
        HashMap<String, Object> map = new HashMap<>(16);
        MessageResult result = MessageResult.ok();

        //当前用户个数
        int count = SocketServer.getOnlineNum();
        //当前用户的username
        List users = SocketServer.getOnlineUsers();

        map.put("count", count);
        map.put("users", users);
        result.setData(map);

        return result;
    }
}
