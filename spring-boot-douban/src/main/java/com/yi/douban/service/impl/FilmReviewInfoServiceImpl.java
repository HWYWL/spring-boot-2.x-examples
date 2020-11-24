package com.yi.douban.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yi.douban.config.Config;
import com.yi.douban.mapper.NezhaMapper;
import com.yi.douban.model.Nezha;
import com.yi.douban.pipeline.CrawlerNeZhaPipeline;
import com.yi.douban.service.FilmReviewInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 自定义实现类
 *
 * @author huangwenyi
 * @date 2019年12月26日
 */
@Service
public class FilmReviewInfoServiceImpl extends ServiceImpl<NezhaMapper, Nezha> implements FilmReviewInfoService, PageProcessor {
    @Autowired
    CrawlerNeZhaPipeline pronPipeline;

    private Site site = Site.me()
            .addHeader("DNT", "1")
            .addHeader("Referer", "https://movie.douban.com/subject/26794435/?from=showing")
            .addHeader("Cookie", "bid=0j5SV2r2nQY; douban-fav-remind=1; ll=\"118281\"; trc_cookie_storage=taboola%2520global%253Auser-id%3D4cfd5831-ecca-41c9-a201-463cc808a852-tuct481f971; __yadk_uid=dse0KyJmilHC5Jh4dgD2gXGun2BgU9jh; __utmz=223695111.1575709593.3.3.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmz=30149280.1576468539.5.5.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1577413752%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DEx8T_l8ZWjgFiizSQiiUHS_Z0MznYDT-FgqtNGw60UOsKvoWtIkuyF9NVg7ZTxOFJxjuG-qSUoOMCk3tQKXLpOuy6lmZUCxS3-QGHXz39m_%26wd%3D%26eqid%3Db0b0074c0002ef06000000065deb6b88%22%5D; _pk_ses.100001.4cf6=*; ap_v=0,6.0; __utma=30149280.1008141462.1572510913.1576468539.1577413753.6; __utmc=30149280; __utma=223695111.1835685239.1572510913.1575709593.1577413753.4; __utmb=223695111.0.10.1577413753; __utmc=223695111; __gads=ID=bd21f68f28b47ae2:T=1577414012:S=ALNI_MaXcQRVayO_6cujpaugn3OSlJvlNw; _vwo_uuid_v2=D7C6675FF58D1B1115398E9AABE553D3C|c683611959e049c63ba1e3cff89cec53; __utmt_t1=1; _pk_id.100001.4cf6=29f00f43b56aaf46.1572510912.4.1577416005.1575709592.; __utmb=30149280.57.8.1577416004582; RT=")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36");

    @Override
    public void cronTask(int page) {
        String url = StrUtil.format(Config.PRON_URL, Dict.create().set("start", page));
        Spider.create(new FilmReviewInfoServiceImpl()).addUrl(url).thread(5).addPipeline(pronPipeline).run();
    }

    @Override
    public void process(Page page) {
//        String s = page.getHtml().xpath("//a[@class = 'reply ']/@href").toString();
//        List<String> all = page.getHtml().xpath("//div[@class = 'main-bd']/h2/a/text()").all();
        //
        page.putField("filmReview", page.getHtml().xpath("//div[@class='main-bd']/h2/a/text()").all());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
