package com.yi.clickhouse.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YI
 * @description
 * @date create in 2021/7/5 14:41
 */
@TableName("event_log_realtime")
@Data
public class EventLogRealtime  implements Serializable {
    private static final long serialVersionUID = 1L;

    private int app_id;
    private int platform_id;
    private int channel_id;
    private int event_id;
    private String open_id;
    private int add_time;
    private int pay_time;
    private int event_time_server;
    private int event_time_client;
    private int os;
    private int real_currency_amount;
    private String order_id;
    private String cp_order_id;
    private String platform_order_id;
    private String goods_name;
    private String real_currency_type;
    private String payment_type;
    private String os_version;
    private String device_id;
    private String register_device_id;
    private String register_channel_id;
    private String role_id;
    private int register_time;
    private Date register_date;
    private int role_create_time;
    private Date role_create_date;
    private int app_register_time;
    private Date app_register_date;
    private int device_app_register_time;
    private Date device_app_register_date;
    private int register_device_app_register_time;
    private Date register_device_app_register_date;
    private Date dt_rs;
    private String dt_time_rs;
}
