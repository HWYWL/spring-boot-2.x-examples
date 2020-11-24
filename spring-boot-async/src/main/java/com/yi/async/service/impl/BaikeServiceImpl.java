package com.yi.async.service.impl;

import com.yi.async.mapper.BaikeDAO;
import com.yi.async.model.Baike;
import com.yi.async.service.BaikeService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.Future;

/**
 * 百科数据操作
 *
 * @author YI
 * @date 2019-3-19 22:18:44
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class BaikeServiceImpl implements BaikeService {
    @Resource
    BaikeDAO baikeDAO;

    /**
     * 异步接口
     * @param baike 百科信息
     * @return 只能返回void或者Future<T>类型
     */

    @Async
    @Override
    public Future<String> save(Baike baike) {
        int i = baikeDAO.insertSelective(baike);

        return new AsyncResult<>("插入行数:" + i);
    }
}
