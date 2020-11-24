package com.yi.lianjia.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yi.lianjia.config.Config;
import com.yi.lianjia.mapper.LianjiaInfoMapper;
import com.yi.lianjia.model.LianjiaInfo;
import com.yi.lianjia.pipeline.CrawlerLianJiaPipeline;
import com.yi.lianjia.service.LianjiaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * 自定义实现类
 *
 * @author huangwenyi
 * @date 2019年12月26日
 */
@Service
public class LianjiaInfoServiceImpl extends ServiceImpl<LianjiaInfoMapper, LianjiaInfo> implements LianjiaInfoService, PageProcessor {
    @Autowired
    CrawlerLianJiaPipeline pronPipeline;

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("Connection", "keep-alive")
            .addHeader("DNT", "1")
            .addHeader("Host", "gz.lianjia.com");

    @Override
    public void cronTask(int page) {
        String url = StrUtil.format(Config.PRON_URL, Dict.create().set("page", page));
        Spider.create(new LianjiaInfoServiceImpl()).addUrl(url).thread(5).addPipeline(pronPipeline).run();
    }

    @Override
    public void process(Page page) {
        List<String> urls = page.getHtml().xpath("//div[@class='title']/a/@href").all();
        page.addTargetRequests(urls);

        // 小区名称
        page.putField("villageName", page.getHtml().xpath("//div[@class='communityName']/a[1]/text()").toString());

        // 所在区域
        page.putField("area", page.getHtml().xpath("//span[@class='info']/a/text()").all().toString());

        // 单价
        page.putField("unitPrice", page.getHtml().xpath("//span[@class='unitPriceValue']/text()").toString());

        // 总价
        page.putField("totalPrices", page.getHtml().xpath("//div[@class='price ']/span/text()").toString());

        // 房屋户型
        page.putField("apartments", page.getHtml().xpath("//div[@class='content']/ul/li[1]/text()").toString());

        // 所在楼层
        page.putField("floor", page.getHtml().xpath("//div[@class='content']/ul/li[2]/text()").toString());

        // 建筑面积
        page.putField("coveredArea", page.getHtml().xpath("//div[@class='content']/ul/li[3]/text()").toString());

        // 户型结构
        page.putField("familyStructure", page.getHtml().xpath("//div[@class='content']/ul/li[4]/text()").toString());

        // 套内面积
        page.putField("planimeter", page.getHtml().xpath("//div[@class='content']/ul/li[5]/text()").toString());

        // 建筑类型
        page.putField("architectureType", page.getHtml().xpath("//div[@class='content']/ul/li[6]/text()").toString());

        // 房屋朝向
        page.putField("orientationOfRoom", page.getHtml().xpath("//div[@class='content']/ul/li[7]/text()").toString());

        // 建筑结构
        page.putField("buildingStructure", page.getHtml().xpath("//div[@class='content']/ul/li[8]/text()").toString());

        // 装修状况
        page.putField("decorationSituation", page.getHtml().xpath("//div[@class='content']/ul/li[9]/text()").toString());

        // 梯户比例
        page.putField("proportionElevatorHouseholds", page.getHtml().xpath("//div[@class='content']/ul/li[10]/text()").toString());

        // 配备电梯
        page.putField("equippedWithElevator", page.getHtml().xpath("//div[@class='content']/ul/li[11]/text()").toString());

        // 产权年限
        page.putField("periodInt", page.getHtml().xpath("//div[@class='content']/ul/li[12]/text()").toString());

        // 挂牌时间
        page.putField("listingTime", page.getHtml().xpath("//div[@class='transaction']/div[2]/ul/li[1]/span[2]/text()").toString());

        // 上次交易时间
        page.putField("lastTradingTime", page.getHtml().xpath("//div[@class='transaction']/div[2]/ul/li[3]/span[2]/text()").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
