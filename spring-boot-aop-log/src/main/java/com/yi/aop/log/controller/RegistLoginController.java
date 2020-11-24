package com.yi.aop.log.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.yi.aop.log.model.User;
import com.yi.aop.log.utils.MessageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录注册
 * @author YI
 * @date 2018-7-19 15:58:19
 */
@Controller()
public class RegistLoginController {
    private static final Logger logger = LoggerFactory.getLogger(RegistLoginController.class);

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123456";

    @RequestMapping("/")
    public String index(){
        return "login";
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/regist", method = RequestMethod.POST)
    public MessageResult regist(@RequestBody User user){
        if (user == null || StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())){
            logger.info("error : 用户名或者密码不能为空");
            return MessageResult.errorMsg("用户名或者密码不能为空");
        }

        return MessageResult.ok("注册成功！！！");
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public MessageResult login(@RequestBody User user) {
        if (user == null || StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())){
            logger.info("error : 用户名或者密码不能为空");
            return MessageResult.errorMsg("用户名或者密码不能为空");
        }

        if (!user.getUsername().equals(USERNAME)){
            logger.info("error : 用户或密码不正确");
            return MessageResult.errorMsg("用户或密码不正确");
        }

        if (!user.getPassword().equals(PASSWORD)){
            logger.info("error : 用户或密码不正确");
            return MessageResult.errorMsg("用户或密码不正确");
        }

        return MessageResult.ok("登录成功！！！");
    }
}
