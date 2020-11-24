package com.yi.redisson.lock.controller;

import cn.hutool.json.JSONUtil;
import com.yi.redisson.lock.model.Baike;
import com.yi.redisson.lock.utils.MessageResult;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 秒杀
 * @author YI
 * @date 2018-11-29 15:28:03
 */
@RestController
@RequestMapping("/kill")
public class BaiKeController {
    private static final Logger log = LoggerFactory.getLogger(BaiKeController.class);

    private final StringRedisTemplate redisTemplate;
    private final RedissonClient redissonClient;

    private AtomicInteger successNum = new AtomicInteger(0);

    @Autowired
    public BaiKeController(RedissonClient redissonClient, StringRedisTemplate redisTemplate) {
        this.redissonClient = redissonClient;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 初始化书本数量
     * @return MessageResult
     */
    @RequestMapping(value = "/initBaiKe", method = RequestMethod.GET)
    public MessageResult initBaiKe() {
        List<String> list = new ArrayList<>();
        list.add("魔幻");
        list.add("小说");
        Baike baike = new Baike(1, "全职法师", list, 1000000, 5, "乱", "男", 1000, 0, 20, new Date(), new Date());

        redisTemplate.opsForValue().set("baike", JSONUtil.toJsonStr(baike));

        successNum.set(0);

        return MessageResult.ok("数据初始化成功");
    }

    /**
     * 秒杀基于Redisson的锁
     * @return
     */
    @RequestMapping(value = "/redis", method = RequestMethod.POST)
    public MessageResult secKillRedis() {
        MessageResult result = MessageResult.ok();
        RLock rLock = redissonClient.getLock("baike_lock");

        try {
            // 加锁
            rLock.lock();
            String baikeJson = redisTemplate.opsForValue().get("baike");
            Baike baike = JSONUtil.toBean(baikeJson, Baike.class);
            Integer amount = baike.getAmount();
            amount = amount - 1;
            if (amount < 0) {
                result.setMsg("库存不足");

                return result;
            }

            baike.setAmount(amount);
            redisTemplate.opsForValue().set("baike", JSONUtil.toJsonStr(baike));

            // 用户抢到商品累计
            String msg = "减少库存成功,共减少" + successNum.incrementAndGet();
            result.setMsg(msg);
            log.info(msg);

            return result;
        } finally {
            // 解锁
            rLock.unlock();
        }
    }

    /**
     * 通过事务解决秒杀
     * @return
     */
    @RequestMapping(value = "/affair", method = RequestMethod.POST)
    public MessageResult affair() {
        MessageResult result = MessageResult.ok();
        redisTemplate.setEnableTransactionSupport(true);

        // 通过事务解决超卖问题
        List results = redisTemplate.execute(new SessionCallback<List>() {
            @Override
            public List execute(RedisOperations operations) throws DataAccessException {
                operations.watch("baike");
                String baikeJson = redisTemplate.opsForValue().get("baike");
                Baike baike = JSONUtil.toBean(baikeJson, Baike.class);
                operations.multi();
                //一定要有空查询
                operations.opsForValue().get("baike");
                Integer amount = baike.getAmount();
                amount = amount - 1;
                if (amount < 0) {
                    return null;
                }

                baike.setAmount(amount);
                redisTemplate.opsForValue().set("baike", JSONUtil.toJsonStr(baike));

                return operations.exec();
            }
        });

        if (results != null && !results.isEmpty()) {
            // 用户抢到商品累计
            String msg = "减少库存成功,共减少" + successNum.incrementAndGet();
            result.setMsg(msg);
            log.info(msg);

            return result;
        }

        result.setMsg("库存不足");
        return result;
    }

    /**
     * 用户抢到商品的数量
     * @return MessageResult
     */
    @RequestMapping(value = "/successNum", method = RequestMethod.POST)
    public MessageResult successNum() {
        return MessageResult.ok("用户秒杀抢到的商品数量：" + successNum.get());
    }
}
