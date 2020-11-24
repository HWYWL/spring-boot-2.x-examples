package com.yi.auto.code.service;



import java.util.List;
import com.zengtengpeng.common.service.BaseService;
import com.yi.auto.code.bean.Clazz;
import com.yi.auto.code.dao.ClazzDao;


/**
 *班级 service
 */
public interface ClazzService extends BaseService<Clazz,ClazzDao>{








	/**
	 * 级联查询(带分页) 用户--班级
	 */
	public Clazz selectUserAndClazz(Clazz clazz);
	/**
	 * 级联条件查询 用户--班级
	 */
	public List<Clazz> selectUserAndClazzByCondition(Clazz clazz);
	/**
	 * 级联删除(根据主键删除) 用户--班级
	 */
	public Integer deleteUserAndClazz(Clazz clazz);



}
