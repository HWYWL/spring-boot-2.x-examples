package com.yi.beatlsql.service;

import com.yi.beatlsql.model.Baike;

import java.util.List;

/**
 * 业务接口
 * @author YI
 * @date 2019-3-5 22:09:21
 */
public interface BaikeService {
    /**
     * 统计行数
     * @return
     */
    long total();

    /**
     * 保存数据
     * @param baike
     */
    void save(Baike baike);

    /**
     * 获取所有数据
     * @param query
     * @return
     */
    List<Baike> allBaike(Baike query);
}
