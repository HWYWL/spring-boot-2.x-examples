package com.yi.solr.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 百科对象用于操作MongoDB
 * @author YI
 * @date 2018-3-20 09:37:07
 */
@SolrDocument(solrCoreName = "core")
public class Baike implements Serializable {

    @Indexed
    @Field
    private Integer id;
    /**
     * 文档说明
     */
    @Field
    private String desc;
    /**
     * 标签
     */
    @Field
    private List<String> tag = new ArrayList<>();
    /**
     * 点赞
     */
    @Field
    private int good;

    /**
     * 鄙视
     */
    @Field
    private int bad;
    /**
     * 作者名称
     */
    @Field
    private String name;
    /**
     * 作者性别
     */
    @Field
    private String gender;
    /**
     * 获得的金币打赏
     */
    @Field
    private Integer goldCoin;
    /**
     * 书本状态，0：上架、-1：下架
     */
    @Field
    private int status = 0;
    
    @Field
    private Date crateDate = new Date();
    @Field
    private Date updateDate = new Date();

    public Baike(Integer id, String desc, List<String> tag, int good, int bad, String name, String gender, Integer goldCoin, int status, Date crateDate, Date updateDate) {
        this.id = id;
        this.desc = desc;
        this.tag = tag;
        this.good = good;
        this.bad = bad;
        this.name = name;
        this.gender = gender;
        this.goldCoin = goldCoin;
        this.status = status;
        this.crateDate = crateDate;
        this.updateDate = updateDate;
    }

    public Baike() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getBad() {
        return bad;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getGoldCoin() {
        return goldCoin;
    }

    public void setGoldCoin(Integer goldCoin) {
        this.goldCoin = goldCoin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(Date crateDate) {
        this.crateDate = crateDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Baike{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", tag=" + tag +
                ", good=" + good +
                ", bad=" + bad +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", goldCoin=" + goldCoin +
                ", status=" + status +
                ", crateDate=" + crateDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
