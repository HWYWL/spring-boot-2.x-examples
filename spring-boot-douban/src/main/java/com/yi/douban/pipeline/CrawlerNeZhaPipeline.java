package com.yi.douban.pipeline;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yi.douban.mapper.NezhaMapper;
import com.yi.douban.model.Nezha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;

/**
 * 详情页面
 *
 * @author YI
 * @date 2019-12-27
 */
@Component
public class CrawlerNeZhaPipeline implements Pipeline {

    @Autowired
    private NezhaMapper mapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> all = resultItems.getAll();

        if (all.size() > 0) {
            List<String> filmReviews = (List) all.get("filmReview");
            filmReviews.forEach(e -> {
                if (StrUtil.isNotEmpty(e)) {
                    Nezha weal = new Nezha();
                    weal.setFilmReview(e);
                    mapper.insert(weal);
                }
            });

        }
    }
}
