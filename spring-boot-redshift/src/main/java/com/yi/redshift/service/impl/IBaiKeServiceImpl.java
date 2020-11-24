package com.yi.redshift.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yi.redshift.mapper.BaikeMapper;
import com.yi.redshift.model.Baike;
import com.yi.redshift.service.IBaiKeService;
import org.springframework.stereotype.Service;

/**
 * 自定义实现类
 * @author YI
 * @date 2018-11-27 14:40:31
 */
@Service
public class IBaiKeServiceImpl extends ServiceImpl<BaikeMapper, Baike> implements IBaiKeService {

}
