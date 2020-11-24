package com.yi.auto.code.controller;



import com.yi.auto.code.service.ClazzService;
import com.yi.auto.code.bean.Clazz;
import java.util.ArrayList;
import javax.annotation.Resource;
import com.zengtengpeng.common.utils.ExcelUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zengtengpeng.common.bean.DataRes;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import com.yi.auto.code.bean.User;
import com.yi.auto.code.service.UserService;


/**
 *测试用户 controller
 */
@Controller
public class UserController  {





	/**
	 * 班级
	 */
	@Resource
	private ClazzService clazzService;



	@Resource
	private UserService userService;
	/**
	 * 删除-测试用户
	 */
	@ResponseBody
	@RequestMapping("user/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.deleteByPrimaryKey(user));
	}


	/**
	 *  保存 (主键为空则增加 否��� 修改)-> 测试用户
	 */
	@ResponseBody
	@RequestMapping("user/save")
	public DataRes save(User user,HttpServletRequest request,HttpServletResponse response){
		if(user.getId()==null){
			return DataRes.success(userService.insert(user));
		}
		return DataRes.success(userService.update(user));

	}


	/**
	 * 根据主键查询->测试用户
	 */
	@ResponseBody
	@RequestMapping("user/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.selectByPrimaryKey(user));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->测试用户
	 */
	@ResponseBody
	@RequestMapping("user/selectByCondition")
	public DataRes selectByCondition(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.selectByCondition(user));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->测试用户
	 */
	@ResponseBody
	@RequestMapping("user/selectAllByPaging")
	public DataRes selectAllByPaging(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.selectAllByPaging(user));
	}


	/**
	 * 导出报表->测试用户
	 */
	@RequestMapping("user/export")
	public void export(User user,HttpServletRequest request,HttpServletResponse response){
		List<User> list= userService.selectAll(user);
		Map<String, String> header = new LinkedHashMap<>();
		header.put("id", "id");
		header.put("name", "名称");
		header.put("age", "年龄");
		header.put("status_", "{\"name\":\"状态\",\"1\":\"启用\",\"0\":\"禁用\"}");
		header.put("birthday_", "生日");
		header.put("remarks", "备注");
		header.put("mun", "数字");
		header.put("createTime_", "创建时间");
		header.put("updateTime_", "更新时间");
		ExcelUtils.exportExcel("测试用户",header,list,response,request);

	}


	/**
	 * 级联查询(带分页) 用户--班级
	 */
	@RequestMapping("user/selectUserAndClazz")
	@ResponseBody
	public DataRes selectUserAndClazz(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.selectUserAndClazz(user));
	}


	/**
	 * 级联条件查询 用户--班级
	 */
	@RequestMapping("user/selectUserAndClazzByCondition")
	@ResponseBody
	public DataRes selectUserAndClazzByCondition(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.selectUserAndClazzByCondition(user));
	}


	/**
	 * 级联删除(根据主键删除) 用户--班级
	 */
	@RequestMapping("user/deleteUserAndClazz")
	@ResponseBody
	public DataRes deleteUserAndClazz(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.deleteUserAndClazz(user));
	}





}
