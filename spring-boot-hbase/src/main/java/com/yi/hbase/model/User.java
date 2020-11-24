package com.yi.hbase.model;

import lombok.Data;

/**
 * @author huangwenyi
 */
@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String gender;
    private String age;
    private String phone;
    private String email;

    public User(String id, String username, String password, String gender, String age, String phone, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public User(){

    }
}
