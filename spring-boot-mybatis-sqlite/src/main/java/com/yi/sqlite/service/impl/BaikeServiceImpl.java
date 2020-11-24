package com.yi.sqlite.service.impl;

import com.yi.sqlite.dao.BaikeDAO;
import com.yi.sqlite.model.Baike;
import com.yi.sqlite.model.BaikeExample;
import com.yi.sqlite.service.BaikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 接口实现
 *
 * @author YI
 * @date 2019-3-26 21:56:25
 */
@Service
public class BaikeServiceImpl implements BaikeService {
    @Resource
    BaikeDAO baikeDAO;
    @Override
    public int saveSelective(Baike baike) {
        return baikeDAO.insertSelective(baike);
    }

    @Override
    public List<Baike> selectByExample(BaikeExample example) {
        return baikeDAO.selectByExample(example);
    }

    @Override
    public Baike selectByPrimaryKey(Integer id) {
        return baikeDAO.selectByPrimaryKey(id);
    }
}
