package com.yi.rabbitmq;

import com.yi.rabbitmq.model.User;
import com.yi.rabbitmq.usersendreceive.UserSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 对象发送与接受
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserSender userSender;

    @Test
    public void hello() throws Exception {
        User user = new User("校花", 18, "女");

        userSender.send(user);
    }
}
