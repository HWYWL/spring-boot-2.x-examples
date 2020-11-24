package com.yi.auto.code.bean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zengtengpeng.common.bean.Page;
import com.zengtengpeng.common.utils.DateUtils;
import java.util.Date;
import java.math.BigDecimal;
import com.zengtengpeng.autoCode.utils.MyStringUtils;


/**
 *百科表 bean
 */
public class Baike   extends Page  {


	/**
	 * ID
	 */
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
	private Integer goldCoin;
	/**
	 * 0：上架、-1：下架
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date crateDate;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	public Long getId(){
		return id;
	}


	public void setId(Long id){
		this.id=id;
	}


	public String getBook(){
		return book;
	}


	public void setBook(String book){
		this.book=book;
	}


	public String getTag(){
		return tag;
	}


	public void setTag(String tag){
		this.tag=tag;
	}


	public Integer getGood(){
		return good;
	}


	public void setGood(Integer good){
		this.good=good;
	}


	public Integer getBad(){
		return bad;
	}


	public void setBad(Integer bad){
		this.bad=bad;
	}


	public String getName(){
		return name;
	}


	public void setName(String name){
		this.name=name;
	}


	public String getGender(){
		return gender;
	}


	public void setGender(String gender){
		this.gender=gender;
	}


	public Integer getGoldCoin(){
		return goldCoin;
	}


	public void setGoldCoin(Integer goldCoin){
		this.goldCoin=goldCoin;
	}


	public Integer getStatus(){
		return status;
	}


	public void setStatus(Integer status){
		this.status=status;
	}


	public String getCrateDate_(){
		return DateUtils.formatDateTime(crateDate);
	}


	@JsonIgnore
	public Date getCrateDate(){
		return crateDate;
	}


	public void setCrateDate(Date crateDate){
		this.crateDate=crateDate;
	}


	public String getUpdateDate_(){
		return DateUtils.formatDateTime(updateDate);
	}


	@JsonIgnore
	public Date getUpdateDate(){
		return updateDate;
	}


	public void setUpdateDate(Date updateDate){
		this.updateDate=updateDate;
	}


}
