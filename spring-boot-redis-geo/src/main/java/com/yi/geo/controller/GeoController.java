package com.yi.geo.controller;

import com.yi.geo.service.RedisService;
import com.yi.geo.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 订阅发布接口
 * @author YI
 * @date 2018-9-6 16:02:52
 */
@RestController
@RequestMapping("/geo")
public class GeoController {
    @Autowired
    RedisService redisService;

    /**
     * 添加地理信息到redis中
     * @return
     */
    @RequestMapping("/redisAddGeo")
    public MessageResult redisAddGeo(){
        redisService.redisAddGeo();

        return MessageResult.ok();
    }

    /**
     * 获取某个地理位置的geohash值
     */
    @RequestMapping("/redisHashGeo")
    public MessageResult redisHashGeo(){
        Map map = redisService.redisHashGeo();

        return MessageResult.ok(map);
    }

    /**
     * 获取某个地理位置的坐标
     * @return
     */
    @RequestMapping("/redisPositionGeo")
    public MessageResult redisPositionGeo(){
        Map map = redisService.redisPositionGeo();

        return MessageResult.ok(map);
    }

    /**
     * 获取两个地理位置的距离
     * @return
     */
    @RequestMapping("/redisDistanceGeo")
    public MessageResult redisDistanceGeo(){
        String geo = redisService.redisDistanceGeo();

        return MessageResult.ok(geo);
    }

    /**
     * 根据给定地理位置坐标获取指定范围内的地理位置集合
     * @return
     */
    @RequestMapping("/redisRadiusGeo")
    public MessageResult redisRadiusGeo(){
        List list = redisService.redisRadiusGeo();

        return MessageResult.ok(list);
    }

    /**
     * 删除位置信息,此命令不是geo提供的，是使用zrem命令删除的
     * @return
     */
    @RequestMapping("/redisRemoveGeo")
    public MessageResult redisRemoveGeo(){
        Long aLong = redisService.redisRemoveGeo();

        return MessageResult.ok(aLong);
    }
}
