package com.yi.auto.code.service;



import java.util.List;
import com.zengtengpeng.common.service.BaseService;
import com.yi.auto.code.bean.User;
import com.yi.auto.code.dao.UserDao;


/**
 *测试用户 service
 */
public interface UserService extends BaseService<User,UserDao>{








	/**
	 * 级联查询(带分页) 用户--班级
	 */
	public User selectUserAndClazz(User user);
	/**
	 * 级联查询(带分页) 用户--班级
	 */
	public List<User> selectUserAndClazzByCondition(User user);
	/**
	 * 级联删除(根据主键删除) 用户--班级
	 */
	public Integer deleteUserAndClazz(User user);



}
