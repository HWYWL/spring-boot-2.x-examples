package com.yi.anima.service;

import com.yi.anima.model.Baike;

import java.util.List;

/**
 * 自定义实现类
 *
 * @author YI
 * @date 2018-11-27 14:40:31
 */
public interface IBaiKeService {
    /**
     * 根据id查询数据
     *
     * @param id id
     * @return
     */
    Baike selectById(int id);

    /**
     * 通过自定义sql查询
     *
     * @return
     */
    List<Baike> selectListBySQL();

    /**
     * 查询所有数据
     *
     * @return
     */
    List<Baike> selectListAll();

    /**
     * 根据条件查询，点赞数大于等于 good的数据
     *
     * @param good 点赞数
     * @return
     */
    List<Baike> selectListByGood(int good);

    /**
     * 保存
     *
     * @param baike 百科数据
     * @return id
     */
    int save(Baike baike);

    /**
     * 批量保存
     *
     * @param baikes 百科数据
     * @return
     */
    void saveBatch(List<Baike> baikes);

    /**
     * 更新
     *
     * @param baike 百科数据
     * @return
     */
    void update(Baike baike);

    /**
     * 根据id删除
     *
     * @param id id
     * @return
     */
    void deleteById(int id);
}
