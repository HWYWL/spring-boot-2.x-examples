package com.yi.echarts.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.yi.echarts.model.BaiduSeries;
import com.yi.echarts.model.ECharData;

/**
 * 数据组装
 * @author YI
 * @date 2018-11-12 16:43:47
 */
public class BaiduEChartUtil {

	List<BaiduSeries> series;

	private List<String> xaxis;

	private String title = "", subTitle = "";

	private ECharData charData;

	public BaiduEChartUtil() {
		this.series = new ArrayList<>();

		charData = new ECharData();
	}

	public ECharData getData() {
		charData.setTitle(title);
		charData.setSeries(series);
		charData.setXaxis(xaxis);
		charData.setSubTitle(subTitle);

		return charData;
	}

	public void addLine(String name, List<?> data) {
		addData(name, "line", data);
	}

	public void addBar(String name, List<?> data) {
		addData(name, "bar", data);
	}

	/**
	 * 
	 * @param name
	 * @param chartType
	 *            bar，line，K（此时data是一个是个浮点数的数组）
	 * @param data
	 */
	private void addData(String name, String chartType, List<?> data) {
		BaiduSeries s = new BaiduSeries();

		s.setName(name);
		s.setType(chartType);
		s.setData(data);

		this.series.add(s);
	}

	public void setXData(List<String> xAxis) {
		this.xaxis = xAxis;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public void addPie(String name, LinkedHashMap<String, Float> data) {
		charData.setName(name);
		charData.setData(data);
	}

}
