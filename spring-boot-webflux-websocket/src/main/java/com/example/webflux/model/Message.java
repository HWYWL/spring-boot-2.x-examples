package com.example.webflux.model;

public class Message {
    //聊天信息
    private String msg;
    //在线人数
    private int online;

    public Message() {
        super();
    }

    public Message(String msg, int online) {
        this.msg = msg;
        this.online = online;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msg='" + msg + '\'' +
                ", online=" + online +
                '}';
    }
}
