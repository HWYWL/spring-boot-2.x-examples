package com.yi.auto.code.controller;
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
import com.yi.auto.code.bean.Baike;
import com.yi.auto.code.service.BaikeService;


/**
 *百科表 controller
 */
@Controller
public class BaikeController  {


	@Resource
	private BaikeService baikeService;
	/**
	 * 删除-百科表
	 */
	@ResponseBody
	@RequestMapping("baike/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(Baike baike,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(baikeService.deleteByPrimaryKey(baike));
	}


	/**
	 *  保存 (主键为空则增加 否则 修改)-> 百科表
	 */
	@ResponseBody
	@RequestMapping("baike/save")
	public DataRes save(Baike baike,HttpServletRequest request,HttpServletResponse response){
		if(baike.getId()==null){
			return DataRes.success(baikeService.insert(baike));
		}
		return DataRes.success(baikeService.update(baike));

	}


	/**
	 * 根据主键查询->百科表
	 */
	@ResponseBody
	@RequestMapping("baike/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(Baike baike,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(baikeService.selectByPrimaryKey(baike));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->百科表
	 */
	@ResponseBody
	@RequestMapping("baike/selectByCondition")
	public DataRes selectByCondition(Baike baike,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(baikeService.selectByCondition(baike));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->百科表
	 */
	@ResponseBody
	@RequestMapping("baike/selectAllByPaging")
	public DataRes selectAllByPaging(Baike baike,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(baikeService.selectAllByPaging(baike));
	}


	/**
	 * 导出报表->百科表
	 */
	@RequestMapping("baike/export")
	public void export(Baike baike,HttpServletRequest request,HttpServletResponse response){
		List<Baike> list= baikeService.selectAll(baike);
		Map<String, String> header = new LinkedHashMap<>();
		header.put("id", "ID");
		header.put("book", "书");
		header.put("tag", "标签");
		header.put("good", "点赞");
		header.put("bad", "鄙视");
		header.put("name", "作者名称");
		header.put("gender", "作者性别");
		header.put("goldCoin", "获得的金币打赏");
		header.put("status", "0：上架、-1：下架");
		header.put("crateDate_", "创建时间");
		header.put("updateDate_", "更新时间");
		ExcelUtils.exportExcel("百科表",header,list,response,request);

	}


}
