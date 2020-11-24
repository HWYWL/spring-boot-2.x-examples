package com.yi.mongodb.service;

import com.yi.mongodb.model.Baike;

import java.util.List;

/**
 * 百科对象用于操作MongoDB 服务类接口
 * @author YI
 * @date 2018-8-22 15:19:39
 */
public interface MongoDbBaiKeService {
    /**
     * 根据id查找对象
     * @param id 百科id
     * @return
     */
    Baike findById(Integer id);

    /**
     * 添加文档
     * @param baike 对象
     * @return
     */
    Baike addDict(Baike baike);

    /**
     * 根据鄙视的数量查找
     * @param bad 鄙视数
     * @return
     */
    List<Baike> queryBad(int bad);

    /**
     * 根据文档标签点一个赞给文章
     * @param tag   标签
     * @return
     */
    String addOne(String tag);

    /**
     * 根据标签分页查找
     * @param tag   标签
     * @param pageNum   当前页
     * @return
     */
    List<Baike> findBaike(String tag, int pageNum);

    /**
     * 多条件查找
     * @param tag   标签
     * @param good  点赞数
     * @param bad   鄙视数
     * @return
     */
    List<Baike> findBaike(String tag, int good, int bad);

    /**
     * 更新文档
     * @param baike
     * @return
     */
    Baike updateDict(Baike baike);

    /**
     * 根据id删除文档
     * @param id 文档id
     * @return
     */
    Baike deleteDict(Integer id);
}
