package com.yi.sharding.sphere;

import com.yi.sharding.sphere.model.User;
import com.yi.sharding.sphere.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootShardingSphereApplicationTests {
    @Autowired
    UserService userService;

    @Test
    public void saveBatch() {
        List<User> list = new ArrayList<>();
        list.add(User.builder().id(1).name("张三").age(13).build());
        list.add(User.builder().id(2).name("李四").age(14).build());
        list.add(User.builder().id(3).name("王五").age(15).build());
        list.add(User.builder().id(4).name("赵柳").age(16).build());

        userService.saveBatch(list);
    }

    @Test
    public void selectAll() {
        List<User> list = userService.list();

        System.out.println(list);
    }

}
