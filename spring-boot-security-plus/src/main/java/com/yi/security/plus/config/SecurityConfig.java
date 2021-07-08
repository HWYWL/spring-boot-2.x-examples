package com.yi.security.plus.config;

import com.yi.security.plus.security.UserDetailsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * @author: YI
 * @description:
 * @date: create in 2021/3/24 16:46
 */
@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsRepository userDetailsRepository() {
        UserDetailsRepository userDetailsRepository = new UserDetailsRepository();

        UserDetails userDetails = User.withUsername("yi").password("{noop}123456").authorities(AuthorityUtils.NO_AUTHORITIES).build();
        userDetailsRepository.createUser(userDetails);

        return userDetailsRepository;
    }

    @Bean
    public UserDetailsManager userDetailsManager(UserDetailsRepository userDetailsRepository) {
        return new UserDetailsManager() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userDetailsRepository.loadUserByUsername(username);
            }

            @Override
            public void createUser(UserDetails userDetails) {
                userDetailsRepository.createUser(userDetails);
            }

            @Override
            public void updateUser(UserDetails userDetails) {
                userDetailsRepository.updateUser(userDetails);
            }

            @Override
            public void deleteUser(String s) {
                userDetailsRepository.deleteUser(s);
            }

            @Override
            public void changePassword(String oldPassword, String newPassword) {
                userDetailsRepository.changePassword(oldPassword, newPassword);
            }

            @Override
            public boolean userExists(String username) {
                return userDetailsRepository.userExists(username);
            }
        };
    }
}
