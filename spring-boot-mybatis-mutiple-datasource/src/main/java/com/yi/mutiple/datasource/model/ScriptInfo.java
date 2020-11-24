package com.yi.mutiple.datasource.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 脚本
 * @author YI
 * @date 2018-12-6 09:24:56
 */
public class ScriptInfo implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 脚本路径
     */
    private String path;

    /**
     * 命令
     */
    private String command;

    /**
     * 0：linux命令、1：linux shll脚本、2：windows命令、3：windows bat脚本
     */
    private Byte type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0：启用、1：失效
     */
    private Byte enable;

    /**
     * 远程脚本IP
     */
    private String host;

    /**
     * 服务器账号
     */
    private String userName;

    /**
     * 服务器密码
     */
    private String passWord;

    /**
     * 0：正常使用、-1：已被删除
     */
    private Byte del;

    /**
     * 创建时间
     */
    private Date crttime;


    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Byte getDel() {
        return del;
    }

    public void setDel(Byte del) {
        this.del = del;
    }

    public Date getCrttime() {
        return crttime;
    }

    public void setCrttime(Date crttime) {
        this.crttime = crttime;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "ScriptInfo{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", command='" + command + '\'' +
                ", type=" + type +
                ", remark='" + remark + '\'' +
                ", enable=" + enable +
                ", host='" + host + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", del=" + del +
                ", crttime=" + crttime +
                '}';
    }
}