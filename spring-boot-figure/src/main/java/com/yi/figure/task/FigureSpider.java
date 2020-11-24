package com.yi.figure.task;

import io.github.biezhi.elves.Elves;
import io.github.biezhi.elves.config.Config;
import io.github.biezhi.elves.pipeline.Pipeline;
import io.github.biezhi.elves.request.Request;
import io.github.biezhi.elves.response.Response;
import io.github.biezhi.elves.response.Result;
import io.github.biezhi.elves.spider.Spider;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YI
 * @date 2020/3/30 10:34
 */
@Slf4j
public class FigureSpider extends Spider {
    private static final String BASE_URL = "https://www.fabiaoqing.com";
    private static final String FIRST_URL = "https://www.fabiaoqing.com/biaoqing/lists/page/1.html";

    public FigureSpider(String name) {
        super(name);
        this.startUrls(FIRST_URL);
    }

    @Override
    public void onStart(Config config) {
        this.addPipeline((Pipeline<List<String>>) (items, request) -> {
            log.info("=== 骚年来斗图吧！！！ ===");
            items.forEach(item -> System.out.println("\r\n" + item + "\r\n============END==========\r\n"));
        });
    }

    @Override
    protected <T> Result<T> parse(Response response) {
        Result result = new Result<>();
        Elements elements = response.body().xpath("//div[@class='tagbqppdiv']/a/img/@data-original").getElements();
        List<String> titles = elements.stream().map(e -> e.attributes().get("data-original")).collect(Collectors.toList());
        result.setItem(titles);

        // 获取下一页 URL //*[@id="bqb"]/div[3]/a[9]
        Elements nextEl = response.body().xpath("//*[@id='bqb']/div[3]/a[8]").getElements();
        if (null != nextEl && nextEl.size() > 0) {
            String nextPageUrl = nextEl.get(0).attr("href");
            Request nextReq = this.makeRequest(BASE_URL + nextPageUrl, this::parse);
            result.addRequest(nextReq);
        }

        return result;
    }

    public static void main(String[] args) {
        FigureSpider doubanSpider = new FigureSpider("斗图");
        Elves.me(doubanSpider, Config.me()).start();
    }
}