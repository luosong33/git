package org.jumao.bi.entites.baidu;

import java.io.Serializable;
import java.util.List;

public class LineChart implements Serializable {

	private static final long serialVersionUID = -5244773794123014069L;
	private String[] legendData;
	private String[] xAxisData;
	private List<int[]> seriesData;

	public String[] getLegendData() {
		return legendData;
	}

	public void setLegendData(String[] legendData) {
		this.legendData = legendData;
	}

	public String[] getxAxisData() {
		return xAxisData;
	}

	public void setxAxisData(String[] xAxisData) {
		this.xAxisData = xAxisData;
	}

	public List<int[]> getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(List<int[]> seriesData) {
		this.seriesData = seriesData;
	}



}
