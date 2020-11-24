package com.yi.geo.service;

import java.util.List;
import java.util.Map;

/**
 * Redis业务类接口
 * @author YI
 * @date 2018-9-7 10:50:28
 */
public interface RedisService {

    /**
     * 添加地理信息到redis中
     * @return
     */
    void redisAddGeo();

    /**
     * 获取某个地理位置的geohash值
     */
    Map redisHashGeo();

    /**
     * 获取某个地理位置的坐标
     * @return
     */
    Map redisPositionGeo();

    /**
     * 获取两个地理位置的距离
     * @return
     */
    String redisDistanceGeo();

    /**
     * 根据给定地理位置坐标获取指定范围内的地理位置集合
     * @return
     */
    List redisRadiusGeo();

    /**
     * 删除位置信息,此命令不是geo提供的，是使用zrem命令删除的
     * @return
     */
    Long redisRemoveGeo();
}
