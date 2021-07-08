package com.yi.clickhouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.clickhouse.model.EventLogRealtime;
import org.springframework.stereotype.Repository;

/**
 * @author huangwenyi
 */
@Repository
public interface ClickHouseMapper extends BaseMapper<EventLogRealtime> {
}
