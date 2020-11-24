package com.yi.presto;

import cn.hutool.core.bean.BeanUtil;
import com.yi.presto.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class SpringBootPrestoApplicationTests {
    @Autowired
    JdbcTemplate prestoJdbcTemplate;

    @Test
    void select() {
        String sql = "select app_id as appId,open_id as openId,event_id as eventId from user_center.minigame_log_data where dt = date '2019-11-25' and app_id = 61 and event_id = 155 limit 100";

        List<Map<String, Object>> maps = prestoJdbcTemplate.queryForList(sql);

        List<UserInfo> userInfos = maps.stream().map(e -> BeanUtil.mapToBean(e, UserInfo.class, true)).collect(Collectors.toList());

        System.out.println(userInfos);
    }
}
