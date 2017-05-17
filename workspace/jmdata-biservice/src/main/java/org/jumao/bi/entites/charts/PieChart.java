package org.jumao.bi.entites.charts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 可表示饼图、中国地图
 */
public class PieChart implements Serializable {

	private static final long serialVersionUID = 4445236181854347736L;
	private String[] legendData = new String[0];
	private List<CommonBean> seriesData = new ArrayList<CommonBean>();

	public String[] getLegendData() {
		return legendData;
	}

	public void setLegendData(String[] legendData) {
		this.legendData = legendData;
	}

	public List<CommonBean> getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(List<CommonBean> seriesData) {
		this.seriesData = seriesData;
	}

}
