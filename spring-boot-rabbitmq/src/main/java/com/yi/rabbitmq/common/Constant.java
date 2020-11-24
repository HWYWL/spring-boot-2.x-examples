package com.yi.rabbitmq.common;

/**
 * 常量枚举
 * @author YI
 * @date 2018-8-16 15:57:57
 */
public enum Constant {
    HELLO("hello", 1), NEO("neo", 2), USER("user", 3),
    TOPICEXCHANGE("topicExchange", 4),FANOUTEXCHANGE("fanoutExchange", 5);

    private String name;
    private int index;

    Constant(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (Constant c : Constant.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
