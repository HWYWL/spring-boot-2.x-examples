package com.yi.excel.utils;

import java.io.Serializable;

/**
 * 自定义响应数据结构
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 * @author YI
 * @date 2018-8-6 22:51:02
 * @version 1.0.0
 */
public class MessageResult implements Serializable {

    /**
     * 响应业务状态
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private Object data;

    /**
     * 不使用
     */
    private String ok;

    public static MessageResult build(Integer code, String msg, Object data) {
        return new MessageResult(code, msg, data);
    }

    public static MessageResult ok(Object data) {
        return new MessageResult(data);
    }

    public static MessageResult ok() {
        return new MessageResult(null);
    }
    
    public static MessageResult errorMsg(String msg) {
        return new MessageResult(500, msg, null);
    }
    
    public static MessageResult errorMap(Object data) {
        return new MessageResult(501, "error", data);
    }
    
    public static MessageResult errorTokenMsg(String msg) {
        return new MessageResult(502, msg, null);
    }
    
    public static MessageResult errorException(String msg) {
        return new MessageResult(555, msg, null);
    }

    public MessageResult() {

    }

    public MessageResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public MessageResult(Object data) {
        this.code = 0;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.code == 0;
    }

    public Integer getcode() {
        return code;
    }

    public void setcode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

    @Override
    public String toString() {
        return "MessageResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", ok='" + ok + '\'' +
                '}';
    }
}
