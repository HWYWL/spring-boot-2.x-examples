package com.yi.clickhouse;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.yi.clickhouse.model.EventLogRealtime;
import com.yi.clickhouse.service.ClickHouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YI
 * @description
 * @date create in 2021/7/5 18:30
 */
@Slf4j
@Component
public class ClickHouseApplicationRunner implements ApplicationRunner {
    @Autowired
    ClickHouseService clickHouseService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<EventLogRealtime> list = new ArrayList<>();
        for (int i = 1; i < 200000000; i++) {
            list.add(getData());
            // 单条插入
//            clickHouseService.save(getData());
            if (list.size() == 100000){
                // 批量插入
                clickHouseService.saveBatch(list);
                list.clear();
                System.out.println("已插入：" + i + "行");
            }
        }
    }

    private EventLogRealtime getData() {
        int timestamp = RandomUtil.randomInt(1625241599, 1625500799);
        int realCurrencyAmount = RandomUtil.randomInt(100, 68800);

        EventLogRealtime realtime = new EventLogRealtime();
        realtime.setApp_id(RandomUtil.randomInt(1, 20));
        realtime.setPlatform_id(RandomUtil.randomInt(1, 100));
        realtime.setChannel_id(RandomUtil.randomInt(1, 1000));
        realtime.setEvent_id(RandomUtil.randomInt(101, 199));
        realtime.setOpen_id(RandomUtil.randomString(32));
        realtime.setAdd_time(timestamp);
        realtime.setPay_time(timestamp + 10);
        realtime.setEvent_time_server(timestamp + 15);
        realtime.setEvent_time_client(timestamp + 2);
        realtime.setOs(RandomUtil.randomInt(0, 1));
        realtime.setReal_currency_amount(realCurrencyAmount);
        realtime.setOrder_id(RandomUtil.randomString(23));
        realtime.setCp_order_id(RandomUtil.randomString(36));
        realtime.setPlatform_order_id(RandomUtil.randomString(18));
        realtime.setGoods_name(realCurrencyAmount + " 订单");
        realtime.setReal_currency_type("人民币");
        realtime.setPayment_type("微信支付");
        realtime.setOs_version(RandomUtil.randomDouble(3, 4, RoundingMode.CEILING) + "");
        realtime.setDevice_id(RandomUtil.randomString(16));
        realtime.setRegister_device_id(RandomUtil.randomString(16));
        realtime.setRegister_channel_id(RandomUtil.randomInt(0, 100) + "");
        realtime.setRole_id(RandomUtil.randomInt(0, 100) + "");

        int registerTime = timestamp - RandomUtil.randomInt(10000000, 100000000);
        realtime.setRegister_time(registerTime);
        realtime.setRegister_date(DateUtil.date(registerTime * 1000L));

        int roleCreateTime = (timestamp + RandomUtil.randomInt(10000, 100000));
        realtime.setRole_create_time(roleCreateTime);
        realtime.setRole_create_date(DateUtil.date(roleCreateTime * 1000L));

        int appRegisterTime = (timestamp - RandomUtil.randomInt(10000, 100000));
        realtime.setApp_register_time(appRegisterTime);
        realtime.setApp_register_date(DateUtil.date(appRegisterTime * 1000L));

        int deviceAppRegisterTime = (timestamp - RandomUtil.randomInt(10000, 100000));
        realtime.setDevice_app_register_time(deviceAppRegisterTime);
        realtime.setDevice_app_register_date(DateUtil.date(deviceAppRegisterTime * 1000L));

        int registerDeviceAppRegisterTime = (timestamp - RandomUtil.randomInt(10000, 100000));
        realtime.setRegister_device_app_register_time(registerDeviceAppRegisterTime);
        realtime.setRegister_device_app_register_date(DateUtil.date(registerDeviceAppRegisterTime * 1000L));

        DateTime date = DateUtil.date(timestamp * 1000L + 15);
        realtime.setDt_rs(DateUtil.parseDate(date.toString(DatePattern.NORM_DATE_PATTERN)));
        realtime.setDt_time_rs(date.toString("HH"));

        return realtime;
    }
}
