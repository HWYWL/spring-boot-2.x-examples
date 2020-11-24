package com.yi.auto.code.dao;



import com.zengtengpeng.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import com.yi.auto.code.bean.Clazz;


/**
 *班级 dao
 */
@Mapper
public interface ClazzDao  extends BaseDao<Clazz>{








	/**
	 * 根据用户删除班级
	 */
	public Integer deleteClazzByUser(Clazz clazz);



}
