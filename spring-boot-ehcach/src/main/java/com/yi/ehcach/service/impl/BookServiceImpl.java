package com.yi.ehcach.service.impl;

import com.yi.ehcach.model.Book;
import com.yi.ehcach.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用book_cache配置的缓存模式
 *
 * @author YI
 * @date 2019-3-2 10:42:11
 */
@Service
@CacheConfig(cacheNames = "book_cache")
public class BookServiceImpl implements BookService {
    private Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    @Cacheable(key = "#id")
    public Book selectById(Integer id) {
        log.info("=====>id查找系统参数入参：id=" + id);

        return new Book(1, "三国演义", "罗贯中");
    }

    @Override
    @Cacheable
    public List<Book> findAll() {
        log.info("=====>查找所有缓存数据");
        List<Book> list = new ArrayList<>();
        list.add(new Book(1, "三国演义", "罗贯中"));

        return list;
    }

    @Override
    @CachePut(key = "#book.id")
    public void updateById(Book book) {
        log.info("=====>更新系统参数入参：" + book.toString());
    }

    @Override
    @CachePut(key = "#book.id")
    public void addBook(Book book) {
        log.info("=====>新增系统参数入参：" + book.toString());
    }

    @Override
    @CacheEvict
    public void deleteById(Integer id) {
        log.info("======>删除系统参数入参ID= " + id);
    }
}
