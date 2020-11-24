package com.yi.hbase;

import com.yi.hbase.model.User;
import com.yi.hbase.service.HBaseService;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class SpringBootHbaseApplicationTests {
    @Autowired
    HBaseService hBaseService;

    /**
     * 创建表
     *
     * @throws IOException
     */
    @Test
    void createTableTest() throws IOException {
        hBaseService.createTable("user_table", new String[]{"information", "contact"});
    }

    /**
     * 删除表
     *
     * @throws IOException
     */
    @Test
    void delteTableTest() throws IOException {
        hBaseService.deleteTable("user_table");
    }

    /**
     * 插入数据
     *
     * @throws IOException
     */
    @Test
    void insertTest() throws IOException {
        User user = new User("001", "xiaoming", "123456", "man", "20", "13355550021", "1232821@csdn.com");
        hBaseService.insertData("user_table", user);
        User user2 = new User("002", "xiaohong", "654321", "female", "18", "18757912212", "214214@csdn.com");
        hBaseService.insertData("user_table", user2);
    }

    /**
     * 获取表中所有数据
     *
     * @throws IOException
     */
    @Test
    void getAllDataTest() throws IOException {
        List<User> list = hBaseService.getAllData("user_table");
        for (User user3 : list) {
            System.out.println(user3.toString());
        }
    }

    /**
     * 获取表中原始数据
     *
     * @throws IOException
     */
    @Test
    void getNoDealDataTest() {
        ResultScanner resutScanner = hBaseService.getNoDealData("user_table");
        for (Result result : resutScanner) {
            System.out.println("scan:  " + result);
        }
    }

    /**
     * 根据rowKey查询
     *
     * @throws IOException
     */
    @Test
    void getDataByRowKeyTest() throws IOException {
        User user = hBaseService.getDataByRowKey("user_table", "user-001");
        System.out.println(user);
    }

    /**
     * 获取指定单条数据
     */
    @Test
    void getCellDataTest() {
        String userPhone = hBaseService.getCellData("user_table", "user-001", "contact", "phone");
        System.out.println(userPhone);
    }

    /**
     * 删除指定cell数据
     *
     * @throws IOException
     */
    @Test
    void deleteByRowKeyTest() throws IOException {
        hBaseService.deleteByRowKey("user_table", "001");
    }

}
