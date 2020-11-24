package com.yi.webflux.mongo.repository;

import com.yi.webflux.mongo.model.Baike;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * 使用ReactiveMongo操作 MongoDB
 * 复杂查找规则：find + By + 属性名（首字母大写）
 * @author YI
 * @date 2018-8-24 22:45:45
 */
@Repository
@Primary
public interface PersonRepository extends ReactiveMongoRepository<Baike, Integer> {
    /**
     * 自定义查找
     * @param bad 鄙视数
     * @return
     */
    Flux<Baike> findByBad(Integer bad);

    /**
     * 根据标签查找，按照创建时间排序
     * @param tag 标签
     * @param of    分页
     * @return
     */
    Flux<Baike> findByTagOrderByCrateDate(String tag, PageRequest of);

    /**
     * 根据标签、点赞数、鄙视数范围查找
     * @param tag   标签
     * @param good  点赞数
     * @param bad   鄙视数
     * @return
     */
    Flux<Baike> findByTagAndGoodGreaterThanAndBadLessThan(String tag, int good, int bad);
}
