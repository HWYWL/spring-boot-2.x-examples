package com.yi.ehcach.service;

import com.yi.ehcach.model.Book;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 操作缓存接口
 *
 * @author YI
 * @date 2019-3-2 10:21:47
 */
public interface BookService {

    /**
     * 查询
     */
    Book selectById(Integer id);

    /**
     * 查询所有缓存
     */
    List<Book> findAll();

    /**
     * 更新
     */
    void updateById(Book book);

    /**
     * 新增
     */
    void addBook(Book book);

    /**
     * 删除
     */
    void deleteById(Integer id);
}
