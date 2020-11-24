package com.yi.mutiple.datasource.service.impl;

import com.yi.mutiple.datasource.dao.db1.BaiKeDao;
import com.yi.mutiple.datasource.model.Baike;
import com.yi.mutiple.datasource.service.BaikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * baike操作逻辑
 * @author YI
 * @date 2018-12-6 09:48:51
 */
@Service
public class BaikeServiceImpl implements BaikeService {
    @Autowired
    BaiKeDao baiKeDao;

    @Override
    public Baike findBaikeById(Integer id) {
        return baiKeDao.findBaikeById(id);
    }
}
