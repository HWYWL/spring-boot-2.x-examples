package com.yi.geo.service.impl;

import com.yi.geo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.BoundGeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Redis业务类
 * @author YI
 * @date 2018-9-7 10:50:33
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 用于地理信息 添加一个ZSET的结构 CHINA:CITY
     */
    public static String key = "CHINA:CITY";

    @Override
    public void redisAddGeo() {
        //用于地理信息 添加一个ZSET的结构 CHINA:CITY
        BoundGeoOperations boundGeoOperations = redisTemplate.boundGeoOps(key);

        //南京市 118.803805,32.060168
        Point nanjing = new Point(118.803805,32.060168);
        //北京市 116.397039,39.9077
        Point beijing = new Point(116.397039,39.9077);
        //天津市 116.397039,39.9077
        Point tianjing = new Point(117.20,39.12);

        //添加地理信息到Redis
        System.out.println(boundGeoOperations.add(nanjing, "南京市"));
        System.out.println(boundGeoOperations.add(beijing, "北京市"));
        System.out.println(boundGeoOperations.add(tianjing, "天津市"));
    }

    @Override
    public Map redisHashGeo() {
        //用于地理信息 添加一个ZSET的结构 CHINA:CITY
        BoundGeoOperations boundGeoOperations = redisTemplate.boundGeoOps(key);
        HashMap<String, List> map = new HashMap<>(16);

        //获取某个地理位置的geohash值
        List<String> list = boundGeoOperations.hash("南京市");

        map.put("南京市的geoHash = ", list);

        return map;
    }

    @Override
    public Map redisPositionGeo() {
        //用于地理信息 添加一个ZSET的结构 CHINA:CITY
        BoundGeoOperations boundGeoOperations = redisTemplate.boundGeoOps(key);
        HashMap<String, List> map = new HashMap<>(16);

        //获取某个地理位置的坐标
        List<Point> pointList = boundGeoOperations.position("南京市");
        map.put("南京市的经纬度", pointList);

        return map;
    }

    @Override
    public String redisDistanceGeo() {
        //用于地理信息 添加一个ZSET的结构 CHINA:CITY
        BoundGeoOperations boundGeoOperations = redisTemplate.boundGeoOps(key);
        StringBuffer buffer = new StringBuffer();

        //获取两个地理位置的距离
        Distance distance = boundGeoOperations.distance("南京市", "天津市", Metrics.KILOMETERS);

        buffer.append("南京市到天津市之间的距离是：");
        buffer.append(distance.getValue());
        buffer.append("km");

        return buffer.toString();
    }

    @Override
    public List redisRadiusGeo() {
        //用于地理信息 添加一个ZSET的结构 CHINA:CITY
        BoundGeoOperations boundGeoOperations = redisTemplate.boundGeoOps(key);
        List<Object> geoList = new ArrayList<>();

        //南京市 118.803805,32.060168
        Point nanjing = new Point(118.803805,32.060168);

        //根据给定地理位置坐标获取指定范围内的地理位置集合
        //查询南京市1000KM范围内的城市
        Circle within = new Circle(nanjing,1000000);
        //设置geo查询参数
        RedisGeoCommands.GeoRadiusCommandArgs geoRadiusArgs = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs();
        //查询返回结果包括距离和坐标
        geoRadiusArgs = geoRadiusArgs.includeCoordinates().includeDistance();
        //按查询出的坐标距离中心坐标的距离进行排序
        geoRadiusArgs.sortAscending();
        //限制查询返回的数量
        geoRadiusArgs.limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = boundGeoOperations.radius(within,geoRadiusArgs);
        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> geoResultList = geoResults.getContent();
        for (GeoResult geoResult : geoResultList) {
            geoList.add("geoRadius1000  " + geoResult.getContent());
        }

        //根据给定地理位置获取指定范围内的地理位置集合 查询南京市800KM范围内的城市
        geoResults = boundGeoOperations.radius("南京市",new Distance(800000), geoRadiusArgs);
        geoResultList = geoResults.getContent();
        for (GeoResult geoResult : geoResultList) {
            geoList.add("geoRadius800  " + geoResult.getContent());
        }

        return geoList;
    }

    @Override
    public Long redisRemoveGeo() {
        //用于地理信息 添加一个ZSET的结构 CHINA:CITY
        BoundGeoOperations boundGeoOperations = redisTemplate.boundGeoOps(key);

        Long aLong = boundGeoOperations.remove("南京市");

        return aLong;
    }
}
