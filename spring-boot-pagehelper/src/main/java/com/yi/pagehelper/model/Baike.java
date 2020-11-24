package com.yi.pagehelper.model;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 百科
 * @author YI
 * @date 2018-8-22 18:28:19
 */
public class Baike implements Serializable {
    /**
     * ID
     */
    @Id
    private Long id;

    /**
     * 书
     */
    private String book;

    /**
     * 标签
     */
    private String tag;

    /**
     * 点赞
     */
    private Integer good;

    /**
     * 鄙视
     */
    private Integer bad;

    /**
     * 作者名称
     */
    private String name;

    /**
     * 作者性别
     */
    private String gender;

    /**
     * 获得的金币打赏
     */
    private Integer goldcoin;

    /**
     * 0：上架、-1：下架
     */
    private Byte status = 0;

    /**
     * 创建时间
     */
    private Date cratedate = new Date();

    /**
     * 更新时间
     */
    private Date updatedate = new Date();

    public Baike() {
        super();
    }

    public Baike(Long id, String book, String tag, Integer good, Integer bad, String name, String gender, Integer goldcoin, Byte status, Date cratedate, Date updatedate) {
        this.id = id;
        this.book = book;
        this.tag = tag;
        this.good = good;
        this.bad = bad;
        this.name = name;
        this.gender = gender;
        this.goldcoin = goldcoin;
        this.status = status;
        this.cratedate = cratedate;
        this.updatedate = updatedate;
    }

    public Baike(String book, String tag, Integer good, Integer bad, String name, String gender, Integer goldcoin) {
        this.book = book;
        this.tag = tag;
        this.good = good;
        this.bad = bad;
        this.name = name;
        this.gender = gender;
        this.goldcoin = goldcoin;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public Integer getBad() {
        return bad;
    }

    public void setBad(Integer bad) {
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

    public Integer getGoldcoin() {
        return goldcoin;
    }

    public void setGoldcoin(Integer goldcoin) {
        this.goldcoin = goldcoin;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCratedate() {
        return cratedate;
    }

    public void setCratedate(Date cratedate) {
        this.cratedate = cratedate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "Baike{" +
                "id=" + id +
                ", book='" + book + '\'' +
                ", tag='" + tag + '\'' +
                ", good=" + good +
                ", bad=" + bad +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", goldcoin=" + goldcoin +
                ", status=" + status +
                ", cratedate=" + cratedate +
                ", updatedate=" + updatedate +
                '}';
    }
}