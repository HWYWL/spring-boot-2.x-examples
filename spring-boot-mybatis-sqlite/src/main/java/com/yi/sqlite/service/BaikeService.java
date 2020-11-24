package com.yi.sqlite.service;

import com.yi.sqlite.model.Baike;
import com.yi.sqlite.model.BaikeExample;

import java.util.List;

/**
 * 接口实现
 *
 * @author YI
 * @date 2019-3-26 21:56:25
 */
public interface BaikeService {
    /**
     * 保存数据
     * @param baike 百科信息
     * @return
     */
    int saveSelective(Baike baike);

    /**
     * 根据条件查找
     * @param example 条件构建
     * @return
     */
    List<Baike> selectByExample(BaikeExample example);

    /**
     * 根据id查找
     * @param id id
     * @return
     */
    Baike selectByPrimaryKey(Integer id);
}
