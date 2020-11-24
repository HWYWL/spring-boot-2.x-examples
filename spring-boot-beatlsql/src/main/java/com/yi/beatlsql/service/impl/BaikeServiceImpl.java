package com.yi.beatlsql.service.impl;

import com.yi.beatlsql.dao.BaikeDao;
import com.yi.beatlsql.model.Baike;
import com.yi.beatlsql.service.BaikeService;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务接口实现
 * @author YI
 * @date 2019-3-5 22:10:33
 */
@Transactional(propagation = Propagation.REQUIRED,rollbackFor={Exception.class, RuntimeException.class})
@Service
public class BaikeServiceImpl implements BaikeService {
    @Autowired
    BaikeDao baikeDao;
    @Autowired
    SQLManager sqlManager;

    @Override
    public long total() {
        return baikeDao.allCount();
    }

    @Override
    public void save(Baike baike) {
        baikeDao.insertTemplate(baike);
    }

    @Override
    public List<Baike> allBaike(Baike query) {
        return baikeDao.template(query);
    }
}
