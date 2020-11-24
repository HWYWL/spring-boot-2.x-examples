package com.yi.pubsub.model;

/**
 * redis Pub/Sub 的Channel统一管理
 * @author YI
 * @date 2018-4-8 15:39:30
 */
public enum PubSub {
    NEWSTEST("测试消息", "news.message");

    private String name;
    private String channel;

    PubSub(String name, String channel) {
        this.name = name;
        this.channel = channel;
    }

    public static String getName(String channel) {
        for (PubSub ps : PubSub.values()) {
            if (ps.getChannel() == channel) {
                return ps.name;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
