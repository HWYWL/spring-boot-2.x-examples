package com.yi.figure.task;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * @author YI
 * @date 2020/3/28 18:56
 */
@Slf4j
@Component
public class FigureTask implements PageProcessor {
    private Site site = Site.me();

    public void runTask(int page) {
        for (int i = 1; i < page; i++) {
            String url = "https://www.fabiaoqing.com/biaoqing/lists/page/" + i + ".html";
            String context = HttpUtil.get(url);

            Html html = new Html(context);
            List<String> all = html.xpath("//div[@class='tagbqppdiv']/a/img/@data-original").all();
            all.forEach(e -> HttpUtil.downloadFile(e, "E:\\images"));
        }
    }

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return site;
    }
}