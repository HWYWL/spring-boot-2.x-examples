package com.yi.sharding.sphere.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yi.sharding.sphere.dao.UserMapper;
import com.yi.sharding.sphere.model.User;
import com.yi.sharding.sphere.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 数据接口实现
 *
 * @author YI
 * @date 2019-6-3 10:18:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
