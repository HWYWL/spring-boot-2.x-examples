package com.yi.token.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yi.token.common.MessageResult;
import com.yi.token.common.RedisOperator;
import com.yi.token.config.Config;
import com.yi.token.model.Users;
import com.yi.token.model.UsersVo;
import com.yi.token.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 登录注册
 * @author YI
 * @date 2018-7-19 15:58:19
 */
@RestController()
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public RedisOperator redis;

    /**
     * 用户登录
     * @param users
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public MessageResult login(@RequestBody Users users) {
        MessageResult result = MessageResult.ok();
        if (users == null || StringUtils.isBlank(users.getUsername()) || StringUtils.isBlank(users.getPassword())){
            result.setCode(-1);
            result.setMsg("用户名或者密码不能为空");
            logger.info("error : 用户名或者密码不能为空");

            return result;
        }

        users.setId(1);
        users.setDel((byte)0);
        users.setCrttime(new Date());
        users.setRole((byte)1);
        users.setRemark("我是管理员大佬");

        UsersVo usersVo = setUserRedisSessionToken(users);
        usersVo.setPassword("");
        result.setData(usersVo);

        redis.set(usersVo.getId().toString(), JSONUtil.toJsonStr(usersVo));

        CookieUtil.addCookie("userToken", usersVo.getUserToken());
        CookieUtil.addCookie("userId", String.valueOf(usersVo.getId()));
        logger.info("userToken : " + usersVo.getUserToken());

        return result;
    }

    /**
     * 用户注销
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public MessageResult logout(String userId){
        redis.del(Config.USER_REDIS_SESSION + ":" + userId);

        return MessageResult.ok();
    }

    /**
     * 用户信息
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public MessageResult userInfo(String userId){
        String s = redis.get(userId);
        UsersVo usersVo = JSONUtil.toBean(s, UsersVo.class);

        return MessageResult.ok(usersVo);
    }

    /**
     * 把Token放入redis
     * @param users
     * @return
     */
    private UsersVo setUserRedisSessionToken(Users users){
        String token = StrUtil.uuid();

        redis.set(Config.USER_REDIS_SESSION + ":" + users.getId(), token, Config.REDIS_TIMEOUT);

        UsersVo usersVo = new UsersVo();

        BeanUtil.copyProperties(users, usersVo);
        usersVo.setUserToken(token);

        return usersVo;
    }
}
