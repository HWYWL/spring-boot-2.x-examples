package com.yi.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yi.mybatis.plus.dao.BaikeMapper;
import com.yi.mybatis.plus.model.Baike;
import com.yi.mybatis.plus.service.IBaiKeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义实现类
 * @author YI
 * @date 2018-11-27 14:40:31
 */
@Service
public class IBaiKeServiceImpl extends ServiceImpl<BaikeMapper, Baike> implements IBaiKeService {
    @Override
    public List<Baike> selectListBySQL() {
        return baseMapper.selectListBySQL();
    }

    @Override
    public List<Baike> selectListByWrapper(Wrapper wrapper) {
        return baseMapper.selectListByWrapper(wrapper);
    }
}
