package com.yi.go.fastdfs.model;

import java.util.Date;

/**
 * 文件系统返回数据
 * @author YI
 * @date 2019-1-29 10:48:11
 */
public class GoFast {

    private String url;
    private String md5;
    private String path;
    private Date domain;
    private String scene;
    private String scenes;
    private String retmsg;
    private int retcode;
    private String src;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getMd5() {
        return md5;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setDomain(Date domain) {
        this.domain = domain;
    }

    public Date getDomain() {
        return domain;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getScene() {
        return scene;
    }

    public void setScenes(String scenes) {
        this.scenes = scenes;
    }

    public String getScenes() {
        return scenes;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }
}
