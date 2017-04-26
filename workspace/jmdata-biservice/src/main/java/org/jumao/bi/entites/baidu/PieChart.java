package org.jumao.bi.entites.baidu;

import java.io.Serializable;
import java.util.List;

public class PieChart implements Serializable {

	private static final long serialVersionUID = 4445236181854347736L;
	private String[] legendData;
	private List<CommonBean> seriesData;

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
