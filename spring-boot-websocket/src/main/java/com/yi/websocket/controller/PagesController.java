package com.yi.websocket.controller;

import com.yi.websocket.service.SocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 页面数据
 * @author YI
 * @date 2018-9-27 14:59:18
 */
@Controller
public class PagesController {
    /**
     * 用户页面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {

        return "index";
    }

    /**
     * 服务器页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin")
    public String admin(Model model) {
        int num = SocketServer.getOnlineNum();
        List<String> list = SocketServer.getOnlineUsers();

        model.addAttribute("num",num);
        model.addAttribute("users",list);

        return "admin";
    }
}
