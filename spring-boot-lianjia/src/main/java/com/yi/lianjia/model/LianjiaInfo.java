package com.yi.lianjia.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  链家房屋数据表
 * @author YI 2019-12-26
 */
@Data
public class LianjiaInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 小区名称
     */
    private String villageName;

    /**
     * 所在区域
     */
    private String area;

    /**
     * 单价
     */
    private Integer unitPrice;

    /**
     * 总价
     */
    private Integer totalPrices;

    /**
     * 房屋户型
     */
    private String apartments;

    /**
     * 所在楼层
     */
    private String floor;

    /**
     * 建筑面积
     */
    private String coveredArea;

    /**
     * 户型结构
     */
    private String familyStructure;

    /**
     * 套内面积
     */
    private String planimeter;

    /**
     * 建筑类型
     */
    private String architectureType;

    /**
     * 房屋朝向
     */
    private String orientationOfRoom;

    /**
     * 建筑结构
     */
    private String buildingStructure;

    /**
     * 装修状况
     */
    private String decorationSituation;

    /**
     * 梯户比例
     */
    private String proportionElevatorHouseholds;

    /**
     * 配备电梯
     */
    private String equippedWithElevator;

    /**
     * 产权年限
     */
    private String periodInt;

    /**
     * 挂牌时间
     */
    private Date listingTime;

    /**
     * 上次交易时间
     */
    private Date lastTradingTime;

    public LianjiaInfo() {
    }

}
