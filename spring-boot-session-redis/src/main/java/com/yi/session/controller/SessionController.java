package com.yi.session.controller;

import com.yi.session.model.User;
import com.yi.session.utils.MessageResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * session 操作
 *
 * @author YI
 * @date 2019-6-5 10:02:06
 */
@RestController
public class SessionController {
    @Value("${server.port}")
    Integer port;

    /**
     * 设置session
     * @param session session
     * @return
     */
    @GetMapping("/set")
    public MessageResult set(HttpSession session) {
        User user = User.builder().id(1).username("YI").role((byte)0).del((byte)0).remark("超级管理员账户").crttime(new Date()).build();
        session.setAttribute("user", user);

        return MessageResult.ok(port);
    }

    /**
     * 获取session
     * @param session session
     * @return
     */
    @GetMapping("/get")
    public MessageResult get(HttpSession session) {
        return MessageResult.ok(session.getAttribute("user") + ":" + port);
    }
}
