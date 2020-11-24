package com.yi.storm.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 付款信息
 *
 * @author huangwenyi
 * @date 2019-9-2
 */
@Data
public class PaymentInfo implements Serializable {
    private static final long serialVersionUID = -7958315778386204397L;

    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 订单创建时间
     */
    private Date createOrderTime;
    /**
     * 支付编号
     */
    private String paymentId;
    /**
     * 支付时间
     */
    private Date paymentTime;
    /**
     * 商品编号
     */
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品价格
     */
    private long productPrice;
    /**
     * 促销价格
     */
    private long promotionPrice;
    /**
     * 商铺编号
     */
    private String shopId;
    /**
     * 商铺名称
     */
    private String shopName;
    /**
     * 商品电话
     */
    private String shopMobile;
    /**
     * 订单支付价格
     */
    private long payPrice;
    /**
     * 订单数量
     */
    private int num;

    /**
     * 省 <Province>19</Province>
     */
    private String province;
    /**
     * 市 <City>1657</City>
     */
    private String city;
    /**
     * 县 <County>4076</County>
     */
    private String county;

    /**
     * 102,144,114
     */
    private String catagorys;

    public PaymentInfo() {
    }

    public PaymentInfo(String orderId, Date createOrderTime, String paymentId, Date paymentTime, String productId, String productName, long productPrice, long promotionPrice, String shopId, String shopName, String shopMobile, long payPrice, int num) {
        this.orderId = orderId;
        this.createOrderTime = createOrderTime;
        this.paymentId = paymentId;
        this.paymentTime = paymentTime;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.promotionPrice = promotionPrice;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopMobile = shopMobile;
        this.payPrice = payPrice;
        this.num = num;
    }

    public String random() {
        this.orderId = UUID.randomUUID().toString().replaceAll("-", "");
        this.paymentId = UUID.randomUUID().toString().replaceAll("-", "");
        this.productPrice = new Random().nextInt(1000);
        this.promotionPrice = new Random().nextInt(500);
        this.payPrice = new Random().nextInt(480);
        this.shopId = new Random().nextInt(200000) + "";

        this.catagorys = new Random().nextInt(10000) + "," + new Random().nextInt(10000) + "," + new Random().nextInt(10000);
        this.province = new Random().nextInt(23) + "";
        this.city = new Random().nextInt(265) + "";
        this.county = new Random().nextInt(1489) + "";
        this.createOrderTime = new Date();

        return JSON.toJSONString(this);
    }

}
