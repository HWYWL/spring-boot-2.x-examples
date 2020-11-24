# spring-boot-redisson-lock

### 环境
- JDK 1.8
- Spring boot 2.1.0.RELEASE
- redisson-spring-boot-starter 3.9.1
- spring-data-redis 2.1.2.RELEASE

### 说明
我们在做业务的时候进程会碰到并发的问题，比如秒杀，下面我们通过Redis解决这类问题
```
初始化接口：http://192.168.1.145:8080/kill/initBaiKe
用户秒杀抢到的商品数量接口：http://192.168.1.145:8080/kill/successNum
Redisson锁秒杀接口：http://192.168.1.145:8080/kill/redis
事务秒杀接口：http://192.168.1.145:8080/kill/affair
```

初始化数据

![](https://i.imgur.com/Ir7fjKo.jpg)

### 通过锁方式秒杀
我们知道在Redis中官方提供了一个名为Redisson的工具，它的功能非常强大在她的文档中有充分的介绍：[官方文档](https://github.com/redisson/redisson/wiki/Redisson%E9%A1%B9%E7%9B%AE%E4%BB%8B%E7%BB%8D)

我们使用它的**lock**功能，好吧废话不多说上代码
```java
/**
 * 秒杀基于Redisson的锁
 * @return
 */
@RequestMapping(value = "/redis", method = RequestMethod.POST)
public MessageResult secKillRedis() {
    MessageResult result = MessageResult.ok();
    RLock rLock = redissonClient.getLock("baike_lock");

    try {
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
        rLock.unlock();
    }
}
```
我使用jmeter进行测试

![](https://i.imgur.com/VB3QINV.jpg)

![](https://i.imgur.com/AWsx9Z9.jpg)

从结果可以看出，商品并没有出现超卖的问题
![](https://i.imgur.com/rUaXApp.jpg)

我们来看看卖出的接口统计的数据，从数据看一切正常，解决超卖问题

![](https://i.imgur.com/tZolJkR.jpg)

### Redis事务方式秒杀
##### spring的`redisTemplate`执行事务

- 注意： 若要使用spring的`redisTemplate`执行事务，需要在开启事务后执行一个redis的查询操作（但不能使用查询到的值）。原因有两点：
    - spring对redis事务的`exec()`方法返回结果做了处理（把返回值的 `OK`结果删掉）。
        - 导致在事务中只有`set`等更新操作时，事务执行失败与成功返回的结果一样
    - 事务过程中查询redis的值只会在事务执行成功后才放回。而在事务执行过程中只会返回`null`

```
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
```

通过事务的方式比较复杂，但效果和锁的方式是一样的，图我就不贴了，有兴趣的可以去看看代码：[代码](https://github.com/HWYWL/spring-boot-2.x-examples/tree/master/spring-boot-redisson-lock)


### 总结
我们推荐用锁的方式进行操作，比较比较简单，不过初期的配置也挺麻烦，
我们可以查看官方的文档进行配置：[配置文档](https://github.com/redisson/redisson/wiki/2.-%E9%85%8D%E7%BD%AE%E6%96%B9%E6%B3%95)

他可以配置的模式如下：
- 集群模式
- 云托管模式
- 单Redis节点模式
- 哨兵模式
- 主从模式

### 问题建议

- 源码：[spring-boot-redisson-lock](https://github.com/HWYWL/spring-boot-2.x-examples/tree/master/spring-boot-redisson-lock)
- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL