package org.jumao.bi.entites.baidu;

import java.io.Serializable;
import java.util.Map;

import org.jumao.bi.entites.CommonResponse;

public class VistSourcesResponse extends CommonResponse implements Serializable {

	private static final long serialVersionUID = 1089202485897772664L;
	private LineChart lineChart;
	private PieChart pieChart;

	public LineChart getLineChart() {
		return lineChart;
	}

	public void setLineChart(LineChart lineChart) {
		this.lineChart = lineChart;
	}

	public PieChart getPieChart() {
		return pieChart;
	}

	public void setPieChart(PieChart pieChart) {
		this.pieChart = pieChart;
	}

}
