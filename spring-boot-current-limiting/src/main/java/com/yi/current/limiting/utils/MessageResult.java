package com.yi.current.limiting.utils;

/**
 * 统一返回数据格式
 * @author YI
 * @date 2018-8-22 11:25:56
 */
public class MessageResult<T> {
    private int code = 0;
    private String msg = "数据读取成功!";
    private T data;

    public MessageResult() {
        super();
    }

    public MessageResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static MessageResult ok() {
        return new MessageResult();
    }

    public static MessageResult ok(Object data) {
        MessageResult result = new MessageResult();
        result.setData(data);

        return result;
    }

    public static MessageResult errorMsg(String msg) {
        return new MessageResult(-1, msg, null);
    }

    public static MessageResult errorMap(Object data) {
        return new MessageResult(-1, "ERROR:出错啦，么么哒！！！", data);
    }

    public static MessageResult errorTokenMsg(String msg) {
        return new MessageResult(-1, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MessageResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
