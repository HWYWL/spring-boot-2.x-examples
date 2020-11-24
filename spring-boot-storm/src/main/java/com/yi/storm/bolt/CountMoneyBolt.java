package com.yi.storm.bolt;

import com.alibaba.fastjson.JSON;
import com.yi.storm.config.RedisConfig;
import com.yi.storm.domain.PaymentInfo;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 统计面板数据
 *
 * @author huangwenyi
 * @date 2019-9-2
 */
@Service
public class CountMoneyBolt extends BaseBasicBolt {
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 初始化方法，程序启动只会调用一次
     *
     * @param stormConf worker的Storm配置
     * @param context   上下文
     */
    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        this.stringRedisTemplate = RedisConfig.stringRedisTemplate;
    }

    /**
     * 接收我们上游kafkaspout发送过来的数据，然后将数据保存到redis当中去
     *
     * @param input
     * @param collector
     */
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        // 获取数据
        Object value = input.getValue(4);
        if (null != value && !StringUtils.isEmpty(value.toString())) {
            String jsonStr = value.toString();
            PaymentInfo paymentInfo = JSON.parseObject(jsonStr, PaymentInfo.class);

            // 平台销售总额
            stringRedisTemplate.opsForValue().increment("order:total:price:date", paymentInfo.getPayPrice());
            //平台今天下单的人数
            stringRedisTemplate.opsForValue().increment("order:total:user:date");
            //平台销售的商品数量
            stringRedisTemplate.opsForValue().increment("order:num:user:date");
            //每个商品的总销售额
            stringRedisTemplate.opsForValue().increment("order:" + paymentInfo.getProductId() + ":price:date", paymentInfo.getPayPrice());
            //统计每个商品的购买人数
            stringRedisTemplate.opsForValue().increment("order:" + paymentInfo.getProductId() + ":user:date");
            // 每个商品的销售数量
            stringRedisTemplate.opsForValue().increment("order:" + paymentInfo.getProductId() + ":num:date");
            //店铺的销售总额
            stringRedisTemplate.opsForValue().increment("order:" + paymentInfo.getShopId() + ":price:date", paymentInfo.getPayPrice());
            //店铺的购买人数
            stringRedisTemplate.opsForValue().increment("order:" + paymentInfo.getShopId() + ":user:date");
            //每个店铺的销售数量
            stringRedisTemplate.opsForValue().increment("order:" + paymentInfo.getShopId() + ":num:date");
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
