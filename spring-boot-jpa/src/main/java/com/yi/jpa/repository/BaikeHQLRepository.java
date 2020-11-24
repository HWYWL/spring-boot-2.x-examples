package com.yi.jpa.repository;

import com.yi.jpa.model.Baike;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 使用HQL 操作数据库
 * @author YI
 * @date 2018-8-27 10:24:29
 */
public interface BaikeHQLRepository extends JpaRepository<Baike,Long>,JpaSpecificationExecutor<Baike> {

    /**
     * 根据用户名查找数据
     * @param name  用户名
     * @return
     */
    @Query("select b from Baike b where b.name = :name")
    List<Baike> searchBaike(@Param("name") String name);

    /**
     * 根据用户名查找数据并排序
     * @param name  用户名
     * @param sort  分类排序
     * @return
     */
    @Query("select b from Baike b where b.name = :name")
    List<Baike> searchBaike(@Param("name") String name, Sort sort);

    /**
     * 根据用户名更新改用户金币数量
     * @param goldcoin  金币数量
     * @param name  用户名
     * @return
     */
    @Modifying
    @Transactional(readOnly = false)
    @Query("update Baike b set b.goldcoin = :goldcoin where b.name = :name")
    int setNameContent(@Param("goldcoin") Integer goldcoin, @Param("name") String name);
}
