package com.yi.jpa.repository;

import com.yi.jpa.model.Baike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 使用JpaRepository 操作数据库
 * @author YI
 * @date 2018-8-27 10:24:29
 */
public interface BaikeRepository extends JpaRepository<Baike,Long> {

    /**
     * 查询用户名称包含name字符串的用户对象
     * @param name
     * @return
     */
    List<Baike> findByNameContaining(String name);

    /**
     * 根据name和book的字段匹配
     * @param name
     * @param book
     * @return
     */
    List<Baike> getByNameIsAndBookIs(String name,String book);

    /**
     * 精确匹配name的用户对象
     * @param name
     * @return
     */
    List<Baike> getByNameIs(String name);
}
