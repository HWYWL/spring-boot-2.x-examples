package com.yi.swagger.controller;

import cn.hutool.core.util.StrUtil;
import com.yi.swagger.model.MessageResult;
import com.yi.swagger.model.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录注册
 * @author YI
 * @date 2018-6-11 21:53:14
 */
@RestController
@Api(value = "用户接口登录注册", tags = "用户接口登录注册")
@RequestMapping("/user")
public class RegistLoginController {

    /**
     * 用户注册
     * @param users
     * @return
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ApiOperation(value="用户注册", notes="用户注册接口")
    public MessageResult regist(@RequestBody Users users){
        if (users == null || StrUtil.isBlank(users.getUsername()) || StrUtil.isBlank(users.getPassword())){
            return MessageResult.errorMsg("用户名或者密码不能为空");
        }

        return MessageResult.ok(users);
    }

    /**
     * 用户登录
     * @param users
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value="用户登录", notes="用户登录接口")
    public MessageResult login(@RequestBody Users users){
        if (users == null || StrUtil.isBlank(users.getUsername()) || StrUtil.isBlank(users.getPassword())){
            return MessageResult.errorMsg("用户名或者密码不能为空");
        }

        return MessageResult.ok(users);
    }

    /**
     * 用户注销
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value="用户注销", notes="用户注销接口")
    @ApiImplicitParam(name="userId", value="用户id", required=true, dataType="String", paramType="query")
    public MessageResult logout(String userId){

        return MessageResult.ok("ID为：" + userId + " 的用户成功注销");
    }
}
