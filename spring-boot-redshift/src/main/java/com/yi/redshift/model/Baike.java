package com.yi.redshift.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * public
 *
 * @author YI 2019-12-20
 */
@Data
public class Baike implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 书
     */
    private String book;

    /**
     * 标签
     */
    private String label;

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
    private Integer status = 0;

    /**
     * 创建时间
     */
    private Date crateDate = new Date();

    /**
     * 更新时间
     */
    private Date updateDate = new Date();

    public Baike() {
    }


    public Baike(Integer id, String book, String label, Integer good, Integer bad, String name, String gender, Integer goldCoin) {
        this.id = id;
        this.book = book;
        this.label = label;
        this.good = good;
        this.bad = bad;
        this.name = name;
        this.gender = gender;
        this.goldCoin = goldCoin;
    }

    public Baike(Integer id, String book, String label, Integer good, Integer bad, String name, String gender, Integer goldCoin, Integer status, Date crateDate, Date updateDate) {
        this.id = id;
        this.book = book;
        this.label = label;
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