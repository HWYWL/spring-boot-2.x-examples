package com.yi.auto.code.service.impl;



import com.yi.auto.code.dao.ClazzDao;
import com.yi.auto.code.bean.User;
import com.yi.auto.code.bean.Clazz;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yi.auto.code.dao.UserDao;
import com.yi.auto.code.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 *测试用户 serverImpl
 */
@Service
@Transactional
public class UserServiceImpl   implements UserService {





	/**
	 * 班级
	 */
	@Resource
	private ClazzDao clazzDao;



	/**
	 * 注入dao
	 */
	@Resource
	private UserDao userDao;
	/**
	 * 初始化
	 */
	@Override
	public UserDao initDao(){
		return userDao;
	}


	/**
	 * 级联查询(带分页) 用户--班级
	 */
	@Override
	public User selectUserAndClazz(User user){
		user = this.selectAllByPaging(user);
		if(user!=null && user.getRows()!=null){
			user.getRows().forEach(t->{
				User data= (User) t;
				Clazz clazz=new Clazz();
				clazz.setUserId(data.getId());
				List<Clazz> lists = clazzDao.selectByCondition(clazz);
				if(lists!=null && lists.size()>0){
					data.setClazz(lists.get(0));
				}
			});
		}
		return user;

	}


	/**
	 * 级联条件查询 用户--班级
	 */
	@Override
	public List<User> selectUserAndClazzByCondition(User user){
		List<User> datas = this.selectByCondition(user);
		if(datas!=null){
			datas.forEach(t->{
				Clazz clazz=new Clazz();
				clazz.setUserId(t.getId());
				List<Clazz> lists = clazzDao.selectByCondition(clazz);
				if(lists!=null && lists.size()>0){
					t.setClazz(lists.get(0));
				}
			});
		}
		return datas;

	}


	/**
	 * 级联删除(根据主表删除) 用户--班级
	 */
	@Override
	public Integer deleteUserAndClazz(User user){
		Clazz clazz=new Clazz();
		clazz.setUserId(user.getId());
		clazzDao.deleteClazzByUser(clazz);
		return userDao.deleteByPrimaryKey(user);

	}





}
