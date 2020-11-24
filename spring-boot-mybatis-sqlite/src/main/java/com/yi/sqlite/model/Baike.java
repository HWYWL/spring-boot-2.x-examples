package com.yi.sqlite.model;

import java.io.Serializable;

/**
 * baike
 * @author 
 */
public class Baike implements Serializable {
    private Integer id;

    private String book;

    private String tag;

    private Integer good;

    private Integer bad;

    private String name;

    private String gender;

    private Integer goldcoin;

    private Integer status;

    private String cratedate;

    private String updatedate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCratedate() {
        return cratedate;
    }

    public void setCratedate(String cratedate) {
        this.cratedate = cratedate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public Baike() {
        super();
    }

    public Baike(String book, String tag, Integer good, Integer bad, String name, String gender, Integer goldcoin, Integer status, String cratedate, String updatedate) {
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

    public Baike(Integer id, String book, String tag, Integer good, Integer bad, String name, String gender, Integer goldcoin, Integer status, String cratedate, String updatedate) {
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Baike other = (Baike) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBook() == null ? other.getBook() == null : this.getBook().equals(other.getBook()))
            && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
            && (this.getGood() == null ? other.getGood() == null : this.getGood().equals(other.getGood()))
            && (this.getBad() == null ? other.getBad() == null : this.getBad().equals(other.getBad()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getGoldcoin() == null ? other.getGoldcoin() == null : this.getGoldcoin().equals(other.getGoldcoin()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCratedate() == null ? other.getCratedate() == null : this.getCratedate().equals(other.getCratedate()))
            && (this.getUpdatedate() == null ? other.getUpdatedate() == null : this.getUpdatedate().equals(other.getUpdatedate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBook() == null) ? 0 : getBook().hashCode());
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getGood() == null) ? 0 : getGood().hashCode());
        result = prime * result + ((getBad() == null) ? 0 : getBad().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getGoldcoin() == null) ? 0 : getGoldcoin().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCratedate() == null) ? 0 : getCratedate().hashCode());
        result = prime * result + ((getUpdatedate() == null) ? 0 : getUpdatedate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", book=").append(book);
        sb.append(", tag=").append(tag);
        sb.append(", good=").append(good);
        sb.append(", bad=").append(bad);
        sb.append(", name=").append(name);
        sb.append(", gender=").append(gender);
        sb.append(", goldcoin=").append(goldcoin);
        sb.append(", status=").append(status);
        sb.append(", cratedate=").append(cratedate);
        sb.append(", updatedate=").append(updatedate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}