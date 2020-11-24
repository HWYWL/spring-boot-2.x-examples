package com.yi.magic.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 爬取伯乐在线http://www.jobbole.com/
 * @author YI
 * @date 2018-11-20 16:54:46
 */
@Component
public class JobBoleTasks implements PageProcessor {
    private static final Logger log = LoggerFactory.getLogger(JobBoleTasks.class);

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private Site site = Site.me().setDomain("blog.jobbole.com/all-posts");

    private int page = 1;

    @Scheduled(cron = "0/1 * * * * ? ")
    public void cronTask() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("http://blog.jobbole.com/all-posts/page/").append(page);

        Spider.create(new JobBoleTasks()).addUrl(buffer.toString())
                .addPipeline(new ConsolePipeline()).thread(2).run();

        page += 1;
        log.info("cron 现在时间是 {} 当前页 {}", DATE_FORMAT.format(new Date()), page);
    }

    @Override
    public void process(Page page) {
        List<String> links = page.getHtml().xpath("//*[@id='archive']/div/div/a/@href").all();
        page.addTargetRequests(links);

        // 获取标题
        page.putField("title", page.getHtml().xpath("//div[@class='entry-header']/h1/text()").toString());

        // 文章创建时间
        page.putField("create_date", page.getHtml().xpath("//*[@class='entry-meta-hide-on-mobile']/text()").toString());

        // 点赞数
        page.putField("praise_nums", page.getHtml().xpath("//span[contains(@class, 'vote-post-up')]/h10/text()").toString());

        // 收藏数
        page.putField("fav_nums", page.getHtml().xpath("//span[contains(@class, 'bookmark-btn')]/text()").toString());

        // 评论数
        page.putField("fav_nums", page.getHtml().xpath("//*[@class='post-adds']/a/span/text()").toString());

        // 文章
        page.putField("fav_nums", page.getHtml().xpath("//div[@class='entry']").toString());

        // 标签
        page.putField("tag", page.getHtml().xpath("//p[@class='entry-meta-hide-on-mobile']/a/text()").all().toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
