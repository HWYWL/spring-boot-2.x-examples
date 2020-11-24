package com.yi.psql.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * baike
 * @author 
 */
@Data
public class Baike implements Serializable {
    /**
     * ID
     */
    private Integer id;

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
    private Integer goldCoin;

    /**
     * 0：上架、-1：下架
     */
    private Byte status = 0;

    /**
     * 创建时间
     */
    private Date crateDate = new Date();

    /**
     * 更新时间
     */
    private Date updateDate = new Date();

    private static final long serialVersionUID = 1L;

    public Baike() {

    }

    public Baike(String book, String tag, Integer good, Integer bad, String name, String gender, Integer goldCoin) {
        this.book = book;
        this.tag = tag;
        this.good = good;
        this.bad = bad;
        this.name = name;
        this.gender = gender;
        this.goldCoin = goldCoin;
    }

    public Baike(Integer id, String book, String tag, Integer good, Integer bad, String name, String gender, Integer goldCoin, Byte status, Date crateDate, Date updateDate) {
        this.id = id;
        this.book = book;
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
}