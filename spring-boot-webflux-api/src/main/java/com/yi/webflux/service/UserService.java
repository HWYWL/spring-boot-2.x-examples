package com.yi.webflux.service;

import com.yi.webflux.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 用户数据接口
 * @author YI
 * @date 2018-8-18 22:16:26
 */
public interface UserService {
    /**
     * 根据用户id查找用户
     * @param id 用户id
     * @return
     */
    Mono<User> getUserById(Long id);

    /**
     * 获取所有用户信息
     * @return
     */
    Flux<User> getAllUsers();

    /**
     * 保存用户数据
     * @param user  用户数据
     * @return
     */
    Mono<Void> saveUser(Mono<User> user);

    /**
     * 验证put请求
     * @param id    用户id
     * @param user  用户信息
     * @return
     */
    Mono<User> putUser(Long id, Mono<User> user);

    /**
     * 根据用户id删除用户
     * @param id    用户id
     * @return
     */
    Mono<String> deleteUser(Long id);
}
