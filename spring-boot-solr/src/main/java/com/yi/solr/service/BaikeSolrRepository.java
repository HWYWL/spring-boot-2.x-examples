package com.yi.solr.service;

import com.yi.solr.model.Baike;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 通过继承SolrCrudRepository 获取CRUD操作，也可以自定义操作
 *
 * @author YI
 * @date 2019-3-21 19:07:31
 */
@Repository
public interface BaikeSolrRepository extends SolrCrudRepository<Baike, String> {

    /**
     * 自定义操作
     * @param name
     * @return
     */
    List<Baike> findByName(String name);
}
