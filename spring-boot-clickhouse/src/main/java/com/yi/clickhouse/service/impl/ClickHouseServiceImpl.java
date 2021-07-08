package com.yi.clickhouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yi.clickhouse.mapper.ClickHouseMapper;
import com.yi.clickhouse.model.EventLogRealtime;
import com.yi.clickhouse.service.ClickHouseService;
import org.springframework.stereotype.Service;

/**
 * @author YI
 * @description
 * @date create in 2021/7/5 14:48
 */
@Service
public class ClickHouseServiceImpl
        extends ServiceImpl<ClickHouseMapper, EventLogRealtime> implements ClickHouseService {
}
