package com.yi.echarts.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class ECharData {

	private List<String> legend;

	/**
	 * 横坐标
	 */
	private List<String> xaxis;

	/**
	 * 显示数据
	 */
	private List<BaiduSeries> series;

	private List<BaiduEntry> entry;

	private String title, subTitle, name;

	public List<BaiduSeries> getSeries() {
		return series;
	}

	public void setSeries(List<BaiduSeries> series) {
		this.series = series;

		if (!series.isEmpty()) {
			this.legend = extractLegend();
		}
	}

	public ECharData() {
		super();
	}

	/**
	 * 
	 * @return list
	 */
	private List<String> extractLegend() {
		List<String> list = new ArrayList<>();

		for (BaiduSeries s : series) {
			list.add(s.getName());
		}

		return list;
	}

	public List<String> getLegend() {
		return legend;
	}

	public void setLegend(List<String> legend) {
		this.legend = legend;
	}

	public List<String> getXaxis() {
		return xaxis;
	}

	public void setXaxis(List<String> xaxis) {
		this.xaxis = xaxis;
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

	public void setData(LinkedHashMap<String, Float> data) {
		setEntry(map2entry(data));
		this.legend = new ArrayList<>(data.keySet());
	}

	private List<BaiduEntry> map2entry(LinkedHashMap<String, Float> data) {
		List<BaiduEntry> list = new ArrayList<>();

		Iterator<Entry<String, Float>> iterator = data.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<String, Float> entry = iterator.next();
			BaiduEntry e = new BaiduEntry();

			e.name = entry.getKey();
			e.value = entry.getValue();

			list.add(e);

		}

		return list;
	}

	public List<BaiduEntry> getEntry() {
		return entry;
	}

	public void setEntry(List<BaiduEntry> entry) {
		this.entry = entry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
