package com.yi.auto.code.controller;



import com.yi.auto.code.service.UserService;
import com.yi.auto.code.bean.User;
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
import com.yi.auto.code.bean.Clazz;
import com.yi.auto.code.service.ClazzService;


/**
 *班级 controller
 */
@Controller
public class ClazzController  {





	/**
	 * 用户
	 */
	@Resource
	private UserService userService;



	@Resource
	private ClazzService clazzService;
	/**
	 * 删除-班级
	 */
	@ResponseBody
	@RequestMapping("clazz/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(Clazz clazz,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(clazzService.deleteByPrimaryKey(clazz));
	}


	/**
	 *  保存 (主键为空则增加 否则 修改)-> 班级
	 */
	@ResponseBody
	@RequestMapping("clazz/save")
	public DataRes save(Clazz clazz,HttpServletRequest request,HttpServletResponse response){
		if(clazz.getId()==null){
			return DataRes.success(clazzService.insert(clazz));
		}
		return DataRes.success(clazzService.update(clazz));

	}


	/**
	 * 根据主键查询->班级
	 */
	@ResponseBody
	@RequestMapping("clazz/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(Clazz clazz,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(clazzService.selectByPrimaryKey(clazz));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->班级
	 */
	@ResponseBody
	@RequestMapping("clazz/selectByCondition")
	public DataRes selectByCondition(Clazz clazz,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(clazzService.selectByCondition(clazz));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字段) (详见Page���.所以的实体都继承该类 默认 page=1 pageSize=10)->班级
	 */
	@ResponseBody
	@RequestMapping("clazz/selectAllByPaging")
	public DataRes selectAllByPaging(Clazz clazz,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(clazzService.selectAllByPaging(clazz));
	}


	/**
	 * 导出报表->班级
	 */
	@RequestMapping("clazz/export")
	public void export(Clazz clazz,HttpServletRequest request,HttpServletResponse response){
		List<Clazz> list= clazzService.selectAll(clazz);
		Map<String, String> header = new LinkedHashMap<>();
		header.put("id", "班级id");
		header.put("userId", "用户id");
		header.put("className", "班级名称");
		header.put("quantity", "班级人数");
		header.put("createTime_", "create_time");
		ExcelUtils.exportExcel("班级",header,list,response,request);

	}


	/**
	 * 级联查询(带分页) 用户--班级
	 */
	@RequestMapping("clazz/selectUserAndClazz")
	@ResponseBody
	public DataRes selectUserAndClazz(Clazz clazz,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(clazzService.selectUserAndClazz(clazz));
	}


	/**
	 * 级联条件查询 用户--班级
	 */
	@RequestMapping("clazz/selectUserAndClazzByCondition")
	@ResponseBody
	public DataRes selectUserAndClazzByCondition(Clazz clazz,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(clazzService.selectUserAndClazzByCondition(clazz));
	}


	/**
	 * 级联删除(根据主键删除) 用户--班级
	 */
	@RequestMapping("clazz/deleteUserAndClazz")
	@ResponseBody
	public DataRes deleteUserAndClazz(Clazz clazz,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(clazzService.deleteUserAndClazz(clazz));
	}





}
