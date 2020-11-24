package com.yi.async.mapper;

import com.yi.async.model.Baike;
import com.yi.async.model.BaikeExample;
import org.springframework.stereotype.Repository;

/**
 * BaikeDAO继承基类
 */
@Repository
public interface BaikeDAO extends MyBatisBaseDao<Baike, Long, BaikeExample> {
}