package com.yi.echarts.controller;

import com.yi.echarts.model.ECharData;
import com.yi.echarts.util.BaiduEChartUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

/**
 * 构建图形
 * @author YI
 * @date 2018-11-12 16:41:48
 */
@RestController
@RequestMapping(value = "/echart")
public class EChartController {

	private static final int DATA_COUNT = 12;

	@RequestMapping("/line")
	public ECharData line() {

		BaiduEChartUtil util = new BaiduEChartUtil();

		util.setTitle("简单的线图");
		util.setSubTitle("子标题");

		// X轴
		util.setXData(getXAxis(DATA_COUNT));

		// 数据
		util.addLine("线段1", createData(DATA_COUNT));
		util.addLine("线段2", createData(DATA_COUNT));

		return util.getData();
	}

	@RequestMapping("/bar")
	public ECharData bar() {

		BaiduEChartUtil util = new BaiduEChartUtil();

		util.setTitle("简单的柱图");
		util.setSubTitle("子标题");

		// X轴
		util.setXData(getXAxis(DATA_COUNT));

		// 数据
		util.addBar("柱1", createData(DATA_COUNT));
		util.addBar("柱2", createData(DATA_COUNT));

		return util.getData();
	}

	@RequestMapping("/lineAndBar")
	public ECharData lineAndBar() {

		BaiduEChartUtil util = new BaiduEChartUtil();

		util.setTitle("简单的线图和柱图");
		util.setSubTitle("子标题");

		// X轴
		util.setXData(getXAxis(DATA_COUNT));

		// 数据
		util.addLine("线段1", createData(DATA_COUNT));
		util.addBar("柱1", createData(DATA_COUNT));

		return util.getData();
	}

	@RequestMapping("/pie")
	public ECharData pie() {

		BaiduEChartUtil util = new BaiduEChartUtil();

		util.setTitle("饼图例子");

		// 数据
		util.addPie("提示文字", createMapData(5));

		return util.getData();
	}

	/**
	 * 产生数据
	 * @param count
	 * @return
	 */
	private List<Integer> createData(int count) {
		List<Integer> data = new ArrayList<>();

		for (int j = 0; j < count; j++) {
			data.add(new Random().nextInt());
		}
		return data;
	}

	/**
	 * x坐标
	 * @param count
	 * @return
	 */
	private List<String> getXAxis(int count) {
		List<String> list = new ArrayList<>();
		for (int j = 1; j <= count; j++) {
			list.add(j + "月");
		}

		return list;
	}

	/**
	 * 构建饼图的数据
	 * @param count
	 * @return
	 */
	private LinkedHashMap<String, Float> createMapData(int count) {
		LinkedHashMap<String, Float> result = new LinkedHashMap<>(count);
		for (int j = 1; j <= count; j++) {
			result.put(j + "月", new Random().nextFloat());
		}

		return result;
	}
}
