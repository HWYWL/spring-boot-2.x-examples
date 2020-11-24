package com.yi.rabbitmq.model;

import java.io.Serializable;

/**
 * 通过MQ传递对象
 * @author YI
 * @date 2018-8-16 16:26:23
 */
public class User implements Serializable {
    private String name;
    private int age;
    private String sex;

    public User() {
        super();
    }

    public User(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
