package com.yi.lianjia.pipeline;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yi.lianjia.mapper.LianjiaInfoMapper;
import com.yi.lianjia.model.LianjiaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * 详情页面
 *
 * @author YI
 * @date 2019-12-26
 */
@Component
public class CrawlerLianJiaPipeline implements Pipeline {

    @Autowired
    private LianjiaInfoMapper mapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> all = resultItems.getAll();

        if (all.size() > 0) {
            LianjiaInfo weal = BeanUtil.mapToBean(all, LianjiaInfo.class, true);
            if (StrUtil.isNotEmpty(weal.getApartments())){
                mapper.insert(weal);
            }
        }
    }
}
