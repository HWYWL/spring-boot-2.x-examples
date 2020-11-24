package com.yi.async.service;

import com.yi.async.model.Baike;

import java.util.concurrent.Future;

public interface BaikeService {
    /**
     * 保存数据
     * @param baike 百科信息
     * @return
     */
    Future<String> save(Baike baike);
}
