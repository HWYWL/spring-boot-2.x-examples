package com.yi.lianjia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yi.lianjia.model.LianjiaInfo;

/**
 * 自定义实现类
 *
 * @author huangwenyi
 * @date 2019-12-26
 */
public interface LianjiaInfoService extends IService<LianjiaInfo> {
    /**
     * 启动爬虫
     *
     * @param page 当前页
     */
    void cronTask(int page);
}
