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

import java.util.List;

/**
 * 爬取拉钩网数据
 * @author YI
 * @date 2018-11-23 11:50:52
 */
@Component
public class LaGouTasks implements PageProcessor {
    private static final Logger log = LoggerFactory.getLogger(LaGouTasks.class);

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("Accept-Language", "zh-HK,zh-CN;q=0.9,zh;q=0.8")
            .addHeader("Connection", "keep-alive")
            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            .addHeader("Cookie", "user_trace_token=20181121103434-fd52ae7b-f9d9-4edb-a1b7-781afc59eb44; _ga=GA1.2.1239959887.1542767672; LGUID=20181121103435-fc0a0536-ed35-11e8-8a7a-5254005c3644; index_location_city=%E5%B9%BF%E5%B7%9E; JSESSIONID=ABAAABAAAGGABCB422C8BB98DF2C8A9286FB6B36414DBAD; _gid=GA1.2.237874082.1542945092; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1542767672,1542945093; TG-TRACK-CODE=index_navigation; _gat=1; LGSID=20181123141122-999a12a2-eee6-11e8-b629-525400f775ce; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2Fzhaopin%2FJava%2F1%2F%3FfilterOption%3D1; LGRID=20181123141122-999a1558-eee6-11e8-b629-525400f775ce; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1542953476; SEARCH_ID=39cb04b00ff8448eb99be0b19233644b")
            .addHeader("DNT", "1")
            .addHeader("Host", "www.lagou.com")
            .addHeader("Origin", "https://www.lagou.com")
            .addHeader("Referer", "https://www.lagou.com/jobs/list_Java")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36")
            .addHeader("X-Anit-Forge-Token", "None")
            .addHeader("X-Requested-With", "XMLHttpRequest");

    private int page = 1;

    @Scheduled(cron = "0/1 * * * * ? ")
    public void cronTask() {
        StringBuffer url = new StringBuffer();

        url.append("https://www.lagou.com/zhaopin/Java/").append(page).append("/?filterOption=").append(page);

        Spider.create(new LaGouTasks()).addUrl(url.toString()).addPipeline(new ConsolePipeline()).run();

        page += 1;
        log.info("cron 当前页 {}", page);
    }

    @Override
    public void process(Page page) {
        List<String> links = page.getHtml().xpath("//div[@class='s_position_list']/ul/li/div[1]/div[1]/div[1]/a/@href").all();
        page.addTargetRequests(links);

        page.putField("company", page.getHtml().xpath("//div[@class='position-content-l']/div[@class='job-name']/div[@class='company']/text()").toString());
        page.putField("job", page.getHtml().xpath("//div[@class='position-content-l']/div[@class='job-name']/span[@class='name']/text()").toString());
        page.putField("salary", page.getHtml().xpath("//div[@class='position-content-l']/dd[@class='job_request']/p/span[1]/text()").toString());
        page.putField("WorkPlace", page.getHtml().xpath("//div[@class='position-content-l']/dd[@class='job_request']/p/span[2]/text()").toString());
        page.putField("WorkExperience", page.getHtml().xpath("//div[@class='position-content-l']/dd[@class='job_request']/p/span[3]/text()").toString());
        page.putField("Record", page.getHtml().xpath("//div[@class='position-content-l']/dd[@class='job_request']/p/span[4]/text()").toString());
        page.putField("workNature", page.getHtml().xpath("//div[@class='position-content-l']/dd[@class='job_request']/p/span[5]/text()").toString());
        page.putField("logo", page.getHtml().xpath("//dl[@id='job_company']/dt/a/img/@src").toString());
        page.putField("field", page.getHtml().xpath("//dl[@id='job_company']/dd/ul/li[1]/text()").toString());
        page.putField("stage", page.getHtml().xpath("//dl[@id='job_company']/dd/ul/li[2]/text()").toString());
        page.putField("size", page.getHtml().xpath("//dl[@id='job_company']/dd/ul/li[3]/text()").toString());
        page.putField("website", page.getHtml().xpath("//dl[@id='job_company']/dd/ul/li[4]/text()").toString());
        page.putField("workAddress", page.getHtml().xpath("//*[@id='job_detail']/dd[3]/div/text()").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
