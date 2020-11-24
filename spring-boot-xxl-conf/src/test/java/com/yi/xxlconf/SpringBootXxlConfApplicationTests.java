package com.yi.xxlconf;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yi.xxlconf.model.ConfBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringBootXxlConfApplicationTests {

    @Test
    void find() {
        String url = "http://localhost:8099/xxl-conf-admin/conf/find";

        List<String> keys = new ArrayList<>();
        ConfBean bean = new ConfBean();

        bean.setAccessToken("H6l8sqOxJlqdHleasRAIYzIdQnwK9jsFifok");
        bean.setEnv("test");

        keys.add("default.key01");
        keys.add("default.key02");
        keys.add("default.key03");
        bean.setKeys(keys);

        String body = HttpUtil.createGet(url).body(JSONUtil.toJsonPrettyStr(bean)).execute().body();
        System.out.println(body);
    }

    @Test
    void monitor() {
        String url = "http://localhost:8099/xxl-conf-admin/conf/monitor";

        List<String> keys = new ArrayList<>();
        ConfBean bean = new ConfBean();

        bean.setAccessToken("H6l8sqOxJlqdHleasRAIYzIdQnwK9jsFifok");
        bean.setEnv("test");

        keys.add("default.key01");
        keys.add("default.key02");
        keys.add("default.key03");
        bean.setKeys(keys);

        String body = HttpUtil.createGet(url).body(JSONUtil.toJsonPrettyStr(bean)).execute().body();
        System.out.println(body);
    }

}
