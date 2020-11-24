package com.yi.mybatis.plus.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yi.mybatis.plus.model.Baike;

import java.util.List;

/**
 * 自定义实现类
 * @author YI
 * @date 2018-11-27 14:40:31
 */
public interface IBaiKeService extends IService<Baike> {
    /**
     * 通过注解sql查询
     * @return
     */
    List<Baike> selectListBySQL();

    /**
     * 根据条件查询
     * @param wrapper 组合条件
     * @return
     */
    List<Baike> selectListByWrapper(Wrapper wrapper);
}
