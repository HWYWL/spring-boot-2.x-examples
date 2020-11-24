package com.yi.auto.code.service.impl;



import com.yi.auto.code.dao.UserDao;
import com.yi.auto.code.bean.User;
import com.yi.auto.code.bean.Clazz;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yi.auto.code.dao.ClazzDao;
import com.yi.auto.code.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 *班级 serverImpl
 */
@Service
@Transactional
public class ClazzServiceImpl   implements ClazzService {





	/**
	 * 班级
	 */
	@Resource
	private UserDao userDao;



	/**
	 * 注入dao
	 */
	@Resource
	private ClazzDao clazzDao;
	/**
	 * 初始化
	 */
	@Override
	public ClazzDao initDao(){
		return clazzDao;
	}


	/**
	 * 级联查询(带分页) 用户--班级
	 */
	@Override
	public Clazz selectUserAndClazz(Clazz clazz){
		clazz = this.selectAllByPaging(clazz);
		if(clazz!=null && clazz.getRows()!=null){
			clazz.getRows().forEach(t->{
				Clazz data= (Clazz) t;
				User user=new User();
				user.setId(data.getUserId());
				data.setUser(userDao.selectByPrimaryKey(user));
			});
		}
		return clazz;

	}


	/**
	 * 级联条件查询用户--班级
	 */
	@Override
	public List<Clazz> selectUserAndClazzByCondition(Clazz clazz){
		List<Clazz> datas = this.selectByCondition(clazz);
		if(datas!=null){
			datas.forEach(t->{
				User user=new User();
				user.setId(t.getUserId());
				t.setUser(userDao.selectByPrimaryKey(user));
			});
		}
		return datas;

	}


	/**
	 * 级联删除(根据主表删除) 用户--班级
	 */
	@Override
	public Integer deleteUserAndClazz(Clazz clazz){
		clazz = clazzDao.selectByPrimaryKey(clazz);
		if(clazz!=null){
			User user=new User();
			user.setId(clazz.getUserId());
			userDao.deleteByPrimaryKey(user);
		}
		return clazzDao.deleteByPrimaryKey(clazz);

	}





}
