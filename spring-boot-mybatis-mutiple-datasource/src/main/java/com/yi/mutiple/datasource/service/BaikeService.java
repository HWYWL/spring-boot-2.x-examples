package com.yi.mutiple.datasource.service;

import com.yi.mutiple.datasource.model.Baike;

/**
 * baike操作逻辑
 * @author YI
 * @date 2018-12-6 09:48:51
 */
public interface BaikeService {
    /**
     * 根据id查找
     * @param id 主键id
     * @return
     */
    Baike findBaikeById(Integer id);
}
