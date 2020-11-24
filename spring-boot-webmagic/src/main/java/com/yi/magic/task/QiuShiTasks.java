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

import java.util.ArrayList;
import java.util.List;

/**
 * 糗事百科
 * @author YI
 * @date 2018-11-27 16:47:06
 */
@Component
public class QiuShiTasks implements PageProcessor {
    private static final Logger log = LoggerFactory.getLogger(QiuShiTasks.class);

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("Accept-Language", "zh-HK,zh-CN;q=0.9,zh;q=0.8")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Connection", "keep-alive")
            .addHeader("Cookie", "_xsrf=2|4322045f|f36fb4bcde4663f7c6ec182e393d65ef|1543308030; __cur_art_index=3700; Hm_lvt_2670efbdd59c7e3ed3749b458cafaa37=1543308029; _ga=GA1.2.1466250293.1543308029; _gid=GA1.2.1960573771.1543308029; _qqq_uuid_=\"2|1:0|10:1543308111|10:_qqq_uuid_|56:YmRhMjQ5MDdkMGFlZThlZjA0NjQxYTVmYjRmNTUyNmUyODdmZDIyOA==|db16c2bf300abf54c23b003a0d535bafc77fda009c00fe4fe799ec5731460a5b\"; _gat=1; Hm_lpvt_2670efbdd59c7e3ed3749b458cafaa37=1543308186")
            .addHeader("DNT", "1")
            .addHeader("Host", "www.qiushibaike.com")
            .addHeader("If-None-Match", "5c009b5fbb92277cceb4411f2646fc3c7e5c4466")
            .addHeader("Referer", "https://www.qiushibaike.com/textnew/")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36")
            .addHeader("Upgrade-Insecure-Requests", "1");

    private int page = 1;

    @Scheduled(cron = "0/1 * * * * ? ")
    public void cronTask() {
        StringBuffer url = new StringBuffer();

        url.append("https://www.qiushibaike.com/textnew/page/").append(page).append("/?s=5144360");

        Spider.create(new QiuShiTasks()).addUrl(url.toString()).addPipeline(new ConsolePipeline()).run();

        page += 1;
        log.info("cron 当前页 {}", page);
    }

    @Override
    public void process(Page page) {
        List<String> urls = new ArrayList<>();
        List<String> links = page.getHtml().xpath("//*[@id='content-left']/div/a/@href").all();
        links.forEach(e -> urls.add("https://www.qiushibaike.com" + e));

        page.addTargetRequests(urls);

        page.putField("段子--> ", page.getHtml().xpath("//*[@id='single-next-link']/div/text()").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
