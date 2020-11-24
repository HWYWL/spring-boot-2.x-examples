package com.yi.presto.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户事件
 *
 * @author YI
 * @date 2019-11-27
 */
@Data
public class UserInfo implements Serializable {
    /**
     * 游戏id
     */
    Integer appId;
    /**
     * 角色id
     */
    String openId;
    /**
     * 事件id
     */
    Integer eventId;
}