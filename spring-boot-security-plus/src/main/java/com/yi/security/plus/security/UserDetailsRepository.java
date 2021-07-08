package com.yi.security.plus.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: YI
 * @description:
 * @date: create in 2021/3/24 15:39
 */
public class UserDetailsRepository {
    private Map<String, UserDetails> users = new HashMap<>();

    public void createUser(UserDetails user) {
        users.putIfAbsent(user.getUsername(), user);
    }

    public void updateUser(UserDetails user) {
        users.put(user.getUsername(), user);
    }

    public void deleteUser(String username) {
        users.remove(username);
    }

    public void changePassword(String oldPassword,String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AccessDeniedException("Can't change password as no Authentication object found in context for current user.");
        }

        String name = authentication.getName();
        UserDetails user = users.get(name);

        if (user == null) {
            throw new IllegalStateException("Current user doesn't exist in database.");
        }

        // todo copy InMemoryUserDetailsManager 自行实现具体的更新密码逻辑
    }

    public boolean userExists(String username){
        return users.containsKey(username);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.get(username);
    }
}
