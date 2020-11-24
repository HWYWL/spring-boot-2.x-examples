package com.yi.auto.code.service.impl;
import com.yi.auto.code.dao.BaikeDao;
import com.yi.auto.code.service.BaikeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 *百科表 serverImpl
 */
@Service
@Transactional
public class BaikeServiceImpl   implements BaikeService {


	/**
	 * 注入dao
	 */
	@Resource
	private BaikeDao baikeDao;
	/**
	 * 初始化
	 */
	@Override
	public BaikeDao initDao(){
		return baikeDao;
	}


}
