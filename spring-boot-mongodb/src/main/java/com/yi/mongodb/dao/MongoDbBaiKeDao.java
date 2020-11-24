package com.yi.mongodb.dao;

import com.mongodb.client.result.UpdateResult;
import com.yi.mongodb.model.Baike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * MongoDB操作Baike
 * @author YI
 * @date 2018-8-22 15:17:44
 */
@Repository
public class MongoDbBaiKeDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Baike findById(Integer id) {

        Baike dict = mongoTemplate.findById(id, Baike.class);
        return dict;
    }

    public Baike addDict(Baike baike) {
        mongoTemplate.insert(baike);

        return baike;
    }

    public List<Baike> queryBad(Criteria criteria) {
        List<Baike> list = mongoTemplate.find(query(criteria), Baike.class);

        return list;
    }

    public long addOne(Criteria criteria, Update update) {
        UpdateResult result = mongoTemplate.updateMulti(query(criteria), update, Baike.class);

        return result.getModifiedCount();
    }

    public List<Baike> findBaike(Query query, int pageNum) {

        List<Baike> list = mongoTemplate.find(query, Baike.class);
        return list;
    }

    public List<Baike> findBaike(Query query) {
        List<Baike> list = mongoTemplate.find(query, Baike.class);

        return list;
    }

    public long count(Query query) {
        long totalCount = mongoTemplate.count(query, Baike.class);

        return totalCount;
    }

    public Baike updateDict(Baike baike) {
        mongoTemplate.save(baike);

        return baike;
    }

    public Baike deleteDict(Baike baike) {
        mongoTemplate.remove(baike);

        return baike;
    }
}
