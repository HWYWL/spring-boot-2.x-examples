package com.yi.webflux.service.impl;

import com.yi.webflux.model.User;
import com.yi.webflux.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现用户接口逻辑
 * @author YI
 * @date 2018-8-18 22:19:01
 */
@Service
public class UserServiceImpl implements UserService {
    private Map<Long, User> users = new HashMap<>(16);

    @PostConstruct
    public void init() throws Exception {
        users.put(Long.valueOf(1), new User(1, "Jack", "Smith", 20));
        users.put(Long.valueOf(2), new User(2, "Peter", "Johnson", 25));
    }

    @Override
    public Mono<User> getUserById(Long id) {
        return Mono.just(users.get(id));
    }

    @Override
    public Flux<User> getAllUsers() {
        return Flux.fromIterable(this.users.values());
    }

    @Override
    public Mono<Void> saveUser(Mono<User> monoUser) {
        Mono<User> userMono = monoUser.doOnNext(user -> {
            // do post
            users.put(user.getId(), user);

            // log on console
            System.out.println("########### POST:" + user);
        });

        return userMono.then();
    }

    @Override
    public Mono<User> putUser(Long id, Mono<User> monoUser) {
        Mono<User> userMono = monoUser.doOnNext(user -> {
            // reset user.Id
            user.setId(id);

            // do put
            users.put(id, user);

            // log on console
            System.out.println("########### PUT:" + user);
        });

        return userMono;
    }

    @Override
    public Mono<String> deleteUser(Long id) {
        // delete processing
        users.remove(id);
        return Mono.just("Delete Succesfully!");
    }
}
