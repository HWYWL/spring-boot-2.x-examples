package com.yi.douban.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yi.douban.model.Nezha;

/**
 * 自定义实现类
 *
 * @author huangwenyi
 * @date 2019-12-26
 */
public interface FilmReviewInfoService extends IService<Nezha> {
    /**
     * 启动爬虫
     *
     * @param page 当前页
     */
    void cronTask(int page);
}
